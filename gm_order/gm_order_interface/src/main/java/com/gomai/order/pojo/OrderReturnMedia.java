package com.gomai.order.pojo;

import javax.persistence.*;

/**
 * 订单退货退款媒体表
 */
@Entity
@Table(name = "order_return_media")
public class OrderReturnMedia {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "orm_id")
    private Integer ormId; //退货退款媒体id
    @Column(name = "or_id")
    private Integer orId; // 退货退款id
    @Column(name = "orm_url")
    private String ormUrl; // 图片url
    @Column(name = "orm_type")
    private Integer ormType; // 媒体类型


    public OrderReturnMedia() {
    }

    public OrderReturnMedia(Integer orId, String ormUrl, Integer ormType) {
        this.orId = orId;
        this.ormUrl = ormUrl;
        this.ormType = ormType;
    }

    public Integer getOrmId() {
        return ormId;
    }

    public void setOrmId(Integer ormId) {
        this.ormId = ormId;
    }

    public Integer getOrId() {
        return orId;
    }

    public void setOrId(Integer orId) {
        this.orId = orId;
    }

    public String getOrmUrl() {
        return ormUrl;
    }

    public void setOrmUrl(String ormUrl) {
        this.ormUrl = ormUrl;
    }

    public Integer getOrmType() {
        return ormType;
    }

    public void setOrmType(Integer ormType) {
        this.ormType = ormType;
    }

    @Override
    public String toString() {
        return "OrderReturnMedia{" +
                "ormId=" + ormId +
                ", orId=" + orId +
                ", ormUrl='" + ormUrl + '\'' +
                ", ormType=" + ormType +
                '}';
    }
}
