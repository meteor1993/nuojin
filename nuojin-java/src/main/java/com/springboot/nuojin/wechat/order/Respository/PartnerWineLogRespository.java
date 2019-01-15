package com.springboot.nuojin.wechat.order.Respository;


import com.springboot.nuojin.wechat.order.model.partnerwinelogmodel;
import com.springboot.nuojin.wechat.order.model.partnerwinemodel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PartnerWineLogRespository extends PagingAndSortingRepository<partnerwinelogmodel, Long>, JpaSpecificationExecutor<partnerwinelogmodel> {



}
