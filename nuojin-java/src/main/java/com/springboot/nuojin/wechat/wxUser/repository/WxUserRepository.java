package com.springboot.nuojin.wechat.wxUser.repository;

import com.springboot.nuojin.wechat.wxUser.model.WxUserModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IDEA
 * author: weishiyao
 * Date:2018/10/30
 * Time:23:21
 **/
public interface WxUserRepository extends PagingAndSortingRepository<WxUserModel, Long>, JpaSpecificationExecutor<WxUserModel> {

    //根据openId获取用户信息
    WxUserModel getByOpenIdIs(String openId);
}
