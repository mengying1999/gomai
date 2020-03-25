package com.gomai.comment.pojo;

import javax.persistence.*;

/**
 * 评论媒体表
 */
@Entity
@Table(name = "oder_evaluation_media")
public class oder_evaluation_media {
    @Id
    @GeneratedValue
    @Column(name = "oem_id")
    private Integer oemId; //主键
    @Column(name = "o_id")
    private String oId; //用户id
    @Column(name = "oem_url")
    private String oemUrl;  //url

    public oder_evaluation_media() {
    }

    public oder_evaluation_media(Integer oemId, String oId, String oemUrl) {
        this.oemId = oemId;
        this.oId = oId;
        this.oemUrl = oemUrl;
    }

    public Integer getOemId() {
        return oemId;
    }

    public void setOemId(Integer oemId) {
        this.oemId = oemId;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
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
        return "oder_evaluation_media{" +
                "oemId=" + oemId +
                ", oId='" + oId + '\'' +
                ", oemUrl='" + oemUrl + '\'' +
                '}';
    }
}

