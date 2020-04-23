package com.gomai.comment.mapper;

import com.gomai.comment.pojo.OrderComment;
import com.gomai.comment.vo.CommentVo;
import com.gomai.comment.vo.OcorderVo;
import tk.mybatis.mapper.common.Mapper;

public interface OrderCommentMapper extends Mapper<OrderComment> {
    public CommentVo selectcommentvoByoId(Integer oId);

    public OcorderVo selectorderByoid(Integer oId);
}
