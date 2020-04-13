package com.gomai.feedback.mapper;

import com.gomai.feedback.pojo.FeedbackMedia;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface FMediaMapper extends Mapper<FeedbackMedia> , InsertListMapper<FeedbackMedia> {
}
