package com.springboot.nuojin.wechat.order.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "partnerwineLog" )
@Data
public class partnerwinelogmodel {

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(name="identityId",nullable=false,length=36)
    public String identityId;
    public String openId;
    public int beforeWineCount;
    public int wineCount;
    public int afterWineCount;
    public Date createTime;
    public String orderId;

}
