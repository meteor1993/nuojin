package com.springboot.nuojin.web.OfflineOrder.repository;

import com.springboot.nuojin.web.OfflineOrder.model.OfflineOrderModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OfflineOrderRepository extends PagingAndSortingRepository<OfflineOrderModel, Long>, JpaSpecificationExecutor<OfflineOrderModel> {
    OfflineOrderModel getByIdIs(String id);
}
