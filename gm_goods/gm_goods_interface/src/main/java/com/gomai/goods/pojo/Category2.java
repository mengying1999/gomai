package com.gomai.goods.pojo;
import javax.persistence.*;

@Entity
@Table(name="goods_category2")
public class Category2 {//二级类别表
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ca2_id")
    private Integer ca2id; //二级类别id
    @Column(name = "ca1_id")
    private Integer ca1id; //一级类别id
    @Column(name = "ca2_name")
    private String ca2name; //二级类别名

    public Category2() {
    }

    public Category2(Integer ca1id, String ca2name) {
        this.ca1id = ca1id;
        this.ca2name = ca2name;
    }

    public Integer getCa2id() {
        return ca2id;
    }

    public void setCa2id(Integer ca2id) {
        this.ca2id = ca2id;
    }

    public Integer getCa1id() {
        return ca1id;
    }

    public void setCa1id(Integer ca1id) {
        this.ca1id = ca1id;
    }

    public String getCa2name() {
        return ca2name;
    }

    public void setCa2name(String ca2name) {
        this.ca2name = ca2name;
    }

    @Override
    public String toString() {
        return "Category2{" +
                "ca2id=" + ca2id +
                ", ca1id=" + ca1id +
                ", ca2name='" + ca2name + '\'' +
                '}';
    }
}
