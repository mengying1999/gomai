package com.gomai.comment.service.impl;

import com.gomai.comment.mapper.OCOderMapper;
import com.gomai.comment.service.OCOderService;
import com.gomai.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OCOderServiceImpl implements OCOderService {
    @Autowired
    private OCOderMapper oderMapper;

    @Override
    public Order selectByoId(Order o) {
        Order order=this.oderMapper.selectOne(o);
        return order;
    }

    @Override
    public int Updateoe(Order order) {
        int flag=this.oderMapper.updateByPrimaryKey(order);
        return flag;
    }

}
