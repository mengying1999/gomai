package com.gomai.order.delay;

import java.util.concurrent.DelayQueue;

import com.gomai.order.pojo.Order;
import com.gomai.order.service.OrderService;
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
                                       log.info("*********取消订单*******");
                                   }
                               }
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
}
