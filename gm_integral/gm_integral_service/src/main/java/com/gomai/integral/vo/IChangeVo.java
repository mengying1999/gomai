package com.gomai.integral.vo;

import com.gomai.goods.pojo.Goods;
import com.gomai.intergral.pojo.IntegralGoods;

import java.util.Date;

public class IChangeVo {
    private Integer ieId; //主键

    private Integer igId; //积分商品id

    private Integer gId; //商品id

    private Integer uId; //用户id

    private Date ieCreateTime; //兑换的时间

    private Integer ieChangeIntegral; //改变的积分

    private Integer ieTotalIntegral; //个人总积分

    private Integer ieType; //个人总积分

    private GoodsVo goodsVo;

    private IntegralGoods integralGoods;

    public IChangeVo() {
    }

    public IChangeVo(Integer ieId, Integer igId, Integer gId, Integer uId, Date ieCreateTime, Integer ieChangeIntegral, Integer ieTotalIntegral, Integer ieType, GoodsVo goodsVo, IntegralGoods integralGoods) {
        this.ieId = ieId;
        this.igId = igId;
        this.gId = gId;
        this.uId = uId;
        this.ieCreateTime = ieCreateTime;
        this.ieChangeIntegral = ieChangeIntegral;
        this.ieTotalIntegral = ieTotalIntegral;
        this.ieType = ieType;
        this.goodsVo = goodsVo;
        this.integralGoods = integralGoods;
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

    public Integer getIeType() {
        return ieType;
    }

    public void setIeType(Integer ieType) {
        this.ieType = ieType;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }

    public IntegralGoods getIntegralGoods() {
        return integralGoods;
    }

    public void setIntegralGoods(IntegralGoods integralGoods) {
        this.integralGoods = integralGoods;
    }

    @Override
    public String toString() {
        return "IChangeVo{" +
                "ieId=" + ieId +
                ", igId=" + igId +
                ", gId=" + gId +
                ", uId=" + uId +
                ", ieCreateTime=" + ieCreateTime +
                ", ieChangeIntegral=" + ieChangeIntegral +
                ", ieTotalIntegral=" + ieTotalIntegral +
                ", ieType=" + ieType +
                ", goodsVo=" + goodsVo +
                ", integralGoods=" + integralGoods +
                '}';
    }
}
