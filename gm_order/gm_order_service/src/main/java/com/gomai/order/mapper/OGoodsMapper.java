package com.gomai.order.mapper;

import com.gomai.goods.pojo.Goods;
import com.gomai.goods.pojo.GoodsMedia;
import com.gomai.order.vo.GoodsVo;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Column;
import java.util.List;
public interface OGoodsMapper  extends Mapper<Goods> {
    @Results(value={
            @Result(id=true,column="g_id" , property="gId"),
            @Result( column="u_id" ,property="uId"),
            @Result( column="ca2_id" ,property="ca2Id"),
            @Result( column="g_name" ,property="gName"),
            @Result( column="g_detail" ,property="gDetail"),
            @Result( column="g_price" ,property="gPrice"),
            @Result( column="g_status" ,property="gStatus"),
            @Result( column="g_create_time" ,property="gCreateTime"),
            @Result(property="goodsMedias",column="g_id" ,many=@Many(select="com.gomai.order.mapper.OGoodsMapper.selectGoodsMediaByGId")
            )}
    )
    @Select("select * from goods where g_id = #{gId}")
    public GoodsVo selectGoodsVoByGId(Integer gId);

    @Select("select gm_id gmId,g_id gId , gm_url gmUrl,gm_type gmType from goods_media where g_id= #{gId}")
    public List<GoodsMedia> selectGoodsMediaByGId(Integer gId);
}
