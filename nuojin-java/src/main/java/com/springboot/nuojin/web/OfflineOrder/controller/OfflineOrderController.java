package com.springboot.nuojin.web.OfflineOrder.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/web/offlineOrder")
public class OfflineOrderController {
    private final Logger logger = LoggerFactory.getLogger(OfflineOrderController.class);

    @Autowired
    OfflineOrderRepository offlineOrderRepository;

    @Autowired
    RegionsRepository regionsRepository;

    /**
     * 管理员线下订单列表页
     * @return
     */
    @GetMapping(value = "/adminOrderListPage")
    public String adminOrderListPage() {
        return "web/adminOrderListPage";
    }

    @GetMapping(value = "/supplierOrderListPage")
    public String supplierOrderListPage() {
        return "web/supplierOrderListPage";
    }

    /**
     * 获取订单列表
     * @return
     */
    @PostMapping(value = "/getOfflineOrderList")
    @ResponseBody
    public CommonJson getOfflineOrderList() {
        List<OfflineOrderModel> offlineOrderModelList = Lists.newArrayList(offlineOrderRepository.findAll(new Sort(Sort.Direction.DESC,"createDate")));
        Map<String, Object> map = Maps.newHashMap();
        map.put("list", offlineOrderModelList);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultData(map);
        json.setResultMsg("success");

        return json;
    }

    /**
     * 删除订单
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/delOfflineOrder")
    @ResponseBody
    @Transactional
    public CommonJson delOfflineOrder() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        logger.info(params);
    JSONObject jsonObject = JSONObject.parseObject(params);
    String id = jsonObject.getString("id");
    OfflineOrderModel offlineOrderModel = offlineOrderRepository.getByIdIs(id);
        offlineOrderRepository.delete(offlineOrderModel);

    CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");

        return json;
}

    /**
     * 保存或更新订单
     * @return
     * @throws IOException
     */
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

        String cityName = regionsRepository.getByCodeIs(region.substring(0, 2)).getName() + regionsRepository.getByCodeIs(region.substring(0, 4)).getName() + regionsRepository.getByCodeIs(region.substring(0, 6)).getName();

        cityName += address;

        logger.info(cityName);

        offlineOrderModel.setAddress(cityName);
        offlineOrderModel.setChannel(channel);
        offlineOrderModel.setCityCode(region);
        offlineOrderModel.setNumber(Integer.parseInt(number));
        offlineOrderModel.setPrice(Integer.parseInt(price) * 100);
        offlineOrderModel.setProductNo(productNo);

        offlineOrderRepository.save(offlineOrderModel);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");

        return json;
    }

    /**
     * 供应商确认订单
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/confirmOrder")
    @ResponseBody
    public CommonJson confirmOrder() throws IOException {
        String params = HttpUtils.getBodyString(ContextHolderUtils.getRequest().getReader());
        logger.info(params);
        JSONObject jsonObject = JSONObject.parseObject(params);
        String id = jsonObject.getString("id");
        String expressNo = jsonObject.getString("expressNo");
        String expressCompany = jsonObject.getString("expressCompany");
        OfflineOrderModel offlineOrderModel = offlineOrderRepository.getByIdIs(id);
        offlineOrderModel.setExpressNo(expressNo);
        offlineOrderModel.setExpressCompany(expressCompany);
        offlineOrderModel.setStatus("1");
        offlineOrderModel.setUpdateDate(new Date());
        offlineOrderModel.setSendDate(new Date());
        offlineOrderRepository.save(offlineOrderModel);

        CommonJson json = new CommonJson();
        json.setResultCode("1");
        json.setResultMsg("success");

        return json;
    }
}
