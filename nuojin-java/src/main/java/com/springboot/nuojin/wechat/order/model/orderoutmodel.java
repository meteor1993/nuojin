package com.springboot.nuojin.wechat.order.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class orderoutmodel extends ordermodel {


    public orderoutmodel(ordermodel inorder,List<orderdetailmodel> indetail)
    {
        this.setAddress(inorder.getAddress());
        this.setCityCode(inorder.getCityCode());
        this.setCreateTime(inorder.getCreateTime());
        this.setExpressCompany(inorder.getExpressCompany());
        this.setExpressNo(inorder.getExpressNo());
        this.setOpenId(inorder.getOpenId());
        this.setOrderId(inorder.getOrderId());
        this.setOrderState(inorder.getOrderState());
        this.setPayTime(inorder.getPayTime());
        this.setPreOpenId(inorder.getPreOpenId());
        this.setUpdateTime(inorder.getUpdateTime());

        this.detail.addAll(indetail);
    }

    public List<orderdetailmodel> detail=new ArrayList<orderdetailmodel>();


}
