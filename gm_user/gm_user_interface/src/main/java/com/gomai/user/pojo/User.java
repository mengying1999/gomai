package com.gomai.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 用户基本信息表
 */
//用户表
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Integer uId; //主键
    @Column(name = "u_name")
    private String uName; //用户名
    @JsonIgnore //在对象系列化json字符串时，忽略该属性
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
    private String uLike;  //爱好
    @Column(name = "u_birthday")
    private Date uBirthday;  //生日
    @Column(name = "u_phone")
    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式不正确")
    private String uPhone; //电话号码
    @Column(name = "u_auto_response")
    private String uAutoResponse; //自动回复
    @Column(name = "u_status")
    private Boolean uStatus;  // 登录状态
    @Column(name = "u_total_integral")
    private int uTotalIntegral;  // 个人积分总数

    public User() {
    }

    public User(String uName, String uPassword, Boolean uSex, String uAvatar, String uIdentity, String uSchool, String uLike, Date uBirthday, String uPhone, String uAutoResponse, Boolean uStatus, int uTotalIntegral) {
        this.uName = uName;
        this.uPassword = uPassword;
        this.uSex = uSex;
        this.uAvatar = uAvatar;
        this.uIdentity = uIdentity;
        this.uSchool = uSchool;
        this.uLike = uLike;
        this.uBirthday = uBirthday;
        this.uPhone = uPhone;
        this.uAutoResponse = uAutoResponse;
        this.uStatus = uStatus;
        this.uTotalIntegral = uTotalIntegral;
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

    public String getuLike() { return uLike; }

    public void setuLike(String uLlike) { this.uLike = uLlike; }

    public Date getuBirthday() { return uBirthday; }

    public void setuBirthday(Date uBirthday) { this.uBirthday = uBirthday; }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuAutoResponse() { return uAutoResponse; }

    public void setuAutoResponse(String uAutoResponse) { this.uAutoResponse = uAutoResponse; }

    public Boolean getuStatus() {
        return uStatus;
    }

    public void setuStatus(Boolean uStatus) {
        this.uStatus = uStatus;
    }

    public int getuTotalIntegral() {
        return uTotalIntegral;
    }

    public void setuTotalIntegral(int uTotalIntegral) {
        this.uTotalIntegral = uTotalIntegral;
    }

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
                ", uLike='" + uLike + '\'' +
                ", uBirthday=" + uBirthday +
                ", uPhone='" + uPhone + '\'' +
                ", uAutoResponse='" + uAutoResponse + '\'' +
                ", uStatus=" + uStatus +
                ", uTotalIntegral=" + uTotalIntegral +
                '}';
    }
}
