package com.gomai.integral.vo;

import com.gomai.goods.pojo.GoodsMedia;

import java.util.Date;
import java.util.List;

public class GoodsVo {
//    Goods的所有媒体对象
private Integer gId; //商品id

    private Integer uId;  //发布用户id

    private Integer ca2Id; //二级类别id

    private String gName;  //商品名

    private String gDetail;  //商品详情信息

    private Double gPrice;  //价格

    private Integer gStatus;  //下架状态
    private Date gCreateTime; //发布时间
    private List<GoodsMedia> goodsMedias;

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

    public Integer getgStatus() {
        return gStatus;
    }

    public void setgStatus(Integer gStatus) {
        this.gStatus = gStatus;
    }

    public Date getgCreateTime() {
        return gCreateTime;
    }

    public void setgCreateTime(Date gCreateTime) {
        this.gCreateTime = gCreateTime;
    }

    public List<GoodsMedia> getGoodsMedias() {
        return goodsMedias;
    }

    public void setGoodsMedias(List<GoodsMedia> goodsMedias) {
        this.goodsMedias = goodsMedias;
    }

    @Override
    public String toString() {
        return "GoodsVo{" +
                "gId=" + gId +
                ", uId=" + uId +
                ", ca2Id=" + ca2Id +
                ", gName='" + gName + '\'' +
                ", gDetail='" + gDetail + '\'' +
                ", gPrice=" + gPrice +
                ", gStatus=" + gStatus +
                ", gCreateTime=" + gCreateTime +
                ", goodsMedias=" + goodsMedias +
                '}';
    }
}
