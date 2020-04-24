package com.gomai.user.controller;

import com.gomai.user.pojo.User;
import com.gomai.user.pojo.UserAddress;
import com.gomai.user.service.AddressService;
import com.gomai.user.service.UserService;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;

    /**
     * 1.验证userAddress是否为空
     * 2.查询用户是否存在
     *3.判断收货人姓名，地址是否为空
     * 4.插入
     * 5.返回
     */

    @PostMapping("/InsertAd")
    public ReturnMessage<Object> InsertAd(@RequestBody UserAddress userAddress){

        if (StringUtils.isEmpty(userAddress)) {
            throw new SbException(400, "输入不合法");
        }
        int uid=userAddress.getuId();
        User user1=this.userService.selectUserByUid(uid);
        if(StringUtils.isEmpty(user1)){
            throw new SbException(400, "不存在该用户");
        }
        if (StringUtils.isEmpty(userAddress.getUaSigner())||(StringUtils.isEmpty(userAddress.getUaAddress()))){
            throw new SbException(400, "输入不合法");
        }
        int flag=this.addressService.insertad(userAddress);
        if(flag==0){
            throw new SbException(100, "新增地址失败");
        }

        return ReturnMessageUtil.sucess(true);
    }
    /**
     * 1.验证userAddress是否为空
     * 2.查询用户是否存在
     *3.判断收货人姓名，地址是否为空
     * 4.更新
     * 5.返回
     */

    @PostMapping("/EditAd")
    public ReturnMessage<Object> EditAd(@RequestBody UserAddress userAddress){

        if (StringUtils.isEmpty(userAddress)) {
            throw new SbException(400, "输入不合法");
        }
        int uid=userAddress.getuId();
        User user1=this.userService.selectUserByUid(uid);
        if(StringUtils.isEmpty(user1)){
            throw new SbException(400, "不存在该用户");
        }
        if (StringUtils.isEmpty(userAddress.getUaSigner())||(StringUtils.isEmpty(userAddress.getUaAddress()))){
            throw new SbException(400, "输入不合法");
        }
        int flag=this.addressService.updatead(userAddress);
        if(flag==0){
            throw new SbException(100, "修改失败");
        }

        return ReturnMessageUtil.sucess(true);
    }
    /**
     * 1.验证userAddress是否为空
     * 2.查询用户是否存在
     *3.判断收货人姓名，地址是否为空
     * 4.更新
     * 5.返回
     */

    @PostMapping("/DelAd/{uaId}")
    public ReturnMessage<Object> DelAd(@PathVariable() Integer uaId){

        if (StringUtils.isEmpty(uaId)||uaId==0) {
            throw new SbException(400, "输入不合法");
        }
        UserAddress userAddress=this.addressService.selectUserByUaId(uaId);
        if(StringUtils.isEmpty(userAddress)){
            throw new SbException(400, "不存在该地址");
        }
        int flag=this.addressService.delad(userAddress);
        if(flag==0){
            throw new SbException(100, "删除失败");
        }

        return ReturnMessageUtil.sucess(true);
    }
    /**
     * 验证uId是否为空
     * 查询用户是否存在
     * 获取数据
     * 返回
     */

    @GetMapping("/SelADs/{uId}")
    public ReturnMessage<Object> SelADs(@PathVariable() Integer uId){

        if (StringUtils.isEmpty(uId)||uId==0) {
            throw new SbException(400, "输入不合法");
        }
        User user=this.userService.selectUserByUid(uId);
        if(StringUtils.isEmpty(user)){
            throw new SbException(400, "不存在该用户");
        }
        UserAddress userAddress=new UserAddress();
        userAddress.setuId(uId);
        List<UserAddress> userAddresses=this.addressService.selectAdByUid(userAddress);
        return ReturnMessageUtil.sucess(userAddresses);
    }

    /**
     * 验证uId是否为空
     * 查询用户是否存在
     * 获取数据
     * 返回
     */

    @GetMapping("/SelADByUaId/{uaId}")
    public ReturnMessage<Object> SelADByUaId(@PathVariable() Integer uaId){

        if (StringUtils.isEmpty(uaId)||uaId==0) {
            throw new SbException(400, "输入不合法");
        }
        UserAddress userAddresse=this.addressService.selectUserByUaId(uaId);
        return ReturnMessageUtil.sucess(userAddresse);
    }
}
