package com.springboot.nuojin.wechat.pay.respository;

import com.springboot.nuojin.wechat.pay.model.WxPayOrderModel;
import com.springboot.nuojin.wechat.bonus.model.bonusmodel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface WxPayOrderRespository  extends PagingAndSortingRepository<WxPayOrderModel, Long>, JpaSpecificationExecutor<WxPayOrderModel> {

    List<WxPayOrderModel> getByTransactionId(String transactionId);


}
