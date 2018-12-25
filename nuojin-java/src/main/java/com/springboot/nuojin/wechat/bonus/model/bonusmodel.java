package com.springboot.nuojin.wechat.bonus.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "bonusInfo" )
@Data
public class bonusmodel {

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(name="bonusId",nullable=false,length=36)
    public String bonusId;
    public String openId;
    public Date createTime;
    public String orderId;
    public int bonus;
    public int companybonus;
    public int state;//-1-退款撤销 0-冻结 1-转入成功
    public Date successTime;
    public Date failTime;


}
