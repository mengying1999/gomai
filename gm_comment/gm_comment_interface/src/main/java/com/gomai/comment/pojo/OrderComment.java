package com.gomai.comment.pojo;

import javax.persistence.*;

/**
 *
 * 评论表
 */
@Entity
@Table(name = "orderComment")
public class OrderComment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "oc_id")
    private Integer ocId; //主键
    @Column(name = "o_id")
    private Integer oId; //订单表
    @Column(name = "u_id")
    private Integer uId;  //用户id
    @Column(name = "oc_content")
    private String ocContent; // 评论
    @Column(name = "oc_time")
    private String ocTime;

    public OrderComment() {
    }

    public OrderComment(Integer oId, Integer uId, String ocContent, String ocTime) {
        this.oId = oId;
        this.uId = uId;
        this.ocContent = ocContent;
        this.ocTime = ocTime;
    }

    public Integer getOcId() {
        return ocId;
    }

    public void setOcId(Integer ocId) {
        this.ocId = ocId;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getOcContent() {
        return ocContent;
    }

    public void setOcContent(String ocContent) {
        this.ocContent = ocContent;
    }

    public String getOcTime() {
        return ocTime;
    }

    public void setOcTime(String ocTime) {
        this.ocTime = ocTime;
    }

    @Override
    public String toString() {
        return "OrderComment{" +
                "ocId=" + ocId +
                ", oId=" + oId +
                ", uId=" + uId +
                ", ocContent='" + ocContent + '\'' +
                ", ocTime='" + ocTime + '\'' +
                '}';
    }
}

