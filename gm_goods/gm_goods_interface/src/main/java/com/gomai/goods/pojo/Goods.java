package com.gomai.goods.pojo;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="goods")

public class Goods {//商品表
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "g_id")
    private Integer gId; //商品id
    @Column(name = "u_id")
    private Integer uId;  //发布用户id
    @Column(name = "ca2_id")
    private Integer ca2Id; //二级类别id
    @Column(name = "g_name")
    private String gName;  //商品名
    @Column(name = "g_detail")
    private String gDetail;  //商品详情信息
    @Column(name = "g_price")
    private Double gPrice;  //价格
    @Column(name = "g_status")
    private String gStatus;  //下架状态
    @Column(name = "g_create_time")
    private Date gCreateTime; //发布时间

    public Goods() {
    }

    public Goods(Integer uId, Integer ca2Id, String gName, String gDetail, Double gPrice, String gStatus, Date gCreateTime) {
        this.uId = uId;
        this.ca2Id = ca2Id;
        this.gName = gName;
        this.gDetail = gDetail;
        this.gPrice = gPrice;
        this.gStatus = gStatus;
        this.gCreateTime = gCreateTime;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getCa2Id() {
        return ca2Id;
    }

    public void setCa2Id(Integer ca2Id) {
        this.ca2Id = ca2Id;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgDetail() {
        return gDetail;
    }

    public void setgDetail(String gDetail) {
        this.gDetail = gDetail;
    }

    public Double getgPrice() {
        return gPrice;
    }

    public void setgPrice(Double gPrice) {
        this.gPrice = gPrice;
    }

    public String getgStatus() {
        return gStatus;
    }

    public void setgStatus(String gStatus) {
        this.gStatus = gStatus;
    }

    public Date getgCreateTime() {
        return gCreateTime;
    }

    public void setgCreateTime(Date gCreateTime) {
        this.gCreateTime = gCreateTime;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gId=" + gId +
                ", uId=" + uId +
                ", ca2Id=" + ca2Id +
                ", gName='" + gName + '\'' +
                ", gDetail='" + gDetail + '\'' +
                ", gPrice=" + gPrice +
                ", gStatus='" + gStatus + '\'' +
                ", gCreateTime=" + gCreateTime +
                '}';
    }
}
