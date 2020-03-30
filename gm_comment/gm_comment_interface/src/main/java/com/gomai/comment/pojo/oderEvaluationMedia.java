package com.gomai.comment.pojo;

import javax.persistence.*;

/**
 *
 * 评论媒体表
 */
@Entity
@Table(name = "oderEvaluationMedia")
public class oderEvaluationMedia {
    @Id
    @GeneratedValue
    @Column(name = "oem_id")
    private Integer oemId; //主键
    @Column(name = "o_id")
    private Integer oId; //用户id
    @Column(name = "oem_url")
    private String oemUrl;  //url

    public oderEvaluationMedia() {
    }

    public oderEvaluationMedia(Integer oId, String oemUrl) {
        this.oId = oId;
        this.oemUrl = oemUrl;
    }

    public Integer getOemId() {
        return oemId;
    }

    public void setOemId(Integer oemId) {
        this.oemId = oemId;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getOemUrl() {
        return oemUrl;
    }

    public void setOemUrl(String oemUrl) {
        this.oemUrl = oemUrl;
    }

    @Override
    public String toString() {
        return "oderEvaluationMedia{" +
                "oemId=" + oemId +
                ", oId=" + oId +
                ", oemUrl='" + oemUrl + '\'' +
                '}';
    }
}

