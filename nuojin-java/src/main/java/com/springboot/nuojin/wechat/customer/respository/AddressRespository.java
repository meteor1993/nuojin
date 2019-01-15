package com.springboot.nuojin.wechat.customer.respository;

import com.springboot.nuojin.wechat.customer.model.accountmodel;
import com.springboot.nuojin.wechat.customer.model.addressmodel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
    public interface AddressRespository extends PagingAndSortingRepository<addressmodel, Long>, JpaSpecificationExecutor<addressmodel> {

        @Query(value="select * from address_info where open_id =:open_id order by update_date desc ",nativeQuery = true)
        List<addressmodel> getByOpenId(@Param("open_id") String open_id);


        addressmodel getByAddressId(@Param("address_id") String address_id);

}
