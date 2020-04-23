package com.gomai.comment.service;

import com.gomai.comment.pojo.OrderComment;
import com.gomai.comment.pojo.OrderEvaluationMedia;
import com.gomai.comment.vo.CommentVo;
import com.gomai.comment.vo.OcorderVo;
import com.gomai.order.pojo.Order;

import java.util.List;

public interface OrderCommentService {
    public int insertoc(Order order);

    public List<OrderComment> selectByoid(OrderComment orderComment);

    public OcorderVo selectorderByoid(Integer oId);

    public int insertocmedia(List<OrderEvaluationMedia> orderEvaluationMedias);
}
