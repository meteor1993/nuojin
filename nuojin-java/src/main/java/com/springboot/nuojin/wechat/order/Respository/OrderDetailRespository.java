package com.springboot.nuojin.wechat.order.Respository;

import com.springboot.nuojin.wechat.order.model.orderdetailmodel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderDetailRespository extends PagingAndSortingRepository<orderdetailmodel, Long>, JpaSpecificationExecutor<orderdetailmodel> {

    List<orderdetailmodel> getByOrderId(String orderId);

}
