package com.gomai.integral.service.impl;

import com.gomai.integral.mapper.IExchangeMapper;
import com.gomai.integral.service.IExchangeService;
import com.gomai.integral.vo.IChangeVo;
import com.gomai.intergral.pojo.IntegralExchange;
import com.gomai.intergral.pojo.IntegralGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class IExchangeServiceImpl implements IExchangeService {
    @Autowired
    private IExchangeMapper iExchangeMapper;

    @Override
    public IntegralGoods selectIGoodsByigId(int igId) {
        IntegralGoods integralGoods = this.iExchangeMapper.selectIGoodsByigId(igId);
        return integralGoods;
    }

    @Override
    public List<IChangeVo> selectIChangeVoByTypes(Integer uid, List<Integer> types) {
        List<IChangeVo> iChangeVos=this.iExchangeMapper.selectIChangeVoByTypes(uid,types);
        return iChangeVos;
    }

    @Override
    public int deleteByieId(int ieId) {
        int  flag=this.iExchangeMapper.deleteByPrimaryKey(ieId);
        return flag;
    }

    @Override
    public IntegralExchange selectByieId(Integer ieId) {
        IntegralExchange ie=this.iExchangeMapper.selectByPrimaryKey(ieId);
        return ie;
    }
}
