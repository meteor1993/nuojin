package com.springboot.nuojin.wechat.product.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name= "productInfo" )
@Data
public class productmodel {
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(name="productId",nullable=false,length=36)
    public String productId;

    public String productName;
    public String productDescString;
    public String productDescImgUrl;
    public String productHeadImgUrl;
    public String productBigImgUrl;
    public String productSmallImgUrl;
    public int productShowPrice;
    public int productNormalPrice;
    public int productPartnerPrice;
    public int productOfflinePrice;
    public int productStateCode;
    public String productSpec;
    public String productFirstType;
    public String productSecondType;

}
