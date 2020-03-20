package com.gomai.goods.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="goods_category1")
public class Category1 { //一级类别
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer ca1_id; //一级类别id
    private String ca1_name; //一级类别名


    public Integer getCa1_id() {
        return ca1_id;
    }

    public void setCa1_id(Integer ca1_id) {
        this.ca1_id = ca1_id;
    }

    public String getCa1_name() {
        return ca1_name;
    }

    public void setCa1_name(String ca1_name) {
        this.ca1_name = ca1_name;
    }
}