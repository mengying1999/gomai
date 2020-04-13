package com.gomai.integral.service.impl;

import com.gomai.integral.mapper.IEUserMapper;
import com.gomai.integral.service.IEUserService;
import com.gomai.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IEUserServiceImpl implements IEUserService {
    @Autowired
    private  IEUserMapper iEUserMapper;
    @Override
    public User selectUserByUid(Integer uId) {
        User user=iEUserMapper.selectByPrimaryKey(uId);
        return user;
    }
}
