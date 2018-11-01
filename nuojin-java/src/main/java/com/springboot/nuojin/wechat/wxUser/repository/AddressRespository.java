package com.springboot.nuojin.wechat.wxUser.repository;

import com.springboot.nuojin.wechat.wxUser.model.AddressModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @Author: daixueyun
 * @Description:
 * @Date: Create in 22:03 2018/11/1
 */
public interface AddressRespository extends PagingAndSortingRepository<AddressModel, Long>, JpaSpecificationExecutor<AddressModel> {

    /**
     * 通过用户id查找收货地址
     * @param userId
     * @return
     */
    List<AddressModel> findAllByUserId(String userId);

    /**
     * 通过地址id获取地址信息
     * @param id
     * @return
     */
    AddressModel findAddressModelById(String id);
}
