package com.springboot.nuojin.wechat.wxUser.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/29
 * Time:23:26
 **/
@Entity
@Table(name = "wx_user")
public class WxUserModel {
    private String id;
    private String openId;
    private String nickName;
    private String wechatImageUrl;
    private Integer sex;
    private String phone;
    // 公司id
    private String companyId;
    // 上级公司id
    private String preCompanyId;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWechatImageUrl() {
        return wechatImageUrl;
    }

    public void setWechatImageUrl(String wechatImageUrl) {
        this.wechatImageUrl = wechatImageUrl;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPreCompanyId() {
        return preCompanyId;
    }

    public void setPreCompanyId(String preCompanyId) {
        this.preCompanyId = preCompanyId;
    }
}
