package com.gomai.integral.mapper;


import com.gomai.intergral.pojo.IntegralGoods;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface IGoodsMapper extends Mapper<IntegralGoods> {
    @Select("SELECT   a.* FROM  integral_goods AS a WHERE(SELECT  COUNT(*)   FROM    integral_goods AS b  WHERE    b.ig_type = a.ig_type AND a.`ig_id`>=b.ig_id  ) <= 3 ORDER BY a.ig_type DESC, a.`ig_id` DESC")
    public List<IntegralGoods> selectNew();
}
