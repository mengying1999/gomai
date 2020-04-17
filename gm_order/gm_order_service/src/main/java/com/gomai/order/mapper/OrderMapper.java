package com.gomai.order.mapper;

import com.gomai.order.pojo.Order;
import com.gomai.order.vo.OrderVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {
    public List<OrderVo> selectOrderVoByOthers(Order order);

    public List<OrderVo> selectOrderVoBySaleUId(OrderVo orderVo);

    public List<Order> selectOrderBySaleUId(@Param("uId") Integer uId, @Param("oId")Integer oId,@Param("oStatus") Integer oStatus);
}
