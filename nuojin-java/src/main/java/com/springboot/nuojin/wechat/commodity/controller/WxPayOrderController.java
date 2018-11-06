package com.springboot.nuojin.wechat.commodity.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springboot.nuojin.system.model.CommonJson;
import com.springboot.nuojin.wechat.commodity.repository.WxPayOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: daixueyun
 * @Description:
 * @Date: Create in 21:44 2018/11/4
 */
@RestController
@RequestMapping(path = "/mp/wxPayOrderController")
public class WxPayOrderController {

    @Autowired
    WxPayOrderRepository wxPayOrderRepository;

    /**
     * 获取订单列表
     * @return
     */
    @PostMapping(value = "/getOrderList")
    public CommonJson getOrderList() {
        Map<String, Object> map = Maps.newHashMap();
        String phone = "1";
        map.put("list", Lists.newArrayList(wxPayOrderRepository.findWxPayOrderModelsByPhoneOrderByPayTime(phone)));
        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");
        json.setResultData(map);
        return json;
    }
}
