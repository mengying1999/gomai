package com.gomai.auth.entity;

/**
 * 载荷对象
 */
public class UserInfo {

    private Integer uId;

    private String uName;

    public UserInfo() {
    }

    public UserInfo(Integer uId, String uName) {
        this.uId = uId;
        this.uName = uName;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                '}';
    }
}