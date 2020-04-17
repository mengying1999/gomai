package com.gomai.order.vo;

import com.gomai.order.pojo.OrderReturn;
import com.gomai.order.pojo.OrderReturnMedia;

import java.util.List;

public class AskReturnParams {
    private Integer oId;
    private Integer uId;
    private Integer oStatus;
    private OrderReturn orderReturn;
    private List<OrderReturnMedia> orderReturnMedias;

    public AskReturnParams() {
    }

    public AskReturnParams(Integer oId, Integer uId, Integer oStatus, OrderReturn orderReturn, List<OrderReturnMedia> orderReturnMedias) {
        this.oId = oId;
        this.uId = uId;
        this.oStatus = oStatus;
        this.orderReturn = orderReturn;
        this.orderReturnMedias = orderReturnMedias;
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

    public Integer getoStatus() {
        return oStatus;
    }

    public void setoStatus(Integer oStatus) {
        this.oStatus = oStatus;
    }

    public OrderReturn getOrderReturn() {
        return orderReturn;
    }

    public void setOrderReturn(OrderReturn orderReturn) {
        this.orderReturn = orderReturn;
    }

    public List<OrderReturnMedia> getOrderReturnMedias() {
        return orderReturnMedias;
    }

    public void setOrderReturnMedias(List<OrderReturnMedia> orderReturnMedias) {
        this.orderReturnMedias = orderReturnMedias;
    }

    @Override
    public String toString() {
        return "AskReturnParams{" +
                "oId=" + oId +
                ", uId=" + uId +
                ", oStatus=" + oStatus +
                ", orderReturn=" + orderReturn +
                ", orderReturnMedias=" + orderReturnMedias +
                '}';
    }
}
