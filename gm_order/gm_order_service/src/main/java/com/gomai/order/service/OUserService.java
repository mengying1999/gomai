package com.gomai.order.service;

import com.gomai.user.pojo.User;
import org.springframework.stereotype.Service;

public interface OUserService {
    public User queryUserByUId(Integer uId);
}
