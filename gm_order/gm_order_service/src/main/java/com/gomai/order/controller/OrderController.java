package com.gomai.order.controller;

import com.gomai.goods.pojo.Goods;
import com.gomai.goods.pojo.Unshelve;
import com.gomai.order.pojo.Order;
import com.gomai.order.service.*;
import com.gomai.order.vo.GoodsVo;
import com.gomai.user.pojo.User;
import com.gomai.user.pojo.UserAddress;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OGoodsService oGoodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OAddressService oAddressService;

    @Autowired
    private OUserService oUserService;

    @Autowired
    private OUnshelveService oUnshelveService;

    /**
     *生成订单
     * 1. 判断order是否为空
     * 2. 判断gId,uId,uaId是否为空
     * 3. 根据gId查询商品，判断查询的商品是否为null
     * 4. 判断商品的uId与买家uId是否相同
     * 5. 根据gId查询是否存在订单且订单信息为待付款，提醒用户该商品已经被拍下还未付款，您还有机会
     * 6. 判断该商品是否下架
     * 7. 根据uaId查询地址信息，判断是否为空
     * 8. 判断地址的uId与买家的uId是否相同
     * 9. 根据uId查询用户信息，判断是否为null
     * 10. 数据操作（应该满足原子性）：
     *          插入订单数据包括：gId,uId,uaId,oStatus,oCreateTime,oSellDelete,oBuyDelete
     *          修改商品信息：将下架状态改成已下架
     *          插入下架商品信息：包括gId,unReason,unCreateTime
     * 11.创建定时器：创建一个1小时之内没有付款就自动取消订单的定时器
     * 12.返回成功与否
     * @param order
     * @return
     */
    @GetMapping("/generateOrder")
    public ReturnMessage<Object> verifyGoodsByGId(Order order){
//        1. 判断order是否为空  2. 判断gId,uId,uaId是否为空
        if(StringUtils.isEmpty(order) && (order.getgId() == null || order.getgId() <0) &&(order.getuId() == null || order.getuId() <0)&&(order.getUaId() == null || order.getUaId() <0)){
            throw new SbException(400, "输入不合法");
        }
//      3.根据gId查询商品，看看是否为null
        Goods goods= this.oGoodsService.queryGoodsByGId(order.getgId());
        if (StringUtils.isEmpty(goods)) {
            throw new SbException(100, "该商品不存在!");
        }
//      4. 判断商品的uId与买家uId是否相同
        if(goods.getuId() == order.getuId()){
            throw new SbException(100, "不能购买自己发布的商品!");
        }
//      5. 根据gId查询是否存在订单且订单信息为待付款，提醒用户该商品已经被拍下还未付款，您还有机会
        List<Order> orders = this.orderService.queryPayOrderByGId(order.getgId());
        if (CollectionUtils.isEmpty(orders)) {  //没有该商品的待付款订单
//       6.根据gId查询是否该商品已经下架，提醒该商品已经下架
            if (goods.getgStatus() == 1){  // 如果已经下架
                throw new SbException(100, "该商品已下架!");
            }
        }else {
            throw new SbException(100, "该商品已被拍下，您还有机会!");
        }
//     7. 根据uaId查询地址信息，判断是否为空
        UserAddress userAddress = this.oAddressService.queryUserAddressByUaId(order.getUaId());
        if (StringUtils.isEmpty(userAddress)){
            throw new SbException(100, "地址信息错误!");
        }
//     8. 判断地址的uId与买家的uId是否相同
        if(userAddress.getuId() != order.getuId()) {
            throw new SbException(100, "地址信息错误!");
        }
//     9. 根据uId查询用户信息，判断是否为null
        User user = this.oUserService.queryUserByUId(order.getuId());
        if (StringUtils.isEmpty(user)){
            throw new SbException(100, "用户信息错误!");
        }
//     10. 数据操作（应该满足原子性）：
//        (1) 插入订单数据包括：gId,uId,uaId,oStatus,oCreateTime,oSellDelete,oBuyDelete
        order.setoStatus(1);//刚创建订单时为待付款
        Date oCreateTime = new Date();
        order.setoCreateTime(oCreateTime);
        order.setoSellDelete(0);
        order.setoBuyDelete(0);
        int flag = this.orderService.addOrder(order);
        if (flag == 0){
            throw new SbException(100, "添加失败!");
        }
//        (2) 修改商品信息：将下架状态改成已下架,插入下架信息
        goods.setgStatus(1);
        flag = this.oGoodsService.updateGoods(goods);
        if (flag == 0){
                throw new SbException(100, "添加失败!");
        }
//        (3) 插入下架商品信息：包括gId,unReason,unCreateTime
        Unshelve unshelve = new Unshelve();
        unshelve.setgId(order.getgId());
        unshelve.setUnCreateTime(oCreateTime);
        unshelve.setUnReason("商品已卖出");
        flag = this.oUnshelveService.addUnshelve(unshelve);
        if (flag == 0){
            throw new SbException(100, "添加失败!");
        }
        return ReturnMessageUtil.sucess(false);
    }
}
