package com.gomai.order.service.impl;

import com.gomai.order.mapper.OUserMapper;

import com.gomai.order.service.OUserService;
import com.gomai.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OUserServiceImpl implements OUserService {
    @Autowired
    private OUserMapper oUserMapper;
    @Override
    public User queryUserByUId(Integer uId) {
        return this.oUserMapper.selectByPrimaryKey(uId);
    }
}
