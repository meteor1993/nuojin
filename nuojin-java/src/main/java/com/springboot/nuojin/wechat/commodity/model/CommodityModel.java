package com.springboot.nuojin.wechat.commodity.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/7
 * Time:20:09
 **/
@Entity
@Table(name = "commodity")
public class CommodityModel {
    private String id;
    private String commodityName;
    private String commodityDesc;
    private String imageUrl;
    // 原价格
    private Integer originalPrice;
    // 现金价格
    private Integer cashPrice;
    //商品类型 1.名庄红酒 2.女王醉蟹 3.滋补养生 4.生鲜直送
    private String commodityType;
    //商品排序
    private Integer commodityOrder;

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

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityDesc() {
        return commodityDesc;
    }

    public void setCommodityDesc(String commodityDesc) {
        this.commodityDesc = commodityDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(Integer cashPrice) {
        this.cashPrice = cashPrice;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public Integer getCommodityOrder() {
        return commodityOrder;
    }

    public void setCommodityOrder(Integer commodityOrder) {
        this.commodityOrder = commodityOrder;
    }
}
