package com.gomai.user.controller;

import com.gomai.user.mapper.UserMapper;
import com.gomai.user.pojo.User;
import com.gomai.user.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserEditController {
    private UserService service;
    @RequestMapping("Edit")
    public String Edit(@RequestParam(name = "user") User user){

        if (service.UpdateU(user)){
            System.out.println("修改成功！");
            return "";
        }
            else System.out.println("修改失败！");
            return  "";
    }
}
