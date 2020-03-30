package com.gomai.order.service;

import com.gomai.goods.pojo.Goods;
import com.gomai.order.vo.GoodsVo;

import java.util.List;


public interface OGoodsService {
    public GoodsVo queryGoodsVoByGId(Integer gId);

    public Goods queryGoodsByGId(Integer getgId);

    public int updateGoods(Goods goods);
}
