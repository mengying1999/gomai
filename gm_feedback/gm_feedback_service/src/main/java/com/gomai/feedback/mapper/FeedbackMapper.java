package com.gomai.feedback.mapper;

import com.gomai.feedback.pojo.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.common.Mapper;

public interface FeedbackMapper extends Mapper<Feedback> {
    @Insert("INSERT INTO feedback (f_id,u_id,f_content,f_status) VALUES (#{fId},#{uId},#{fContent},#{fStatus})")
    @Options(useGeneratedKeys=true,keyProperty="fId",keyColumn="f_id")
    Integer insertFeedback(Feedback application);
}
