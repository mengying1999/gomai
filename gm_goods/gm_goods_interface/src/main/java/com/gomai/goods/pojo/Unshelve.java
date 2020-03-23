package com.gomai.goods.pojo;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="goods_unshelve")
public class Unshelve {//下架表
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "un_id")
    private Integer unid; //下架商品id
    @Column(name = "g_id")
    private Integer gid; //商品id
    @Column(name = "un_reason")
    private String unreason; //下架原因
    @Column(name = "un_create_time")
    private Date uncreatetime; //下架时间

    public Unshelve() {
    }

    public Unshelve(Integer gid, String unreason, Date uncreatetime) {
        this.gid = gid;
        this.unreason = unreason;
        this.uncreatetime = uncreatetime;
    }

    public Integer getUnid() {
        return unid;
    }

    public void setUnid(Integer unid) {
        this.unid = unid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getUnreason() {
        return unreason;
    }

    public void setUnreason(String unreason) {
        this.unreason = unreason;
    }

    public Date getUncreatetime() {
        return uncreatetime;
    }

    public void setUncreatetime(Date uncreatetime) {
        this.uncreatetime = uncreatetime;
    }

    @Override
    public String toString() {
        return "Unshelve{" +
                "unid=" + unid +
                ", gid=" + gid +
                ", unreason='" + unreason + '\'' +
                ", uncreatetime=" + uncreatetime +
                '}';
    }
}

