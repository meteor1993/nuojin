package com.springboot.nuojin.web.OfflineOrder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/web/offlineOrder")
public class OfflineOrderController {
    private final Logger logger = LoggerFactory.getLogger(OfflineOrderController.class);

    /**
     * 线下订单列表页
     * @return
     */
    @GetMapping(value = "/adminOrderListPage")
    public String adminOrderListPage() {
        return "web/adminOrderListPage";
    }
}
