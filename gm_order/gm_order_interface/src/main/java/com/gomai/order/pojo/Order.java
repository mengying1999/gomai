package com.gomai.order.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单表
 */
@Entity
@Table(name = "order_form")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "o_id")
    private Integer oId; //主键
    @Column(name = "u_id")
    private Integer uId; //买家id
    @Column(name = "g_id")
    private Integer gId; //商品id
    @Column(name = "ua_id")
    private Integer uaId;  // 地址id
    @Column(name = "o_status")
    private Integer oStatus;  //订单状态
    @Column(name = "o_cancel_type")
    private Integer oCancelType; // 订单取消类型
    @Column(name = "o_evaluation")
    private String oEvaluation;  // 订单评价
    @Column(name = "o_evaluation_add")
    private String oEvaluationAdd; //订单追评
    @Column(name = "o_cancel_reason")
    private String oCancelReason;  //订单取消理由
    @Column(name = "o_create_time")
    private Date oCreateTime;  //订单创建时间
    @Column(name = "o_pay_time")
    private Date oPayTime;  //订单支付时间
    @Column(name = "o_remind_shipments")
    private Date oRemindShipments; //提醒发货时间
    @Column(name = "o_shipments_time")
    private Date oShipmentsTime; //发货时间
    @Column(name = "o_remind_receive")
    private Date oRemindReceive;  // 提醒收货时间
    @Column(name = "o_receive_time")
    private Date oReceiveTime;  //收货时间
    @Column(name = "o_evaluation_time")
    private Date oEvaluationTime;  //评价时间
    @Column(name = "o_evaluation_add_time")
    private Date oEvaluationAddTime;  //追加评价时间
    @Column(name = "o_sell_delete")
    private Integer oSellDelete; // 卖家是否删除
    @Column(name = "o_buy_delete")
    private Integer oBuyDelete; //买家是否删除

    public Order() {
    }

    public Order(Integer uId, Integer gId, Integer uaId, Integer oStatus, Integer oCancelType, String oEvaluation, String oEvaluationAdd, String oCancelReason, Date oCreateTime, Date oPayTime, Date oRemindShipments, Date oShipmentsTime, Date oRemindReceive, Date oReceiveTime, Date oEvaluationTime, Date oEvaluationAddTime, Integer oSellDelete, Integer oBuyDelete) {
        this.uId = uId;
        this.gId = gId;
        this.uaId = uaId;
        this.oStatus = oStatus;
        this.oCancelType = oCancelType;
        this.oEvaluation = oEvaluation;
        this.oEvaluationAdd = oEvaluationAdd;
        this.oCancelReason = oCancelReason;
        this.oCreateTime = oCreateTime;
        this.oPayTime = oPayTime;
        this.oRemindShipments = oRemindShipments;
        this.oShipmentsTime = oShipmentsTime;
        this.oRemindReceive = oRemindReceive;
        this.oReceiveTime = oReceiveTime;
        this.oEvaluationTime = oEvaluationTime;
        this.oEvaluationAddTime = oEvaluationAddTime;
        this.oSellDelete = oSellDelete;
        this.oBuyDelete = oBuyDelete;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public Integer getUaId() {
        return uaId;
    }

    public void setUaId(Integer uaId) {
        this.uaId = uaId;
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

    @Override
    public String toString() {
        return "Order{" +
                "oId=" + oId +
                ", uId=" + uId +
                ", gId=" + gId +
                ", uaId=" + uaId +
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
                '}';
    }
}
