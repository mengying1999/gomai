package com.gomai.goods.service.impl;

import com.gomai.goods.mapper.GoodsMediaMapper;
import com.gomai.goods.pojo.GoodsMedia;
import com.gomai.goods.service.GoodsMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsMediaServiceImpl implements GoodsMediaService {
    @Autowired
    private GoodsMediaMapper goodsMediaMapper;

    @Override
    public int addGoodsMedia(List<GoodsMedia> goodsMedias){

     //   for (GoodsMedia gmedias:goodsMedias) {
            int flag =  this.goodsMediaMapper.insertList(goodsMedias);
     //   }

        return flag;
    }
}
