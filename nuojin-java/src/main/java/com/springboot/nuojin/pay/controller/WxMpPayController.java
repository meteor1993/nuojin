package com.springboot.nuojin.pay.controller;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.service.WxPayService;
import com.springboot.nuojin.system.model.CommonJson;
import com.springboot.nuojin.system.utils.ContextHolderUtils;
import com.springboot.nuojin.wechat.Utils.Common;
import com.springboot.nuojin.wechat.order.Respository.OrderDetailRespository;
import com.springboot.nuojin.wechat.order.Respository.OrderRespository;
import com.springboot.nuojin.wechat.order.model.orderdetailmodel;
import com.springboot.nuojin.wechat.order.model.ordermodel;
import com.springboot.nuojin.wechat.product.model.productmodel;
import com.springboot.nuojin.wechat.product.respository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class WxMpPayController {

    @Autowired
    WxPayService wxPayService;
    @Autowired
    OrderRespository orderRespository;
    @Autowired
    OrderDetailRespository orderDetailRespository;
@Autowired
    ProductRespository productRespository;

    @PostMapping(value = "/unifiedOrder")
    public void unifiedOrder(@RequestParam String orderId) throws IOException {
        String token = ContextHolderUtils.getRequest().getHeader("token");
        String openid = Common.getOpenId();

        //根据orderId获取订单
        ordermodel ordermodel = orderRespository.getByOrderId(orderId);

        //根据orderId获取订单明细
        List<orderdetailmodel> orderdetailmodel = orderDetailRespository.getByOrderId(orderId);

        //
        productmodel productmodel = productRespository.getByProductId(orderdetailmodel.get(0).productId);

        HttpServletRequest request = ContextHolderUtils.getRequest();
        String basePath = request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/";



        //初始化微信下单信息
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = WxPayUnifiedOrderRequest.newBuilder().build();
        wxPayUnifiedOrderRequest.setNonceStr(UUID.randomUUID().toString().replaceAll("-", ""));
        wxPayUnifiedOrderRequest.setOpenid(openid);
        wxPayUnifiedOrderRequest.setBody(productmodel.productName);
        wxPayUnifiedOrderRequest.setOutTradeNo(ordermodel.orderId);
        //wxPayUnifiedOrderRequest. orderdetailmodel.get(0).productRealTotalPrice

    }

}
