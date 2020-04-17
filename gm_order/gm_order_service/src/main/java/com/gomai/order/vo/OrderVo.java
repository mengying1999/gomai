package com.gomai.order.vo;

import com.gomai.user.pojo.User;
import com.gomai.user.pojo.UserAddress;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class OrderVo {
    private Integer oId; //主键
    private User user;//买家
    private GoodsVo goodsVo; //商品
    private UserAddress userAddress;  // 地址id
    private Integer oStatus;  //订单状态
    private Integer oCancelType; // 订单取消类型
    private String oEvaluation;  // 订单评价
    private String oEvaluationAdd; //订单追评
    private String oCancelReason;  //订单取消理由
    private Date oCreateTime;  //订单创建时间
    private Date oPayTime;  //订单支付时间
    private Date oRemindShipments; //提醒发货时间
    private Date oShipmentsTime; //发货时间
    private Date oRemindReceive;  // 提醒收货时间
    private Date oReceiveTime;  //收货时间
    private Date oEvaluationTime;  //评价时间
    private Date oEvaluationAddTime;  //追加评价时间
    private Integer oSellDelete; // 卖家是否删除
    private Integer oBuyDelete; //买家是否删除
    private String oTradeNo;//支付宝宝交易号

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getoStatus() {
        return oStatus;
    }

    public void setoStatus(Integer oStatus) {
        this.oStatus = oStatus;
    }

    public Integer getoCancelType() {
        return oCancelType;
    }

    public void setoCancelType(Integer oCancelType) {
        this.oCancelType = oCancelType;
    }

    public String getoEvaluation() {
        return oEvaluation;
    }

    public void setoEvaluation(String oEvaluation) {
        this.oEvaluation = oEvaluation;
    }

    public String getoEvaluationAdd() {
        return oEvaluationAdd;
    }

    public void setoEvaluationAdd(String oEvaluationAdd) {
        this.oEvaluationAdd = oEvaluationAdd;
    }

    public String getoCancelReason() {
        return oCancelReason;
    }

    public void setoCancelReason(String oCancelReason) {
        this.oCancelReason = oCancelReason;
    }

    public Date getoCreateTime() {
        return oCreateTime;
    }

    public void setoCreateTime(Date oCreateTime) {
        this.oCreateTime = oCreateTime;
    }

    public Date getoPayTime() {
        return oPayTime;
    }

    public void setoPayTime(Date oPayTime) {
        this.oPayTime = oPayTime;
    }

    public Date getoRemindShipments() {
        return oRemindShipments;
    }

    public void setoRemindShipments(Date oRemindShipments) {
        this.oRemindShipments = oRemindShipments;
    }

    public Date getoShipmentsTime() {
        return oShipmentsTime;
    }

    public void setoShipmentsTime(Date oShipmentsTime) {
        this.oShipmentsTime = oShipmentsTime;
    }

    public Date getoRemindReceive() {
        return oRemindReceive;
    }

    public void setoRemindReceive(Date oRemindReceive) {
        this.oRemindReceive = oRemindReceive;
    }

    public Date getoReceiveTime() {
        return oReceiveTime;
    }

    public void setoReceiveTime(Date oReceiveTime) {
        this.oReceiveTime = oReceiveTime;
    }

    public Date getoEvaluationTime() {
        return oEvaluationTime;
    }

    public void setoEvaluationTime(Date oEvaluationTime) {
        this.oEvaluationTime = oEvaluationTime;
    }

    public Date getoEvaluationAddTime() {
        return oEvaluationAddTime;
    }

    public void setoEvaluationAddTime(Date oEvaluationAddTime) {
        this.oEvaluationAddTime = oEvaluationAddTime;
    }

    public Integer getoSellDelete() {
        return oSellDelete;
    }

    public void setoSellDelete(Integer oSellDelete) {
        this.oSellDelete = oSellDelete;
    }

    public Integer getoBuyDelete() {
        return oBuyDelete;
    }

    public void setoBuyDelete(Integer oBuyDelete) {
        this.oBuyDelete = oBuyDelete;
    }

    public String getoTradeNo() {
        return oTradeNo;
    }

    public void setoTradeNo(String oTradeNo) {
        this.oTradeNo = oTradeNo;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "oId=" + oId +
                ", user=" + user +
                ", goodsVo=" + goodsVo +
                ", userAddress=" + userAddress +
                ", oStatus=" + oStatus +
                ", oCancelType=" + oCancelType +
                ", oEvaluation='" + oEvaluation + '\'' +
                ", oEvaluationAdd='" + oEvaluationAdd + '\'' +
                ", oCancelReason='" + oCancelReason + '\'' +
                ", oCreateTime=" + oCreateTime +
                ", oPayTime=" + oPayTime +
                ", oRemindShipments=" + oRemindShipments +
                ", oShipmentsTime=" + oShipmentsTime +
                ", oRemindReceive=" + oRemindReceive +
                ", oReceiveTime=" + oReceiveTime +
                ", oEvaluationTime=" + oEvaluationTime +
                ", oEvaluationAddTime=" + oEvaluationAddTime +
                ", oSellDelete=" + oSellDelete +
                ", oBuyDelete=" + oBuyDelete +
                ", oTradeNo='" + oTradeNo + '\'' +
                '}';
    }
}
