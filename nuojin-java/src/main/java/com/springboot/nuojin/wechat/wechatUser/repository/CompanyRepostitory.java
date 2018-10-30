package com.springboot.nuojin.wechat.wechatUser.repository;

import com.springboot.nuojin.wechat.wechatUser.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/30
 * Time:23:25
 **/
public interface CompanyRepostitory extends PagingAndSortingRepository<CompanyModel, Long>, JpaSpecificationExecutor<CompanyModel> {
}
