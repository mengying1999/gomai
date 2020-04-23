package com.gomai.comment.vo;

import com.gomai.comment.pojo.OrderComment;
import com.gomai.comment.pojo.OrderEvaluationMedia;
import com.gomai.order.pojo.Order;
import com.gomai.user.pojo.User;
import com.gomai.user.pojo.UserAddress;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class OComentVo {
    private Order order;
    private List<OrderEvaluationMedia> orderEvaluationMedia;

    public OComentVo() {
    }

    public OComentVo(Order order, List<OrderEvaluationMedia> orderEvaluationMedia) {
        this.order = order;
        this.orderEvaluationMedia = orderEvaluationMedia;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderEvaluationMedia> getOrderEvaluationMedia() {
        return orderEvaluationMedia;
    }

    public void setOrderEvaluationMedia(List<OrderEvaluationMedia> orderEvaluationMedia) {
        this.orderEvaluationMedia = orderEvaluationMedia;
    }

    @Override
    public String toString() {
        return "OComentVo{" +
                "order=" + order +
                ", orderEvaluationMedia=" + orderEvaluationMedia +
                '}';
    }
}
