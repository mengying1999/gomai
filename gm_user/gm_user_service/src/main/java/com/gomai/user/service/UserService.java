package com.gomai.user.service;

import com.gomai.user.pojo.User;

public interface UserService {
    public User selectUserByUid(Integer uid);

    public int updateuser(User user);

    public User selectUserByuName(String uName);

    public int insetrtuser(User user);
    public String sendVerifyCode(String phone);
    public Boolean register(User user, String code);
    public Boolean checkData(String data, Integer type);
    public User queryBy(String uName, String uPassword);
}
