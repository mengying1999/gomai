package com.gomai.goods.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gomai.goods.pojo.Goods;
import com.gomai.goods.pojo.GoodsMedia;
import com.gomai.goods.pojo.Unshelve;
import com.gomai.goods.service.Category2Service;
import com.gomai.goods.service.GoodsMediaService;
import com.gomai.goods.service.GoodsService;
import com.gomai.goods.service.UnshelveService;
import com.gomai.goods.service.impl.Category1ServiceImpl;
import com.gomai.goods.vo.GoodsVo;
import com.gomai.goods.vo.RecommendGoods;
import com.gomai.goods.vo.UnshelveGoodsVo;
import com.gomai.utils.PageResult;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsMediaService goodsMediaService;
    @Autowired
    private Category2Service category2Service;
    @Autowired
    private UnshelveService unshelveService;

    @Transactional //回滚
    @PostMapping("/insertGoods")
    public  ReturnMessage<Object> insertGoods(@RequestBody GoodsVo goodsVo){//发布商品

        System.out.println(goodsVo);
        Date goodCreateTime = new Date();
        goodsVo.setgCreateTime(goodCreateTime);
        int flag=0;
        int flagMedia=0;
        if ((goodsVo.getUser().getuId() == null ||goodsVo.getUser().getuId() <0)&&(goodsVo.getCa2Id()==null||goodsVo.getCa2Id() <0)
                ) {
            throw new SbException(400, "商品信息不完整！");
        } else {
            //goodsVo.setUser(goodsVo.);  userId？
            Goods goods = new Goods();
            goods.setuId(goodsVo.getUser().getuId());
            goods.setCa2Id(goodsVo.getCa2Id());
            goods.setgName(goodsVo.getgName());
            goods.setgDetail(goodsVo.getgDetail());
            goods.setgPrice(goodsVo.getgPrice());
            goods.setgStatus(goodsVo.getgStatus());
            goods.setgCreateTime(goodsVo.getgCreateTime());
             flag = this.goodsService.addGoods(goods);
            System.out.println(flag);

            for (GoodsMedia media : goodsVo.getGoodsMedias()) {
                media.setgId(goods.getgId());
            }
             flagMedia = this.goodsMediaService.addGoodsMedia(goodsVo.getGoodsMedias());
            System.out.println(flagMedia);
        }
            boolean flagAll = false;
            if (flag != 0 && flagMedia != 0) {
                flagAll = true;
            }
        System.out.println(flagAll);
        return ReturnMessageUtil.sucess(flagAll);
    }

    @GetMapping(value = "/findRecommendGoods" )
    public ReturnMessage<Object> findRecommendGoods(){//推荐商品

        List<RecommendGoods> goodsRecommendList = this.goodsService.findRecommendGoods();
        return ReturnMessageUtil.sucess(goodsRecommendList);
    }

    @GetMapping(value = "/findGoodsDetail" )
    public ReturnMessage<Object> findGoodsDetail(Integer g_id){//商品详情

        GoodsVo goodsDetail = this.goodsService.findGoodsDetail(g_id);
        System.out.println(goodsDetail);
        return ReturnMessageUtil.sucess(goodsDetail);
    }

    @GetMapping(value = "/findGoodsByType/{ca1_id}/{size}/{currentPage}" )//size:大小（每页几个）currentPage:第几页
    public ReturnMessage<Object> findGoodsByType(@PathVariable("ca1_id")Integer ca1_id,@PathVariable("size")Integer size,@PathVariable("currentPage")Integer currentPage){//按ca1_id（类别）查询商品

        List<Integer> categoryTwoList=this.category2Service.findCategoryTwoByOne(ca1_id);//根据ca1_id查找对应的ca2类别
       // GoodsVo goodsDetail = this.goodsService.findGoodsDetail(g_id);
        System.out.println(categoryTwoList);
        PageHelper.startPage(currentPage,size);
        List<RecommendGoods> goodsByTypeList=this.goodsService.findGoodsByType(categoryTwoList);
        PageResult pageResult = new PageResult();
        pageResult.setRows(goodsByTypeList);
        pageResult.setTotal(new PageInfo(goodsByTypeList).getTotal());
        System.out.println(goodsByTypeList);
        return ReturnMessageUtil.sucess(pageResult);
    }

    @GetMapping(value = "/findGoodsByDetail/{g_detail}/{size}/{currentPage}" )
    public ReturnMessage<Object> findGoodsByDetail(@PathVariable("g_detail")String g_detail,@PathVariable("size")Integer size,@PathVariable("currentPage")Integer currentPage){//按照搜索内容查询商品
        System.out.println(g_detail);
        PageHelper.startPage(currentPage,size);
        List<RecommendGoods> goodsByDetailList=this.goodsService.findGoodsByDetail(g_detail);
        PageResult pageResult = new PageResult();
        pageResult.setRows(goodsByDetailList);
        pageResult.setTotal(new PageInfo(goodsByDetailList).getTotal());
        System.out.println(goodsByDetailList);
        return ReturnMessageUtil.sucess(pageResult);
    }

    @GetMapping(value = "/findGoodsByuId/{u_id}/{size}/{currentPage}" )
    public ReturnMessage<Object> findGoodsByuId(@PathVariable("u_id")Integer u_id,@PathVariable("size")Integer size,@PathVariable("currentPage")Integer currentPage){//根据用户id查询自己发布的商品
        PageHelper.startPage(currentPage,size);
        List<RecommendGoods> goodsByuIdList = this.goodsService.findGoodsByuId(u_id);
        PageResult pageResult = new PageResult();
        pageResult.setRows(goodsByuIdList);
        pageResult.setTotal(new PageInfo(goodsByuIdList).getTotal());
        System.out.println(goodsByuIdList);
        return ReturnMessageUtil.sucess(pageResult);
    }

    @GetMapping(value = "/findUnshelveGoodsByuId/{u_id}/{size}/{currentPage}" )
    public ReturnMessage<Object> findUnshelveGoodsByuId(@PathVariable("u_id")Integer u_id,@PathVariable("size")Integer size,@PathVariable("currentPage")Integer currentPage){//根据用户id查询自己的下架商品
        PageHelper.startPage(currentPage,size);
        List<UnshelveGoodsVo> unshelveGoodsByuIdList = this.goodsService.findUnshelveGoodsByuId(u_id);
        PageResult pageResult = new PageResult();
        pageResult.setRows(unshelveGoodsByuIdList);
        pageResult.setTotal(new PageInfo(unshelveGoodsByuIdList).getTotal());
        System.out.println(unshelveGoodsByuIdList);
        return ReturnMessageUtil.sucess(pageResult);
    }

    @Transactional //回滚
    @PostMapping("/updateGoodPrice/{gId}/{gPrice}")
    public  ReturnMessage<Object> updateGoodPrice(@PathVariable("gId") Integer g_id,@PathVariable("gPrice")  Double g_price){//修改商品价格
        System.out.println(g_id);
        System.out.println(g_price);
        Goods goods = goodsService.queryGoodsByGId(g_id);
        if (StringUtils.isEmpty(goods)){
            throw new SbException(100, "无该商品!");
        }
        goods.setgPrice(g_price);
        int flag = goodsService.updateGoods(goods);
        if (flag == 0){
            throw new SbException(100, "修改失败!");
        }
        return ReturnMessageUtil.sucess(flag);
    }


    @Transactional //回滚
    @PostMapping("/unshelveGoods/{gId}")
    public  ReturnMessage<Object> unshelveGoods(@PathVariable("gId") Integer g_id){//根据gid商品下架
        //1.商品表中gStatus下架状态：0->1
        System.out.println(g_id);
        Goods goods = goodsService.queryGoodsByGId(g_id);
        if (StringUtils.isEmpty(goods)){
            throw new SbException(100, "无该商品!");
        }
        if(goods.getgStatus()==1){
            throw new SbException(100, "该商品已下架!");
        }else{
            //Integer gStatus=1;
            goods.setgStatus(1);
        }
        int flag1 = goodsService.updateGoods(goods);
        if (flag1 == 0){
            throw new SbException(100, "修改失败!");
        }
        //2.往下架表中插入下架信息
        Date un_create_time = new Date();
        Unshelve unshelve = new Unshelve();
        unshelve.setgId(g_id);
        unshelve.setUnCreateTime(un_create_time);
        System.out.println(un_create_time);
        unshelve.setUnReason("主动下架");
         flag1 = this.unshelveService.addUnshelve(unshelve);
        if (flag1 == 0){
            throw new SbException(100, "添加失败!");
        }
        //int flag=1;

        return ReturnMessageUtil.sucess(true);
    }

    @Transactional //回滚
    @PostMapping("/deleteUnshelveGoods/{gId}")
    public  ReturnMessage<Object> deleteUnshelveGoods(@PathVariable("gId") Integer g_id){ //根据gid将下架商品重新上架
        //1.商品表中gStatus下架状态：1—>0
        System.out.println(g_id);
        Goods goods = goodsService.queryGoodsByGId(g_id);
        List<Unshelve> unshelveGood=unshelveService.queryUnshelveGoodsByGId(g_id);
        System.out.println(unshelveGood);
        if (StringUtils.isEmpty(goods)){
            throw new SbException(100, "无该商品!");
        }
        if (CollectionUtils.isEmpty(unshelveGood)){
            throw new SbException(100, "该商品未下架!");
        }
        if (StringUtils.isEmpty(unshelveGood.get(0))){
              throw new SbException(100, "该商品未下架!");
        }

        if(goods.getgStatus()==0){
            throw new SbException(100, "该商品未下架!");
        }else{
            //Integer gStatus=1;
            goods.setgStatus(0);
        }
        int flag1 = goodsService.updateGoods(goods);
        if (flag1 == 0){
            throw new SbException(100, "重新上架失败!");
        }
        //2.下架表中删除该商品下架记录
        flag1 = this.unshelveService.deleteUnshelve(g_id);
        if (flag1 == 0){
            throw new SbException(100, "删除失败!");
        }
        //int flag=1;

        return ReturnMessageUtil.sucess(true);
    }

    @Transactional //回滚
    @PostMapping("/deleteGoodsByGId/{gId}")
    public  ReturnMessage<Object> deleteGoodsByGId(@PathVariable("gId") Integer g_id){//根据gid将下架商品删除
        //1.商品表中gStatus下架状态：1—>2
        System.out.println(g_id);
        Goods goods = goodsService.queryGoodsByGId(g_id);
        List<Unshelve> unshelveGood=unshelveService.queryUnshelveGoodsByGId(g_id);
        if (StringUtils.isEmpty(goods)){
            throw new SbException(100, "无该商品!");
        }

        if (CollectionUtils.isEmpty(unshelveGood)){
            throw new SbException(100, "该商品未下架或已删除，暂时无法删除!");
        }
        if (StringUtils.isEmpty(unshelveGood.get(0))){
            throw new SbException(100, "该商品未下架或已删除，暂时无法删除!");
        }
        if(goods.getgStatus()==0){
            throw new SbException(100, "该商品未下架，暂时无法删除!");
        }
        if(goods.getgStatus()==2){
            throw new SbException(100, "该商品已删除!");
        }else{
            //Integer gStatus=1;
            goods.setgStatus(2);
        }
        int flag1 = goodsService.updateGoods(goods);
        if (flag1 == 0){
            throw new SbException(100, "删除失败!");
        }
        //2.下架表中删除该商品下架记录
        flag1 = this.unshelveService.deleteUnshelve(g_id);
        if (flag1 == 0){
            throw new SbException(100, "删除失败!");
        }
        //int flag=1;

        return ReturnMessageUtil.sucess(true);
    }
}
