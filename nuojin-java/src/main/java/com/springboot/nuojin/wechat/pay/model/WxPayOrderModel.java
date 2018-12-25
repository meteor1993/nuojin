package com.springboot.nuojin.wechat.pay.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name= "WxPayOrder" )
@Data
public class WxPayOrderModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(name="Id",nullable=false,length=36)
    private String id;//主键ID

    private String orderNo;//订单编号

    private Date orderTime;//订单创建时间

    private Double orderMoney;//订单金额

    private String orderComment;//订单描述

    private String orderStatus;//支付状态 0:创建 1：订单完成

    private String openId;//支付openId

    private Date payTime;//发起支付时间

    private String txOrderNo;//腾讯订单ID

    private String paySuccessTime;//支付完成时间

    private String transactionId;//微信支付订单号

    private Integer payTimes;//第几次下单

    private String productNo; //商品编号

    private String payType; // 支付方式

    private Date updateDate;
}
