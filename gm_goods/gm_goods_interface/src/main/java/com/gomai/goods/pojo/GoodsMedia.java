package com.gomai.goods.pojo;

import javax.persistence.*;
@Entity
@Table(name="goods_media")
public class GoodsMedia {//商品媒体表
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "gm_id")
    private Integer gmId; //商品媒体id
    @Column(name = "g_id")
    private Integer gId; //商品id
    @Column(name = "gm_url")
    private String gmUrl; //商品媒体地址
    @Column(name = "gm_type")
    private Integer gmType; //商品媒体类别

    public GoodsMedia() {
    }

    public GoodsMedia(Integer gId, String gmUrl, Integer gmType) {
        this.gId = gId;
        this.gmUrl = gmUrl;
        this.gmType = gmType;
    }

    public Integer getGmId() {
        return gmId;
    }

    public void setGmId(Integer gmId) {
        this.gmId = gmId;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getGmUrl() {
        return gmUrl;
    }

    public void setGmUrl(String gmUrl) {
        this.gmUrl = gmUrl;
    }

    public Integer getGmType() {
        return gmType;
    }

    public void setGmType(Integer gmType) {
        this.gmType = gmType;
    }

    @Override
    public String toString() {
        return "GoodsMedia{" +
                "gmId=" + gmId +
                ", gId=" + gId +
                ", gmUrl='" + gmUrl + '\'' +
                ", gmType=" + gmType +
                '}';
    }
}
