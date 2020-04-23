package com.gomai.comment.pojo;

import javax.persistence.*;

/**
 *
 * 评论媒体表
 */
@Entity
@Table(name = "oder_evaluation_media")
public class OrderEvaluationMedia {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "oem_id")
    private Integer oemId; //主键
    @Column(name = "o_id")
    private Integer oId; //用户id
    @Column(name = "oem_url")
    private String oemUrl;  //url
    @Column(name = "oem_type")
    private Integer oemType;  //url

    public OrderEvaluationMedia() {
    }

    public OrderEvaluationMedia(Integer oId, String oemUrl, Integer oemType) {
        this.oId = oId;
        this.oemUrl = oemUrl;
        this.oemType = oemType;
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

    public Integer getOemType() {
        return oemType;
    }

    public void setOemType(Integer oemType) {
        this.oemType = oemType;
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

