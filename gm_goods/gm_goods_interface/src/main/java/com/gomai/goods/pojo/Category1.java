package com.gomai.goods.pojo;

import javax.persistence.*;
@Entity
@Table(name="goods_category1")
public class Category1 { //一级类别表
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ca1_id")
    private Integer ca1Id; //一级类别id
    @Column(name = "ca1_name")
    private String ca1Name; //一级类别名

    public Category1() {
    }
    public Category1(Integer ca1Id, String ca1Name) {
        this.ca1Id = ca1Id;
        this.ca1Name = ca1Name;
    }

    public Integer getCa1Id() {
        return ca1Id;
    }

    public void setCa1Id(Integer ca1Id) {
        this.ca1Id = ca1Id;
    }

    public String getCa1Name() {
        return ca1Name;
    }

    public void setCa1Name(String ca1Name) {
        this.ca1Name = ca1Name;
    }

    @Override
    public String toString() {
        return "Category1{" +
                "ca1Id=" + ca1Id +
                ", ca1Name='" + ca1Name + '\'' +
                '}';
    }
}