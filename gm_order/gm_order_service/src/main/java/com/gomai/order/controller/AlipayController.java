package com.gomai.order.controller;

import com.gomai.order.delay.DelayService;
import com.gomai.order.delay.DshOrder;
import com.gomai.order.pojo.Order;
import com.gomai.order.service.OrderService;
import com.gomai.utils.SbException;
import com.netflix.discovery.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/alipay")
public class AlipayController {
    private Log log = LogFactory.getLog(DelayService.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private DelayService delayService;

    @ApiOperation("支付异步通知接口")
    @GetMapping("/notify_url")
    public String notifyAlipay() {

        log.info("----notify-----");
        return " a li pay notify ";
    }

    /**
     * 支付成功后：
     * 1. 判断oId是否合法
     * 2. 根据oId查询order
     * 3. 判断返回的order的状态是否是未付款，如果是改为未发货
     * 4. 返回我的购买页面
     *
     * @return
     */
    @ApiOperation("支付完成以后的回调接口")
    @GetMapping("/return_url")
    public ModelAndView returnAlipay(@RequestParam("out_trade_no") Integer oId, @RequestParam("trade_no") String trade_no) {
        System.out.println(trade_no);
        log.info("----return-----");
        if (oId <= 0) {
            throw new SbException(100, "支付错误！");
        }
        if (StringUtils.isEmpty(trade_no)) {
            throw new SbException(100, "支付错误！");
        }
        Order order = orderService.queryOrderByOId(oId);
        if (StringUtils.isEmpty(order)) {
            throw new SbException(100, "该订单不存在！");
        }
        if (order.getoStatus() != 1) {
            throw new SbException(100, "订单状态错误！");
        }
        order.setoTradeNo(trade_no);//设置支付宝交易号
        order.setoStatus(2);//将状态改为未发货即已支付
        Date date = new Date();
        System.out.println(date);
        order.setoPayTime(date);//设置付款时间
        int flag = orderService.updateOrder(order);
        if (flag == 0) {
            throw new SbException(100, "状态更改失败！");
        }
        DshOrder dshOrder = new DshOrder("" + order.getoId(), 60 * 60 * 1000, 2);
        delayService.add(dshOrder);
        ModelAndView modelAndView = new ModelAndView("redirect:http://www.gomai.com/buyOrder");
        return modelAndView;
    }
}
