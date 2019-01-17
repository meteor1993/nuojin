package com.springboot.nuojin.wechat.product.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.springboot.nuojin.system.model.CommonJson;
import com.springboot.nuojin.system.utils.ContextHolderUtils;
import com.springboot.nuojin.system.utils.HttpUtils;
import com.springboot.nuojin.wechat.Utils.Common;
import com.springboot.nuojin.wechat.customer.model.customermodel;
import com.springboot.nuojin.wechat.customer.respository.CustomerRespository;
import com.springboot.nuojin.wechat.product.model.partnerpricemodel;
import com.springboot.nuojin.wechat.product.model.productmodel;
import com.springboot.nuojin.wechat.product.respository.PartnerPriceRespository;
import com.springboot.nuojin.wechat.product.respository.ProductRespository;
import com.springboot.nuojin.wechat.wxUser.controller.WxUserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/mp/productController")
public class productController {

    private final Logger logger = LoggerFactory.getLogger(WxUserController.class);
    @Autowired
    ProductRespository productRespository;
    @Autowired
    PartnerPriceRespository partnerPriceRespository;
    @Autowired
    CustomerRespository customerRespository;

    @PostMapping(value = "/getByProductFirstType")
    @ResponseBody
    public CommonJson getByProductFirstType() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        String openId = Common.getOpenId();;//Common.getOpenId();
        JSONObject jsonObject = JSON.parseObject(params);
        String productFirstType = jsonObject.getString("productFirstType");
        List<productmodel> list = productRespository.getByProductFirstType(productFirstType);

        for(productmodel oneproduct:list)
        {
            getNowProductPrice(oneproduct,openId);
        }


        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        Map<String, Object> map = Maps.newHashMap();
        map.put("info",list);
        json.setResultData(map);
        return json;
    }

    private void getNowProductPrice(productmodel one,String openId)
    {
        customermodel mycust = customerRespository.getByOpenId(openId);
        int level = mycust.getLevel();
        if (level == 2)
        {
            //合伙人
            List<partnerpricemodel> partnerList = partnerPriceRespository.getByProductId(one.productId,level);
            one.productNowPrice = partnerList.get(0).price;

        }
        else if(level == 1)
        {
            one.productNowPrice = one.productOfflinePrice;
        }
        else
        {
            one.productNowPrice = one.productNormalPrice;
        }

    }

    @PostMapping(value = "/getByProductId")
    @ResponseBody
    public CommonJson getByProductId() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        String openId = Common.getOpenId();;//Common.getOpenId();

        JSONObject jsonObject = JSON.parseObject(params);
        String productId = jsonObject.getString("productId");

        productmodel one = productRespository.getByProductId(productId);

        getNowProductPrice(one,openId);
        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        Map<String, Object> map = Maps.newHashMap();
        map.put("info",one);
        json.setResultData(map);
        return json;
    }

    @PostMapping(value = "/getPartnerPrice")
    @ResponseBody
    public CommonJson getPartnerPrice() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        JSONObject jsonObject = JSON.parseObject(params);
        String productId = jsonObject.getString("productId");
        String openId = Common.getOpenId();;//Common.getOpenId();
        customermodel onecustomer = customerRespository.getByOpenId(openId);
        HashMap hm = new HashMap();
        CommonJson commonJson = new CommonJson();
        commonJson.setResultCode("1");
        commonJson.setResultMsg("success");
        hm.put("info",partnerPriceRespository.getByProductId(productId,onecustomer.level));
        commonJson.setResultData(hm);
        return commonJson;


    }

}
