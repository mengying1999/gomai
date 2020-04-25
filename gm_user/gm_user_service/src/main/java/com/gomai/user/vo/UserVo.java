package com.gomai.user.vo;

import com.gomai.user.pojo.User;

public class UserVo {
    private User user;
    private String pw;
    private String code;
    private String repw;
    public UserVo() {
    }

    public UserVo(User user, String pw, String code, String repw) {
        this.user = user;
        this.pw = pw;
        this.code = code;
        this.repw = repw;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRepw() {
        return repw;
    }

    public void setRepw(String repw) {
        this.repw = repw;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "user=" + user +
                ", pw='" + pw + '\'' +
                ", code='" + code + '\'' +
                ", repw='" + repw + '\'' +
                '}';
    }
}
