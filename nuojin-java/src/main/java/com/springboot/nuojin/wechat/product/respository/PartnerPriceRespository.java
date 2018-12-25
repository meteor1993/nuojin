package com.springboot.nuojin.wechat.product.respository;

import com.springboot.nuojin.wechat.product.model.partnerpricemodel;
import com.springboot.nuojin.wechat.product.model.productmodel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PartnerPriceRespository extends PagingAndSortingRepository<partnerpricemodel, Long>, JpaSpecificationExecutor<partnerpricemodel> {

    @Query(value = "select * from partnerprice_info a where a.product_id=?1 and a.customer_level=?2 order by a.price desc", nativeQuery = true)
    List<partnerpricemodel> getByProductId(String ProductId,int Level);
}
