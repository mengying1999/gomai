package com.gomai.goods.service.impl;

import com.gomai.goods.mapper.GoodsMapper;
import com.gomai.goods.pojo.Goods;
import com.gomai.goods.service.GoodsService;
import com.gomai.goods.vo.GoodsVo;
import com.gomai.goods.vo.RecommendGoods;
import com.gomai.goods.vo.UnshelveGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int addGoods(Goods good){

        int flag =  goodsMapper.insertSelective(good);
        return flag;
    }
    @Override
    public  List<RecommendGoods> findRecommendGoods(){
        return this.goodsMapper.findRecommendGoods();
    }
    @Override
    public GoodsVo findGoodsDetail(Integer g_id){
        return this.goodsMapper.findGoodsDetail(g_id);
    }
    @Override
    public  List<RecommendGoods> findGoodsByType(List<Integer> categoryTwoList){//根据ca2_id查询对应商品
        return this.goodsMapper.findGoodsByType(categoryTwoList);
    }
    @Override
    public  List<RecommendGoods> findGoodsByDetail(String g_detail){
        System.out.println(g_detail);
        return this.goodsMapper.findGoodsByDetail(g_detail);
    }//根据搜索内容查询对应商品
    @Override
    public  List<RecommendGoods> findGoodsByuId(Integer u_id){

        return this.goodsMapper.findGoodsByuId(u_id);

    }//根据用户id查询发布的商品
    @Override
    public  List<UnshelveGoodsVo> findUnshelveGoodsByuId(Integer u_id){
        return this.goodsMapper.findUnshelveGoodsByuId(u_id);
    }//根据用户id查询自己的下架商品

    @Override
    public Goods queryGoodsByGId(Integer g_id) {//按id查询商品表（不带媒体）
        Goods goods = this.goodsMapper.selectByPrimaryKey(g_id);
        return goods;
    }
    @Override
    public int updateGoods(Goods goods) {//修改商品
        int flag = this.goodsMapper.updateByPrimaryKey(goods);
        return flag;
    }
}
