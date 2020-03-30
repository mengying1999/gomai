package com.gomai.order.service.impl;

import com.gomai.order.mapper.OAddressMapper;
import com.gomai.order.service.OAddressService;
import com.gomai.user.pojo.User;
import com.gomai.user.pojo.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OAddressServiceImpl implements OAddressService {
    @Autowired
    private OAddressMapper oAddressMapper;

    public List<UserAddress> queryUserAddressByUId(Integer uId){
        UserAddress userAddress = new UserAddress();
        userAddress.setuId(uId);
        return this.oAddressMapper.select(userAddress);
    }

    public UserAddress queryUserAddressByUaId(Integer uaId){
        return this.oAddressMapper.selectByPrimaryKey(uaId);
    }
}
