package com.gomai.comment.pojo;

import javax.persistence.*;

/**
 *
 * 评论表
 */
@Entity
@Table(name = "order_comment")
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

    public OrderComment() {
    }

    public OrderComment(Integer oId, Integer uId, String ocContent) {
        this.oId = oId;
        this.uId = uId;
        this.ocContent = ocContent;
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

    @Override
    public String toString() {
        return "OrderComment{" +
                "ocId=" + ocId +
                ", oId=" + oId +
                ", uId=" + uId +
                ", ocContent='" + ocContent + '\'' +
                '}';
    }
}

