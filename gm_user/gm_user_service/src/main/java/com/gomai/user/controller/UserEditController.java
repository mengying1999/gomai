package com.gomai.user.controller;

import com.gomai.user.pojo.User;
import com.gomai.user.service.UserService;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/UserEdit")
public class UserEditController {
    @Autowired
    private UserService userService;

    /**
     * 1.验证user是否为空
     * 2.查询该类型是否存在
     *
     * 4.更新
     * 5.返回
     */
    @RequestMapping("Edit")
    public ReturnMessage<Object> Edit(@RequestBody User user){

        if (StringUtils.isEmpty(user)) {
            throw new SbException(400, "输入不合法");
        }
        int uid=user.getuId();
        User user1=this.userService.selectUserByUid(uid);
        if(StringUtils.isEmpty(user1)){
            throw new SbException(400, "不存在该用户");
        }

            else System.out.println("修改失败！");
        return ReturnMessageUtil.sucess(true);
    }
}
