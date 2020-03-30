package com.gomai.intergral.pojo;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "integral_exchange")
public class IntegralExchange {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ie_id")
    private Integer ieId; //主键
    @Column(name = "ig_id")
    private Integer igId; //积分商品id
    @Column(name = "g_id")
    private Integer gId; //商品id
    @Column(name = "u_id")
    private Integer uId; //用户id
    @Column(name = "ie_create_time")
    private Date ieCreateTime; //兑换的时间
    @Column(name = "ie_change_integral")
    private Integer ieChangeIntegral; //改变的积分
    @Column(name = "ie_total_integral")
    private Integer ieTotalIntegral; //个人总积分
    public IntegralExchange() {
    }

    public IntegralExchange( Integer igId, Integer gId, Integer uId, Date ieCreateTime, Integer ieChangeIntegral, Integer ieTotalIntegral) {
        this.gId = gId;
        this.uId = uId;
        this.ieCreateTime = ieCreateTime;
        this.ieChangeIntegral = ieChangeIntegral;
        this.ieTotalIntegral = ieTotalIntegral;
    }

    public Integer getIeId() {
        return ieId;
    }

    public void setIeId(Integer ieId) {
        this.ieId = ieId;
    }

    public Integer getIgId() {
        return igId;
    }

    public void setIgId(Integer igId) {
        this.igId = igId;
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

    public Date getIeCreateTime() {
        return ieCreateTime;
    }

    public void setIeCreateTime(Date ieCreateTime) {
        this.ieCreateTime = ieCreateTime;
    }

    public Integer getIeChangeIntegral() {
        return ieChangeIntegral;
    }

    public void setIeChangeIntegral(Integer ieChangeIntegral) {
        this.ieChangeIntegral = ieChangeIntegral;
    }

    public Integer getIeTotalIntegral() {
        return ieTotalIntegral;
    }

    public void setIeTotalIntegral(Integer ieTotalIntegral) {
        this.ieTotalIntegral = ieTotalIntegral;
    }

    @Override
    public String toString() {
        return "IntegralExchange{" +
                "ieId=" + ieId +
                ", igId=" + igId +
                ", gId=" + gId +
                ", uId=" + uId +
                ", ieCreateTime=" + ieCreateTime +
                ", ieChangeIntegral=" + ieChangeIntegral +
                ", ieTotalIntegral=" + ieTotalIntegral +
                '}';
    }
}