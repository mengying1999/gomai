package com.gomai.feedback.controller;


import com.gomai.feedback.pojo.Feedback;
import com.gomai.feedback.pojo.FeedbackMedia;
import com.gomai.feedback.service.FMediaService;
import com.gomai.feedback.service.FeedbackService;
import com.gomai.feedback.vo.F_FMdia;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FMediaService fMediaService;

    /**
     * 将反馈信息插入到反馈表Feedback和媒体表FeedbackMedia
     *
     * @return
     */
    @PostMapping("/AddFeedback")
    @Transactional
    public ReturnMessage<Object> AddFeedback(@RequestBody F_FMdia f_FMdia) {
        Feedback feedback = f_FMdia.getFeedback();
        List<FeedbackMedia> feedbackMedias = f_FMdia.getFeedbackMedia();
        //        1. 判断feedback 是否为空  2. 判断uId是否为空
        if (StringUtils.isEmpty(feedback) && (feedback.getuId() == null || feedback.getuId() < 0)) {
            throw new SbException(400, "输入不合法");
        }
        //          2.判断反馈内容是否为空
        if (StringUtils.isEmpty(feedback.getfContent())) {
            throw new SbException(100, "反馈信息错误!");
        }
        //          3.数据操作
        int flag = feedbackService.FeedbackAdd(feedback);
        if (flag == 0) {
            throw new SbException(100, "反馈信息添加失败!");
        }

        if (!StringUtils.isEmpty(feedbackMedias)) {
            for(int i=0;feedbackMedias.size()<=i;i++){
                feedbackMedias.get(i).setfId(feedback.getfId());
            }
            int flag1 = fMediaService.FMediaAdd(feedbackMedias);
            if (flag1 == 0) {
                throw new SbException(100, "反馈媒体添加失败!");
            }
        }
        return  ReturnMessageUtil.sucess(true);
    }
}


