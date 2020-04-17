package com.gomai.order.service;

import com.gomai.order.pojo.OrderReturn;
import com.gomai.order.vo.OrderReturnVo;

import java.util.List;

public interface OrderReturnService {
    public int  insertOrderReturn(OrderReturn orderReturn);

    public List<OrderReturnVo> queryOrderReturnVoByOthers(Integer oId, List<Integer> orStatus);

    public OrderReturn queryOrderReturnById(Integer orId);

    public int updateReturnService(OrderReturn orderReturn);
}
