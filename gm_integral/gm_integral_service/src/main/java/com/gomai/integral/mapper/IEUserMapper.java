package com.gomai.integral.mapper;



import com.gomai.intergral.pojo.IntegralExchange;

import com.gomai.intergral.pojo.IntegralGoods;
import com.gomai.user.pojo.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


//tk.mybatis.mapper.common.Mapper通用mapper

public interface IEUserMapper extends Mapper<User> {
}
