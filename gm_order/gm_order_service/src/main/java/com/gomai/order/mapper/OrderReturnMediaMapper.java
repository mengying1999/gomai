package com.gomai.order.mapper;

import com.gomai.order.pojo.OrderReturn;
import com.gomai.order.pojo.OrderReturnMedia;
import com.gomai.order.vo.OrderReturnVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderReturnMediaMapper  extends Mapper<OrderReturnMedia>, InsertListMapper<OrderReturnMedia> {
    @Select("select * from order_return_media where or_id=#{orId}")
    public List<OrderReturnMedia> selectOrderReturnMediasByOrId(@Param("orId") Integer orId);
}
