package com.gomai.comment.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gomai.comment.pojo.OrderEvaluationMedia;
import com.gomai.comment.service.GoodService;
import com.gomai.comment.service.OCOderService;
import com.gomai.comment.vo.*;
import com.gomai.order.pojo.Order;
import com.gomai.comment.pojo.OrderComment;
import com.gomai.comment.service.OrderCommentService;
import com.gomai.user.pojo.User;
import com.gomai.utils.PageResult;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/OrderComment")
public class OrderCommentController {
    @Autowired
    private OrderCommentService commentService;
    @Autowired
    private OCOderService oderService;
    @Autowired
    private GoodService goodService;

    /**
     * 1.验证orderComment是否为空
     *
     *3.判断评论内容是否为空
     * 4.插入
     * 5.返回
     */

    @PostMapping("/InsertOC")
    public ReturnMessage<Object> InsertOC(@RequestBody OComentVo oComentVo){
        Order order=oComentVo.getOrder();
        List<OrderEvaluationMedia> orderEvaluationMedias=oComentVo.getOrderEvaluationMedia();
        if (StringUtils.isEmpty(order)) {
            throw new SbException(400, "输入不合法");
        }
        if (StringUtils.isEmpty(order.getoEvaluation())){
            throw new SbException(400, "评论内容不可为空");
        }
        Order order1=new Order();
        order1.setoId(order.getoId());
        Order o=this.oderService.selectByoId(order1);
        System.out.println("我是oid"+order.getoEvaluation());
        o.setoEvaluation(order.getoEvaluation());
        if (o.getoStatus()!=4){//待评价
            throw new SbException(100, "此订单状态不可评价");
        }

        System.out.println("我是oid"+order.getoId());
        if(!StringUtils.isEmpty(orderEvaluationMedias)) {
            for(int i=0;orderEvaluationMedias.size()>i;i++){
                orderEvaluationMedias.get(i).setoId(order.getoId());
            }
            int flag = this.commentService.insertocmedia(orderEvaluationMedias);
            if (flag == 0) {
                throw new SbException(100, "添加评价媒体失败");
            }
        }
        o.setoStatus(5);
        int flag=this.oderService.Updateoe(o);
        return ReturnMessageUtil.sucess(true);
    }
//    /**
//     * 1.验证订单是否存在
//     *2.判断状态类型
//     *3.判断评论内容是否为空
//     * 4.更新
//     * 5.修改订单状态
//     * 6.返回
//     */
//
//    @PostMapping("/AddoEvaluation/{oId}/{oStatus}/{oEvaluation}")
//    public ReturnMessage<Object> AddoEvaluation(@PathVariable("oId") Integer oId,@PathVariable("oStatus") Integer oStatus ,@PathVariable("oEvaluation") String oEvaluation){
//        Order o=new Order();
//        o.setoId(oId);
//        Order order=this.oderService.selectByoId(o);
//        if (StringUtils.isEmpty(order)) {
//            throw new SbException(400, "不存在该订单");
//        }
//        if (oStatus!=4){//待评价
//            throw new SbException(100, "此订单状态不可评价");
//            }
//        if(oEvaluation==null) {
//            throw new SbException(100, "评价不能为空");
//        }
//            Date date=new Date();
//            order.setoEvaluationTime(date);
//            order.setoEvaluation(oEvaluation);
//
//        int flag=this.oderService.Updateoe(order);
//        if(flag==0){
//            throw new SbException(100, "评价失败");
//        }
//        order.setoStatus(5);
//        return ReturnMessageUtil.sucess(true);
//    }
    /**
     * 1.验证订单是否存在
     *2.判断状态类型
     *3.判断追评内容是否为空
     * 4.更新
     * 5.修改订单状态
     * 6.返回
     */

    @PostMapping("/Updateoe/{oId}/{oEvaluationAdd}")
    public ReturnMessage<Object> Updateoe(@PathVariable("oId") Integer oId,@PathVariable("oEvaluationAdd") String oEvaluationAdd){
        Order o=new Order();
        o.setoId(oId);
        Order order=this.oderService.selectByoId(o);
        if (StringUtils.isEmpty(order)) {
            throw new SbException(400, "不存在该订单");
        }
        System.out.println(order.getoStatus()+"++++=");
        if (order.getoStatus()!=5) {//待追评
            throw new SbException(100, "此订单状态不可追评");
        }
        if(oEvaluationAdd==null){
            throw new SbException(100, "追评不能为空");
        }
            Date date=new Date();
            order.setoEvaluationAddTime(date);
            order.setoEvaluationAdd(oEvaluationAdd);
            order.setoStatus(9);
        int flag=this.oderService.Updateoe(order);
        if(flag==0){
            throw new SbException(100, "追评失败");
        }
        return ReturnMessageUtil.sucess(true);
    }
    /**
     * 1.验证订单是否存在
     * 2.获取信息
     * 3.返回
     */
//
//    @GetMapping ("/SecletByuId/{uId}/{size}/{currentPage}")
//    public ReturnMessage<Object> SecletByuId(@PathVariable("uId") Integer uId,@PathVariable("size")Integer size,@PathVariable("currentPage")Integer currentPage){
//        if (StringUtils.isEmpty(uId)||uId ==0|| size == 0 || currentPage == 0) {
//            throw new SbException(400, "输入不合法");
//        }
//        PageHelper.startPage(currentPage, size);
//        ComUserVo comUserVo=this.oderService.SecletByuId(uId);
//        if (StringUtils.isEmpty(comUserVo)) {
//            throw new SbException(400, "获取失败");
//        }
//        List<OrderMidaVo> orderMidaVos=comUserVo.getoComentVos();
//        PageResult pageResult = new PageResult();
//        pageResult.setRows(orderMidaVos);
//        pageResult.setTotal(new PageInfo(orderMidaVos).getTotal());
//        return ReturnMessageUtil.sucess(comUserVo);
//    }
    /**
     * 1.验证订单是否存在
     * 2.获取信息
     * 3.返回
     */

    @GetMapping ("/Secletoe/{oId}")
    public ReturnMessage<Object> Secletoe(@PathVariable("oId") Integer oId){
        if (StringUtils.isEmpty(oId)) {
            throw new SbException(400, "非法输入");
        }
        OrderComentVo orderComentVo=this.commentService.selectOrderComentVoByOId(oId);
        if (StringUtils.isEmpty(orderComentVo)) {
            throw new SbException(400, "获取失败");
        }
        return ReturnMessageUtil.sucess(orderComentVo);
    }
    /**
     * 1.验证订单是否存在
     * 2.获取信息
     * 3.返回
     */

    @GetMapping ("/SecletComByUId/{uId}")
    public ReturnMessage<Object> SecletComByUId(@PathVariable("uId") Integer uId){
        if (StringUtils.isEmpty(uId)) {
            throw new SbException(400, "输入不合法");
        }
        OrderComment orderComment=new OrderComment();
        orderComment.setuId(uId);
        List<OrderComment> orderComments=this.commentService.selectByuId(orderComment);
        if (StringUtils.isEmpty(orderComments)) {
            throw new SbException(400, "获取失败");
        }
        User user=this.oderService.selectUserByuId(uId);
        UserCommentVo userCommentVo=new UserCommentVo();
        userCommentVo.setUser(user);
        userCommentVo.setOrderComment(orderComments);
        return ReturnMessageUtil.sucess(userCommentVo);
    }
    @GetMapping ("/SecletSellByuId/{uId}/{size}/{currentPage}")
    public ReturnMessage<Object> SecletSellByuId(@PathVariable("uId") Integer uId,@PathVariable("size")Integer size,@PathVariable("currentPage")Integer currentPage){
        if (StringUtils.isEmpty(uId)||uId ==0|| size == 0 || currentPage == 0) {
            throw new SbException(400, "输入不合法");
        }
        PageHelper.startPage(currentPage, size);
        List<Integer> orStatus=new ArrayList<Integer>();
        orStatus.add(5);
        orStatus.add(9);
        List<OrderComentVo>  orderComentVos=this.commentService.selectOrderComentVosBySellUId(uId,orStatus);
        if (StringUtils.isEmpty(orderComentVos)) {
            throw new SbException(400, "获取失败");
        }
        PageResult pageResult = new PageResult();
        pageResult.setRows(orderComentVos);
        pageResult.setTotal(new PageInfo(orderComentVos).getTotal());
        return ReturnMessageUtil.sucess(pageResult);
    }
    @GetMapping ("/selectByUId/{uId}/{size}/{currentPage}")
    public ReturnMessage<Object> selectByUId(@PathVariable("uId") Integer uId,@PathVariable("size")Integer size,@PathVariable("currentPage")Integer currentPage){
        if (StringUtils.isEmpty(uId)||uId ==0|| size == 0 || currentPage == 0) {
            throw new SbException(400, "输入不合法");
        }
        PageHelper.startPage(currentPage, size);
        List<Integer> orStatus=new ArrayList<Integer>();
        orStatus.add(5);
        orStatus.add(9);
        List<OrderComentVo>  orderComentVos=this.commentService.selectOrderComrntVosByUId(uId,orStatus);
        if (StringUtils.isEmpty(orderComentVos)) {
            throw new SbException(400, "获取失败");
        }
        PageResult pageResult = new PageResult();
        pageResult.setRows(orderComentVos);
        pageResult.setTotal(new PageInfo(orderComentVos).getTotal());
        return ReturnMessageUtil.sucess(pageResult);
    }
    @PostMapping("/InsertComment")
    public ReturnMessage<Object> InsertComment(@RequestBody OrderComment orderComment){
        if (StringUtils.isEmpty(orderComment)||orderComment.getoId() ==0|| orderComment.getuId() == 0 || StringUtils.isEmpty(orderComment.getOcContent()) ) {
            throw new SbException(400, "输入不合法");
        }
        Order o=new Order();
        o.setoId(orderComment.getoId());
        Order order=this.oderService.selectByoId(o);
        if (StringUtils.isEmpty(order)) {
            throw new SbException(400, "不存在该订单");
        }
        User user=this.oderService.selectUserByuId(orderComment.getuId());
        if (StringUtils.isEmpty(order)) {
            throw new SbException(400, "不存在该用户");
        }
        Date date=new Date();

        orderComment.setOcTime(date);
        int flag=this.commentService.insertComment(orderComment);
        return ReturnMessageUtil.sucess(true);
    }
}
