package com.gomai.integral.mapper;


import com.gomai.intergral.pojo.IntegralGoods;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface IGoodsMapper extends Mapper<IntegralGoods> {
    @Select("select a.* from integral_goods a where ig_id in (select b.ig_id from integral_goods b where b.ig_type = a.ig_type ORDER BY ig_id LIMIT 0,3) order by a.ig_type")
    public List<IntegralGoods> selectNew();
}
