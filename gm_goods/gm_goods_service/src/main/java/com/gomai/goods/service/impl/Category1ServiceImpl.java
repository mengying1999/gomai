package com.gomai.goods.service.impl;
import com.gomai.goods.mapper.Category1Mapper;
import com.gomai.goods.pojo.Category1;
import com.gomai.goods.service.Category1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Category1ServiceImpl implements Category1Service {
    @Autowired
    private Category1Mapper category1Mapper;

    public List<Category1> queryListByParent(Long ca1_id) {
        Category1 category = new Category1();
        category.setCa1_id(ca1_id);
        return this.category1Mapper.select(category);
    }

}
