package com.gomai.goods.pojo;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name="goods")

public class Goods {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer g_id; //商品id
    private Integer u_id;  //发布用户id
    private Integer ca2_id; //二级类别id
    private String g_name;  //商品名
    private String g_detail;  //商品详情信息
    private Double g_price;  //价格
    private String g_status;  //下架状态
    private Date g_create_time; //发布时间

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getCa2_id() {
        return ca2_id;
    }

    public void setCa2_id(Integer ca2_id) {
        this.ca2_id = ca2_id;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getG_detail() {
        return g_detail;
    }

    public void setG_detail(String g_detail) {
        this.g_detail = g_detail;
    }

    public Double getG_price() {
        return g_price;
    }

    public void setG_price(Double g_price) {
        this.g_price = g_price;
    }

    public String getG_status() {
        return g_status;
    }

    public void setG_status(String g_status) {
        this.g_status = g_status;
    }

    public Date getG_create_time() {
        return g_create_time;
    }

    public void setG_create_time(Date g_create_time) {
        this.g_create_time = g_create_time;
    }
}
