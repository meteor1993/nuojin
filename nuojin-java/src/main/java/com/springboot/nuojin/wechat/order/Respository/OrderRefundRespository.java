package com.springboot.nuojin.wechat.order.Respository;

import com.springboot.nuojin.wechat.order.model.orderrefundmodel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRefundRespository extends PagingAndSortingRepository<orderrefundmodel, Long>, JpaSpecificationExecutor<orderrefundmodel> {


    List<orderrefundmodel> getByOrderId(String orderId);

}
