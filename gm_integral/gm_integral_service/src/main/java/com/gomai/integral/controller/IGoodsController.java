package com.gomai.integral.controller;

import com.gomai.integral.service.IEUserService;
import com.gomai.integral.service.IExchangeService;
import com.gomai.integral.service.IGoodsService;
import com.gomai.integral.vo.IChangeVo;
import com.gomai.intergral.pojo.IntegralGoods;
import com.gomai.user.pojo.User;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/IGoods")
public class IGoodsController {
    @Autowired
    private IGoodsService iGoodsService;
    @Autowired
    private IEUserService iEUserService;

    /**
     *
     * 1.验证ig_type是否合法
     * 2.查询该类型是否存在
     *
     * 4.查询
     * 5.返回
     */
    @GetMapping("/SelectByigType/{igType}")
    public ReturnMessage<Object> SelectByigType(@PathVariable("igType")String igType) {
        //验证uid和type合法
        if(igType==null||igType.equals("")){
            throw new SbException(400, "空类型");
        }
        List<IntegralGoods> integralGoods=iGoodsService.selecetByigType(igType);
        if(StringUtils.isEmpty(integralGoods)){
            throw  new  SbException(100,"不存在该类型商品");
        }
            return  ReturnMessageUtil.sucess(integralGoods);
    }
    /**
     *
     * 1.验证igId是否合法
     * 2.查询商品是否存在
     *
     * 4.查询
     * 5.返回
     */
    @GetMapping("/SelectByigId/{igId}")
    public ReturnMessage<Object> SelectByigId(@PathVariable("igId")Integer igId) {
        //验证uid和type合法
        if(igId==0||StringUtils.isEmpty(igId)){
            throw new SbException(400, "非法id");
        }
        IntegralGoods integralGoods=iGoodsService.SelectByigId(igId);
        if(StringUtils.isEmpty(integralGoods)){
            throw  new  SbException(100,"不存在该类型商品");
        }
        return  ReturnMessageUtil.sucess(integralGoods);
    }
    /**
     *
     * 1.验证igId是否合法
     * 2.查询商品是否存在
     *
     * 4.查询
     * 5.返回
     */
    @GetMapping("/SelectByigName/{igName}")
    public ReturnMessage<Object> SelectByigName(@PathVariable("igName")String igName) {
        //验证uid和type合法
        if(igName==null||igName.equals("")){
            throw new SbException(400, "商品名不能为空");
        }
        List<IntegralGoods> integralGoods=iGoodsService.SelectByigName(igName);
        if(StringUtils.isEmpty(integralGoods)){
            throw  new  SbException(100,"不存在该类型商品");
        }
        return  ReturnMessageUtil.sucess(integralGoods);
    }
}
