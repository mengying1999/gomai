package com.gomai.feedback.pojo;
import javax.persistence.*;
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "f_id")
    private Integer fId; //主键
    @Column(name = "u_id")
    private Integer uId; //用户id
    @Column(name = "f_content")
    private String fContent; //反馈内容
    @Column(name = "f_status")
    private Integer fStatus; //审核状态
    public Feedback() {
    }

    public Feedback(Integer uId, String fContent, Integer fStatus) {
        this.uId = uId;
        this.fContent = fContent;
        this.fStatus = fStatus;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent;
    }

    public Integer getfStatus() {
        return fStatus;
    }

    public void setfStatus(Integer fStatus) {
        this.fStatus = fStatus;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "fId=" + fId +
                ", uId=" + uId +
                ", fContent='" + fContent + '\'' +
                ", fStatus=" + fStatus +
                '}';
    }
}
