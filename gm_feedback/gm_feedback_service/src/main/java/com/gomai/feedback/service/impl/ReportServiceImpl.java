package com.gomai.feedback.service.impl;

import com.gomai.feedback.mapper.FeedbackMapper;
import com.gomai.feedback.mapper.ReportMapper;
import com.gomai.feedback.pojo.Feedback;
import com.gomai.feedback.pojo.Report;
import com.gomai.feedback.service.FeedbackService;
import com.gomai.feedback.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;


    @Override
    public int ReportAdd(Report report) {
        int flag=this.reportMapper.insertreport(report);
        return flag;
    }
}
