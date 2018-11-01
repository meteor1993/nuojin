package com.springboot.nuojin.wechat.commodity.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author: daixueyun
 * @Description: 商品图片表
 * @Date: Create in 21:12 2018/11/1
 */
@Entity
@Table(name = "commodity_image")
public class CommodityImageModel {

    //图片编号
    private String id;

    //商品编号
    private String commodityId;

    //图片流
    private String imageUrl;

    //图片排序
    private Integer imageOrder;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="ID",nullable=false,length=36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getImageOrder() {
        return imageOrder;
    }

    public void setImageOrder(Integer imageOrder) {
        this.imageOrder = imageOrder;
    }
}
