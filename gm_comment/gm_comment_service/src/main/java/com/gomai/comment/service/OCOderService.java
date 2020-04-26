package com.gomai.comment.service;

import com.gomai.comment.vo.ComUserVo;
import com.gomai.comment.vo.OComentVo;
import com.gomai.order.pojo.Order;
import com.gomai.user.pojo.User;

import java.util.List;

public interface OCOderService {
    public Order selectByoId(Order order);

    public ComUserVo SecletByuId(Integer uid);

    public int Updateoe(Order order);
    public User selectUserByuId(Integer uId);
}
