package com.springboot.nuojin.wechat.commodity.repository;

import com.springboot.nuojin.wechat.commodity.model.CommodityImageModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @Author: daixueyun
 * @Description:
 * @Date: Create in 21:39 2018/11/1
 */
public interface CommodityImageRepository extends PagingAndSortingRepository<CommodityImageModel, Long>, JpaSpecificationExecutor<CommodityImageModel> {

    /**
     * 根据商品编号查找商品描述图
     * @param commodityId
     * @return
     */
    List<CommodityImageModel> findAllByCommodityIdOrderByImageOrder(String commodityId);
}
