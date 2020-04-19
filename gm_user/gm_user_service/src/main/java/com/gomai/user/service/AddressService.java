package com.gomai.user.service;

import com.gomai.user.pojo.UserAddress;

public interface AddressService {
    public int insertad(UserAddress userAddress);

    public int updatead(UserAddress userAddress);

    public UserAddress selectUserByUaId(Integer uaId);

    public int delad(UserAddress userAddress);
}
