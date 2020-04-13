package com.gomai.feedback.vo;

import com.gomai.feedback.pojo.Feedback;
import com.gomai.feedback.pojo.FeedbackMedia;

import java.util.List;

public class F_FMdia {
    private Feedback feedback;
    private List<FeedbackMedia> feedbackMedia;

    public F_FMdia(Feedback feedback, List<FeedbackMedia> feedbackMedia) {
        this.feedback = feedback;
        this.feedbackMedia = feedbackMedia;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public List<FeedbackMedia> getFeedbackMedia() {
        return feedbackMedia;
    }

    public void setFeedbackMedia(List<FeedbackMedia> feedbackMedia) {
        this.feedbackMedia = feedbackMedia;
    }
}
