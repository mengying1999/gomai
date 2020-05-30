package com.gomai.order.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gomai.goods.pojo.Goods;
import com.gomai.goods.pojo.Unshelve;
import com.gomai.intergral.pojo.IntegralExchange;
import com.gomai.order.config.AlipayConfig;
import com.gomai.order.delay.DelayService;
import com.gomai.order.delay.DshOrder;
import com.gomai.order.pojo.Order;
import com.gomai.order.pojo.OrderReturn;
import com.gomai.order.pojo.OrderReturnMedia;
import com.gomai.order.service.*;
import com.gomai.order.utils.PageUtils;
import com.gomai.order.vo.*;
import com.gomai.user.pojo.User;
import com.gomai.user.pojo.UserAddress;
import com.gomai.utils.PageResult;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OGoodsService oGoodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OAddressService oAddressService;

    @Autowired
    private OUserService oUserService;

    @Autowired
    private OUnshelveService oUnshelveService;

    @Autowired
    private DelayService delayService;
    @Autowired
    private OrderReturnService orderReturnService;

    @Autowired
    private OrderReturnMediaService orderReturnMediaService;
    @Autowired
    private OExchangeService oExchangeService;

    //----------------------------------------------------------枝功能开始------------------------------------------------------------------------
    /**
     *生成订单
     * 1. 判断order是否为空
     * 2. 判断gId,uId,uaId是否为空
     * 3. 根据gId查询商品，判断查询的商品是否为null
     * 4. 判断商品的uId与买家uId是否相同
     * 5. 根据gId查询是否存在订单且订单信息为待付款，提醒用户该商品已经被拍下还未付款，您还有机会
     * 6. 判断该商品是否下架
     * 7. 根据uaId查询地址信息，判断是否为空
     * 8. 判断地址的uId与买家的uId是否相同
     * 9. 根据uId查询用户信息，判断是否为null
     * 10. 数据操作（应该满足原子性）：
     *          插入订单数据包括：gId,uId,uaId,oStatus,oCreateTime,oSellDelete,oBuyDelete
     *          修改商品信息：将下架状态改成已下架
     *          插入下架商品信息：包括gId,unReason,unCreateTime
     * 11.创建定时器：创建一个1小时之内没有付款就自动取消订单的定时器
     * 12.返回成功与否
     * @param order
     * @return
     */
    @Transactional
    @PostMapping("/generateOrder")
    public ReturnMessage<Object> generateOrder(@RequestBody  Order order){
//        1. 判断order是否为空  2. 判断gId,uId,uaId是否为空
        System.out.println(order);
        if(StringUtils.isEmpty(order) && (order.getgId() == null || order.getgId() <=0) &&(order.getuId() == null || order.getuId() <=0)&&(order.getUaId() == null || order.getUaId() <=0)){
            throw new SbException(400, "输入不合法");
        }
//      3.根据gId查询商品，看看是否为null
        Goods goods= this.oGoodsService.queryGoodsByGId(order.getgId());
        if (StringUtils.isEmpty(goods)) {
            throw new SbException(100, "该商品不存在!");
        }
//      4. 判断商品的uId与买家uId是否相同
        if(goods.getuId().equals(order.getuId())){
            throw new SbException(100, "不能购买自己发布的商品!");
        }
//      5. 根据gId查询是否存在订单且订单信息为待付款，提醒用户该商品已经被拍下还未付款，您还有机会
        List<Order> orders = this.orderService.queryPayOrderByGId(order.getgId());
        if (CollectionUtils.isEmpty(orders)) {  //没有该商品的待付款订单
//       6.根据gId查询是否该商品已经下架，提醒该商品已经下架
            if (goods.getgStatus() == 1){  // 如果已经下架
                throw new SbException(100, "该商品已下架!");
            }
        }else {
            throw new SbException(100, "该商品已被拍下，您还有机会!");
        }
//     7. 根据uaId查询地址信息，判断是否为空
        UserAddress userAddress = this.oAddressService.queryUserAddressByUaId(order.getUaId());
        if (StringUtils.isEmpty(userAddress)){
            throw new SbException(100, "地址信息错误!");
        }
//     8. 判断地址的uId与买家的uId是否相同
        if(!userAddress.getuId().equals(order.getuId()) ) {
            throw new SbException(100, "地址信息错误!");
        }
//     9. 根据uId查询用户信息，判断是否为null
        User user = this.oUserService.queryUserByUId(order.getuId());
        if (StringUtils.isEmpty(user)){
            throw new SbException(100, "用户信息错误!");
        }
//     10. 数据操作（应该满足原子性）：
//        (1) 插入订单数据包括：gId,uId,uaId,oStatus,oCreateTime,oSellDelete,oBuyDelete
        order.setoStatus(1);//刚创建订单时为待付款
        Date oCreateTime = new Date();
        order.setoCreateTime(oCreateTime);
        order.setoSellDelete(1);
        order.setoBuyDelete(1);
        int flag = this.orderService.addOrder(order);
        if (flag == 0){
            throw new SbException(100, "添加失败!");
        }
//        (2) 修改商品信息：将下架状态改成已下架,插入下架信息
        goods.setgStatus(1);
        flag = this.oGoodsService.updateGoods(goods);
        if (flag == 0){
                throw new SbException(100, "添加失败!");
        }
//        (3) 插入下架商品信息：包括gId,unReason,unCreateTime
        Unshelve unshelve = new Unshelve();
        unshelve.setgId(order.getgId());
        unshelve.setUnCreateTime(oCreateTime);
        System.out.println(oCreateTime);
        unshelve.setUnReason("商品已卖出");
        flag = this.oUnshelveService.addUnshelve(unshelve);
        if (flag == 0){
            throw new SbException(100, "添加失败!");
        }
//      11.创建定时器：创建一个1小时之内没有付款就自动取消订单的定时器
        if (order.getoId() == 0){
            throw new SbException(100, "添加失败!");
        }
        DshOrder dshOrder = new DshOrder(""+order.getoId(),30 * 60 * 1000,1);
        delayService.add(dshOrder);
        return ReturnMessageUtil.sucess(order);
    }

    /**
     *跳转去支付
     * 设置阿里支付的参数
     * 返回阿里页面
     * @param oId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goAlipay/{oId}")
    @ResponseBody
    public ReturnMessage<Object> goAlipay(@PathVariable("oId") Integer oId) throws Exception {
        Order order = orderService.queryOrderByOId(oId);
        Goods goods = oGoodsService.queryGoodsByGId(order.getgId());
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = "" + oId;
        //付款金额，必填
        String total_amount = ""+goods.getgPrice();
        //订单名称，必填
        String subject = goods.getgName();
        //商品描述，可空
        String body = "";

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1h";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println(result);
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",result);
        System.out.println(message);
        return message;
    }

    /**
     * 发货请求
     * 1. 判断oId,uId,oStatus是否合法
     * 2. 判断oStatus是否为2即未发货
     * 3. 根据三个条件查询订单是否存在，根据卖家id查询是否存在oid和oStatus的订单
     * 4. 更新订单状态
     * 5. 创建定时器（即15天未确认收货则自动确认收货）
     * @param uId  卖家uId
     * @param oId  订单Id
     * @param oStatus 订单状态
     * @return
     */
    @Transactional
    @PostMapping("/shipments/{uId}/{oId}/{oStatus}")
    public ReturnMessage<Object> shipments(@PathVariable("uId") Integer uId,@PathVariable("oId") Integer oId,@PathVariable("oStatus") Integer oStatus){
        if (StringUtils.isEmpty(uId) || uId == 0 ||StringUtils.isEmpty(oId) || oId == 0||StringUtils.isEmpty(oStatus) || oStatus == 0 ){
            throw new SbException(100, "参数错误!");
        }
        if(oStatus != 2){
            throw new SbException(100, "不是待发货订单!");
        }
        List<Order> orders = orderService.queryOrderBySaleUId(uId, oId, oStatus);
        for (Order order:orders) {
            System.out.println(order);
        }
        if (CollectionUtils.isEmpty(orders)){
            throw new SbException(100, "无该订单!");
        }
        if( orders.size() <= 0){
            throw new SbException(100, "无该订单!");
        }
        Order order = orders.get(0);

        if (StringUtils.isEmpty(order)){
            throw new SbException(100, "无该订单!");
        }
        order.setoShipmentsTime(new Date());
        order.setoStatus(3);
        int flag = orderService.updateOrder(order);
        if (flag == 0){
            throw new SbException(100, "更新失败!");
        }
        DshOrder dshOrder = new DshOrder(""+order.getoId(),15 * 24 * 60 * 60 * 1000,3);
        delayService.add(dshOrder);
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",true);
        System.out.println(message);
        return message;
    }


    /**
     * 确认收货函数
     * 1. 判断oId,uId,oStatus是否合法
     * 2. 判断oStatus是否为3即未收货
     * 3. 根据三个条件查询订单是否存在，根据买家id查询是否存在oid和oStatus的订单
     * 4. 更新订单状态
     * 5. 生成积分明细
     * 6. 创建定时器（即15天未评价则将订单改为已评价，评价为默认评价）
     */
    @Transactional
    @PostMapping("/received")
    public ReturnMessage<Object> shipments(@RequestBody Order order){
        System.out.println(order);
        if (StringUtils.isEmpty(order)){
            throw new SbException(100, "参数错误!");
        }
        if (StringUtils.isEmpty(order.getuId()) || order.getuId() == 0 ||StringUtils.isEmpty(order.getoId()) || order.getoId() == 0||StringUtils.isEmpty(order.getoStatus()) || order.getoStatus() == 0 ){
            throw new SbException(100, "参数错误!");
        }
        if (order.getoStatus() != 3){
            throw new SbException(100, "不是未收货订单!");
        }
        List<Order> orders =  orderService.queryOrderByOrder(order);
        if (CollectionUtils.isEmpty(orders)){
            throw new SbException(100, "无该订单!");
        }
        if( orders.size() <= 0){
            throw new SbException(100, "无该订单!");
        }
        Order order1 = orders.get(0);
        order1.setoStatus(4);
        order1.setoReceiveTime(new Date());
        int flag = orderService.updateOrder(order1);
        if (flag == 0){
            throw new SbException(100, "更新错误!");
        }
        //生成积分明细:插入积分明细和更改个人总积分
        flag = oExchangeService.addIntegralExchange(order1.getoId());
        if (flag == 0){
            throw new SbException(100, "更新错误!");
        }
        //创建定时器（即15天未评价则将订单改为已评价，评价为默认评价）
        DshOrder dshOrder = new DshOrder(""+order.getoId(),15 * 24 * 60 * 60 * 1000,4);
        delayService.add(dshOrder);
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",true);
        System.out.println(message);
        return message;
    }

    /**
     * 修改价格
     * 1. 判断oId,uId,oStatus，g_price是否合法
     * 2. 判断oStatus是否为1即未付款
     * 3. 根据三个条件查询订单是否存在，根据卖家id查询是否存在oid和oStatus的订单
     * 4. 根据gId查询对应商品
     * 5. 修改商品价格
     */
    @Transactional
    @PostMapping("/updatePrice/{uId}/{oId}/{oStatus}/{gPrice}")
    public ReturnMessage<Object> updatePrice(@PathVariable("uId") Integer uId,@PathVariable("oId") Integer oId,@PathVariable("oStatus") Integer oStatus,@PathVariable("gPrice") Double gPrice){
        if (StringUtils.isEmpty(uId) || uId == 0 ||StringUtils.isEmpty(oId) || oId == 0||StringUtils.isEmpty(oStatus) || oStatus == 0 ||StringUtils.isEmpty(gPrice)){
            throw new SbException(100, "参数错误!");
        }
        if(oStatus != 1){
            throw new SbException(100, "不是未付款订单!");
        }
        List<Order> orders = orderService.queryOrderBySaleUId(uId, oId, oStatus);
        for (Order order:orders) {
            System.out.println(order);
        }
        if (CollectionUtils.isEmpty(orders)){
            throw new SbException(100, "无该订单!");
        }
        if( orders.size() <= 0){
            throw new SbException(100, "无该订单!");
        }
        Order order = orders.get(0);
        if (StringUtils.isEmpty(order)){
            throw new SbException(100, "无该订单!");
        }
        Goods goods = oGoodsService.queryGoodsByGId(order.getgId());
        if (StringUtils.isEmpty(goods)){
            throw new SbException(100, "无该商品!");
        }
        goods.setgPrice(gPrice);
        int flag = oGoodsService.updateGoods(goods);
        if (flag == 0){
            throw new SbException(100, "修改失败!");
        }
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",true);
        System.out.println(message);
        return message;
    }

    /**
     * 提醒发货(买家的操作)
     * 1. 判断oId,uId,oStatus,是否合法
     * 2. 判断oStatus是否为2即待发货订单
     * 3. 根据三个条件查询订单是否存在，根据买家id
     * 4. 判断提醒发货是否为空，为空则直接修改提醒发货时间，给卖家发消息（先不实现）
     * 5. 如果不为空，判断两个时间是否为同一天，不是同一天就修改提醒发货时间，是就返回错误信息，今天已经提醒发货了
     */
    @Transactional
    @PostMapping("/remindShipments")
    public ReturnMessage<Object> remindShipments(@RequestBody Order order) {
        System.out.println(order);
        if (StringUtils.isEmpty(order)) {
            throw new SbException(100, "参数错误!");
        }
        if (StringUtils.isEmpty(order.getuId()) || order.getuId() == 0 || StringUtils.isEmpty(order.getoId()) || order.getoId() == 0 || StringUtils.isEmpty(order.getoStatus()) || order.getoStatus() == 0) {
            throw new SbException(100, "参数错误!");
        }
        if (order.getoStatus() != 2) {
            throw new SbException(100, "不是未发货订单!");
        }
        List<Order> orders = orderService.queryOrderByOrder(order);
        if (CollectionUtils.isEmpty(orders)) {
            throw new SbException(100, "无该订单!");
        }
        if (orders.size() <= 0) {
            throw new SbException(100, "无该订单!");
        }
        Order order1 = orders.get(0);
        if(StringUtils.isEmpty(order1.getoRemindShipments())){
            order1.setoRemindShipments(new Date());
        }else{
            boolean flag = isThisTime(order1.getoRemindShipments(),"yyyy-MM-dd");
            if (flag){ // 是今天
                throw new SbException(100, "今天已经提醒发货了!");
            }else {
                order1.setoRemindShipments(new Date());
            }
        }
        int flag = orderService.updateOrder(order1);
        if (flag == 0){
            throw new SbException(100, "提醒失败!");
        }
//        此处需给卖家发消息（迪）提醒发货
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",true);
        System.out.println(message);
        return message;
    }

    /**
     * 提醒收货(卖家的操作)
     * 1. 判断oId,uId,oStatus,是否合法
     * 2. 判断oStatus是否为3即待发收货订单
     * 3. 根据三个条件查询订单是否存在，根据卖家id
     * 4. 判断提醒收货时间是否为空，为空则直接修改提醒收货时间，给买家发消息（先不实现）
     * 5. 如果不为空，判断两个时间是否为同一天，不是同一天就修改提醒收货时间，是就返回错误信息，今天已经提醒收货了
     */
    @Transactional
    @PostMapping("/remindReceive/{uId}/{oId}/{oStatus}")
    public ReturnMessage<Object> remindReceive(@PathVariable("uId") Integer uId,@PathVariable("oId") Integer oId,@PathVariable("oStatus") Integer oStatus) {
        if (StringUtils.isEmpty(uId) || uId == 0 || StringUtils.isEmpty(oId) || oId == 0 || StringUtils.isEmpty(oStatus) || oStatus == 0) {
            throw new SbException(100, "参数错误!");
        }
        if (oStatus != 3) { //待发货订单
            throw new SbException(100, "不是待发货订单!");
        }
        List<Order> orders = orderService.queryOrderBySaleUId(uId, oId, oStatus);
        for (Order order : orders) {
            System.out.println(order);
        }
        if (CollectionUtils.isEmpty(orders)) {
            throw new SbException(100, "无该订单!");
        }
        if (orders.size() <= 0) {
            throw new SbException(100, "无该订单!");
        }
        Order order = orders.get(0);
        if (StringUtils.isEmpty(order)) {
            throw new SbException(100, "无该订单!");
        }
        if(StringUtils.isEmpty(order.getoRemindReceive())){
            order.setoRemindReceive(new Date());
        }else{
            boolean flag = isThisTime(order.getoRemindReceive(),"yyyy-MM-dd");
            if (flag){ // 是今天
                throw new SbException(100, "今天已经提醒收货了!");
            }else {
                order.setoRemindReceive(new Date());
            }
        }
        int flag = orderService.updateOrder(order);
        if (flag == 0){
            throw new SbException(100, "提醒失败!");
        }
//        此处需给卖家发消息（迪）提醒发货
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",true);
        System.out.println(message);
        return message;
    }

    /**
     *删除订单(买家)
     * 1. 判断oId,uId,oStatus,是否合法
     * 2. 判断oStatus是否为4即待评价订单5和9即交易成功，6即取消订单
     * 3. 根据三个条件查询订单是否存在，根据买家id
     * @return
     */
    @Transactional
    @PostMapping("/buyDelete")
    public ReturnMessage<Object> buyDelete(@RequestBody Order order) {
        System.out.println(order);
        if (StringUtils.isEmpty(order)) {
            throw new SbException(100, "参数错误!");
        }
        if (StringUtils.isEmpty(order.getuId()) || order.getuId() == 0 || StringUtils.isEmpty(order.getoId()) || order.getoId() == 0 || StringUtils.isEmpty(order.getoStatus()) || order.getoStatus() == 0) {
            throw new SbException(100, "参数错误!");
        }
        if (order.getoStatus() != 4 && order.getoStatus() != 6&& order.getoStatus() != 5&& order.getoStatus() != 9) {
            throw new SbException(100, "不能删除!");
        }
        List<Order> orders = orderService.queryOrderByOrder(order);
        if (CollectionUtils.isEmpty(orders)) {
            throw new SbException(100, "无该订单!");
        }
        if (orders.size() <= 0) {
            throw new SbException(100, "无该订单!");
        }
        Order order1 = orders.get(0);
        if (StringUtils.isEmpty(order1)) {
            throw new SbException(100, "无该订单!");
        }
        order1.setoBuyDelete(2);//删除
        int flag = orderService.updateOrder(order1);
        if (flag == 0){
            throw new SbException(100, "删除失败!");
        }
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",true);
        System.out.println(message);
        return message;
    }

    /**
     *删除订单(卖家)
     * @return
     */
    @Transactional
    @PostMapping("/sellDelete/{uId}/{oId}/{oStatus}")
    public ReturnMessage<Object> sellDelete(@PathVariable("uId") Integer uId,@PathVariable("oId") Integer oId,@PathVariable("oStatus") Integer oStatus) {
        if (StringUtils.isEmpty(uId) || uId == 0 || StringUtils.isEmpty(oId) || oId == 0 || StringUtils.isEmpty(oStatus) || oStatus == 0) {
            throw new SbException(100, "参数错误!");
        }
        if (oStatus != 4 && oStatus != 6 && oStatus != 5 && oStatus != 9) { //待评价订单
            throw new SbException(100, "不能删除!");
        }
        List<Order> orders = orderService.queryOrderBySaleUId(uId, oId, oStatus);
        for (Order order : orders) {
            System.out.println(order);
        }
        if (CollectionUtils.isEmpty(orders)) {
            throw new SbException(100, "无该订单!");
        }
        if (orders.size() <= 0) {
            throw new SbException(100, "无该订单!");
        }
        Order order = orders.get(0);
        if (StringUtils.isEmpty(order)) {
            throw new SbException(100, "无该订单!");
        }
        order.setoSellDelete(2);//删除
        int flag = orderService.updateOrder(order);
        if (flag == 0){
            throw new SbException(100, "删除失败!");
        }
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",true);
        System.out.println(message);
        return message;
    }

    private static boolean isThisTime(Date date,String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if(param.equals(now)){
            return true;
        }
        return false;
    }

    /**
     * 取消订单功能
     * 1.验证前端参数
     * 2.根据类型分别验证是否存在订单
     * 3.取消订单
     * 4.该退款的订单退款
     * 5.返回
     */
    @Transactional
    @PostMapping("/cancelOrder/{oId}/{uId}/{oCancelType}")
    public ReturnMessage<Object> cancelOrder(@PathVariable("oId") Integer oId,@PathVariable("uId") Integer uId,@PathVariable("oCancelType") Integer oCancelType)throws Exception{
        // 1.验证前端参数
        if (StringUtils.isEmpty(uId) || uId <= 0 ||StringUtils.isEmpty(oId) || oId <= 0||StringUtils.isEmpty(oCancelType) || oCancelType <= 0 ){
            throw new SbException(100, "参数错误!");
        }
        // 2. 根据类型分别验证是否存在订单
        if (oCancelType == 1 || oCancelType == 3){  //如果取消订单类型为1,即买家取消未支付订单,如果取消订单类型为2，即买家取消待发货订单
            Order order = new Order();
            order.setoId(oId);
            order.setuId(uId);
            if (oCancelType == 1){
                order.setoStatus(1);
            }
            if ( oCancelType == 3){
                order.setoStatus(2);
            }
            List<Order> orders =  orderService.queryOrderByOrder(order);
            if (CollectionUtils.isEmpty(orders)){
                throw new SbException(100, "无相关订单!");
            }
            if (StringUtils.isEmpty(orders.get(0))){
                throw new SbException(100, "无相关订单!");
            }
            // 3. 取消订单
            order.setoStatus(6);
            order.setoCancelType(oCancelType);
            int flag = orderService.updateOrder(order);
            if (flag == 0){
                throw new SbException(100, "取消订单失败!");
            }
        }else if (oCancelType == 2 || oCancelType == 4){//如果取消订单类型为1,即卖家取消未支付订单,如果取消订单类型为2，即卖家取消待发货订单
            OrderVo orderVo = new OrderVo();
            GoodsVo goodsVo = new GoodsVo();
            User user = new User();
            user.setuId(uId);
            goodsVo.setUser(user);
            orderVo.setGoodsVo(goodsVo);
            orderVo.setoId(oId);
            if (oCancelType == 2){
                orderVo.setoStatus(1);
            }
            if ( oCancelType == 4){
                orderVo.setoStatus(2);
            }
            List<OrderVo> orderVos = orderService.queryOrderVoBySaleUId(orderVo);
            if (CollectionUtils.isEmpty(orderVos)){
                throw new SbException(100, "无相关订单!");
            }
            if (StringUtils.isEmpty(orderVos.get(0))){
                throw new SbException(100, "无相关订单!");
            }
            // 3. 取消订单
            Order order = new Order();
            order.setoId(oId);
            order.setoStatus(6);
            order.setoCancelType(oCancelType);
            int flag = orderService.updateOrder(order);
            if (flag == 0){
                throw new SbException(100, "取消订单失败!");
            }
        }
        //4. 退货退款
        if (oCancelType == 3 || oCancelType == 4){
            //调用支付宝退款函数
            delayService.alipayReturn(oId);
        }
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",true);
        System.out.println(message);
        return message;
    }


    //----------------------------------------------------------凡功能开始------------------------------------------------------------------------

    /**
     * 申请退货退款功能
     * 1. 验证传来的参数 u_id、o_id、oStatus、or_reason、or_received和媒体类参数
     * 2. 根据 u_id、o_id、oStatus查询是否存在订单，oStatus是否是3,4
     * 3. 插入退货退款表，和退货退款媒体表
     * 4. 更新订单的状态为7,8
     * 5. 开启定时器，未在规定时间内处理自动处理
     * 6. 返回
     */
    @Transactional
    @PostMapping("/askReturn")
    public ReturnMessage<Object> askReturn(@RequestBody AskReturnParams askReturnParams){
        if (StringUtils.isEmpty(askReturnParams)){
            throw new SbException(100, "输入不合法");
        }
        if (StringUtils.isEmpty(askReturnParams.getoId()) || askReturnParams.getoId() <0 || StringUtils.isEmpty(askReturnParams.getuId()) || askReturnParams.getuId() <0 || StringUtils.isEmpty(askReturnParams.getoStatus()) || askReturnParams.getoStatus() <0 ){
            throw new SbException(100, "输入不合法");
        }
        if (StringUtils.isEmpty(askReturnParams.getOrderReturn())){
            throw new SbException(100, "输入不合法");
        }
        if (StringUtils.isEmpty(askReturnParams.getOrderReturn().getOrReason()) || StringUtils.isEmpty(askReturnParams.getOrderReturn().getOrReceived())){
            throw new SbException(100, "输入不合法");
        }
        if (askReturnParams.getoStatus() != 3  && askReturnParams.getoStatus() != 4 ){
            throw new SbException(100, "不能退货退款！");
        }
        Order order = new Order();
        order.setoId(askReturnParams.getoId());
        order.setoStatus(askReturnParams.getoStatus());
        order.setuId(askReturnParams.getuId());
        List<Order> orders = orderService.queryOrderByOrder(order);
        if (CollectionUtils.isEmpty(orders)){
            throw new SbException(100, "订单不存在");
        }
        askReturnParams.getOrderReturn().setoId(askReturnParams.getoId());
        askReturnParams.getOrderReturn().setOrCreateTime(new Date());
        askReturnParams.getOrderReturn().setOrStatus(1);//买家申请退货退款
        int flag = orderReturnService.insertOrderReturn(askReturnParams.getOrderReturn());
        if (flag == 0){
            throw new SbException(100, "申请失败！");
        }
        if (!CollectionUtils.isEmpty(askReturnParams.getOrderReturnMedias())){
            for (OrderReturnMedia orderReturnMedia :askReturnParams.getOrderReturnMedias()) {
                orderReturnMedia.setOrId(askReturnParams.getOrderReturn().getOrId());
            }
            flag = orderReturnMediaService.insertOrderReturnMedias(askReturnParams.getOrderReturnMedias());
            if (flag == 0){
                throw new SbException(100, "申请失败！");
            }
        }
        if (askReturnParams.getoStatus() == 3){
            orders.get(0).setoStatus(7);//如果是待收货申请退货退款就将订单状态改为7
        }
        if (askReturnParams.getoStatus() == 4){
            orders.get(0).setoStatus(7);//如果是待评价申请退货退款就将订单状态改为8
        }
        flag = orderService.updateOrder(orders.get(0));
        //定时器，多久之内没有处理就自动同意
        DshOrder dshOrder = new DshOrder(""+askReturnParams.getOrderReturn().getOrId(),7 * 24 * 60 * 60 * 1000,5);
        delayService.add(dshOrder);
        if (flag == 0){
            throw new SbException(100, "申请失败！");
        }
        return ReturnMessageUtil.sucess(true);
    }

    /**
     * 查看退货退款详情
     * 1. 验证前端传来的数据
     * 2. 根据oId,uId,oStatus和isBuy查询是否存在该订单
     * 2. 根据oId查询退货退款表状态码为1.2.3的退货退款
     * 3. 返回
     */
    @Transactional
    @PostMapping("/getReturnDetail/{oId}/{uId}/{oStatus}/{isBuy}")
    public ReturnMessage<Object> getReturnDetail(@PathVariable("uId") Integer uId,@PathVariable("oId") Integer oId,@PathVariable("oStatus") Integer oStatus,@PathVariable("isBuy") Boolean isBuy) {
        check(uId, oId, oStatus, isBuy);
        List<Integer> orStatus = new ArrayList<Integer>();
        orStatus.add(1);
        orStatus.add(2);
        orStatus.add(3);
        //根据oId查询退货退款表状态码为1.2.3的退货退款
        List<OrderReturnVo> orderReturnVos =  orderReturnService.queryOrderReturnVoByOthers(oId,orStatus);
        return ReturnMessageUtil.sucess(orderReturnVos.get(0));
    }

    /**
     * 同意退货退款请求
     * 1.验证
     * 2. 退款
     * 3. 更改退货退款状态
     * 5. 扣除之前已经加入的积分
     * 4. 返回
     * @param  orId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goReturn/{orId}")
    @ResponseBody
    public ReturnMessage<Object> goReturn(@PathVariable("orId") Integer orId) throws Exception {
        if (StringUtils.isEmpty(orId) || orId == 0){
            throw new SbException(100, "参数错误!");
        }
        OrderReturn orderReturn = orderReturnService.queryOrderReturnById(orId);
        if (StringUtils.isEmpty(orderReturn)){
            throw new SbException(100, "参数错误!");
        }
//        退款函数
        boolean temp = delayService.alipayReturn(orderReturn.getoId());
        if (!temp){
            throw new SbException(100, "退款失败!");
        }
//        alipayReturn();
        //更改退货退款状态
        orderReturn.setOrStatus(2);
        int flag= orderReturnService.updateReturnService (orderReturn);
        if (flag == 0){
            throw new SbException(100, "退款失败");
        }
        //扣除之前加入的积分
        flag = oExchangeService.reduceIntegralExchange(orderReturn.getoId());
        if (flag == 0){
            throw new SbException(100, "退款失败");
        }
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",true);
        System.out.println(message);
        return message;
    }
    /*
        拒绝申请
        1. 验证
        2. 修改状态
        3. 创建定时器
     */
    @RequestMapping(value = "/refusedReturn/{orId}")
    @ResponseBody
    public ReturnMessage<Object> refusedReturn(@PathVariable("orId") Integer orId) throws Exception {
        if (StringUtils.isEmpty(orId) || orId == 0) {
            throw new SbException(100, "参数错误!");
        }
        OrderReturn orderReturn = orderReturnService.queryOrderReturnById(orId);
        if (StringUtils.isEmpty(orderReturn)) {
            throw new SbException(100, "参数错误!");
        }
        orderReturn.setOrStatus(3);
        orderReturn.setOrRefusedTime(new Date());
        int flag = orderReturnService.updateReturnService(orderReturn);
        if (flag == 0){
            throw new SbException(100, "拒绝失败");
        }
        //创建定时器
        DshOrder dshOrder = new DshOrder(""+orId,7 * 24 * 60 * 60 * 1000,6);
        delayService.add(dshOrder);
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",true);
        System.out.println(message);
        return message;
    }


    /*
        撤销申请
        1.验证
        2. 修改状态
        3. 更改订单的状态，改回之前的状态
     */
    @RequestMapping(value = "/backoutReturn/{orId}")
    @ResponseBody
    public ReturnMessage<Object> backoutReturn(@PathVariable("orId") Integer orId) throws Exception {
        if (StringUtils.isEmpty(orId) || orId == 0) {
            throw new SbException(100, "参数错误!");
        }
        OrderReturn orderReturn = orderReturnService.queryOrderReturnById(orId);
        if (StringUtils.isEmpty(orderReturn)) {
            throw new SbException(100, "参数错误!");
        }
        orderReturn.setOrStatus(4);
        int flag = orderReturnService.updateReturnService(orderReturn);
        if (flag == 0){
            throw new SbException(100, "撤销失败");
        }
        Order order = orderService.queryOrderByOId(orderReturn.getoId());
        if (StringUtils.isEmpty(order)){
            throw new SbException(100, "撤销失败");
        }
        if (order.getoStatus() == 7){
            order.setoStatus(3);
        }
        if (order.getoStatus() == 8){
            order.setoStatus(4);
        }
        flag = orderService.updateOrder(order);
        if (flag == 0){
            throw new SbException(100, "撤销失败");
        }
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",true);
        System.out.println(message);
        return message;
    }

    public void check(Integer uId,Integer oId,Integer oStatus, Boolean isBuy){
        if (StringUtils.isEmpty(uId) || uId == 0 || StringUtils.isEmpty(oId) || oId == 0 || StringUtils.isEmpty(oStatus) || oStatus == 0 || StringUtils.isEmpty(isBuy)) {
            throw new SbException(100, "参数错误!");
        }
        if (isBuy) {//如果是买家
            Order order = new Order();
            order.setoId(oId);
            order.setuId(uId);
            order.setoStatus(oStatus);
            List<Order> orders = orderService.queryOrderByOrder(order);
            if (CollectionUtils.isEmpty(orders)) {
                throw new SbException(100, "无该订单!");
            }
            if (orders.size() <= 0) {
                throw new SbException(100, "无该订单!");
            }
            Order order1 = orders.get(0);
            if (StringUtils.isEmpty(order1)) {
                throw new SbException(100, "无该订单!");
            }
        }else{//如果是卖家
            List<Order> orders = orderService.queryOrderBySaleUId(uId, oId, oStatus);
            for (Order order : orders) {
                System.out.println(order);
            }
            if (CollectionUtils.isEmpty(orders)) {
                throw new SbException(100, "无该订单!");
            }
            if (orders.size() <= 0) {
                throw new SbException(100, "无该订单!");
            }
            Order order = orders.get(0);
            if (StringUtils.isEmpty(order)) {
                throw new SbException(100, "无该订单!");
            }
        }
    }



    //----------------------------------------------------------萱功能开始------------------------------------------------------------------------
    /**
     * 根据oId查询订单详情
     */
    @GetMapping(value = "/getOrderDetail/{oId}")
    @ResponseBody
    public ReturnMessage<Object> getOrderDetail(@PathVariable("oId")Integer oId){
        if (oId < 0){
            throw new SbException(100, "参数错误!");
        }
        Order order = new Order();
        order.setoId(oId);
        List<OrderVo> orderVos = orderService.queryOrderVoByOthers(order);
        if (CollectionUtils.isEmpty(orderVos)){
            throw new SbException(100, "查询结果为空!");
        }
        if (StringUtils.isEmpty(orderVos.get(0))){
            throw new SbException(100, "查询结果为空!");
        }
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",orderVos.get(0));
        System.out.println(message);
        return message;
    }

    /**
     * 查询已经购买的商品的订单
     * @param uId   买家Id
     * @param type  状态
     * @param size  每页个数
     * @param currentPage 页码
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getOrder/{uId}/{type}/{size}/{currentPage}")
    @ResponseBody
    public ReturnMessage<Object> getOrder(@PathVariable("uId")Integer uId,@PathVariable("type")Integer type,@PathVariable("size")Integer size,@PathVariable("currentPage")Integer currentPage) throws Exception {
        if (uId == 0 || size == 0 || currentPage == 0 ){
            throw new SbException(100, "参数错误!");
        }
        Order order = new Order();
        order.setoStatus(type);
        order.setoBuyDelete(1);
        order.setuId(uId);
        System.out.println(order);
        PageUtils pageUtils = new PageUtils();
        QueryParams<Order> queryParams = new QueryParams<Order>();
        queryParams.setData(order);
        queryParams.setPage(currentPage);
        queryParams.setRows(size);
        pageUtils.startPage(queryParams);
        List<OrderVo> orderVos = orderService.queryOrderVoByOthers( queryParams.getData());
        PageResult pageResult = pageUtils.getDataTable(orderVos);
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",pageResult);
        System.out.println(message);
        return message;
    }

    /**
     * 查询已经卖出商品的订单
     * @param uId  卖家id
     * @param type  状态
     * @param size  每页个数
     * @param currentPage 页码
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getSaleOrder/{uId}/{type}/{size}/{currentPage}")
    @ResponseBody
    public ReturnMessage<Object> getSaleOrder(@PathVariable("uId")Integer uId,@PathVariable("type")Integer type,@PathVariable("size")Integer size,@PathVariable("currentPage")Integer currentPage) throws Exception {
        if (uId == 0 || size == 0 || currentPage == 0 ){
            throw new SbException(100, "参数错误!");
        }
        User user = new User();
        user.setuId(uId);
        GoodsVo goodsVo = new GoodsVo();
        goodsVo.setUser(user);
        OrderVo orderVo = new OrderVo();
        orderVo.setoStatus(type);
        orderVo.setGoodsVo(goodsVo);
        orderVo.setoSellDelete(1);
        PageUtils pageUtils = new PageUtils();
        System.out.println(orderVo);
        QueryParams<OrderVo> queryParams = new QueryParams<OrderVo>();
        queryParams.setData(orderVo);
        queryParams.setPage(currentPage);
        queryParams.setRows(size);
        pageUtils.startPage(queryParams);
        List<OrderVo> orderVos = orderService.queryOrderVoBySaleUId( queryParams.getData());
        PageResult pageResult = pageUtils.getDataTable(orderVos);
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",pageResult);
        System.out.println(message);
        return message;
    }

    /**
     * 模糊查询传入参数uId,gName,size,currentPage,isBuy返回结果：List<OrderVo>
     *     1.验证参数
     *     2.根据uId查询是否存在该用户
     *     3.查询
     *     4.返回
     */
    @GetMapping(value = "/searchOrder/{uId}/{gName}/{size}/{currentPage}/{isBuy}")
    @ResponseBody
    public ReturnMessage<Object> searchOrder(@PathVariable("uId")Integer uId,@PathVariable("gName")String gName,@PathVariable("size")Integer size,@PathVariable("currentPage")Integer currentPage,@PathVariable("isBuy") Boolean isBuy) throws Exception{
        if (uId < 0 || size < 0 || currentPage < 0 ){
            throw new SbException(100, "参数错误!");
        }
        User user = oUserService.queryUserByUId(uId);
        if (StringUtils.isEmpty(user)){
            throw new SbException(100, "不存在该用户!");
        }
        List<OrderVo> orderVos = new ArrayList<OrderVo>();
        PageHelper.startPage(currentPage, size);
        if(isBuy){//买家
            orderVos  = orderService.searchOrderVoByUId(uId,gName);
        }else {//卖家
            orderVos  = orderService.searchOrderVoBySaleUId(uId,gName);
        }
        PageResult pageResult = new PageResult();
        pageResult.setRows(orderVos);
        pageResult.setTotal(new PageInfo(orderVos).getTotal());
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",pageResult);
        System.out.println(message);
        return message;
    }

}
