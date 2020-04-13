package com.gomai.feedback.service.impl;

import com.gomai.feedback.mapper.FMediaMapper;
import com.gomai.feedback.pojo.FeedbackMedia;
import com.gomai.feedback.service.FMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FMediaServiceImpl implements FMediaService {
    @Autowired
    private FMediaMapper fMediaMapper;

    @Override
    public int FMediaAdd(List<FeedbackMedia> feedbackMedias) {
        int flag=this.fMediaMapper.insertList(feedbackMedias);
        return flag;
    }


}
