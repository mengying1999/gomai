package com.gomai.feedback.mapper;


import com.gomai.feedback.pojo.ReportMedia;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface RMediaMapper extends Mapper<ReportMedia>, InsertListMapper<ReportMedia> {
}
