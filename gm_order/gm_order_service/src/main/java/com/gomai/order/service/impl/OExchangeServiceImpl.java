package com.gomai.order.service.impl;

import com.gomai.intergral.pojo.IntegralExchange;
import com.gomai.order.mapper.OExchangeMapper;
import com.gomai.order.mapper.OUserMapper;
import com.gomai.order.mapper.OrderMapper;
import com.gomai.order.pojo.Order;
import com.gomai.order.service.OExchangeService;
import com.gomai.order.vo.OrderVo;
import com.gomai.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OExchangeServiceImpl implements OExchangeService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OExchangeMapper oExchangeMapper;
    @Autowired
    private OUserMapper oUserMapper;
    /**
     * 确认收货后，生成积分明细
     *      插入积分明细
     *      更改个人总积分
     */
    public int addIntegralExchange(Integer oId){
        //根据oId查询订单信息
        Order temp = new Order();
        temp.setoId(oId);
        List<OrderVo> orderVos = orderMapper.selectOrderVoByOthers(temp);
        OrderVo orderVo = orderVos.get(0);
        IntegralExchange integralExchange = new IntegralExchange();
        integralExchange.setgId(orderVo.getGoodsVo().getgId());
        integralExchange.setuId(orderVo.getUser().getuId());
        integralExchange.setIeCreateTime(new Date());
        int integral = 0;
        double price = orderVo.getGoodsVo().getgPrice()*0.1;
        if (orderVo.getGoodsVo().getgPrice() > 10){
            integral = (int)price;
        }else{
            integral = 1;
        }
        integralExchange.setIeChangeIntegral(integral);
        integralExchange.setIeType(2);
        int flag = oExchangeMapper.insert(integralExchange);
        if (flag == 0){
            return flag;
        }
        User user = orderVo.getUser();
        user.setuTotalIntegral(user.getuTotalIntegral() + integral);
        flag =oUserMapper.updateByPrimaryKey(user);
        return flag;
    }

    /**
     * 退货退款减积分
     */
    public int reduceIntegralExchange(Integer oId){
        //根据oId查询订单信息
        Order temp = new Order();
        temp.setoId(oId);
        List<OrderVo> orderVos = orderMapper.selectOrderVoByOthers(temp);
        OrderVo orderVo = orderVos.get(0);
        IntegralExchange integralExchange = new IntegralExchange();
        integralExchange.setgId(orderVo.getGoodsVo().getgId());
        integralExchange.setuId(orderVo.getUser().getuId());
        integralExchange.setIeCreateTime(new Date());
        int integral = 0;
        double price = orderVo.getGoodsVo().getgPrice()*0.1;
        if (orderVo.getGoodsVo().getgPrice() > 10){
            integral = (int)price;
        }else{
            integral = 1;
        }
        integralExchange.setIeChangeIntegral(integral);
        integralExchange.setIeType(3);
        int flag = oExchangeMapper.insert(integralExchange);
        if (flag == 0){
            return flag;
        }
        User user = orderVo.getUser();
        user.setuTotalIntegral(user.getuTotalIntegral() - integral);
        flag =oUserMapper.updateByPrimaryKey(user);
        return flag;
    }
}
