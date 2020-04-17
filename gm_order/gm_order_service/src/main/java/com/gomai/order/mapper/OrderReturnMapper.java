package com.gomai.order.mapper;

import com.gomai.order.pojo.Order;
import com.gomai.order.pojo.OrderReturn;
import com.gomai.order.vo.OrderReturnVo;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderReturnMapper extends Mapper<OrderReturn> {
    @Results(value={
            @Result(id=true,column="or_id" , property="orId"),
            @Result( column="o_id" ,property="oId"),
            @Result( column="or_reason" ,property="orReason"),
            @Result( column="or_status" ,property="orStatus"),
            @Result( column="or_received" ,property="orReceived"),
            @Result( column="or_create_time" ,property="orCreateTime"),
            @Result(property="orderReturnMedias",column="or_id" ,many=@Many(select="com.gomai.order.mapper.OrderReturnMediaMapper.selectOrderReturnMediasByOrId")
            )}
    )
    @Select({"<script>", "select * from order_return where o_id=#{oId} and or_status in ","<foreach collection='orStatus' item='item' open='(' separator=',' close=')'>#{item}</foreach>","</script>"})
    public List<OrderReturnVo> selectOrderReturnVoByOthers(@Param("oId") Integer oId, @Param("orStatus") List<Integer> orStatus);


}
