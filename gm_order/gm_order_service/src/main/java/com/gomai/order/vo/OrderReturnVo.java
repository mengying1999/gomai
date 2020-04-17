package com.gomai.order.vo;

import com.gomai.order.pojo.OrderReturnMedia;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

public class OrderReturnVo {
    private Integer orId; //退货退款id
    private Integer oId; //订单id
    private String orReason; //退货退款理由
    private Integer orStatus; //退货退款状态
    private Integer orReceived; //是否已收货
    private Date orCreateTime; //创建时间
    private Date orRefusedTime;
    private List<OrderReturnMedia> orderReturnMedias;

    public OrderReturnVo() {
    }

    public OrderReturnVo(Integer orId, Integer oId, String orReason, Integer orStatus, Integer orReceived, Date orCreateTime, Date orRefusedTime, List<OrderReturnMedia> orderReturnMedias) {
        this.orId = orId;
        this.oId = oId;
        this.orReason = orReason;
        this.orStatus = orStatus;
        this.orReceived = orReceived;
        this.orCreateTime = orCreateTime;
        this.orRefusedTime = orRefusedTime;
        this.orderReturnMedias = orderReturnMedias;
    }

    public Integer getOrId() {
        return orId;
    }

    public void setOrId(Integer orId) {
        this.orId = orId;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getOrReason() {
        return orReason;
    }

    public void setOrReason(String orReason) {
        this.orReason = orReason;
    }

    public Integer getOrStatus() {
        return orStatus;
    }

    public void setOrStatus(Integer orStatus) {
        this.orStatus = orStatus;
    }

    public Integer getOrReceived() {
        return orReceived;
    }

    public void setOrReceived(Integer orReceived) {
        this.orReceived = orReceived;
    }

    public Date getOrCreateTime() {
        return orCreateTime;
    }

    public void setOrCreateTime(Date orCreateTime) {
        this.orCreateTime = orCreateTime;
    }

    public List<OrderReturnMedia> getOrderReturnMedias() {
        return orderReturnMedias;
    }

    public void setOrderReturnMedias(List<OrderReturnMedia> orderReturnMedias) {
        this.orderReturnMedias = orderReturnMedias;
    }

    public Date getOrRefusedTime() {
        return orRefusedTime;
    }

    public void setOrRefusedTime(Date orRefusedTime) {
        this.orRefusedTime = orRefusedTime;
    }

    @Override
    public String toString() {
        return "OrderReturnVo{" +
                "orId=" + orId +
                ", oId=" + oId +
                ", orReason='" + orReason + '\'' +
                ", orStatus=" + orStatus +
                ", orReceived=" + orReceived +
                ", orCreateTime=" + orCreateTime +
                ", orRefusedTime=" + orRefusedTime +
                ", orderReturnMedias=" + orderReturnMedias +
                '}';
    }
}
