package com.gomai.comment.vo;

import com.gomai.comment.pojo.OrderComment;
import com.gomai.user.pojo.User;

import java.util.List;

public class UserCommentVo {
    private List<OrderComment> orderComment;
    private User user;

    public UserCommentVo() {
    }

    public UserCommentVo(List<OrderComment> orderComment, User user) {
        this.orderComment = orderComment;
        this.user = user;
    }

    public List<OrderComment> getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(List<OrderComment> orderComment) {
        this.orderComment = orderComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserCommentVo{" +
                "orderComment=" + orderComment +
                ", user=" + user +
                '}';
    }
}
