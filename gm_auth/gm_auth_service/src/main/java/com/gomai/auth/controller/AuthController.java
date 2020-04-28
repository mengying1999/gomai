package com.gomai.auth.controller;

import com.gomai.auth.config.JwtProperties;
import com.gomai.auth.entity.UserInfo;
import com.gomai.auth.service.AuthService;
import com.gomai.auth.utils.JwtUtils;
import com.gomai.utils.CookieUtils;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProperties jwtProperties;
    /**
     * 登录：
     *      用账户登录
     */
    @PostMapping("/login/{uName}/{uPassword}")
    public ReturnMessage<Object> login(@PathVariable("uName")String uName, @PathVariable("uPassword")String uPassword,
                                       HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(uName) ||StringUtils.isEmpty(uPassword)){
            throw new SbException(100, "参数错误！");
        }
        String token = authService.login(uName,uPassword);
        if (StringUtils.isEmpty(token)) {
            throw new SbException(100, "用户名或密码错误！");
        }
        CookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getCookieMaxAge());
        return ReturnMessageUtil.sucess(true);
    }




    /**
     * 认证
     */
    @GetMapping("/verify")
    public ReturnMessage<Object> verify(@CookieValue("GM_TOKEN")String token,HttpServletRequest request, HttpServletResponse response) {
        try {
            //使用公钥解析jwt
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            //刷新jwt的有效时间
            token = JwtUtils.generateToken(userInfo,jwtProperties.getPrivateKey(),jwtProperties.getExpire());
            //刷新cookie的有效时间
            CookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getCookieMaxAge());
            return ReturnMessageUtil.sucess(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //认证失败
        return ReturnMessageUtil.error(100,"认证失败");
    }

}
