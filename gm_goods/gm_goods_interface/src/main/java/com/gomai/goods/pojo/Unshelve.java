package com.gomai.goods.pojo;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="goods_unshelve")
public class Unshelve {//下架表
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "un_id")
    private Integer unId; //下架商品id
    @Column(name = "g_id")
    private Integer gId; //商品id
    @Column(name = "un_reason")
    private String unReason; //下架原因
    @Column(name = "un_create_time")
    private Date unCreateTime; //下架时间

    public Unshelve() {
    }


    public Unshelve(Integer gId, String unReason, Date unCreateTime) {
        this.gId = gId;
        this.unReason = unReason;
        this.unCreateTime = unCreateTime;
    }

    public Integer getUnId() {
        return unId;
    }

    public void setUnId(Integer unId) {
        this.unId = unId;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getUnReason() {
        return unReason;
    }

    public void setUnReason(String unReason) {
        this.unReason = unReason;
    }

    public Date getUnCreateTime() {
        return unCreateTime;
    }

    public void setUnCreateTime(Date unCreateTime) {
        this.unCreateTime = unCreateTime;
    }


    @Override
    public String toString() {
        return "Unshelve{" +
                "unId=" + unId +
                ", gId=" + gId +
                ", unReason='" + unReason + '\'' +
                ", unCreateTime=" + unCreateTime +
                '}';
    }
}

