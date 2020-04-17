package com.gomai.feedback.controller;


import com.gomai.feedback.pojo.Report;
import com.gomai.feedback.pojo.ReportMedia;
import com.gomai.feedback.service.RMediaService;
import com.gomai.feedback.service.ReportService;
import com.gomai.feedback.vo.R_RMdia;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Feedback")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private RMediaService rMediaService;

    /**
     * 将反馈信息插入到反馈表Feedback和媒体表FeedbackMedia
     *
     * @return
     */
    @PostMapping("/AddReport")
    public ReturnMessage<Object> AddReport(@RequestBody R_RMdia r_RMdia) {
        Report report = r_RMdia.getReport();
        List<ReportMedia> reportMedias = r_RMdia.getReportMedia();
        //        1. 判断feedback feedbackMedias是否为空  2. 判断fId,uId是否为空
        if (StringUtils.isEmpty(report) &&  (report.getuId() == null || report.getuId() < 0)) {
            throw new SbException(400, "输入不合法");
        }
        //          2.判断反馈内容是否为空
        if (StringUtils.isEmpty(report.getrContent())) {
            throw new SbException(100, "举报信息错误!");
        }
        //          3.数据操作
        int flag = reportService.ReportAdd(report);
        if (flag == 0) {
            throw new SbException(100, "举报信息添加失败!");
        }
        if (!StringUtils.isEmpty(reportMedias)) {
            for(int i=0;reportMedias.size()<=i;i++){
                reportMedias.get(i).setrId(report.getrId());
            }
            int flag1 = rMediaService.RMediaAdd(reportMedias);
            if (flag1 == 0) {
                throw new SbException(100, "反馈媒体添加失败!");
            }
        }
        return ReturnMessageUtil.sucess(true);
    }
}

