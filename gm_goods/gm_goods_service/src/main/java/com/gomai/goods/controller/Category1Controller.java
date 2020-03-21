package com.gomai.goods.controller;

import com.gomai.goods.pojo.Category1;
import com.gomai.goods.service.impl.Category1ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category1")
public class Category1Controller {

    @Autowired
    private Category1ServiceImpl category1ServiceImpl;

    /**
     * 根据父节点查询商品类目
     * @param ca1Id
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category1>> queryByParentId(
            @RequestParam(value = "ca1_id", defaultValue = "0") Integer ca1Id) {
        if(ca1Id == null || ca1Id <0){
            return ResponseEntity.badRequest().build();
        }
        List<Category1> list = this.category1ServiceImpl.queryListByParent(ca1Id);
        if (CollectionUtils.isEmpty(list)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
}
