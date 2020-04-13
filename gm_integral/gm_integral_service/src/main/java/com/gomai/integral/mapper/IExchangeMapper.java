package com.gomai.integral.mapper;


import com.gomai.goods.pojo.Goods;
import com.gomai.integral.vo.GoodsVo;
import com.gomai.integral.vo.IChangeVo;
import com.gomai.integral.vo.IGoodsVo;
import com.gomai.intergral.pojo.IntegralExchange;
import com.gomai.intergral.pojo.IntegralGoods;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


//tk.mybatis.mapper.common.Mapper通用mapper

public interface IExchangeMapper extends Mapper<IntegralExchange> {

    @Select("select * from integral_goods where ig_id = #{igId}")
    public IntegralGoods selectIGoodsByigId(int igId);

    @Results(value={
            @Result(id=true,column="ie_id" , property="ieId"),
            @Result( column="ig_id" ,property="igId"),
            @Result( column="g_id" ,property="gId"),
            @Result( column="u_id" ,property="uId"),
            @Result( column="ie_create_time" ,property="ieCreateTime"),
            @Result( column="ie_change_integral" ,property="ieChangeIntegral"),
            @Result( column="ie_total_integral" ,property="ieTotalIntegral"),
            @Result( column="g_create_time" ,property="gCreateTime"),
            @Result( column="ie_type" ,property="ieType"),
            @Result(property="goodsVo",column="g_id" ,one=@One(select="com.gomai.integral.mapper.IEGoodsMapper.selectGoodsVoByGId")),
            @Result(property="integralGoods",column="ig_id" ,one=@One(select="com.gomai.integral.mapper.IExchangeMapper.selectIGoodsByigId"))
    }
    )
    @Select({"<script>", "select * from integral_exchange where u_id=#{uid} and ie_type in ","<foreach collection='types' item='type' open='(' separator=',' close=')'>#{type}</foreach>","</script>"})
    List<IChangeVo> selectIChangeVoByTypes(@Param("uid") Integer uid,@Param("types") List<Integer> types);
}
