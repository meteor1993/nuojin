package com.springboot.nuojin.web.OfflineOrder.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "offline_order")
@Data
public class OfflineOrderModel {
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="ID", nullable = false, length = 36)
    private String id;
    private Date createDate;
    private Date updateDate;
    // 省份、城市、区县、乡镇 code
    private String cityCode;
    // 地址
    private String address;
    // 订单号
    private String orderNo;
    // 单价
    private Integer price;
    // 数量
    private Integer number;
    // 渠道
    private  String channel;
    // 状态 0创建 1发货 2退货
    private String status;
    // 产品 1.标准版 2.星空版
    private String productNo;
}
