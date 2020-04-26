package com.gomai.comment.service.impl;

import com.gomai.comment.mapper.CommentMidaMapper;
import com.gomai.comment.mapper.OCOderMapper;
import com.gomai.comment.mapper.OrderCommentMapper;
import com.gomai.comment.pojo.OrderComment;
import com.gomai.comment.pojo.OrderEvaluationMedia;
import com.gomai.comment.service.OrderCommentService;
import com.gomai.comment.vo.OComentVo;
import com.gomai.comment.vo.OcorderVo;
import com.gomai.comment.vo.OrderComentVo;
import com.gomai.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCommentServiceImpl implements OrderCommentService {
    @Autowired
    private OrderCommentMapper orderCommentMapper;
    @Autowired
    private CommentMidaMapper commentMidaMapper;
    @Autowired
    private OCOderMapper oderMapper;

    @Override
    public int insertoc(Order order) {
        int flag=this.oderMapper.insert(order);
        return flag;
    }

    @Override
    public  List<OrderComment> selectByoid(OrderComment orderComment) {
        List<OrderComment> orderComment1=this.orderCommentMapper.select(orderComment);
        return orderComment1;
    }

    @Override
    public OcorderVo selectorderByoid(Integer oId) {
        OcorderVo ocorderVo=this.orderCommentMapper.selectorderByoid(oId);
        return ocorderVo;
    }

    @Override
    public int insertocmedia(List<OrderEvaluationMedia> orderEvaluationMedias) {
        int flag=this.commentMidaMapper.insertList(orderEvaluationMedias);
        return flag;
    }

    @Override
    public List<OrderComment> selectByuId(OrderComment orderComment) {
        List<OrderComment> orderComments=this.orderCommentMapper.select(orderComment);
        return orderComments;
    }

    @Override
    public OrderComentVo selectOrderComentVoByOId(Integer oId) {
        return this.orderCommentMapper.selectOrderComentVoByOId(oId);
    }

    @Override
    public List<OrderComentVo> selectOrderComentVosBySellUId(Integer uId, List<Integer> orStatus) {
        return this.orderCommentMapper.selectOrderComentVosBySellUId(uId,orStatus);
    }

    @Override
    public List<OrderComentVo> selectOrderComrntVosByUId(Integer uId, List<Integer> orStatus) {
        return this.orderCommentMapper.selectOrderComrntVosByUId(uId, orStatus);
    }

    @Override
    public int insertComment(OrderComment orderComment) {
        int flag=this.orderCommentMapper.insert(orderComment);
        return flag;
    }
}
