package com.gomai.integral.service.impl;

import com.gomai.integral.mapper.IExchangeMapper;
import com.gomai.integral.mapper.IGoodsMapper;
import com.gomai.integral.service.IGoodsService;
import com.gomai.intergral.pojo.IntegralGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class IGoodsServiceImpl implements IGoodsService {
    @Autowired
    private IGoodsMapper iGoodsMapper;


    @Override
    public IntegralGoods SelectByigId(int igId) {
        IntegralGoods integralGoods=this.iGoodsMapper.selectByPrimaryKey(igId);
        return integralGoods;
    }

    @Override
    public List<IntegralGoods> selecetByigType(String igType) {
        IntegralGoods integralGood=new IntegralGoods();
        integralGood.setIgType(igType);
        List<IntegralGoods>  integralGoods=this.iGoodsMapper.select(integralGood);
        return integralGoods;
    }

    @Override
    public List<IntegralGoods> SelectByigName(String igName) {
        Example example=new Example(IntegralGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("igName","%"+igName+"%");
        List<IntegralGoods> integralGoodsList = this.iGoodsMapper.selectByExample(example);
        return integralGoodsList;
    }

    @Override
    public List<IntegralGoods> SelectNew() {
        List<IntegralGoods> integralGoods=this.iGoodsMapper.selectNew();
        return integralGoods;
    }
}
