package com.gomai.comment.controller;

import com.gomai.comment.pojo.OrderEvaluationMedia;
import com.gomai.comment.service.GoodService;
import com.gomai.comment.service.OCOderService;
import com.gomai.comment.vo.OComentVo;
import com.gomai.comment.vo.OcorderVo;
import com.gomai.order.pojo.Order;
import com.gomai.comment.pojo.OrderComment;
import com.gomai.comment.service.OrderCommentService;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import com.gomai.utils.SbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    /**
     * 1.验证订单是否存在
     *2.判断状态类型
     *3.判断评论内容是否为空
     * 4.更新
     * 5.修改订单状态
     * 6.返回
     */

    @PostMapping("/AddoEvaluation/{oId}/{oStatus}/{oEvaluation}")
    public ReturnMessage<Object> AddoEvaluation(@PathVariable("oId") Integer oId,@PathVariable("oStatus") Integer oStatus ,@PathVariable("oEvaluation") String oEvaluation){
        Order o=new Order();
        o.setoId(oId);
        Order order=this.oderService.selectByoId(o);
        if (StringUtils.isEmpty(order)) {
            throw new SbException(400, "不存在该订单");
        }
        if (oStatus!=4){//待评价
            throw new SbException(100, "此订单状态不可评价");
            }
        if(oEvaluation==null) {
            throw new SbException(100, "评价不能为空");
        }
            Date date=new Date();
            order.setoEvaluationTime(date);
            order.setoEvaluation(oEvaluation);

        int flag=this.oderService.Updateoe(order);
        if(flag==0){
            throw new SbException(100, "评价失败");
        }
        order.setoStatus(5);
        return ReturnMessageUtil.sucess(true);
    }
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

    @GetMapping ("/Secletoe/{oId}")
    public ReturnMessage<Object> Secletoe(@PathVariable("oId") Integer oId){
        Order o=new Order();
        o.setoId(oId);
        Order order=this.oderService.selectByoId(o);
        if (StringUtils.isEmpty(order)) {
            throw new SbException(400, "不存在该订单");
        }
        OcorderVo ocorderVo=this.commentService.selectorderByoid(oId);
        if (StringUtils.isEmpty(ocorderVo)) {
            throw new SbException(400, "获取失败");
        }
        return ReturnMessageUtil.sucess(ocorderVo);
    }



}
