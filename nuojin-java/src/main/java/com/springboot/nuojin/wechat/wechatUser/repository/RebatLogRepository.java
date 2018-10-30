package com.springboot.nuojin.wechat.wechatUser.repository;

import com.springboot.nuojin.wechat.wechatUser.model.RebatLogModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/30
 * Time:23:23
 **/
public interface RebatLogRepository extends PagingAndSortingRepository<RebatLogModel, Long>, JpaSpecificationExecutor<RebatLogModel> {
}
