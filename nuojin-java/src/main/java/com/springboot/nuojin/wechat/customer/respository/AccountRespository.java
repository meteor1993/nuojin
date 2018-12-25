package com.springboot.nuojin.wechat.customer.respository;

import com.springboot.nuojin.wechat.customer.model.accountmodel;
import com.springboot.nuojin.wechat.customer.model.customermodel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRespository extends PagingAndSortingRepository<accountmodel, Long>, JpaSpecificationExecutor<accountmodel> {


    accountmodel getByOpenId(String open_id);


}
