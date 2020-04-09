package com.gomai.order.service.impl;

import com.gomai.goods.pojo.Goods;
import com.gomai.order.mapper.OGoodsMapper;
import com.gomai.order.service.OGoodsService;
import com.gomai.order.vo.GoodsVo;
import com.gomai.user.pojo.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OGoodsServiceImpl implements OGoodsService {

    @Autowired
    private OGoodsMapper oGoodsMapper;
    
    @Override
    public GoodsVo queryGoodsVoByGId(Integer gId) {
        GoodsVo goodsVos = this.oGoodsMapper.selectGoodsVoByGId(gId);
        System.out.println(this.oGoodsMapper.selectGoodsMediaByGId(gId));
        System.out.println(goodsVos);
        return this.oGoodsMapper.selectGoodsVoByGId(gId);
    }

    @Override
    public Goods queryGoodsByGId(Integer gId) {
        Goods goods = this.oGoodsMapper.selectByPrimaryKey(gId);
        return goods;
    }

    @Override
    public int updateGoods(Goods goods) {
        int flag = this.oGoodsMapper.updateByPrimaryKey(goods);
        return flag;
    }
}
