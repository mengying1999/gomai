package com.gomai.comment.vo;

import com.gomai.user.pojo.User;

import java.util.List;

public class ComUserVo {
    private List<OrderMidaVo> oComentVos;
    private User user;

    public ComUserVo() {
    }

    public ComUserVo(List<OrderMidaVo> oComentVos, User user) {
        this.oComentVos = oComentVos;
        this.user = user;
    }

    public List<OrderMidaVo> getoComentVos() {
        return oComentVos;
    }

    public void setoComentVos(List<OrderMidaVo> oComentVos) {
        this.oComentVos = oComentVos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ComUserVo{" +
                "oComentVo=" + oComentVos +
                ", user=" + user +
                '}';
    }
}
