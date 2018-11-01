package com.springboot.nuojin.wechat.wxUser.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author: daixueyun
 * @Description: 地址表
 * @Date: Create in 21:46 2018/11/1
 */
@Entity
@Table(name = "address")
public class AddressModel {

    //地址编号
    private String id;

    //用户id
    private String userId;

    //收件人姓名
    private String name;

    //收件人手机号
    private String phone;

    //收件地址
    private String address;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
