package com.springboot.nuojin.web.OfflineOrder.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.springboot.nuojin.system.model.CommonJson;
import com.springboot.nuojin.system.regions.repository.RegionsRepository;
import com.springboot.nuojin.system.utils.ContextHolderUtils;
import com.springboot.nuojin.system.utils.HttpUtils;
import com.springboot.nuojin.system.utils.IdGenerator;
import com.springboot.nuojin.web.OfflineOrder.model.OfflineOrderModel;
import com.springboot.nuojin.web.OfflineOrder.repository.OfflineOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping(path = "/web/offlineOrder")
public class OfflineOrderController {
    private final Logger logger = LoggerFactory.getLogger(OfflineOrderController.class);

    @Autowired
    OfflineOrderRepository offlineOrderRepository;

    @Autowired
    RegionsRepository regionsRepository;

    /**
     * 线下订单列表页
     * @return
     */
    @GetMapping(value = "/adminOrderListPage")
    public String adminOrderListPage() {
        return "web/adminOrderListPage";
    }

    @PostMapping(value = "/saveOrUpdateOrder")
    @ResponseBody
    public CommonJson saveOrUpdateOrder() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        logger.info(params);
        JSONObject jsonObject = JSONObject.parseObject(params);
        String id = jsonObject.getString("id");
        String productNo = jsonObject.getString("productNo");
        String address = jsonObject.getString("address");
        String price = jsonObject.getString("price");
        String number = jsonObject.getString("number");
        String channel = jsonObject.getString("channel");
        String region = jsonObject.getString("region");

        OfflineOrderModel offlineOrderModel = null;
        if (Strings.isNullOrEmpty(id)) { // 如果id不存在，则为新增
            offlineOrderModel = new OfflineOrderModel();
            offlineOrderModel.setCreateDate(new Date());
            offlineOrderModel.setOrderNo(IdGenerator.INSTANCE.nextId());
            offlineOrderModel.setStatus("0");
        } else {
            offlineOrderModel = offlineOrderRepository.getByIdIs(id);
            offlineOrderModel.setUpdateDate(new Date());
        }

        String cityName = regionsRepository.getByCodeIs(region.substring(0, 2)).getName() + regionsRepository.getByCodeIs(region.substring(0, 4)).getName() + regionsRepository.getByCodeIs(region.substring(0, 6)).getName() + regionsRepository.getByCodeIs(region.substring(0, 9)).getName();

        cityName += address;

        logger.info(cityName);

        offlineOrderModel.setAddress(cityName);
        offlineOrderModel.setChannel(channel);
        offlineOrderModel.setCityCode(region);
        offlineOrderModel.setNumber(Integer.parseInt(number));
        offlineOrderModel.setPrice(Integer.parseInt(price));
        offlineOrderModel.setProductNo(productNo);

        offlineOrderRepository.save(offlineOrderModel);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");

        return json;
    }
}
