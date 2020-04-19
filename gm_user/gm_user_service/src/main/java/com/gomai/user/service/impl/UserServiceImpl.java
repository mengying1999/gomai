package com.gomai.user.service.impl;


import com.gomai.user.mapper.UserMapper;
import com.gomai.user.pojo.User;
import com.gomai.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    public User selectUserByUid(Integer uId) {
        User user=this.userMapper.selectByPrimaryKey(uId);
        return user;
    }

    @Override
    public int updateuser(User user) {
        int flag=this.userMapper.updateByPrimaryKey(user);
        return flag;
    }

    @Override
    public User selectUserByuName(String uName) {
        User user=new User();
        user.setuName(uName);
        User user1=this.userMapper.selectOne(user);
        return user1;
    }

    @Override
    public int insetrtuser(User user) {
        int flag=this.userMapper.insert(user);

        return flag;
    }

}
