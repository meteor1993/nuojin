package com.springboot.nuojin.wechat.customer.model;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class customeroutmodel {
    public String openId;
    public String nickName;
    public int level;
    public String preOpenId;
    public String mobile;
    public BigDecimal balance;
    public String accountId;

    public customeroutmodel(String openId,String nickName,int level,String preOpenId,String mobile,BigDecimal balance,String accountId)
    {
        this.accountId=accountId;
        this.openId=openId;
        this.nickName=nickName;
        this.level=level;
        this.preOpenId=preOpenId;
        this.mobile=mobile;
        this.balance=balance;


    }

}
