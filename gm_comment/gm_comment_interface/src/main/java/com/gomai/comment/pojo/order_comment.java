package com.gomai.comment.pojo;

import javax.persistence.*;

/**
 *
 * 评论表
 */
@Entity
@Table(name = "order_comment")
public class order_comment {
    @Id
    @GeneratedValue
    @Column(name = "oc_id")
    private Integer ocId; //主键
    @Column(name = "o_id")
    private String oId; //订单表
    @Column(name = "u_id")
    private String uId;  //用户id
    @Column(name = "oc_content")
    private Boolean ocContent; // 评论

    public order_comment() {
    }

    public order_comment(Integer ocId, String oId, String uId, Boolean ocContent) {
        this.ocId = ocId;
        this.oId = oId;
        this.uId = uId;
        this.ocContent = ocContent;
    }

    public Integer getOcId() { return ocId; }

    public void setOcId(Integer ocId) { this.ocId = ocId; }

    public String getoId() { return oId; }

    public void setoId(String oId) { this.oId = oId; }

    public String getuId() { return uId; }

    public void setuId(String uId) { this.uId = uId; }

    public Boolean getOcContent() { return ocContent; }

    public void setOcContent(Boolean ocContent) { this.ocContent = ocContent; }


    @Override
    public String toString() {
        return "order_comment{" +
                "ocId=" + ocId +
                ", oId='" + oId + '\'' +
                ", uId='" + uId + '\'' +
                ", ocContent=" + ocContent +
                '}';
    }
}

