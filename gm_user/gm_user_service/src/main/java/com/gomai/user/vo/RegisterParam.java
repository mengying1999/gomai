package com.gomai.user.vo;

import javax.validation.constraints.Pattern;

public class RegisterParam {
    private String uName;
    private String uPassword;
    private String confirmUPassword;
    private String uPhone;
    private String code;


    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getConfirmUPassword() {
        return confirmUPassword;
    }

    public void setConfirmUPassword(String confirmUPassword) {
        this.confirmUPassword = confirmUPassword;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RegisterParam{" +
                "uName='" + uName + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", confirmUPassword='" + confirmUPassword + '\'' +
                ", uPhone='" + uPhone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
