package com.springboot.nuojin.wechat.commodity.repository;

import com.springboot.nuojin.wechat.commodity.model.WxPayOrderModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @Author: daixueyun
 * @Description:
 * @Date: Create in 21:46 2018/11/4
 */
public interface WxPayOrderRepository extends PagingAndSortingRepository<WxPayOrderModel, Long>, JpaSpecificationExecutor<WxPayOrderModel> {

    List<WxPayOrderModel> findWxPayOrderModelsByPhoneOrderByPayTime(String phone);
}
