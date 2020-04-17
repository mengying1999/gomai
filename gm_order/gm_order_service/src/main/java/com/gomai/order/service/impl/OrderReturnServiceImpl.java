package com.gomai.order.service.impl;

import com.gomai.order.mapper.OrderReturnMapper;
import com.gomai.order.pojo.OrderReturn;
import com.gomai.order.service.OrderReturnService;
import com.gomai.order.vo.OrderReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderReturnServiceImpl implements OrderReturnService {
    @Autowired
    OrderReturnMapper orderReturnMapper;
    @Override
    public int insertOrderReturn(OrderReturn orderReturn) {
        return orderReturnMapper.insert(orderReturn);
    }

    @Override
    public List<OrderReturnVo> queryOrderReturnVoByOthers(Integer oId, List<Integer> orStatus) {
        return orderReturnMapper.selectOrderReturnVoByOthers(oId,orStatus);
    }

    @Override
    public OrderReturn queryOrderReturnById(Integer orId) {
        return orderReturnMapper.selectByPrimaryKey(orId);
    }

    @Override
    public int updateReturnService(OrderReturn orderReturn) {
        return orderReturnMapper.updateByPrimaryKey(orderReturn);
    }
}
