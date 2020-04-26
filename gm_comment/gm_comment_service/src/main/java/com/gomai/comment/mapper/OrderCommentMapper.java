package com.gomai.comment.mapper;

import com.gomai.comment.pojo.OrderComment;
import com.gomai.comment.vo.CommentVo;
import com.gomai.comment.vo.OComentVo;
import com.gomai.comment.vo.OcorderVo;
import com.gomai.comment.vo.OrderComentVo;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderCommentMapper extends Mapper<OrderComment> {
    public CommentVo selectcommentvoByoId(Integer oId);

    public OcorderVo selectorderByoid(Integer oId);

    @Results(id = "orderComentVo",value={
            @Result(id=true,property="oId" ,column="o_id"),
            @Result (property = "uId",column = "u_id"),
            @Result (property = "gId",column = "g_id"),
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
            @Result(property = "user",column = "u_id",one = @One(select = "com.gomai.comment.mapper.OcUserMapper.selectByPrimaryKey")),
            @Result(property = "goodsVo",column = "g_id",one = @One(select = "com.gomai.comment.mapper.OCGoodMapper.selectGoodsVoByGId")),
            @Result(property = "commentVos",column = "o_id",one = @One(select = "com.gomai.comment.mapper.OrderCommentMapper.selectcommentvoByoId")),
            @Result(property="orderEvaluationMedia",column="o_id" ,many=@Many(select="com.gomai.comment.mapper.OComentVoMapper.selectMediaByOId"))
    }
    )
    @Select("select * from order_form where o_id=#{oId}")
    public OrderComentVo selectOrderComentVoByOId(Integer oId);

    @ResultMap("orderComentVo")
    @Select({"<script>", "select order_form.* from order_form,goods where order_form.g_id=goods.g_id and goods.u_id=#{uId} and o_status in ","<foreach collection='orStatus' item='item' open='(' separator=',' close=')'>#{item}</foreach>","</script>"})
    public List<OrderComentVo> selectOrderComentVosBySellUId(@Param("uId") Integer uId, @Param("orStatus") List<Integer> orStatus);

    @ResultMap("orderComentVo")
    @Select({"<script>", "select * from order_form where  u_id=#{uId} and o_status in ","<foreach collection='orStatus' item='item' open='(' separator=',' close=')'>#{item}</foreach>","</script>"})
    public  List<OrderComentVo> selectOrderComrntVosByUId(@Param("uId") Integer uId, @Param("orStatus") List<Integer> orStatus);
}
