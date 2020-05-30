package com.gomai.integral.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gomai.integral.service.IEUserService;
import com.gomai.integral.service.IExchangeService;
import com.gomai.integral.service.IGoodsService;
import com.gomai.integral.vo.GoodsVo;
import com.gomai.integral.vo.IChangeVo;
import com.gomai.intergral.pojo.IntegralExchange;
import com.gomai.intergral.pojo.IntegralGoods;
import com.gomai.user.pojo.User;
import com.gomai.utils.PageResult;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/IEchangeController")
public class IEchangeController {
    @Autowired
    private IExchangeService iExchangeService;
    @Autowired
    private IEUserService iEUserService;
    @Autowired
    private IGoodsService iGoodsService;
    /**
     *
     * 1.验证uid和type合法
     * 2.查询uid是否存在
     * 3.type=1时，查询全部。type=2时，查询增加（买商品即数据库中ie_type=2），type=3时，查询减少（买积分商品（ie_type=1）和退商品（ie_type=3））
     * 4.查询
     * 5.返回
     */
    @GetMapping("/SelectBytype/{type}/{uid}/{size}/{currentPage}")
    public ReturnMessage<Object> SelectBytype(@PathVariable("type")Integer type,@PathVariable("uid")Integer uid,@PathVariable("size")Integer size,@PathVariable("currentPage")Integer currentPage) {
        //验证uid和type合法
        if((StringUtils.isEmpty(type) || type ==0||type>3)&&(StringUtils.isEmpty(uid) || uid ==0|| size == 0 || currentPage == 0)){
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
        PageHelper.startPage(currentPage, size);
        List<IChangeVo> iChangeVos=iExchangeService.selectIChangeVoByTypes(uid,types);
        PageResult pageResult = new PageResult();
        pageResult.setRows(iChangeVos);
        pageResult.setTotal(new PageInfo(iChangeVos).getTotal());
        ReturnMessage<Object> message = new ReturnMessage<Object>(0,"sucess",pageResult);
        System.out.println(message);
        return message;
    }
    /**
     *
     * 1.判断ieId是否为空
     * 2.判断是否存在该积分明细
     * 3.执行删除
     * 4.返回
     */
    @PostMapping("/deleteByieId/{ieId}")
    public ReturnMessage<Object> deleteByieId(@PathVariable("ieId")Integer ieId ) {
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

    @PostMapping("/deleteByuId/{uId}")
    public ReturnMessage<Object> deleteByuId(@PathVariable("uId")Integer uId ) {
        if(StringUtils.isEmpty(uId)||uId<0){
            throw  new  SbException(100,"非法字符");
        }
        User user=this.iEUserService.selectUserByUid(uId);
        if (StringUtils.isEmpty(user)){
            throw  new SbException(400,"不存在该积分明细");
        }

        int flag=this.iExchangeService.deleteByuId(uId);
        if(flag==0){
            throw  new SbException(400,"删除失败");
        }
        return ReturnMessageUtil.sucess(true);
    }

    /**
     * 思路：买积分商品，形成积分明细，更改总积分
     * 1.判断uId和igId是否为空
     * 2.判断uid用户，igId商品是否存在
     * 3.执行积分明细插入
     * 4.更改总积分
     * 5.返回
     * @param uId  获取用户id
     * @param igId  积分商品id
     * @return
     */
    @PostMapping("/insertIE/{uId}/{igId}/{num}")
    public ReturnMessage<Object> insertIE(@PathVariable("uId") Integer uId ,@PathVariable("igId") Integer igId,@PathVariable("num") Integer num) {
        if((StringUtils.isEmpty(uId)||uId<0)||(StringUtils.isEmpty(igId)||igId<0)||num<0){
            System.out.println(uId+""+igId);
            throw  new  SbException(100,"非法字符");
        }
        User user=this.iEUserService.selectUserByUid(uId);
        IntegralGoods integralGoods=this.iGoodsService.SelectByigId(igId);
        if (StringUtils.isEmpty(user)||StringUtils.isEmpty(integralGoods)) {
            throw new SbException(400, "非法积分商品或用户");
        }
        if(integralGoods.getIgStore()==0){
            throw new SbException(400, "该商品库存不足，正在催商家加货！");
        }
        int igStore;
        igStore=integralGoods.getIgStore()-num;
        if(igStore<0){
            igStore=integralGoods.getIgStore();
            throw  new SbException(400,"库存不足");
        }
        int uTotalIntegral=user.getuTotalIntegral()-(num*integralGoods.getIgIntegral());
        if(uTotalIntegral<0){
            uTotalIntegral=user.getuTotalIntegral();
            throw  new SbException(400,"积分不足");
        }
        integralGoods.setIgStore(igStore);
        user.setuTotalIntegral(uTotalIntegral);
        int flag=this.iExchangeService.insertIE(uId,igId,num);
        int flag1=this.iEUserService.updateByuTotalIntegral(user);
        int flag2=this.iGoodsService.updateByigStore(integralGoods);
        if(flag==0||flag1==0||flag2==0){
            throw  new SbException(400,"购买失败,请稍后重试");
        }
        return ReturnMessageUtil.sucess(true);
    }
    /**
     * 查询用户总积分
     * 1.验证uid是否为空
     * 2.验证是否存在用户
     * 3.返回
     */
    @GetMapping("/selectByuId/{uId}")
    public ReturnMessage<Object> selectByuId(@PathVariable("uId")Integer uId ) {
        if(StringUtils.isEmpty(uId)||uId==0){
            throw  new  SbException(100,"非法字符");
        }
        User user=this.iEUserService.selectUserByUid(uId);
        if (StringUtils.isEmpty(user)){
            throw  new SbException(400,"该用户不存在");
        }
        return ReturnMessageUtil.sucess(user);
    }

}
