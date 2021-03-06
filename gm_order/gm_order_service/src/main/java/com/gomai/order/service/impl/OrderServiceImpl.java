package com.gomai.order.service.impl;

import com.gomai.order.mapper.OrderMapper;
import com.gomai.order.pojo.Order;
import com.gomai.order.service.OrderService;
import com.gomai.order.vo.OrderVo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Order> queryPayOrderByGId(Integer gId) {
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("gId", gId);
        criteria.andEqualTo("oStatus", 1);
        List<Order> orders = orderMapper.selectByExample(example);
        return orders;
    }

    @Override
    public int addOrder(Order order) {
       int flag =  orderMapper.insert(order);
        return flag;
    }

    @Override
    public Order queryOrderByOId(Integer oId) {
        System.out.println(oId);
        Order order = orderMapper.selectByPrimaryKey(oId);
        System.out.println(order);
        return order;
    }

    @Override
    public List<Order> queryOrderByOrder(Order order) {
        List<Order> orders = orderMapper.select(order);
        System.out.println(orders);
        return orders;
    }

    @Override
    public List<Order> queryOrderBySaleUId(Integer uId, Integer oId, Integer oStatus) {
        System.out.println(""+uId+oId+oStatus);
        return orderMapper.selectOrderBySaleUId(uId, oId, oStatus);
    }

    @Override
    public List<OrderVo> searchOrderVoByUId(Integer uId, String gName) {
        return orderMapper.searchOrderVoByUId(uId,gName);
    }

    @Override
    public List<OrderVo> searchOrderVoBySaleUId(Integer uId, String gName) {
        return orderMapper.searchOrderVoBySaleUId(uId,gName);
    }

    @Override
    public int updateOrder(Order order) {
        int flag = orderMapper.updateByPrimaryKeySelective(order);
        return flag;
    }

    @Override
    public List<OrderVo> queryOrderVoByOthers(Order order) {
        List<OrderVo> orderVos = orderMapper.selectOrderVoByOthers(order);
        return orderVos;
    }

    @Override
    public List<OrderVo> queryOrderVoBySaleUId(OrderVo orderVo) {
        List<OrderVo> orderVos = orderMapper.selectOrderVoBySaleUId(orderVo);
        return orderVos;
    }
}
