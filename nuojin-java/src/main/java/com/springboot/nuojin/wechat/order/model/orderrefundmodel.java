package com.springboot.nuojin.wechat.order.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "orderRefund" )
@Data
public class orderrefundmodel {
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(name="Id",nullable=false,length=36)
    public String Id;
    public String orderId;
    //退货提交时间
    public Date createTime;
    public String expressCompany;
    public String expressNo;

}
