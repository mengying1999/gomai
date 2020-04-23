package com.gomai.comment.service.impl;

import com.gomai.comment.mapper.OCGoodMapper;
import com.gomai.comment.service.GoodService;
import com.gomai.goods.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private OCGoodMapper goodmapper;


    @Override
    public Goods selectBygid(Integer gId) {
        Goods goods=this.goodmapper.selectByPrimaryKey(gId);
        return goods;
    }
}
