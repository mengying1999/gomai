package com.gomai.user.service.impl;


import com.gomai.user.mapper.UserMapper;
import com.gomai.user.pojo.User;
import com.gomai.user.service.UserService;
import com.gomai.utils.CodecUtils;
import com.gomai.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String slat = "&%1A2Asc*&%$$#@";

    static final Logger logger = LoggerFactory.getLogger(UserService.class);

    static final String KEY_PREFIX = "user:code:phone:";

    public User selectUserByUid(Integer uId) {
        User user=this.userMapper.selectByPrimaryKey(uId);
        return user;
    }

    @Override
    public int updateuser(User user) {
        user.setuPassword(CodecUtils.md5Hex(user.getuPassword(), slat));
        int flag=this.userMapper.updateByPrimaryKey(user);
        return flag;
    }

    @Override
    public User selectUserByuName(String uName) {
        User user=new User();
        user.setuName(uName);
        User user1=this.userMapper.selectOne(user);
        return user1;
    }

    @Override
    public int insetrtuser(User user) {
        int flag=this.userMapper.insert(user);

        return flag;
    }



    public String sendVerifyCode(String phone) {
        // 生成验证码
        String code = NumberUtils.generateCode(6);
//        加入redis ，设置5分钟过期
        this.redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);
        return code;
    }


    public Boolean register(User user, String code) {
        // 校验短信验证码
        String cacheCode = this.redisTemplate.opsForValue().get(KEY_PREFIX + user.getuPhone());

        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(cacheCode)){
            return false;
        }
        if (!code.equals(cacheCode)){
            return false;
        }
        // 对密码加密
        user.setuPassword(CodecUtils.md5Hex(user.getuPassword(), slat));
        user.setuTotalIntegral(3000);
        // 强制设置不能指定的参数为null
        user.setuId(null);
        // 添加到数据库
        boolean b = this.userMapper.insertSelective(user) == 1;
        if(b){
            // 注册成功，删除redis中的记录
            this.redisTemplate.delete(KEY_PREFIX + user.getuPhone());
        }
        return b;
    }

    public Boolean checkData(String data, Integer type) {
        User record = new User();
        switch (type) {
            case 1:
                record.setuName(data);
                break;
            case 2:
                record.setuPhone(data);
                break;
            default:
                return null;
        }
        return this.userMapper.selectCount(record) == 0;
    }


    @Override
    public User queryBy(String uName, String uPassword) {
        //根据用户名查询
        User temp = new User();
        temp.setuName(uName);
        temp.setuPassword(uPassword);
        System.out.println(temp);
        String newPassword = CodecUtils.md5Hex(uPassword, slat);
//        if (newPassword.equals(user.getuPassword())) {
//            return user;
//        }
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uName", uName);
        criteria.andEqualTo("uPassword", newPassword);
        User user = userMapper.selectOneByExample(example);
        System.out.println(user);
        if (user == null) {
            return null;
        }
        return user;
    }
}
