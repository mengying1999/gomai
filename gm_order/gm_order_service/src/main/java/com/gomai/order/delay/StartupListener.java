package com.gomai.order.delay;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;



/**
 * 用于监听延时队列的类
 * @author Administrator
 * spring监听器必须加上@Service，注入到bean对象中
 */
@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent>{

    private static final Log log = LogFactory.getLog(StartupListener.class);

    @Autowired
    private DelayService delayService;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        log.info(">>>>>>>>>>>>系统启动完成,onApplicationEvent");
        /* applicationontext和使用MVC之后的webApplicationontext会两次调用监听器的方法，
         * 这样可以解决，applicationontext是父容器，所以没有父级元素，这句代表父容器（applicationontext）直接返回，不执行
         * 监听器方法，子容器（springMVC的）才会执行后面的监听器方法，这样就不会两次调用了*/
        if (evt.getApplicationContext().getParent() == null) {
            return;
        }

        delayService.start(new DelayService.OnDelayedListener() {

            @Override
            public void onDelayedArrived(final DshOrder order) {
                ThreadPoolUtils.execute(new Runnable() {

                    @Override
                    public void run() {
                        String orderNo = order.getOrderNo();
                        //查库判断是否需要进行删除
                        log.info("30分钟自动取消订单,onDelayedArrived():" + orderNo);
                        delayService.remove(order);
                    }
                });

            }
        });

        //查找需要入队的订单
        ThreadPoolUtils.execute(new Runnable() {

            @Override
            public void run() {
                log.info("查找需要入队的订单");
                Set<String> orderNos = jedisUtil.hKeys("ORDER");
                log.info("30分钟未支付需要入队的订单:" + orderNos);
                if (orderNos == null || orderNos.size() <= 0) {
                    return;
                }

                //写到DelayQueue
                for (String str : orderNos) {
                    //通过redis取key中的str域的value
                    DshOrder dshOrder = (DshOrder) jedisUtil.getHash("ORDER", str);
                    //存入延时队列里面
                    delayService.add(dshOrder, null);
                }
            }
        });

    }

}
