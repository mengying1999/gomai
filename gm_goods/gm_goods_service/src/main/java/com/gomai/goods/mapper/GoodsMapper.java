package com.gomai.goods.mapper;

import com.gomai.goods.pojo.Goods;
import com.gomai.goods.pojo.GoodsMedia;
import com.gomai.goods.pojo.Unshelve;
import com.gomai.goods.vo.GoodsVo;
import com.gomai.goods.vo.RecommendGoods;
import com.gomai.goods.vo.UnshelveGoodsVo;
import com.gomai.user.pojo.User;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Column;
import java.util.List;

public interface GoodsMapper extends Mapper<Goods>{

//    @Insert("insert into goods(g_id,u_id,ca2_id,g_name,g_detail,g_price,g_status,g_create_time) values(null,#uId,#ca2Id,#gName,#gDetail,#gPrice,#gStatus,#gCreateTime)")
//       boolean insertGoods(Goods goods);
@Select("select g_id,g_name,g_price from goods where g_create_time>='2020-05-20 00:00:00' and g_create_time<'2020-05-20 23:59:59' and g_status=0 order by g_create_time asc limit 8")
@Results(id = "findRecommendGoods",value = {@Result(id = true,column = "g_id",property = "gId"),
        @Result(column = "g_name",property = "gName"),
        @Result(column = "g_price",property = "gPrice"),
        @Result(column = "g_id",property = "goodsMedias",many = @Many(select = "com.gomai.goods.mapper.GoodsMapper.findRecommendGoodsMedia"))
})
  List<RecommendGoods> findRecommendGoods();//查询推荐商品

    @Select("select gm_id,g_id,gm_url,gm_type  from goods_media where g_id=#{g_id}&&gm_type=0 LIMIT 1")
    @Results(id = "findRecommendGoodsMedia",value = {@Result(id = true,column = "gm_id",property = "gmId"),
            @Result(column = "g_id",property = "gId"),
            @Result(column = "gm_url",property = "gmUrl"),
            @Result(column = "gm_type",property = "gmType"),
    })
    GoodsMedia findRecommendGoodsMedia(Integer g_id);//查询推荐商品的照片（一张）

    @Select("select gm_id,g_id,gm_url,gm_type  from goods_media where g_id=#{g_id}")
    @Results(id = "findGoodsMedia",value = {@Result(id = true,column = "gm_id",property = "gmId"),
            @Result(column = "g_id",property = "gId"),
            @Result(column = "gm_url",property = "gmUrl"),
            @Result(column = "gm_type",property = "gmType"),
    })
    List<GoodsMedia> findGoodsMedia(Integer g_id);//查询某个商品的所有媒体

    @Select("select u_id,u_name,u_avatar from user where u_id=#{u_id}")
    @Results(id = "findGoodsUserDetail",value = {@Result(id = true,column = "u_id",property = "uId"),
            @Result(column = "u_name",property = "uName"),
            @Result(column = "u_avatar",property = "uAvatar"),
    })
    User findGoodsUserDetail(Integer u_id);//查询某个商品的所有媒体

    @Select("select * from goods where g_id=#{g_id}")
    @Results(id = "findGoodsDetail",value = {@Result(id = true,column = "g_id",property = "gId"),
            @Result(column = "ca2_id",property = "ca2Id"),
            @Result(column = "g_name",property = "gName"),
            @Result(column = "g_detail",property = "gDetail"),
            @Result(column = "g_price",property = "gPrice"),
            @Result(column = "g_status",property = "gStatus"),
            @Result(column = "g_create_time",property = "gCreateTime"),
            @Result(column = "u_id",property = "user",many = @Many(select = "com.gomai.goods.mapper.GoodsMapper.findGoodsUserDetail")),
            @Result(column = "g_id",property = "goodsMedias",many = @Many(select = "com.gomai.goods.mapper.GoodsMapper.findGoodsMedia"))
    })
    GoodsVo findGoodsDetail(Integer g_id);//根据id查询商品详情

  @Select({"<script>", "select g_id,g_name,g_price from goods where g_status=0 and ca2_id in ","<foreach collection='categoryTwoList' item='ca2_id' open='(' separator=',' close=')'>#{ca2_id}</foreach>","</script>"})
//  @Select({"<script>", "select * from integral_exchange where u_id=#{uid} and ie_type in ","<foreach collection='types' item='type' open='(' separator=',' close=')'>#{type}</foreach>","</script>"})
  @Results(id = "findGoodsByType",value = {@Result(id = true,column = "g_id",property = "gId"),
          @Result(column = "g_name",property = "gName"),
          @Result(column = "g_price",property = "gPrice"),
          @Result(column = "g_id",property = "goodsMedias",many = @Many(select = "com.gomai.goods.mapper.GoodsMapper.findRecommendGoodsMedia"))
  })

    List<RecommendGoods> findGoodsByType(@Param("categoryTwoList") List<Integer> categoryTwoList);

  @Select( "select g_id,g_name,g_price from goods where g_status=0 and g_detail like CONCAT('%',#{g_detail},'%')")
  @Results(id = "findGoodsByDetail",value = {@Result(id = true,column = "g_id",property = "gId"),
          @Result(column = "g_name",property = "gName"),
          @Result(column = "g_price",property = "gPrice"),
          @Result(column = "g_id",property = "goodsMedias",many = @Many(select = "com.gomai.goods.mapper.GoodsMapper.findRecommendGoodsMedia"))
  })
    List<RecommendGoods> findGoodsByDetail(String g_detail);

  @Select( "select g_id,g_name,g_price from goods where u_id=#{u_id} and g_status=0 ")
  @Results(id = "findGoodsByuId",value = {@Result(id = true,column = "g_id",property = "gId"),
          @Result(column = "g_name",property = "gName"),
          @Result(column = "g_price",property = "gPrice"),
          @Result(column = "g_id",property = "goodsMedias",many = @Many(select = "com.gomai.goods.mapper.GoodsMapper.findRecommendGoodsMedia"))
  })
    List<RecommendGoods> findGoodsByuId(@Param("u_id") Integer u_id);


  @Select("select * from goods_unshelve where g_id=#{g_id}")
  @Results(id = "findUnshelveGoodsDetail",value = {@Result(id = true,column = "un_id",property = "unId"),
          @Result(column = "g_id",property = "gId"),
          @Result(column = "un_reason",property = "unReason"),
          @Result(column = "un_create_time",property = "unCreateTime"),
  })
  Unshelve findUnshelveGoodsDetail(Integer g_id);//根据商品id查询该下架信息


  @Select("select g_id,g_name,g_price from goods where u_id=#{u_id}&&g_status=1 ")
  @Results(id = "findUnshelveGoodsByuId",value = {@Result(id = true,column = "g_id",property = "gId"),
          @Result(column = "g_name",property = "gName"),
          @Result(column = "g_price",property = "gPrice"),
          @Result(column = "g_id",property = "unshelveGoods",one = @One(select = "com.gomai.goods.mapper.GoodsMapper.findUnshelveGoodsDetail")),
          @Result(column = "g_id",property = "goodsMedias",one = @One(select = "com.gomai.goods.mapper.GoodsMapper.findRecommendGoodsMedia"))
  })
   List<UnshelveGoodsVo> findUnshelveGoodsByuId(Integer u_id);


}

