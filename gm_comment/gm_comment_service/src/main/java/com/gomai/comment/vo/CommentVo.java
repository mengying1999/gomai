package com.gomai.comment.vo;

import com.gomai.user.pojo.User;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CommentVo {

    private Integer ocId; //主键

    private Integer oId; //订单表

    private Integer uId;  //用户id

    private String ocContent; // 评论

    private String ocTime;

    private User user;

    public CommentVo() {
    }

    public CommentVo(Integer ocId, Integer oId, Integer uId, String ocContent, String ocTime, User user) {
        this.ocId = ocId;
        this.oId = oId;
        this.uId = uId;
        this.ocContent = ocContent;
        this.ocTime = ocTime;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CommentVo{" +
                "ocId=" + ocId +
                ", oId=" + oId +
                ", uId=" + uId +
                ", ocContent='" + ocContent + '\'' +
                ", ocTime='" + ocTime + '\'' +
                ", user=" + user +
                '}';
    }
}
