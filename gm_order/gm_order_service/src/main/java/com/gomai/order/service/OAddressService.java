package com.gomai.order.service;

import com.gomai.order.mapper.OAddressMapper;
import com.gomai.user.pojo.User;
import com.gomai.user.pojo.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OAddressService {

    public List<UserAddress> queryUserAddressByUId(Integer uId);

    public UserAddress queryUserAddressByUaId(Integer uaId);


}
