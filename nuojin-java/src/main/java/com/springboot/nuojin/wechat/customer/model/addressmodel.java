package com.springboot.nuojin.wechat.customer.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "address_info" )
@Data
public class addressmodel {

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name="addressId",nullable=false,length=36)
    public String addressId;
    public String provinceCode;
    public String cityCode;
    public String areaCode;
    public String provinceValue;
    public String cityValue;
    public String areaValue;
    public String detailAddress;
    public String postcode;
    public String name;
    public String mobile;
    public String openId;
    public Date updateDate;
}
