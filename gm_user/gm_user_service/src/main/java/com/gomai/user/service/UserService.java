package com.gomai.user.service;

import com.gomai.user.pojo.User;

public interface UserService {
    public User selectUserByUid(Integer uid);

    public int updateuser(User user);

    public User selectUserByuName(String uName);

    public int insetrtuser(User user);
}
