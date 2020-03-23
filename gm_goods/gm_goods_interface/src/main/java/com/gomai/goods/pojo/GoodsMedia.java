package com.gomai.goods.pojo;

import javax.persistence.*;
@Entity
@Table(name="GoodsMedia")
public class GoodsMedia {//商品媒体表
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "gm_id")
    private Integer gmid; //商品媒体id
    @Column(name = "g_id")
    private Integer gid; //商品id
    @Column(name = "gm_url")
    private String gmurl; //商品媒体地址
    @Column(name = "gm_type")
    private Integer gmtype; //商品媒体类别

    public GoodsMedia() {
    }

    public GoodsMedia(Integer gid, String gmurl, Integer gmtype) {
        this.gid = gid;
        this.gmurl = gmurl;
        this.gmtype = gmtype;
    }

    public Integer getGmid() {
        return gmid;
    }

    public void setGmid(Integer gmid) {
        this.gmid = gmid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGmurl() {
        return gmurl;
    }

    public void setGmurl(String gmurl) {
        this.gmurl = gmurl;
    }

    public Integer getGmtype() {
        return gmtype;
    }

    public void setGmtype(Integer gmtype) {
        this.gmtype = gmtype;
    }

    @Override
    public String toString() {
        return "GoodsMedia{" +
                "gmid=" + gmid +
                ", gid=" + gid +
                ", gmurl='" + gmurl + '\'' +
                ", gmtype=" + gmtype +
                '}';
    }
}
