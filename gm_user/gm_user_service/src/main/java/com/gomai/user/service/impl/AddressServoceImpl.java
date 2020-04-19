package com.gomai.user.service.impl;

import com.gomai.user.mapper.AddressMapper;
import com.gomai.user.pojo.UserAddress;
import com.gomai.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressServoceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public int insertad(UserAddress userAddress) {
        int flag=this.addressMapper.insert(userAddress);
        return flag;
    }

    @Override
    public int updatead(UserAddress userAddress) {
        int flag=this.addressMapper.updateByPrimaryKey(userAddress);
        return flag;
    }

    @Override
    public UserAddress selectUserByUaId(Integer uaId) {
        UserAddress userAddress=this.addressMapper.selectByPrimaryKey(uaId);
        return userAddress;
    }

    @Override
    public int delad(UserAddress userAddress) {
        int flag=this.addressMapper.delete(userAddress);
        return flag;
    }
}
