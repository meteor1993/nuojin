package com.springboot.nuojin.wechat.order.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "orderInfo" )
@Data
public class ordermodel {

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(name="orderId",nullable=false,length=36)
    public String orderId;
    public Date createTime;
    public String openId;
    public Date payTime;
    //未支付，已支付，已发货,已完成，退货中，退货完成
    public String orderState;
    public String preOpenId;
    public String expressCompany;
    public String expressNo;
    public String cityCode;
    public String address;
    public Date updateTime;
    @Column(name="bonusFlag",nullable=false,columnDefinition = "int default 0")
    public int bonusFlag;


}
