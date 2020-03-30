package com.gomai.order.controller;

import com.gomai.goods.pojo.Category1;
import com.gomai.order.service.OAddressService;
import com.gomai.user.pojo.UserAddress;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oAddress")
public class OAddressController {
    @Autowired
    private OAddressService oAddressService;
    /**
     * 根据UId查询该用户的所有地址
     * @return
     */
    @GetMapping("/queryUserAddressByUId/{uId}")
    public ReturnMessage<Object> queryUserAddressByUId(@PathVariable("uId") Integer uId){
        if(uId == null || uId <0){
            throw new SbException(400, "不合法字符");
        }
        List<UserAddress> list = this.oAddressService.queryUserAddressByUId(uId);
        return ReturnMessageUtil.sucess(list);
    }
}

