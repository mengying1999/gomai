package com.gomai.goods.service;

import com.gomai.goods.vo.Option;

import java.util.List;

public interface Category2Service {

    public List<Option> findAllGoodsCategry() ;//查询所有类别
    public List<Integer> findCategoryTwoByOne(Integer ca1_id);//根据ca1_id查找对应的ca2类别
}
