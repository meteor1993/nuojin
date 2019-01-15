package com.springboot.nuojin.wechat.customer.respository;

import com.springboot.nuojin.wechat.customer.model.accountmodel;
import com.springboot.nuojin.wechat.customer.model.customermodel;
import com.springboot.nuojin.wechat.customer.model.customeroutmodel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRespository extends PagingAndSortingRepository<customermodel, Long>, JpaSpecificationExecutor<customermodel> {


//    @Query( value = "select a.open_id,a.nick_name,a.level,a.pre_open_id,a.mobile,a.sex,a.wechat_image_url,b.balance,b.account_id from customer_info a left join account_info b on a.open_id=b.open_id where a.open_id=:open_id",nativeQuery = true)
//    List<customeroutmodel> selectallinfo(@Param("open_id") String open_id);


    customermodel getByOpenId(String open_id);




}
