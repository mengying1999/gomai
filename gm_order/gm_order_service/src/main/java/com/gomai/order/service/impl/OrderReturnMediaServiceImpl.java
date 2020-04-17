package com.gomai.order.service.impl;

import com.gomai.order.mapper.OrderReturnMediaMapper;
import com.gomai.order.pojo.OrderReturnMedia;
import com.gomai.order.service.OrderReturnMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderReturnMediaServiceImpl implements OrderReturnMediaService {
    @Autowired
    OrderReturnMediaMapper orderReturnMediaMapper;
    @Override
    public int insertOrderReturnMedias(List<OrderReturnMedia> orderReturnMedias) {
        return orderReturnMediaMapper.insertList(orderReturnMedias);
    }
}
