package com.gomai.integral.service.impl;

import com.gomai.integral.mapper.IExchangeMapper;
import com.gomai.integral.mapper.IGoodsMapper;
import com.gomai.integral.service.IGoodsService;
import com.gomai.intergral.pojo.IntegralGoods;

import java.util.List;

public class IGoodsServiceImpl implements IGoodsService {
    private IGoodsMapper iGoodsMapper;


    @Override
    public List<IntegralGoods> SelectByigId(int igId) {
        IntegralGoods integralGoods=this.iGoodsMapper.selectByPrimaryKey(igId);
        return null;
    }
}
