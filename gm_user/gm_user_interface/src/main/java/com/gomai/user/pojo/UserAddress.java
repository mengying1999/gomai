package com.gomai.user.pojo;

import javax.persistence.*;

/**
 * 用户基本信息表
 */
@Entity
@Table(name = "user_address")
public class UserAddress {
    @Id
    @GeneratedValue
    @Column(name = "ua_id")
    private Integer uAid; //主键
    @Column(name = "u_id")
    private String uId; //用户id
    @Column(name = "ua_signer")
    private String uaSigner;  //收件人姓名
    @Column(name = "ua_address")
    private Boolean uaAddress; // 地址
    @Column(name = "ua_phone")
    private String uaPhone;  // 电话号码


    public UserAddress() {
    }

    public UserAddress(Integer uAid, String uId, String uaSigner, Boolean uaAddress, String uaPhone) {
        this.uAid = uAid;
        this.uId = uId;
        this.uaSigner = uaSigner;
        this.uaAddress = uaAddress;
        this.uaPhone = uaPhone;
    }

    public Integer getuAid() { return uAid; }

    public void setuAid(Integer uAid) { this.uAid = uAid; }

    public String getuId() { return uId; }

    public void setuId(String uId) { this.uId = uId; }

    public String getUaSigner() { return uaSigner; }

    public void setUaSigner(String uaSigner) { this.uaSigner = uaSigner; }

    public Boolean getUaAddress() { return uaAddress; }

    public void setUaAddress(Boolean uaAddress) { this.uaAddress = uaAddress; }

    public String getUaPhone() { return uaPhone; }

    public void setUaPhone(String uaPhone) { this.uaPhone = uaPhone; }

    @Override
    public String toString() {
        return "UserAddress{" +
                "uAid=" + uAid +
                ", uId='" + uId + '\'' +
                ", uaSigner='" + uaSigner + '\'' +
                ", uaAddress=" + uaAddress +
                ", uaPhone='" + uaPhone + '\'' +
                '}';
    }
}
