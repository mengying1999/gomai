package com.gomai.order.service.impl;

import com.gomai.order.mapper.OrderMapper;
import com.gomai.order.pojo.Order;
import com.gomai.order.service.OrderService;
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
}
