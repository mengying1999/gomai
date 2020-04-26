package com.gomai.comment.mapper;

import com.gomai.comment.pojo.OrderEvaluationMedia;
import com.gomai.comment.vo.OComentVo;
import com.gomai.comment.vo.OrderMidaVo;
import com.gomai.goods.pojo.GoodsMedia;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OComentVoMapper extends Mapper<OrderMidaVo> {
    @Results(value={
            @Result(id=true,property="oId" ,column="o_id"),
            @Result(property="oStatus" ,column="o_status"),
            @Result(property="oCancelType" ,column="o_cancel_type"),
            @Result(property="oEvaluation" ,column="o_evaluation"),
            @Result(property="oEvaluationAdd" ,column="o_evaluation_add"),
            @Result(property="oCancelReason" ,column="o_cancel_reason"),
            @Result(property="oCreateTime" ,column="o_create_time"),
            @Result(property="oPayTime" ,column="o_pay_time"),
            @Result(property="oRemindShipments" ,column="o_remind_shipments"),
            @Result(property="oShipmentsTime" ,column="o_shipments_time"),
            @Result(property="oRemindReceive" ,column="o_remind_receive"),
            @Result(property="oReceiveTime" ,column="o_receive_time"),
            @Result(property="oEvaluationTime" ,column="o_evaluation_time"),
            @Result(property="oEvaluationAddTime" ,column="o_evaluation_add_time"),
            @Result(property="oSellDelete" ,column="o_sell_delete"),
            @Result(property="oTradeNo" ,column="o_trade_no"),
            @Result(property="oBuyDelete" ,column="o_buy_delete"),
            @Result(property="orderEvaluationMedia",column="o_id" ,many=@Many(select="com.gomai.comment.mapper.OComentVoMapper.selectMediaByOId"))
    }
    )
    @Select("select * from order_form where u_id = #{uId}")
    public List<OrderMidaVo> selectOrderVoByUId(Integer uId);

   @Select("select * from oder_evaluation_media where o_id= #{oId}")
  public List<OrderEvaluationMedia> selectMediaByOId(Integer oId);
}
