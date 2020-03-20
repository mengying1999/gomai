package com.gomai.goods.pojo;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="goods_category2")
public class Category2 {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer ca2_id; //二级类别id
    private Integer ca1_id; //一级类别id
    private String ca2_name; //二级类别名

    public Integer getCa2_id() {
        return ca2_id;
    }

    public void setCa2_id(Integer ca2_id) {
        this.ca2_id = ca2_id;
    }

    public Integer getCa1_id() {
        return ca1_id;
    }

    public void setCa1_id(Integer ca1_id) {
        this.ca1_id = ca1_id;
    }

    public String getCa2_name() {
        return ca2_name;
    }

    public void setCa2_name(String ca2_name) {
        this.ca2_name = ca2_name;
    }




}
