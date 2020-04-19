package com.gomai.goods.controller;

import com.gomai.goods.service.Category2Service;
import com.gomai.goods.service.impl.Category1ServiceImpl;
import com.gomai.goods.service.impl.Category2ServiceImpl;
import com.gomai.goods.vo.Option;
import com.gomai.utils.ReturnMessage;
import com.gomai.utils.ReturnMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("category2")
public class Category2Controller {


    @Autowired
    private Category2Service category2Service;

    @GetMapping("/findAllGoodsCategry")
    public ReturnMessage<Object> findAllGoodsCategry(){

        List<Option> goodsCategryList = this.category2Service.findAllGoodsCategry();
        return ReturnMessageUtil.sucess(goodsCategryList);
    }
}
