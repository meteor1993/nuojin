
package com.springboot.nuojin.wechat.order.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/mp/orderController")
public class orderController {
    private final Logger logger = LoggerFactory.getLogger(orderController.class);

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

    private static final String payCallUrl = "mp/orderController/paySuccess";

    public orderController() {

    }




    @PostMapping(value = "/getOrderListByOpenId")
    @ResponseBody
    @Transactional
    public CommonJson getOrderListByOpenId() throws IOException {
        //获取所有除了未支付以外状态的所有订单
        String openId = Common.getOpenId();
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
    public CommonJson InsertOrder() throws IOException, WxPayException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        String openId = Common.getOpenId();
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

            // 微信统一下单流程
            HttpServletRequest request = ContextHolderUtils.getRequest();
            String basePath = request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/";

            WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = WxPayUnifiedOrderRequest.newBuilder().build();
            // 随机数
            wxPayUnifiedOrderRequest.setNonceStr(UUID.randomUUID().toString().replaceAll("-", ""));
            // openid
            wxPayUnifiedOrderRequest.setOpenid(Common.getOpenId());
            // 商品描述
            wxPayUnifiedOrderRequest.setBody(oneproduct.getProductDescString());
            // 商户订单号
            wxPayUnifiedOrderRequest.setOutTradeNo(order.getOrderId());
            // 标价金额
            wxPayUnifiedOrderRequest.setTotalFee(finaloneprice*count);
            // 终端IP
            wxPayUnifiedOrderRequest.setSpbillCreateIp(getIp(request));
//        wxPayUnifiedOrderRequest.setSpbillCreateIp("172.16.13.50");
            // 通知地址
            wxPayUnifiedOrderRequest.setNotifyUrl(basePath + payCallUrl);
            //.setNotifyURL(basePath + payCallUrl);
            // 交易类型
            wxPayUnifiedOrderRequest.setTradeType("JSAPI");


            WxPayUnifiedOrderResult result = wxPayService.unifiedOrder(wxPayUnifiedOrderRequest);

            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>result:" + JSON.toJSONString(result));

            order.setTxOrderNo(result.getPrepayId());
            order.setPayTime(new Date());


            String signType = "MD5";
            String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
            String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
            Map<String, String> configMap = Maps.newHashMap();
            configMap.put("appId", this.wxPayService.getConfig().getAppId());
            configMap.put("timeStamp", timeStamp);
            configMap.put("nonceStr", nonceStr);
            configMap.put("package", "prepay_id=" + result.getPrepayId());
            configMap.put("signType", signType);
            String paySign = SignUtils.createSign(configMap, signType, this.wxPayService.getConfig().getMchKey(), new String[]{"sign", "key", "xmlString", "xmlDoc", "couponList"});
//        String paySign = SignUtils.createSign(configMap, signType, this.wxMaService.getWxMaConfig().getSecret(), false);
            this.logger.info(">>>>>>>>>>map:" + JSON.toJSONString(configMap) + ">>>>>>>paySign:" + paySign);
            Map<String, Object> map = Maps.newHashMap();
            map.put("appId", this.wxPayService.getConfig().getAppId());
            map.put("timeStamp", timeStamp);
            map.put("nonceStr", nonceStr);
            map.put("package", result.getPrepayId());
            map.put("signType", signType);
            map.put("paySign", paySign);

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

            HashMap hm = new HashMap();
            hm.put("orderId", order.orderId);
            hm.put("appId", this.wxPayService.getConfig().getAppId());
            hm.put("timeStamp", timeStamp);
            hm.put("nonceStr", nonceStr);
            hm.put("package", result.getPrepayId());
            hm.put("signType", signType);
            hm.put("paySign", paySign);
            commonJson.setResultCode("1");
            commonJson.setResultMsg("下单成功！");
            commonJson.setResultData(hm);

        }




        return commonJson;
    }

    /**
     * 获取真实ip
     * @param request
     * @return
     */
    private static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    @PostMapping(value = "/paySuccess")
    @ResponseBody
    @Transactional
    /***
     * 微信支付回调函数，订单号需要改成微信支付订单号
     */
    public void PaySuccess(HttpServletRequest request) throws IOException {

        try {
            // 更新微信订单
            String restxml = HttpUtils.getBodyString(request.getReader());
            this.logger.info(">>>>>>>>>>>>>>OnlineCourseController.notify>>>>>>>>>restxml:" + restxml);
            WxPayOrderNotifyResult wxPayOrderNotifyResult = wxPayService.parseOrderNotifyResult(restxml);
            ordermodel wechatOrderModel = orderRespository.getByOrderId(wxPayOrderNotifyResult.getOutTradeNo());
            this.logger.info(">>>>>>>>>>>>>>OnlineCourseController.notify>>>>>>>>>wechatOrderModel:" + JSON.toJSONString(wechatOrderModel));
            // 如果支付成功
            if ("SUCCESS".equals(wxPayOrderNotifyResult.getReturnCode()) && "SUCCESS".equals(wxPayOrderNotifyResult.getResultCode())) {


                String openId = wechatOrderModel.getOpenId();
                String transactionId =wxPayOrderNotifyResult.getTransactionId();
                wechatOrderModel.updateTime = new Date();
                wechatOrderModel.paySuccessTime =  wxPayOrderNotifyResult.getTimeEnd();
                if (wechatOrderModel.orderState.equals("未支付"))
                    wechatOrderModel.orderState = "已支付";
                if (wechatOrderModel.preOpenId != null && wechatOrderModel.bonusFlag == 0 ) {
                    List<orderdetailmodel> orderdetaillist = orderDetailRespository.getByOrderId(wechatOrderModel.orderId);
                    //有上线
                    List<partnerpricemodel> partner = partnerPriceRespository.getByProductId(orderdetaillist.get(0).productId, 2);
                    productmodel product = productRespository.getByProductId(orderdetaillist.get(0).productId);
                    int profitmoney = product.productOfflinePrice - partner.get(0).price;
                    int companyprofit = new BigDecimal(profitmoney).multiply(new BigDecimal(0.3)).intValue();
                    int customerprofit = profitmoney - companyprofit;

                    bonusmodel customerbonus = new bonusmodel();
                    customerbonus.orderId = wechatOrderModel.orderId;
                    customerbonus.state = 0;
                    customerbonus.bonus = customerprofit;
                    customerbonus.bonusId = UUID.randomUUID().toString();
                    customerbonus.openId = openId;
                    customerbonus.createTime = new Date();
                    customerbonus.companybonus = companyprofit;
                    wechatOrderModel.bonusFlag = 1;
                    bonusRepository.save(customerbonus);
                }
                orderRespository.save(wechatOrderModel);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WxPayException e) {
            e.printStackTrace();
        }
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
        String openId = Common.getOpenId();
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
