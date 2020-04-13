package com.gomai.integral.service;

import com.gomai.user.pojo.User;
import org.springframework.stereotype.Service;

public interface IEUserService {
    public User selectUserByUid(Integer uid);
}
