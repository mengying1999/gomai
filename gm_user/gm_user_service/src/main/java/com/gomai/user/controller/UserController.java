package com.gomai.user.controller;

import com.gomai.user.pojo.User;
import com.gomai.user.service.UserService;
import com.gomai.user.vo.RegisterParam;
import com.gomai.user.vo.UserVo;
import com.gomai.utils.CodecUtils;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    static final String KEY_PREFIX = "user:code:phone:";
    /**
     * 1.验证user是否为空
     * 2.查询该类型是否存在
     *
     * 4.更新
     * 5.返回
     */

    @PostMapping("/Edit")
    public ReturnMessage<Object> Edit(@RequestBody UserVo userVo){
        System.out.println(userVo);
        User user=userVo.getUser();
        if (StringUtils.isEmpty(user.getuPhone())||StringUtils.isEmpty(user.getuId())||StringUtils.isEmpty(userVo.getRepw())||StringUtils.isEmpty(userVo.getPw())) {
            throw new SbException(400, "输入不合法");
        }
        int uid=user.getuId();
        User user1=this.userService.selectUserByUid(uid);
        if(StringUtils.isEmpty(user1)){
            throw new SbException(400, "不存在该用户");
        }
        if (!user.getuName().equals(user1.getuName())) {
            if (!this.userService.checkData(user.getuName(), 1)) {
                throw new SbException(100, "该用户名已注册！");
            }
        }
        if (!userVo.getRepw().equals(userVo.getPw())){
            throw new SbException(100, "两次密码不一致！");
        }
        if (!user.getuPhone().equals(user1.getuPhone())) {
            String cacheCode = this.redisTemplate.opsForValue().get(KEY_PREFIX + user.getuPhone());
            if (StringUtils.isEmpty(userVo.getCode()) || StringUtils.isEmpty(cacheCode)){
                throw new SbException(100, "验证码错误");
            }
            if (!userVo.getCode().equals(cacheCode)){
                throw new SbException(100, "验证码错误");
            }
            if (!this.userService.checkData(user.getuPhone(), 2)) {
                throw new SbException(100, "该手机号已注册！");
            }
        }
        String expression = "((^((0\\d{2,3})-)(\\d{7,8})(-(\\d{3,}))?$)|(^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7])|(17[0,1,3,5-8]))\\d{8}$))";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(user.getuPhone());
        if(!matcher.matches()){
            throw new SbException(100, "手机号格式不正确！");
        }
        user.setuPassword(userVo.getPw());
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
//    @PostMapping("/registry")
//    public ReturnMessage<Object> registry(@RequestBody User user){
//
//        if (StringUtils.isEmpty(user)) {
//            throw new SbException(400, "输入不合法");
//        }
//        User user1=this.userService.selectUserByuName(user.getuName());
//        if (!StringUtils.isEmpty(user)) {
//            throw new SbException(400, "该用户名已被注册");
//        }
//        int flag=this.userService.insetrtuser(user);
//        if(flag==0){
//            throw new SbException(100, "注册失败");
//        }
//        return ReturnMessageUtil.sucess(true);
//    }


    @GetMapping("check/{data}/{type}")
    public ReturnMessage<Object> checkUserData(@PathVariable("data") String data, @PathVariable(value = "type") Integer type) {
        Boolean boo = this.userService.checkData(data, type);
        if (boo == null || boo == false) {
            return ReturnMessageUtil.sucess(false);
        }
        return ReturnMessageUtil.sucess(true);
    }

    //用户注册：参数：uName，uPassword，confirmUPassword，u_phone，code
    @Transactional
    @PostMapping("register")
    public ReturnMessage<Object> register(@RequestBody RegisterParam registerParam) {
        if (StringUtils.isEmpty(registerParam)){
            throw new SbException(100, "参数错误！");
        }
        if (StringUtils.isEmpty(registerParam.getCode()) || StringUtils.isEmpty(registerParam.getConfirmUPassword()) ||StringUtils.isEmpty(registerParam.getuName()) ||StringUtils.isEmpty(registerParam.getuPassword()) ||StringUtils.isEmpty(registerParam.getuPhone()) ){
            throw new SbException(100, "参数错误！");
        }
        if (!registerParam.getConfirmUPassword().equals(registerParam.getuPassword())){
            throw new SbException(100, "两次密码不一致！");
        }
        if (!registerParam.getConfirmUPassword().equals(registerParam.getuPassword())){
            throw new SbException(100, "两次密码不一致！");
        }
        String expression = "((^((0\\d{2,3})-)(\\d{7,8})(-(\\d{3,}))?$)|(^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7])|(17[0,1,3,5-8]))\\d{8}$))";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(registerParam.getuPhone());
        if (!matcher.matches()){
            throw new SbException(100, "手机号格式不正确！");
        }
        if (!this.userService.checkData(registerParam.getuName(), 1)){
            throw new SbException(100, "该用户已注册！");
        }
        if (!this.userService.checkData(registerParam.getuPhone(), 2)){
            throw new SbException(100, "该手机号已注册！");
        }
        User user = new User();
        user.setuName(registerParam.getuName());
        user.setuPhone(registerParam.getuPhone());
        user.setuPassword(registerParam.getuPassword());
        boolean flag = userService.register(user,registerParam.getCode());
        if (!flag){
            throw new SbException(100, "注册失败！");
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



    /**
     * 发送手机验证码
     * @param uPhone
     * @return
     */
    @PostMapping("/code/{uPhone}")
    public ReturnMessage<Object> sendVerifyCode(@PathVariable("uPhone") String uPhone) {
        String code = this.userService.sendVerifyCode(uPhone);
        if (StringUtils.isEmpty(code)){
            throw new SbException(400, "请求验证码错误");
        }
        return ReturnMessageUtil.sucess(code);
    }

    @GetMapping("/query")
    public User queryBy(@RequestParam("uName") String uName, @RequestParam("uPassword") String uPassword) {
        System.out.println(uName);
        User user = userService.queryBy(uName,uPassword);
        System.out.println(user);
//        if (user == null) {
//            return ResponseEntity.badRequest().build();
//        }
        return user;
    }

}
