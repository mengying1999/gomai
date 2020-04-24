package com.gomai.feedback.mapper;


import com.gomai.feedback.pojo.Feedback;
import com.gomai.feedback.pojo.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.common.Mapper;

public interface ReportMapper extends Mapper<Report> {
    @Insert("INSERT INTO report (r_id,u_id,o_id,r_content,r_status) VALUES (#{rId},#{uId},#{oId},#{rContent},#{rStatus})")
    @Options(useGeneratedKeys=true,keyProperty="rId",keyColumn="r_id")
    Integer insertreport(Report application);
}
