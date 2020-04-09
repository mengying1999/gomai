package com.gomai.order.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.gomai.goods.pojo.Goods;
import com.gomai.goods.pojo.Unshelve;
import com.gomai.order.config.AlipayConfig;
import com.gomai.order.delay.DelayService;
import com.gomai.order.delay.DshOrder;
import com.gomai.order.pojo.Order;
import com.gomai.order.service.*;
import com.gomai.order.vo.GoodsVo;
import com.gomai.user.pojo.User;
import com.gomai.user.pojo.UserAddress;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
        if(goods.getuId() == order.getuId()){
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
        if(userAddress.getuId() != order.getuId()) {
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
        order.setoSellDelete(0);
        order.setoBuyDelete(0);
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
        unshelve.setUnReason("商品已卖出");
        flag = this.oUnshelveService.addUnshelve(unshelve);
        if (flag == 0){
            throw new SbException(100, "添加失败!");
        }
//      11.创建定时器：创建一个1小时之内没有付款就自动取消订单的定时器
        if (order.getoId() == 0){
            throw new SbException(100, "添加失败!");
        }
        DshOrder dshOrder = new DshOrder(""+order.getoId(),60 * 60 * 1000,1);
        delayService.add(dshOrder);
        return ReturnMessageUtil.sucess(order);
    }

    /**
     *
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
}
