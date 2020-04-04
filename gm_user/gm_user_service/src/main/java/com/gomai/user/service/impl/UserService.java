package com.gomai.user.service.impl;

import com.gomai.user.mapper.UserMapper;
import com.gomai.user.pojo.User;

public class UserService {
    private UserMapper mapper; //数据库操作的DAO

    public UserMapper getMapper() {
        return mapper;
    }

    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    public User SelectUAll(int uId){
        return mapper.SelectUAll(uId);
    }

    public Boolean UpdateU(User user){
        return mapper.UpdateU(user);
    }

    public Boolean DeleteU(int uId){
        return mapper.DeleteU(uId);
    }

    public Boolean AddU(int uId){
        return mapper.AddU(uId);
    }
}
