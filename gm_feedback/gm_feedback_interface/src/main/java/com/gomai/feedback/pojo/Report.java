package com.gomai.feedback.pojo;

import javax.persistence.*;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Integer rId; //主键
    @Column(name = "u_id")
    private Integer uId; //用户id
    @Column(name = "o_id")
    private Integer oId; //订单id
    @Column(name = "r_content")
    private String rContent; //举报内容
    @Column(name = "r_status")
    private Integer rStatus; //审核状态
    public Report() {
    }
    public Report(Integer uId, Integer oId, String rContent, Integer rStatus) {
        this.uId = uId;
        this.oId = oId;
        this.rContent = rContent;
        this.rStatus = rStatus;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }

    public Integer getrStatus() {
        return rStatus;
    }

    public void setrStatus(Integer rStatus) {
        this.rStatus = rStatus;
    }

    @Override
    public String toString() {
        return "Report{" +
                "rId=" + rId +
                ", uId=" + uId +
                ", oId=" + oId +
                ", rContent='" + rContent + '\'' +
                ", rStatus=" + rStatus +
                '}';
    }
}
