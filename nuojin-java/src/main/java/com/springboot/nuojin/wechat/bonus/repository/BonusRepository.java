package com.springboot.nuojin.wechat.bonus.repository;

import com.springboot.nuojin.wechat.bonus.model.bonusmodel;
import com.springboot.nuojin.wechat.customer.model.customermodel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BonusRepository extends PagingAndSortingRepository<bonusmodel, Long>, JpaSpecificationExecutor<bonusmodel> {


    List<bonusmodel> getByOpenId(String openId);



}
