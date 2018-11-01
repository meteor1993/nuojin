package com.springboot.nuojin.wechat.commodity.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author: daixueyun
 * @Description: 订单表
 * @Date: Create in 0:00 2018/11/1
 */
@Entity
@Table(name = "wx_payOrder")
public class WxPayOrderModel {

    //微信Id
    private String id;

    //商品编号
    private String commodityId;

    //商品数量
    private String commodityCount;

    //手机号
    private String phone;

    //openId
    private String openId;

    //订单编号
    private String orderId;

    //订单创建时间
    private String orderTime;

    //订单金额
    private String orderMoney;

    //订单状态 0：创建 1：支付中 2：支付完成 3：退款完成
    private String orderStatus;

    //发起支付时间
    private String payTime;

    //腾讯订单Id
    private String txOrderNo;

    //支付完成时间
    private String paySuccessTime;

    //微信支付订单号
    private String transaction;

    //快递号
    private String expressId;

    //快递公司
    private String expressCompany;

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

    public String getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(String commodityCount) {
        this.commodityCount = commodityCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getTxOrderNo() {
        return txOrderNo;
    }

    public void setTxOrderNo(String txOrderNo) {
        this.txOrderNo = txOrderNo;
    }

    public String getPaySuccessTime() {
        return paySuccessTime;
    }

    public void setPaySuccessTime(String paySuccessTime) {
        this.paySuccessTime = paySuccessTime;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }
}
