package com.gomai.goods.service;

import com.gomai.goods.pojo.Category1;

import java.util.List;

public interface Category1Service {
    public List<Category1> queryListByParent(Long ca1_id);
}
