package com.gomai.order.service;

import com.gomai.goods.pojo.Goods;
import com.gomai.order.pojo.Order;
import com.gomai.user.pojo.UserAddress;

import java.util.List;

public interface OrderService {
    public List<Order> queryPayOrderByGId(Integer gId);

    public int addOrder(Order order);


}
