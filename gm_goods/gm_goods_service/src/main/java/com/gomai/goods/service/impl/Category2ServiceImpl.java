package com.gomai.goods.service.impl;

import com.gomai.goods.mapper.Category2Mapper;
import com.gomai.goods.service.Category1Service;
import com.gomai.goods.service.Category2Service;
import com.gomai.goods.vo.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Category2ServiceImpl implements Category2Service {
    @Autowired
    private Category2Mapper category2Mapper;

    public List<Option> findAllGoodsCategry(){
        return this.category2Mapper.findAllGoodsCategry();
    }

    public List<Integer> findCategoryTwoByOne(Integer ca1_id){
        return this.category2Mapper.findCategoryTwoByOne(ca1_id);
    }
}
