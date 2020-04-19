package com.gomai.goods.service;

import com.gomai.goods.pojo.Goods;
import com.gomai.goods.vo.GoodsVo;
import com.gomai.goods.vo.RecommendGoods;
import com.gomai.goods.vo.UnshelveGoodsVo;

import java.util.List;

public interface GoodsService {

    public int addGoods(Goods good);//发布商品
    public  List<RecommendGoods> findRecommendGoods() ;//推荐商品数据
    public GoodsVo findGoodsDetail(Integer g_id) ;//按id查询商品详情
    public  List<RecommendGoods> findGoodsByType(List<Integer> categoryTwoList);//根据ca2_id查询对应商品
    public  List<RecommendGoods> findGoodsByDetail(String g_detail);//根据搜索内容查询对应商品
    public  List<RecommendGoods> findGoodsByuId(Integer u_id);//根据用户id查询发布的商品
    public  List<UnshelveGoodsVo> findUnshelveGoodsByuId(Integer u_id);//根据用户id查询自己的下架商品
    public Goods queryGoodsByGId (Integer g_id) ;//按id查询商品表（不带媒体）
    public int updateGoods(Goods goods);//修改商品
}
