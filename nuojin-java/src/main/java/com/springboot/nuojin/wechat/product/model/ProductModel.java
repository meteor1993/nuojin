package com.springboot.nuojin.wechat.product.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/7
 * Time:20:09
 **/
@Entity
@Table(name = "product")
public class ProductModel {
    private String id;
    private String name;
    private String productDesc;
    private String imageURL;
    // 现金价格
    private Integer cashPrice;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(Integer cashPrice) {
        this.cashPrice = cashPrice;
    }
}
