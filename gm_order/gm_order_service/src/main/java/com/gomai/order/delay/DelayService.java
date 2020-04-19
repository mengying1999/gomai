package com.gomai.order.delay;

import java.util.Date;
import java.util.List;
import java.util.concurrent.DelayQueue;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.gomai.order.config.AlipayConfig;
import com.gomai.order.pojo.Order;
import com.gomai.order.pojo.OrderReturn;
import com.gomai.order.service.OExchangeService;
import com.gomai.order.service.OrderReturnService;
import com.gomai.order.service.OrderService;
import com.gomai.order.vo.OrderVo;
import com.gomai.utils.SbException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 延时队列service
 * @author Administrator
 *
 */
@Service
public class DelayService {
    private boolean start;//判断是否启动队列

    private OnDelayedListener listener;//内部接口监听器

    private DelayQueue<DshOrder> delayQueue = new DelayQueue<DshOrder>(); //队列集合

    private Log log = LogFactory.getLog(DelayService.class);

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DelayService delayService;
    @Autowired
    private OExchangeService oExchangeService;
    @Autowired
    private OrderReturnService orderReturnService;
    public static interface OnDelayedListener{
        public void onDelayedArrived(DshOrder order);
    }


    public void start(OnDelayedListener listener) {
        if (start) {
            log.error(">>>>>>>>>>>>DelayService已经在启动状态");
            return;
        }
        log.info(">>>>>>>>>>>>DelayService 启动");
        start = true;
        this.listener = listener;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        log.info("*********准备获取延迟队列里面将要执行的队列*******");
                        /* 延时队列会将加入队列中的元素按照过期时间的先后顺序排序，先过期的在队首，该take方法会判断队首
                         * 元素是否过期，如果没过期，会阻塞等待，直到队首元素过期，才会取出来，往下执行逻辑 */
                        DshOrder order = delayQueue.take();
                        log.info(order.getOrderNo());
                        if(order.getType() == 1){   //如果是类型为1，未付款取消订单
                            log.info(order.getOrderNo());
                            log.info(Integer.parseInt(order.getOrderNo()));
                               Order myOrder =  orderService.queryOrderByOId(Integer.parseInt(order.getOrderNo()));
                               if (!StringUtils.isEmpty(myOrder)){
                                   log.info(myOrder);
                                   if (myOrder.getoStatus() == 1){  //还为待付款
                                       //取消订单
                                       myOrder.setoStatus(6);
                                       myOrder.setoCancelType(1);
                                       int flag = orderService.updateOrder(myOrder);
                                       if (flag == 0){
                                           throw new SbException(100, "取消订单失败!");
                                       }
                                       log.info("*********未在规定时间内付款取消订单*******");
                                   }
                               }
                        }
                        if(order.getType() == 2){   //如果是类型为2，未发货订单，取消订单
                            log.info(order.getOrderNo());
                            log.info(Integer.parseInt(order.getOrderNo()));
                            Order myOrder =  orderService.queryOrderByOId(Integer.parseInt(order.getOrderNo()));
                            if (!StringUtils.isEmpty(myOrder)){
                                log.info(myOrder);
                                if (myOrder.getoStatus() == 2){  //还为待发货
                                    //取消订单
                                    myOrder.setoStatus(6);
                                    myOrder.setoCancelType(4);
                                    int flag = orderService.updateOrder(myOrder);
                                    if (flag == 0){
                                        throw new SbException(100, "取消订单失败!");
                                    }
                                    alipayReturn(myOrder.getoId());
                                    log.info("*********未在规定时间内发货取消订单*******");
                                }
                            }
                        }
                        if(order.getType() == 3){   //如果是类型为3，未收货订单，确认收货
                            log.info(order.getOrderNo());
                            log.info(Integer.parseInt(order.getOrderNo()));
                            Order myOrder =  orderService.queryOrderByOId(Integer.parseInt(order.getOrderNo()));
                            if (!StringUtils.isEmpty(myOrder)){
                                log.info(myOrder);
                                if (myOrder.getoStatus() == 3){  //还为待收货
                                    //确认收货
                                    log.info("*********未在规定时间内收货，自动确认收货*******");
                                    myOrder.setoStatus(4);
                                    myOrder.setoReceiveTime(new Date());
                                    int flag = orderService.updateOrder(myOrder);
                                    if (flag == 0){
                                        throw new SbException(100, "更新错误!");
                                    }
                                    //生成积分明细
                                    flag = oExchangeService.addIntegralExchange(myOrder.getoId());
                                    if (flag == 0){
                                        throw new SbException(100, "更新错误!");
                                    }
                                    //创建定时器（即15天未评价则将订单改为已评价，评价为默认评价）
                                    DshOrder dshOrder = new DshOrder(""+myOrder.getoId(),60 * 60 * 1000,4);
                                    delayService.add(dshOrder);
                                }
                            }
                        }
                        if(order.getType() == 4){   //如果是类型为4，未评价订单，系统默认评价
                            log.info(order.getOrderNo());
                            log.info(Integer.parseInt(order.getOrderNo()));
                            Order myOrder =  orderService.queryOrderByOId(Integer.parseInt(order.getOrderNo()));
                            if (!StringUtils.isEmpty(myOrder)){
                                log.info(myOrder);
                                if (myOrder.getoStatus() == 4){  //还为未评价
                                    //评价
                                    log.info("*********未在规定时间内评价，自动评价*******");
                                    myOrder.setoStatus(5);
                                    myOrder.setoEvaluation("系统自动评价");
                                    myOrder.setoEvaluationTime(new Date());
                                    int flag = orderService.updateOrder(myOrder);
                                    if (flag == 0){
                                        throw new SbException(100, "更新错误!");
                                    }
                                }
                            }
                        }
                        if(order.getType() == 5) {   //如果是类型为5，未及时处理退货退款申请,则系统自动同意退款
                           Integer orId = Integer.parseInt(order.getOrderNo());
                           OrderReturn orderReturn = orderReturnService.queryOrderReturnById(orId);
                            if (!StringUtils.isEmpty(orderReturn)) {
                                if (orderReturn.getOrStatus() == 1) {
                                    alipayReturn(orderReturn.getoId());
                                    //更改退货退款状态
                                    orderReturn.setOrStatus(2);
                                    int flag = orderReturnService.updateReturnService(orderReturn);
                                    if (flag == 0) {
                                        throw new SbException(100, "退款失败");
                                    }
                                    flag = oExchangeService.reduceIntegralExchange(orderReturn.getoId());
                                    if (flag == 0){
                                        throw new SbException(100, "退款失败");
                                    }
                                }
                            }
                        }
                        if(order.getType() == 6) {   //如果是类型为6，未及时撤销退款请求,则系统自动撤销
                            Integer orId = Integer.parseInt(order.getOrderNo());
                            OrderReturn orderReturn = orderReturnService.queryOrderReturnById(orId);
                            if (!StringUtils.isEmpty(orderReturn)) {
                                if (orderReturn.getOrStatus() == 3) {
                            if (StringUtils.isEmpty(orderReturn)) {
                                throw new SbException(100, "参数错误!");
                            }
                            orderReturn.setOrStatus(4);
                            int flag = orderReturnService.updateReturnService(orderReturn);
                            if (flag == 0){
                                throw new SbException(100, "撤销失败");
                            }
                            Order order1 = orderService.queryOrderByOId(orderReturn.getoId());
                            if (StringUtils.isEmpty(order1)){
                                throw new SbException(100, "撤销失败");
                            }
                            if (order1.getoStatus() == 7){
                                order1.setoStatus(3);
                            }
                            if (order1.getoStatus() == 8){
                                order1.setoStatus(4);
                            }
                            flag = orderService.updateOrder(order1);
                            if (flag == 0){
                                throw new SbException(100, "撤销失败");
                            }}}
                        }
                        if (DelayService.this.listener != null) {
                            DelayService.this.listener.onDelayedArrived(order);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public void add(DshOrder order){
        //写入队列
        delayQueue.put(order);
        //存入redis
        jedisUtil.setHash("ORDER", order.getOrderNo(), order);
        log.info("**************订单号:" + order.getOrderNo() + "被写入订单成功!*************");
    }
    /**
     * 重载主要是为了业务中只需要写入延时队列，而不需要写入redis的情况
     * @param order 延时订单
     * @param type null
     */
    public void add(DshOrder order,String type){
        //写入队列
        delayQueue.put(order);
        //存入redis
        jedisUtil.setHash("ORDER_SHIP", order.getOrderNo(), order);
    }

    public boolean remove(DshOrder order){
        //从redis中删除
        jedisUtil.removeHash("ORDER", order.getOrderNo());

        log.info("**************订单号:" + order.getOrderNo() + "被删除成功!*************");
        //从队列里面删除
        return delayQueue.remove(order);

    }

    public void remove(String orderNo){
        DshOrder[] array = delayQueue.toArray(new DshOrder[]{});
        if(array == null || array.length <= 0){
            return;
        }
        DshOrder target = null;
        for(DshOrder order : array){
            if(order.getOrderNo().equals(orderNo)){
                target = order;
                break;
            }
        }
        if(target != null){
            this.remove(target);
        }
    }
    public boolean  alipayReturn(Integer oId){
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        Order temp = new Order();
        temp.setoId(oId);
        List<OrderVo> orderVos = orderService.queryOrderVoByOthers(temp);
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = "" + oId;
        //支付宝交易号
        String trade_no = orderVos.get(0).getoTradeNo();
        //请二选一设置
        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = ""+ orderVos.get(0).getGoodsVo().getgPrice();
        //退款的原因说明
        String refund_reason = "";
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        String out_request_no = "";
//
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"trade_no\":\""+ trade_no +"\","
                + "\"refund_amount\":\""+ refund_amount +"\","
                + "\"refund_reason\":\""+ refund_reason +"\","
                + "\"out_request_no\":\""+ out_request_no +"\"}");

        //请求
        AlipayTradeRefundResponse response;
        try {
            response = alipayClient.execute(alipayRequest);
            if (!response.isSuccess()) {
                String returnStr = response.getSubMsg();//失败会返回错误信息
                System.out.println(returnStr);
            }
            if(response.isSuccess()){
                System.out.println("退款请求发送成功");
            }
            //判断退款是否成功
            if(response.getFundChange().equals("Y")){
                System.out.println("退款成功");
            }
            return response.isSuccess();
        } catch (AlipayApiException e) {
            System.out.println("11111111111111111111111111111111111111111111111");
            e.printStackTrace();
        }
        return false;
    }
}
