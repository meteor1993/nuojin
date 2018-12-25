package com.springboot.nuojin.wechat.product.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name= "partnerprice_info" )
@Data
public class partnerpricemodel {
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(name="partnerPriceId",nullable=false,length=36)
    public String partnerPriceId;
    @Column(name="productId",nullable=false,length=36)
    public String productId;

    public int startNum;
    public int endNum;
    public int customerLevel;
    public int price;

}
