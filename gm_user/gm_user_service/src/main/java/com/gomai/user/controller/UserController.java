package com.gomai.user.controller;

import com.gomai.user.pojo.User;
import com.gomai.user.service.UserService;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 1.验证user是否为空
     * 2.查询该类型是否存在
     *
     * 4.更新
     * 5.返回
     */

    @PostMapping("/Edit")
    public ReturnMessage<Object> Edit(@RequestBody User user){

        if (StringUtils.isEmpty(user)) {
            throw new SbException(400, "输入不合法");
        }
        int uid=user.getuId();
        User user1=this.userService.selectUserByUid(uid);
        if(StringUtils.isEmpty(user1)){
            throw new SbException(400, "不存在该用户");
        }
        if(user.getuName().length()!=11){
            throw new SbException(400, "非法电话号码");
        }
        int flag=this.userService.updateuser(user);
        if(flag==0){
            throw new SbException(100, "更新失败");
        }
        return ReturnMessageUtil.sucess(true);
    }
    /**
     * 1.验证user是否为空
     * 2.检查 u_name是否已被注册过
     *
     * 4.更新
     * 5.返回
     */
    @PostMapping("/registry")
    public ReturnMessage<Object> registry(@RequestBody User user){

        if (StringUtils.isEmpty(user)) {
            throw new SbException(400, "输入不合法");
        }
        User user1=this.userService.selectUserByuName(user.getuName());
        if (!StringUtils.isEmpty(user)) {
            throw new SbException(400, "该用户名已被注册");
        }
        int flag=this.userService.insetrtuser(user);
        if(flag==0){
            throw new SbException(100, "注册失败");
        }
        return ReturnMessageUtil.sucess(true);
    }
    /**
     * 1.验证uId是否为空
     * 2.检查用户是否存在
     *
     * 
     * 5.返回
     */
    @GetMapping("/selectByuid/{uId}")
    public ReturnMessage<Object> selectByuid(@PathVariable("uId")Integer uId){

        if (StringUtils.isEmpty(uId)||uId==0) {
            throw new SbException(400, "输入不合法");
        }
        User user=this.userService.selectUserByUid(uId);
        if (StringUtils.isEmpty(user)) {
            throw new SbException(400, "该用户不存在");
        }
        return ReturnMessageUtil.sucess(user);
    }
}
