package com.gomai.feedback.pojo;

import javax.persistence.*;

@Entity
@Table(name = "feedback_media")
public class FeedbackMedia {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "fm_id")
    private Integer fmId; //主键
    @Column(name = "f_id")
    private Integer fId; //反馈表id
    @Column(name = "fm_url")
    private String fmUrl; //反馈url
    @Column(name = "fm_type")
    private Integer fmType; //媒体的类型
    public FeedbackMedia() {
    }

    public FeedbackMedia(Integer fId, String fmUrl, Integer fmType) {
        this.fId = fId;
        this.fmUrl = fmUrl;
        this.fmType = fmType;
    }

    public Integer getFmId() {
        return fmId;
    }

    public void setFmId(Integer fmId) {
        this.fmId = fmId;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getFmUrl() {
        return fmUrl;
    }

    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl;
    }

    public Integer getFmType() {
        return fmType;
    }

    public void setFmType(Integer fmType) {
        this.fmType = fmType;
    }

    @Override
    public String toString() {
        return "FeedbackMedia{" +
                "fmId=" + fmId +
                ", fId=" + fId +
                ", fmUrl='" + fmUrl + '\'' +
                ", fmType='" + fmType + '\'' +
                '}';
    }
}
