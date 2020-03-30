package com.gomai.feedback.pojo;

import javax.persistence.*;

@Entity
@Table(name = "report_media")
public class ReportMedia {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "rm_id")
    private Integer rmId; //主键
    @Column(name = "r_id")
    private Integer rId; //举报id
    @Column(name = "rm_url")
    private String rmUrl; //举报url
    @Column(name = "rm_type")
    private String rmType; //媒体的类型
    public ReportMedia() {
    }
    public ReportMedia(Integer rId, String rmUrl, String rmType) {
        this.rId = rId;
        this.rmUrl = rmUrl;
        this.rmType = rmType;
    }

    public Integer getRmId() {
        return rmId;
    }

    public void setRmId(Integer rmId) {
        this.rmId = rmId;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getRmUrl() {
        return rmUrl;
    }

    public void setRmUrl(String rmUrl) {
        this.rmUrl = rmUrl;
    }

    public String getRmType() {
        return rmType;
    }

    public void setRmType(String rmType) {
        this.rmType = rmType;
    }

    @Override
    public String toString() {
        return "ReportMedia{" +
                "rmId=" + rmId +
                ", rId=" + rId +
                ", rmUrl='" + rmUrl + '\'' +
                ", rmType='" + rmType + '\'' +
                '}';
    }
}
