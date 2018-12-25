package com.springboot.nuojin.wechat.order.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "orderDetailInfo" )
@Data
public class orderdetailmodel {

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(name="orderDetailId",nullable=false,length=36)
    public String orderDetailId;
    @Column(name="orderId",nullable=false,length=36)
    public String orderId;
    public String productId;
    public int productUnitPrice;
    public int productRealUnitPrice;
    public String productName;
    public String productSpec;
    public int productTotalPrice;
    public int productRealTotalPrice;
    public int productGiveNum;
    public int ProductNum;
    public Date updateTime;

}
