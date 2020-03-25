package com.gomai.goods.pojo;
import javax.persistence.*;

@Entity
@Table(name="goods_category2")
public class Category2 {//二级类别表
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ca2_id")
    private Integer ca2Id; //二级类别id
    @Column(name = "ca1_id")
    private Integer ca1Id; //一级类别id
    @Column(name = "ca2_name")
    private String ca2Name; //二级类别名

    public Category2() {
    }

    public Category2(Integer ca1Id, String ca2Name) {
        this.ca1Id = ca1Id;
        this.ca2Name = ca2Name;
    }

    public Integer getCa2Id() {
        return ca2Id;
    }

    public void setCa2Id(Integer ca2Id) {
        this.ca2Id = ca2Id;
    }

    public Integer getCa1Id() {
        return ca1Id;
    }

    public void setCa1Id(Integer ca1Id) {
        this.ca1Id = ca1Id;
    }

    public String getCa2Name() {
        return ca2Name;
    }

    public void setCa2Name(String ca2Name) {
        this.ca2Name = ca2Name;
    }

    @Override
    public String toString() {
        return "Category2{" +
                "ca2Id=" + ca2Id +
                ", ca1Id=" + ca1Id +
                ", ca2Name='" + ca2Name + '\'' +
                '}';
    }
}
