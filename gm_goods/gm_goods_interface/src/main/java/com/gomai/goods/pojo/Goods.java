package com.gomai.goods.pojo;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="goods")

public class Goods {//商品表
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "g_id")
    private Integer gid; //商品id
    @Column(name = "u_id")
    private Integer uid;  //发布用户id
    @Column(name = "ca2_id")
    private Integer ca2id; //二级类别id
    @Column(name = "g_name")
    private String gname;  //商品名
    @Column(name = "g_detail")
    private String gdetail;  //商品详情信息
    @Column(name = "g_price")
    private Double gprice;  //价格
    @Column(name = "g_status")
    private String gstatus;  //下架状态
    @Column(name = "g_create_time")
    private Date gcreatetime; //发布时间

    public Goods() {
    }

    public Goods(Integer uid, Integer ca2id, String gname, String gdetail, Double gprice, String gstatus, Date gcreatetime) {
        this.uid = uid;
        this.ca2id = ca2id;
        this.gname = gname;
        this.gdetail = gdetail;
        this.gprice = gprice;
        this.gstatus = gstatus;
        this.gcreatetime = gcreatetime;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCa2id() {
        return ca2id;
    }

    public void setCa2id(Integer ca2id) {
        this.ca2id = ca2id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGdetail() {
        return gdetail;
    }

    public void setGdetail(String gdetail) {
        this.gdetail = gdetail;
    }

    public Double getGprice() {
        return gprice;
    }

    public void setGprice(Double gprice) {
        this.gprice = gprice;
    }

    public String getGstatus() {
        return gstatus;
    }

    public void setGstatus(String gstatus) {
        this.gstatus = gstatus;
    }

    public Date getGcreatetime() {
        return gcreatetime;
    }

    public void setGcreatetime(Date gcreatetime) {
        this.gcreatetime = gcreatetime;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", uid=" + uid +
                ", ca2id=" + ca2id +
                ", gname='" + gname + '\'' +
                ", gdetail='" + gdetail + '\'' +
                ", gprice=" + gprice +
                ", gstatus='" + gstatus + '\'' +
                ", gcreatetime=" + gcreatetime +
                '}';
    }
}
