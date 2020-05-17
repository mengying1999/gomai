package com.gomai.order.controller;

import com.gomai.goods.pojo.Goods;
import com.gomai.goods.pojo.Unshelve;
import com.gomai.order.delay.DelayService;
import com.gomai.order.delay.DshOrder;
import com.gomai.order.pojo.Order;
import com.gomai.order.service.*;
import com.gomai.order.vo.GoodsVo;
import com.gomai.user.pojo.User;
import com.gomai.user.pojo.UserAddress;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/oGoods")
public class OGoodsController {
    @Autowired
    private OGoodsService oGoodsService;
    @Autowired
    private OrderService orderService;
    /**
     * 根据GId查询该商品的信息,从商品详情跳转到订单生成页时验证商品是否符合跳转条件
     * 1.判断gId是否合法
     * 2.根据gId查询商品，看看是否为null
     * 3.根据gId查询是否存在订单且订单信息为待付款，符合不走4，提醒用户该商品已经被拍下还未付款，您还有机会，不符合走4
     * 4.根据gId查询是否该商品已经下架，提醒该商品已经下架
     * @return
     */
    @GetMapping("/verifyGoodsByGId/{gId}/{uId}")
    public ReturnMessage<Object> verifyGoodsByGId(@PathVariable("gId") Integer gId,@PathVariable("uId") Integer uId){
//        1.判断gId是否合法
        if(gId == null || gId <0){
            throw new SbException(400, "不合法字符");
        }
//        2.根据gId查询商品，看看是否为null
        GoodsVo goodsVo = this.oGoodsService.queryGoodsVoByGId(gId);
        if (StringUtils.isEmpty(goodsVo)) {
            throw new SbException(100, "该商品不存在!");
        }
        if(goodsVo.getUser().getuId().equals(uId)){
            throw new SbException(100, "不能购买自己发布的商品!");
        }
//        3.根据gId查询是否存在该订单且订单信息为待付款，符合不走4，提醒用户该商品已经被拍下还未付款，您还有机会，不符合走4
        List<Order> orders = this.orderService.queryPayOrderByGId(gId);
        if (CollectionUtils.isEmpty(orders)) {  //没有该商品的待付款订单
//       4.根据gId查询是否该商品已经下架，提醒该商品已经下架
            if (goodsVo.getgStatus() == 1){  // 如果已经下架
                throw new SbException(100, "该商品已下架!");
            }
        }else {
            throw new SbException(100, "该商品已被拍下，您还有机会!");
        }
        return ReturnMessageUtil.sucess(goodsVo);
    }
}