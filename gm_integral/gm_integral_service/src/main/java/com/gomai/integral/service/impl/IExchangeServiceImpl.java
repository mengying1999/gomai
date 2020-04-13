package com.gomai.integral.service.impl;

import com.gomai.goods.pojo.Goods;
import com.gomai.integral.mapper.IExchangeMapper;
import com.gomai.integral.service.IExchangeService;
import com.gomai.integral.vo.GoodsVo;
import com.gomai.integral.vo.IChangeVo;
import com.gomai.integral.vo.IGoodsVo;
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
}
