package com.gomai.comment.vo;

import com.gomai.comment.pojo.OrderEvaluationMedia;
import com.gomai.order.vo.GoodsVo;
import com.gomai.user.pojo.User;

import java.util.Date;
import java.util.List;

public class OrderComentVo {
    private Integer oId; //主键
    private Integer oStatus;  //订单状态
    private Integer uId;
    private Integer gId;
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
    private User user;
    private List<OrderEvaluationMedia> orderEvaluationMedia;//媒体
    private List<CommentVo> commentVos;
    private GoodsVo goodsVo;

    public OrderComentVo() {
    }

    public OrderComentVo(Integer oId, Integer oStatus, Integer uId, Integer gId, Integer oCancelType, String oEvaluation, String oEvaluationAdd, String oCancelReason, Date oCreateTime, Date oPayTime, Date oRemindShipments, Date oShipmentsTime, Date oRemindReceive, Date oReceiveTime, Date oEvaluationTime, Date oEvaluationAddTime, Integer oSellDelete, Integer oBuyDelete, String oTradeNo, User user, List<OrderEvaluationMedia> orderEvaluationMedia, List<CommentVo> commentVos, GoodsVo goodsVo) {
        this.oId = oId;
        this.oStatus = oStatus;
        this.uId = uId;
        this.gId = gId;
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
        this.oTradeNo = oTradeNo;
        this.user = user;
        this.orderEvaluationMedia = orderEvaluationMedia;
        this.commentVos = commentVos;
        this.goodsVo = goodsVo;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getoStatus() {
        return oStatus;
    }

    public void setoStatus(Integer oStatus) {
        this.oStatus = oStatus;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderEvaluationMedia> getOrderEvaluationMedia() {
        return orderEvaluationMedia;
    }

    public void setOrderEvaluationMedia(List<OrderEvaluationMedia> orderEvaluationMedia) {
        this.orderEvaluationMedia = orderEvaluationMedia;
    }

    public List<CommentVo> getCommentVos() {
        return commentVos;
    }

    public void setCommentVos(List<CommentVo> commentVos) {
        this.commentVos = commentVos;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }

    @Override
    public String toString() {
        return "OrderComentVo{" +
                "oId=" + oId +
                ", oStatus=" + oStatus +
                ", uId=" + uId +
                ", gId=" + gId +
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
                ", user=" + user +
                ", orderEvaluationMedia=" + orderEvaluationMedia +
                ", commentVos=" + commentVos +
                ", goodsVo=" + goodsVo +
                '}';
    }
}
