package com.gomai.goods.vo;

import com.gomai.goods.pojo.GoodsMedia;

public class RecommendGoods {

    private Integer gId; //商品id
    private String gName;  //商品名
    private Double gPrice;  //价格
    private GoodsMedia goodsMedias;//图片

    public RecommendGoods(Integer gId, String gName, Double gPrice, GoodsMedia goodsMedias) {
        this.gId = gId;
        this.gName = gName;
        this.gPrice = gPrice;
        this.goodsMedias = goodsMedias;
    }

    public RecommendGoods() {
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Double getgPrice() {
        return gPrice;
    }

    public void setgPrice(Double gPrice) {
        this.gPrice = gPrice;
    }

    public GoodsMedia getGoodsMedias() {
        return goodsMedias;
    }

    public void setGoodsMedias(GoodsMedia goodsMedias) {
        this.goodsMedias = goodsMedias;
    }

    @Override
    public String toString() {
        return "RecommendGoods{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", gPrice=" + gPrice +
                ", goodsMedias=" + goodsMedias +
                '}';
    }
}
