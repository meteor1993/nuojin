
package com.springboot.nuojin.wechat.customer.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name= "accountInfo" )
@Data
public class accountmodel {

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name="accountId",nullable=false,length=36)
    public String accountId;

    public String openId;
    public int balance;
    public Date moditfyTime;

}
