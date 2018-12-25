package com.springboot.nuojin.wechat.product.respository;


import com.springboot.nuojin.wechat.customer.model.customermodel;
import com.springboot.nuojin.wechat.product.model.partnerpricemodel;
import com.springboot.nuojin.wechat.product.model.productmodel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRespository extends PagingAndSortingRepository<productmodel, Long>, JpaSpecificationExecutor<productmodel> {


    List<productmodel> getByProductFirstType(String ProductFirstType);
    productmodel getByProductId(String ProductId);



}
