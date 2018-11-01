package com.springboot.nuojin.wechat.wxUser.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/29
 * Time:23:30
 **/
@Entity
@Table(name = "company")
public class CompanyModel {
    private String id;
    // 返利额度
    private Integer rebate;
    // 现金
    private Integer cash;
    // 订单号
    private String orderId;

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

    public Integer getRebate() {
        return rebate;
    }

    public void setRebate(Integer rebate) {
        this.rebate = rebate;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
