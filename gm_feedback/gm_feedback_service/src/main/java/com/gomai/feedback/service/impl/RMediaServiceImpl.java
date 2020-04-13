package com.gomai.feedback.service.impl;


import com.gomai.feedback.mapper.RMediaMapper;

import com.gomai.feedback.pojo.ReportMedia;

import com.gomai.feedback.service.RMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RMediaServiceImpl implements RMediaService {
    @Autowired
    private RMediaMapper rMediaMapper;


    @Override
    public int RMediaAdd(List<ReportMedia> reportMedias) {
        int flag=this.rMediaMapper.insertList(reportMedias);
        return flag;
    }
}
