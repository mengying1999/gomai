package com.gomai.comment.service;

import com.gomai.comment.pojo.OrderComment;
import com.gomai.comment.pojo.OrderEvaluationMedia;
import com.gomai.comment.vo.CommentVo;
import com.gomai.comment.vo.OComentVo;
import com.gomai.comment.vo.OcorderVo;
import com.gomai.comment.vo.OrderComentVo;
import com.gomai.order.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderCommentService {
    public int insertoc(Order order);

    public List<OrderComment> selectByoid(OrderComment orderComment);

    public OcorderVo selectorderByoid(Integer oId);

    public int insertocmedia(List<OrderEvaluationMedia> orderEvaluationMedias);

    public List<OrderComment> selectByuId(OrderComment orderComment);

    public OrderComentVo selectOrderComentVoByOId(Integer oId);
    public List<OrderComentVo> selectOrderComentVosBySellUId(Integer uId, List<Integer> orStatus);

    public  List<OrderComentVo> selectOrderComrntVosByUId(@Param("uId") Integer uId, @Param("orStatus") List<Integer> orStatus);

    public int insertComment(OrderComment orderComment);
}
