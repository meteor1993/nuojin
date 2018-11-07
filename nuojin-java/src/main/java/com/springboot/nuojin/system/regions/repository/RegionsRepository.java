package com.springboot.nuojin.system.regions.repository;

import com.springboot.nuojin.system.regions.model.RegionsModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RegionsRepository extends PagingAndSortingRepository<RegionsModel, Long>, JpaSpecificationExecutor<RegionsModel> {
    RegionsModel getByCodeIs(String code);
}
