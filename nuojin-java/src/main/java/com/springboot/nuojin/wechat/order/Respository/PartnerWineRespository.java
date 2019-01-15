package com.springboot.nuojin.wechat.order.Respository;

import com.springboot.nuojin.wechat.order.model.orderrefundmodel;
import com.springboot.nuojin.wechat.order.model.partnerwinemodel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PartnerWineRespository extends PagingAndSortingRepository<partnerwinemodel, Long>, JpaSpecificationExecutor<partnerwinemodel> {

    List<partnerwinemodel> getByOpenId(String openId);


}