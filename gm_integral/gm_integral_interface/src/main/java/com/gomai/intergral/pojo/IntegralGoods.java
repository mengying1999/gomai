package com.gomai.intergral.pojo;

import javax.persistence.*;

@Entity
@Table(name = "integral_goods")
public class IntegralGoods {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ig_id")
    private Integer igId; //主键
    @Column(name="ig_type")
    private String igType; //积分商品类别
    @Column(name="ig_integral")
    private Integer igIntegral; //积分
    @Column(name="ig_store")
    private Integer igStore; //库存
    @Column(name="ig_name")
    private String igName; //积分商品名
    @Column(name="ig_describe")
    private String igDescribe; //积分商品描述
    public IntegralGoods() {
    }

    public IntegralGoods( String igType, Integer igIntegral, Integer igStore, String igName, String igDescribe) {
        this.igType = igType;
        this.igIntegral = igIntegral;
        this.igStore = igStore;
        this.igName = igName;
        this.igDescribe = igDescribe;
    }

    public Integer getIgId() {
        return igId;
    }

    public void setIgId(Integer igId) {
        this.igId = igId;
    }

    public String getIgType() {
        return igType;
    }

    public void setIgType(String igType) {
        this.igType = igType;
    }

    public Integer getIgIntegral() {
        return igIntegral;
    }

    public void setIgIntegral(Integer igIntegral) {
        this.igIntegral = igIntegral;
    }

    public Integer getIgStore() {
        return igStore;
    }

    public void setIgStore(Integer igStore) {
        this.igStore = igStore;
    }

    public String getIgName() {
        return igName;
    }

    public void setIgName(String igName) {
        this.igName = igName;
    }

    public String getIgDescribe() {
        return igDescribe;
    }

    public void setIgDescribe(String igDescribe) {
        this.igDescribe = igDescribe;
    }

    @Override
    public String toString() {
        return "IntegralGoods{" +
                "igId=" + igId +
                ", igType='" + igType + '\'' +
                ", igIntegral=" + igIntegral +
                ", igStore=" + igStore +
                ", igName='" + igName + '\'' +
                ", igDescribe='" + igDescribe + '\'' +
                '}';
    }
}

