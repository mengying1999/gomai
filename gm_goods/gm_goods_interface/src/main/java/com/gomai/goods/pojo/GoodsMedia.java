package com.gomai.goods.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="GoodsMedia")
public class GoodsMedia {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer gm_id; //商品媒体id
    private Integer g_id; //商品id
    private String gm_url; //商品媒体地址
    private Integer gm_type; //商品媒体类别

    public Integer getGm_id() {
        return gm_id;
    }

    public void setGm_id(Integer gm_id) {
        this.gm_id = gm_id;
    }

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    public String getGm_url() {
        return gm_url;
    }

    public void setGm_url(String gm_url) {
        this.gm_url = gm_url;
    }

    public Integer getGm_type() {
        return gm_type;
    }

    public void setGm_type(Integer gm_type) {
        this.gm_type = gm_type;
    }
}
