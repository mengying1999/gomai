package com.gomai.comment.mapper;

import com.gomai.comment.pojo.OrderEvaluationMedia;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CommentMidaMapper extends Mapper<OrderEvaluationMedia> , InsertListMapper<OrderEvaluationMedia> {
    @Select("select * from oder_evaluation_media where o_id=#{oId}")
    public List<OrderEvaluationMedia> selectOEM(Integer oId);
}
