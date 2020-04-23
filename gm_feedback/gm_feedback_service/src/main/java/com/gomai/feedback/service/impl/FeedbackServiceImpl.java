package com.gomai.feedback.service.impl;

import com.gomai.feedback.mapper.FeedbackMapper;
import com.gomai.feedback.pojo.Feedback;
import com.gomai.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public int FeedbackAdd(Feedback feedback) {
        int flag=this.feedbackMapper.insertFeedback(feedback);
        return flag;
    }
}
