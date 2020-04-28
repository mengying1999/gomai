package com.gomai.auth.service;

import com.gomai.auth.config.JwtProperties;
import com.gomai.auth.entity.UserInfo;
import com.gomai.auth.feign.UserApiFeign;
import com.gomai.auth.utils.JwtUtils;
import com.gomai.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthService {
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserApiFeign userApiFeign;
//    @Autowired
//    private AUserMapper aUserMapper;
    public String login(String uName, String uPassword) {
//        User temp = new User();
//        temp.setuName(uName);
//        temp.setuPassword(uPassword);
        User user = userApiFeign.queryBy(uName,uPassword);
//        User user = aUserMapper.selectOne(temp);
        if (user == null) {
            return null;
        }
        //生成jwtToken
        UserInfo userInfo = new UserInfo();
        userInfo.setuId(user.getuId());
        userInfo.setuName(user.getuName());
        try {
            return JwtUtils.generateToken(userInfo,jwtProperties.getPrivateKey(),jwtProperties.getExpire());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
