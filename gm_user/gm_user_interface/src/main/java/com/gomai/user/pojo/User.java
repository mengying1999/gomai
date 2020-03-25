package com.gomai.user.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户基本信息表
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "u_id")
    private Integer uId; //主键
    @Column(name = "u_name")
    private String uName; //用户名
    @Column(name = "u_password")
    private String uPassword;  //密码
    @Column(name = "u_sex")
    private Boolean uSex; // 性别
    @Column(name = "u_avatar")
    private String uAvatar;  // 头像url
    @Column(name = "u_identity")
    private String uIdentity; //身份
    @Column(name = "u_school")
    private String uSchool;  //学校
    @Column(name = "u_like")
    private String uLlike;  //爱好
    @Column(name = "u_birthday")
    private Date uBirthday;  //生日
    @Column(name = "u_phone")
    private Integer uPhone; //电话号码
    @Column(name = "u_auto_response")
    private String uAutoResponse; //自动回复
    @Column(name = "u_status")
    private Boolean uAtatus;  // 登录状态

    public User() {
    }

    public User(Integer uId, String uName, String uPassword, Boolean uSex, String uAvatar, String uIdentity, String uSchool, String uLlike, Date uBirthday, Integer uPhone, String uAutoResponse, Boolean uAtatus) {
        this.uId = uId;
        this.uName = uName;
        this.uPassword = uPassword;
        this.uSex = uSex;
        this.uAvatar = uAvatar;
        this.uIdentity = uIdentity;
        this.uSchool = uSchool;
        this.uLlike = uLlike;
        this.uBirthday = uBirthday;
        this.uPhone = uPhone;
        this.uAutoResponse = uAutoResponse;
        this.uAtatus = uAtatus;
    }

    public Integer getuId() { return uId; }

    public void setuId(Integer uId) { this.uId = uId; }

    public String getuName() { return uName; }

    public void setuName(String uName) { this.uName = uName; }

    public String getuPassword() { return uPassword; }

    public void setuPassword(String uPassword) { this.uPassword = uPassword; }

    public Boolean getuSex() { return uSex; }

    public void setuSex(Boolean uSex) { this.uSex = uSex; }

    public String getuAvatar() { return uAvatar; }

    public void setuAvatar(String uAvatar) { this.uAvatar = uAvatar; }

    public String getuIdentity() { return uIdentity; }

    public void setuIdentity(String uIdentity) { this.uIdentity = uIdentity; }

    public String getuSchool() { return uSchool; }

    public void setuSchool(String uSchool) { this.uSchool = uSchool; }

    public String getuLlike() { return uLlike; }

    public void setuLlike(String uLlike) { this.uLlike = uLlike; }

    public Date getuBirthday() { return uBirthday; }

    public void setuBirthday(Date uBirthday) { this.uBirthday = uBirthday; }

    public Integer getuPhone() { return uPhone; }

    public void setuPhone(Integer uPhone) { this.uPhone = uPhone; }

    public String getuAutoResponse() { return uAutoResponse; }

    public void setuAutoResponse(String uAutoResponse) { this.uAutoResponse = uAutoResponse; }

    public Boolean getuAtatus() { return uAtatus; }

    public void setuAtatus(Boolean uAtatus) { this.uAtatus = uAtatus; }

    @Override

    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", uSex=" + uSex +
                ", uAvatar='" + uAvatar + '\'' +
                ", uIdentity='" + uIdentity + '\'' +
                ", uSchool='" + uSchool + '\'' +
                ", uLlike='" + uLlike + '\'' +
                ", uBirthday=" + uBirthday +
                ", uPhone=" + uPhone +
                ", uAutoResponse='" + uAutoResponse + '\'' +
                ", uAtatus=" + uAtatus +
                '}';
    }
}
