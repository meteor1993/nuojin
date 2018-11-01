package com.springboot.nuojin.wechat.commodity.repository;

import com.springboot.nuojin.wechat.commodity.model.CommodityModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @Author: daixueyun
 * @Description:
 * @Date: Create in 21:00 2018/11/1
 */
public interface CommodityRepository extends PagingAndSortingRepository<CommodityModel, Long>, JpaSpecificationExecutor<CommodityModel> {

    /**
     * 通过商品类别查找商品
     *
     * @param commodityType
     * @return
     */
    List<CommodityModel> findAllByCommodityTypeOrderByCommodityOrder(String commodityType);

    /**
     * 通过商品id查找商品信息
     *
     * @param id
     * @return
     */
    CommodityModel findCommodityModelById(String id);
}
