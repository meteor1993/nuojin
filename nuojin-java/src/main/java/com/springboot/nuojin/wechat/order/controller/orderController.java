
package com.springboot.nuojin.wechat.order.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.common.collect.Maps;
import com.springboot.nuojin.system.model.CommonJson;
import com.springboot.nuojin.system.utils.ContextHolderUtils;
import com.springboot.nuojin.system.utils.HttpUtils;
import com.springboot.nuojin.wechat.Utils.Common;
import com.springboot.nuojin.wechat.bonus.model.bonusmodel;
import com.springboot.nuojin.wechat.bonus.repository.BonusRepository;
import com.springboot.nuojin.wechat.customer.model.addressmodel;
import com.springboot.nuojin.wechat.customer.model.customermodel;
import com.springboot.nuojin.wechat.customer.respository.AddressRespository;
import com.springboot.nuojin.wechat.customer.respository.CustomerRespository;
import com.springboot.nuojin.wechat.order.Respository.*;
import com.springboot.nuojin.wechat.order.model.*;
import com.springboot.nuojin.wechat.pay.model.WxPayOrderModel;
import com.springboot.nuojin.wechat.pay.respository.WxPayOrderRespository;
import com.springboot.nuojin.wechat.product.model.partnerpricemodel;
import com.springboot.nuojin.wechat.product.model.productmodel;
import com.springboot.nuojin.wechat.product.respository.PartnerPriceRespository;
import com.springboot.nuojin.wechat.product.respository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/mp/orderController")
public class orderController {

    @Autowired
    WxPayService wxPayService;
    @Autowired
    ProductRespository productRespository;
    @Autowired
    CustomerRespository customerRespository;
    @Autowired
    PartnerPriceRespository partnerPriceRespository;
    @Autowired
    OrderRespository orderRespository;
    @Autowired
    OrderDetailRespository orderDetailRespository;
    @Autowired
    BonusRepository bonusRepository;
    @Autowired
    OrderRefundRespository orderRefundRespository;
    @Autowired
    WxPayOrderRespository wxPayOrderRespository;
    @Autowired
    AddressRespository addressRespository;
    @Autowired
    PartnerWineRespository partnerWineRespository;
    @Autowired
    PartnerWineLogRespository partnerWineLogRespository;
    public orderController() {

    }




    @PostMapping(value = "/getOrderListByOpenId")
    @ResponseBody
    @Transactional
    public CommonJson getOrderListByOpenId()
    {
        //获取所有除了未支付以外状态的所有订单
        String openId = "oQz2Q0YfNmE--tNH-P7reZb7nXSE"; //Common.getOpenId();
        List<ordermodel> list = orderRespository.getByOpenIdOrderByCreateTimeDesc(openId);
        //List<ordermodel> list = orderRespository.getByOpenIdOrderByCreateTimeDesc(openId);
        ArrayList outlist = new ArrayList<>();
        for(ordermodel one : list)
        {
            List<orderdetailmodel> dlist = orderDetailRespository.getByOrderId(one.orderId);
            orderoutmodel oneout = new orderoutmodel(one,dlist);
            outlist.add(oneout);

        }

        CommonJson commonJson = new CommonJson();
        commonJson.setResultCode("1");
        commonJson.setResultMsg("success");
        Map<String, Object> map = Maps.newHashMap();
        map.put("info",outlist);
        commonJson.setResultData(map);
        return commonJson;
    }

    /**
     * 生成订单号
     * @return
     */
    private String getOrderNo(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String str = simpleDateFormat.format(new Date());
        return str + String.valueOf(System.nanoTime());
    }

    @PostMapping(value = "/InsertOrder")
    @ResponseBody
    @Transactional
    public CommonJson InsertOrder() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        String openId = "oQz2Q0YfNmE--tNH-P7reZb7nXSE"; //Common.getOpenId();
        JSONObject jsonObject = JSON.parseObject(params);
        String productId = jsonObject.getString("productId");
        boolean wxpayflag = true;
        int count = Integer.parseInt(jsonObject.getString("count"));
        //String cityCode = jsonObject.getString("cityCode");
        //String address = jsonObject.getString("address");
        String addressId = jsonObject.getString("addressId");
        CommonJson commonJson = new CommonJson();
        if (addressId.equals("")) {
            commonJson.setResultCode("0");
            commonJson.setResultMsg("地址不能为空！");
        }

        addressmodel thisaddress = addressRespository.getByAddressId(addressId);

        productmodel oneproduct = productRespository.getByProductId(productId);
        customermodel onecustomer = customerRespository.getByOpenId(openId);
        int finaloneprice = 0;
        int givenum = 0;
        if (onecustomer.getLevel() == 2) {
            //合伙人
            List<partnerpricemodel> ppm = partnerPriceRespository.getByProductId(productId, 2);
            for (partnerpricemodel p : ppm) {
                if (count >= p.startNum && count < p.endNum) {
                    finaloneprice = p.price;
                    break;
                }
            }

        } else if (onecustomer.getLevel() == 1) {
            //下线
            finaloneprice = oneproduct.productOfflinePrice;
        } else {

            givenum = count / 3;
            givenum = givenum * 3;
            finaloneprice = oneproduct.productNormalPrice;
        }

        ordermodel order = new ordermodel();
//        if (address == "") {
//            commonJson.setResultCode("0");
//            commonJson.setResultMsg("地址不能为空！");
//
//        } else if (cityCode == "") {
//            commonJson.setResultCode("0");
//            commonJson.setResultMsg("省市区请选择！");
//
//        }
        if (count <= 0) {
            commonJson.setResultCode("0");
            commonJson.setResultMsg("商品数量不能小于或等于0");

        } else {

            List<partnerwinemodel> mypartnerwinemodel = partnerWineRespository.getByOpenId(openId);
            int mywinecount = 0;
            partnerwinemodel tempmywine = null;
            for(partnerwinemodel p : mypartnerwinemodel)
            {
                if (productId.equals(p.productId))
                {
                    wxpayflag = count> p.wineCount;
                    //mywinecount = p.wineCount;
                    tempmywine = p;
                    break;
                }

            }

            order.address = thisaddress.detailAddress;
            order.provinceCode = thisaddress.provinceCode;
            order.provinceValue = thisaddress.provinceValue;
            order.cityCode = thisaddress.cityCode;
            order.cityValue = thisaddress.cityValue;
            order.areaCode = thisaddress.areaCode;
            order.areaValue = thisaddress.areaValue;
            order.postcode = thisaddress.postcode;

            order.createTime = new Date();
            order.openId = openId;
            order.orderId = getOrderNo();
            order.orderState = "未支付";
            if(!wxpayflag) {
                order.orderState = "已支付";
            }

            order.preOpenId = onecustomer.preOpenId;
            order.updateTime = new Date();

            orderRespository.save(order);

            orderdetailmodel odm = new orderdetailmodel();
            odm.orderId = order.orderId;
            odm.orderDetailId = UUID.randomUUID().toString();
            odm.productGiveNum = givenum;
            odm.productId = productId;
            odm.productName = oneproduct.productName;
            odm.ProductNum = count + givenum;
            odm.productRealUnitPrice = finaloneprice;
            odm.productSmallHeadImgUrl = oneproduct.productHeadImgUrl;
            odm.productSpec = oneproduct.productSpec;
            odm.productRealTotalPrice = finaloneprice * count;
            odm.productUnitPrice = oneproduct.productNormalPrice;
            odm.productTotalPrice = oneproduct.productNormalPrice * count;
            odm.updateTime = new Date();
            orderDetailRespository.save(odm);

            if(!wxpayflag)
            {
                partnerwinelogmodel winelog = new partnerwinelogmodel();
                winelog.beforeWineCount =tempmywine.wineCount;
                winelog.wineCount =count;
                winelog.afterWineCount = tempmywine.wineCount - count;
                winelog.createTime = new Date();
                winelog.openId = openId;
                winelog.orderId = order.orderId;
                winelog.identityId = UUID.randomUUID().toString();
                tempmywine.wineCount = tempmywine.wineCount - count;
                tempmywine.updateTime = new Date();
                partnerWineRespository.save(tempmywine);
                partnerWineLogRespository.save(winelog);
            }


        }



        HashMap hm = new HashMap();
        hm.put("orderId", order.orderId);
        commonJson.setResultCode("1");
        commonJson.setResultMsg("下单成功！");
        commonJson.setResultData(hm);
        return commonJson;
    }

    @PostMapping(value = "/PaySuccess")
    @ResponseBody
    @Transactional
    /***
     * 微信支付回调函数，订单号需要改成微信支付订单号
     */
    public void PaySuccess() throws IOException {

        //之后orderId以微信的的订单号查询出具体的orderId
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        String openId = "oQz2Q0YfNmE--tNH-P7reZb7nXSE"; //Common.getOpenId();
        //获取微信支付订单号
        JSONObject jsonObject = JSON.parseObject(params);
        String transactionId = jsonObject.getString("transactionId");
        List<WxPayOrderModel> paymodel = wxPayOrderRespository.getByTransactionId(transactionId);
        String orderId= paymodel.get(0).getOrderNo();
        ordermodel one = orderRespository.getByOrderId(orderId);
        one.updateTime = new Date();
        one.payTime = new Date();
        if (one.orderState.equals("未支付"))
            one.orderState = "已支付";

        if (one.preOpenId != null && one.bonusFlag == 0 ) {
            List<orderdetailmodel> orderdetaillist = orderDetailRespository.getByOrderId(orderId);
            //有上线
            List<partnerpricemodel> partner = partnerPriceRespository.getByProductId(orderdetaillist.get(0).productId, 2);
            productmodel product = productRespository.getByProductId(orderdetaillist.get(0).productId);
            int profitmoney = product.productOfflinePrice - partner.get(0).price;
            int companyprofit = new BigDecimal(profitmoney).multiply(new BigDecimal(0.3)).intValue();
            int customerprofit = profitmoney - companyprofit;

            bonusmodel customerbonus = new bonusmodel();
            customerbonus.orderId = orderId;
            customerbonus.state = 0;
            customerbonus.bonus = customerprofit;
            customerbonus.bonusId = UUID.randomUUID().toString();
            customerbonus.openId = openId;
            customerbonus.createTime = new Date();
            customerbonus.companybonus = companyprofit;
            one.bonusFlag = 1;
            bonusRepository.save(customerbonus);
        }
        orderRespository.save(one);
    }

    /***
     * 校验是否允许退款
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/CheckReFund")
    @ResponseBody
    @Transactional
    public CommonJson CheckReFund() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        String openId = "oQz2Q0YfNmE--tNH-P7reZb7nXSE"; //Common.getOpenId();
        JSONObject jsonObject = JSON.parseObject(params);
        String orderId = jsonObject.getString("orderId");
        ordermodel thisorder = orderRespository.getByOrderId(orderId);
        Date nowdate = new Date();
        int days = (int) (( nowdate.getTime()- thisorder.payTime.getTime()) / (1000 * 60 * 60 * 24));
        CommonJson commonJson = new CommonJson();

        if (days>=10)
        {
            commonJson.setResultCode("0");
            commonJson.setResultMsg("订单已超过10天，不予退款");
        }
        else if (thisorder.orderState != "已支付" && thisorder.orderState != "已完成" && thisorder.orderState != "退货中")
        {
            commonJson.setResultCode("0");
            commonJson.setResultMsg("当前订单状态不可退款");

        }
        commonJson.setResultCode("1");
        commonJson.setResultMsg("success");
        return commonJson;
    }


    @PostMapping(value = "/ReFund")
    @ResponseBody
    @Transactional
    /***
     * 退货提交
     */
    public CommonJson ReFund() throws IOException {
        String params=HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());;
        JSONObject jsonObject = JSON.parseObject(params);
        String expresscompany = jsonObject.getString("expressCompany");;
        String expressno = jsonObject.getString("expressNo");
        String orderId = jsonObject.getString("orderId");
        ordermodel refundorder = orderRespository.getByOrderId(orderId);
        refundorder.orderState = "退货中";
        orderRespository.save(refundorder);

        orderrefundmodel refund = new orderrefundmodel();
        refund.setId( UUID.randomUUID().toString());
        refund.setCreateTime(new Date());
        refund.setExpressNo(expressno);
        refund.setExpressCompany(expresscompany);
        refund.setOrderId(orderId);
        orderRefundRespository.save(refund);

        CommonJson commonJson = new CommonJson();
        commonJson.setResultCode("1");
        commonJson.setResultMsg("提交成功");
        return commonJson;
    }
}
