package com.springboot.nuojin.wechat.order.Respository;

import com.springboot.nuojin.wechat.order.model.ordermodel;
import org.hibernate.sql.Insert;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRespository extends PagingAndSortingRepository<ordermodel, Long>, JpaSpecificationExecutor<ordermodel> {


 ordermodel getByOrderId(String orderId);

// @Query(value = "select a.order_id,a.addressa.city_code,a.create_time,a.express_company,a.express_no,a.open_id,a.order_state,a.pay_time,a.pre_open_id,a.update_time order_update_time, a.bonus_flag,b.order_detail_id,b.product_num,b.product_give_num,b.product_id,b.product_name,b.product_real_total_price,b.product_real_unit_price,b.product_spec,b.product_total_price,b.product_unit_price,b.update_time orderdetail_update_time from order_info a left join order_detail_info b on a.order_id = b.order_id where a.open_id = :open_Id and a.order_state!='未支付' order by a.update_time desc", nativeQuery = true)
 //List<Object> getByOpenId(@Param("open_id") String open_id);
List<ordermodel> getByOpenId(String openId);

}
