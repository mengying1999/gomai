package com.gomai.comment.service.impl;

import com.gomai.comment.mapper.OCOderMapper;
import com.gomai.comment.mapper.OComentVoMapper;
import com.gomai.comment.mapper.OcUserMapper;
import com.gomai.comment.service.OCOderService;
import com.gomai.comment.vo.ComUserVo;
import com.gomai.comment.vo.OComentVo;
import com.gomai.comment.vo.OrderMidaVo;
import com.gomai.order.pojo.Order;
import com.gomai.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OCOderServiceImpl implements OCOderService {
    @Autowired
    private OCOderMapper oderMapper;
    @Autowired
    private OComentVoMapper oComentVoMapper;
    @Autowired
    private OcUserMapper userMapper;

    @Override
    public Order selectByoId(Order o) {
        Order order=this.oderMapper.selectOne(o);
        return order;
    }

@Override
    public User selectUserByuId(Integer uId) {
        User user=this.userMapper.selectByPrimaryKey(uId);
        return user;
    }

    @Override
    public ComUserVo SecletByuId(Integer uid) {
        List<OrderMidaVo> oComentVo=this.oComentVoMapper.selectOrderVoByUId(uid);
        User user=this.userMapper.selectByPrimaryKey(uid);
        ComUserVo comUserVo=new ComUserVo();
        comUserVo.setoComentVos(oComentVo);
        comUserVo.setUser(user);
        return comUserVo;
    }

    @Override
    public int Updateoe(Order order) {
        int flag=this.oderMapper.updateByPrimaryKey(order);
        return flag;
    }

}
