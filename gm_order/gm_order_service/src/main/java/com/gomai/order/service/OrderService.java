package com.gomai.order.service;

import com.gomai.goods.pojo.Goods;
import com.gomai.order.pojo.Order;
import com.gomai.order.vo.OrderVo;
import com.gomai.user.pojo.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    public List<Order> queryPayOrderByGId(Integer gId);

    public int addOrder(Order order);

    public Order queryOrderByOId(Integer oId);

    public int updateOrder(Order order);

    public List<OrderVo> queryOrderVoByOthers(Order order);

    public List<OrderVo> queryOrderVoBySaleUId(OrderVo orderVo);

    public List<Order> queryOrderByOrder(Order order);

    public List<Order> queryOrderBySaleUId(Integer uId, Integer oId,Integer oStatus);

    public List<OrderVo> searchOrderVoByUId(Integer uId, String gName);
    public List<OrderVo> searchOrderVoBySaleUId(Integer uId, String gName);
}
