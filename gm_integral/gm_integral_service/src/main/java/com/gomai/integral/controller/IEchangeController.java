package com.gomai.integral.controller;

import com.gomai.integral.service.IEUserService;
import com.gomai.integral.service.IExchangeService;
import com.gomai.integral.vo.GoodsVo;
import com.gomai.integral.vo.IChangeVo;
import com.gomai.intergral.pojo.IntegralExchange;
import com.gomai.intergral.pojo.IntegralGoods;
import com.gomai.user.pojo.User;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Feedback")
public class IEchangeController {
    @Autowired
    private IExchangeService iExchangeService;
    @Autowired
    private IEUserService iEUserService;

    /**
     *
     * 1.验证uid和type合法
     * 2.查询uid是否存在
     * 3.type=1时，查询全部。type=2时，查询增加（买商品即数据库中ie_type=2），type=3时，查询减少（买积分商品（ie_type=1）和退商品（ie_type=3））
     * 4.查询
     * 5.返回
     */
    @GetMapping("/SelectBytype/{type}/{uid}")
    public ReturnMessage<Object> SelectBytype(@PathVariable("type")Integer type,@PathVariable("uid")Integer uid ) {
        //验证uid和type合法
        if((StringUtils.isEmpty(type) || type < 0||type>3)&&(StringUtils.isEmpty(uid) || uid < 0)){
            throw new SbException(400, "不合法字符");
        }
        User user=iEUserService.selectUserByUid(uid);
        if(StringUtils.isEmpty(user)){
            throw  new  SbException(100,"非法用户");
        }
        List<Integer> types=new ArrayList<Integer>();
        if(type==1){
            types.add(1);
            types.add(2);
            types.add(3);
        }
        if(type==2){
            types.add(2);
        }
        if(type==3){
            types.add(1);
            types.add(3);
        }
        List<IChangeVo> iChangeVos=iExchangeService.selectIChangeVoByTypes(uid,types);
            return  ReturnMessageUtil.sucess(iChangeVos);
    }

    /**
     *
     * 1.判断ieId是否为空
     * 2.判断是否存在该积分明细
     * 3.执行删除
     * 4.返回
     */
    @GetMapping("/deleteByieId/{ieId}")
    public ReturnMessage<Object> deleteByieId(Integer ieId ) {
        if(StringUtils.isEmpty(ieId)||ieId<0){
            throw  new  SbException(100,"非法字符");
        }
        IntegralExchange ie=this.iExchangeService.selectByieId(ieId);
        if (StringUtils.isEmpty(ie)){
            throw  new SbException(400,"不存在该积分明细");
        }
        int flag=this.iExchangeService.deleteByieId(ieId);
        if(flag==0){
            throw  new SbException(400,"删除失败");
        }
        return ReturnMessageUtil.sucess(true);
    }
    @GetMapping("/deleteByuId/{uId}")
    public ReturnMessage<Object> deleteByuId(Integer uId ) {
        if(StringUtils.isEmpty(uId)||uId<0){
            throw  new  SbException(100,"非法字符");
        }
        User user=this.iEUserService.selectUserByUid(uId);
        if (StringUtils.isEmpty(user)){
            throw  new SbException(400,"不存在该积分明细");
        }

        int flag=this.iEUserService.deleteByuId(uId);
        if(flag==0){
            throw  new SbException(400,"删除失败");
        }
        return ReturnMessageUtil.sucess(true);
    }
}
