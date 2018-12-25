package com.springboot.nuojin.wechat.customer.model;

import lombok.Data;
import org.hibernate.Query;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "customerInfo" )
@Data
public class customermodel {

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @Column(nullable=false,length=36)
        public String openId;
        public String nickName;
        public Date moditfyTime;
        public int level;
        public String preOpenId;
        public String mobile;
        public String wechatImageUrl;
        public Integer sex;



}


