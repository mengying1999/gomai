package com.gomai.user.pojo;

import javax.persistence.*;

/**
 * 用户基本信息表
 */
//用户地址
@Entity
@Table(name = "user_address")
public class UserAddress {
    @Id
    @GeneratedValue
    @Column(name = "ua_id")
    private Integer uaId; //主键
    @Column(name = "u_id")
    private Integer uId; //用户id
    @Column(name = "ua_signer")
    private String uaSigner;  //收件人姓名
    @Column(name = "ua_address")
    private String uaAddress; // 地址
    @Column(name = "ua_phone")
    private Integer uaPhone;  // 电话号码


    public UserAddress() {
    }

    public UserAddress(Integer uId, String uaSigner, String uaAddress, Integer uaPhone) {
        this.uId = uId;
        this.uaSigner = uaSigner;
        this.uaAddress = uaAddress;
        this.uaPhone = uaPhone;
    }

    public Integer getUaId() {
        return uaId;
    }

    public void setUaId(Integer uaId) {
        this.uaId = uaId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getUaSigner() {
        return uaSigner;
    }

    public void setUaSigner(String uaSigner) {
        this.uaSigner = uaSigner;
    }

    public String getUaAddress() {
        return uaAddress;
    }

    public void setUaAddress(String uaAddress) {
        this.uaAddress = uaAddress;
    }

    public Integer getUaPhone() {
        return uaPhone;
    }

    public void setUaPhone(Integer uaPhone) {
        this.uaPhone = uaPhone;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "uaId=" + uaId +
                ", uId=" + uId +
                ", uaSigner='" + uaSigner + '\'' +
                ", uaAddress='" + uaAddress + '\'' +
                ", uaPhone=" + uaPhone +
                '}';
    }
}
