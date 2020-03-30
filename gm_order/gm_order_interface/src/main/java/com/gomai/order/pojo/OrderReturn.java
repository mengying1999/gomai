package com.gomai.order.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单退货退款表
 */
@Entity
@Table(name = "order_return")
public class OrderReturn {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "or_id")
    private Integer orId; //退货退款id
    @Column(name = "o_id")
    private Integer oId; //订单id
    @Column(name = "or_reason")
    private String orReason; //退货退款理由
    @Column(name = "or_status")
    private Integer orStatus; //退货退款状态
    @Column(name = "or_received")
    private Integer orReceived; //是否已收货
    @Column(name = "or_create_time")
    private Date orCreateTime; //创建时间

    public OrderReturn() {
    }

    public OrderReturn(Integer oId, String orReason, Integer orStatus, Integer orReceived, Date orCreateTime) {
        this.oId = oId;
        this.orReason = orReason;
        this.orStatus = orStatus;
        this.orReceived = orReceived;
        this.orCreateTime = orCreateTime;
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

    @Override
    public String toString() {
        return "OrderReturn{" +
                "orId=" + orId +
                ", oId=" + oId +
                ", orReason='" + orReason + '\'' +
                ", orStatus=" + orStatus +
                ", orReceived=" + orReceived +
                ", orCreateTime=" + orCreateTime +
                '}';
    }
}
