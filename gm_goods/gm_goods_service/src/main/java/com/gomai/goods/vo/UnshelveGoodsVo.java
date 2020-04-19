package com.gomai.goods.vo;

import com.gomai.goods.pojo.GoodsMedia;
import com.gomai.goods.pojo.Unshelve;

public class UnshelveGoodsVo {
    private Integer gId; //商品id
    private String gName;  //商品名
    private Double gPrice;  //价格
    private GoodsMedia goodsMedias;//图片
    private Unshelve unshelveGoods;//下架商品信息

    public UnshelveGoodsVo() {
    }

    public UnshelveGoodsVo(Integer gId, String gName, Double gPrice, GoodsMedia goodsMedias, Unshelve unshelveGoods) {
        this.gId = gId;
        this.gName = gName;
        this.gPrice = gPrice;
        this.goodsMedias = goodsMedias;
        this.unshelveGoods = unshelveGoods;
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

    public Unshelve getUnshelveGoods() {
        return unshelveGoods;
    }

    public void setUnshelveGoods(Unshelve unshelveGoods) {
        this.unshelveGoods = unshelveGoods;
    }

    @Override
    public String toString() {
        return "UnshelveGoodsVo{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", gPrice=" + gPrice +
                ", goodsMedias=" + goodsMedias +
                ", unshelveGoods=" + unshelveGoods +
                '}';
    }
}
