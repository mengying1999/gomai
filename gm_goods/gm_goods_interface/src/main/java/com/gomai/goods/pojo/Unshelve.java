package com.gomai.goods.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name="goods_unshelve")
public class Unshelve {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer un_id; //下架商品id
    private Integer g_id; //商品id
    private String un_reason; //下架原因
    private Date un_create_time; //下架时间
    public Integer getUn_id() {
        return un_id;
    }

    public void setUn_id(Integer un_id) {
        this.un_id = un_id;
    }

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    public String getUn_reason() {
        return un_reason;
    }

    public void setUn_reason(String un_reason) {
        this.un_reason = un_reason;
    }

    public Date getUn_create_time() {
        return un_create_time;
    }

    public void setUn_create_time(Date un_create_time) {
        this.un_create_time = un_create_time;
    }


}

