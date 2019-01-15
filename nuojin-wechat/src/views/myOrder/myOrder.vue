<template>
  <div>
    <!-- 这个是最头部的导航 -->
    <div class="header">
      <div class="arrow " @click="goback()">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
      <div class="headerTitle">我的订单</div>
    </div>
    <div class="content">
      <ul>
        <li class="item clearfix" v-for="(order, index) in orderlist" :key="index">
          <div class="orderNumber clearfix">
            <p class="fl">订单号:{{order.orderId}} </p>
            <p class="fr">{{order.orderState}}</p>
          </div>
          <div class="contentBox">
            <div class="listImg">
              <img
                :src="order.detail[0].productSmallHeadImgUrl"
                alt=""
              >
            </div>
            <div class="firstLine clearfix">
              <span class="fl">{{order.detail[0].productName}}</span>
              <span class="fr">{{order.detail[0].productNum}}</span>
            </div>
            <div class="secondLine">
              原单价: <span>&yen;{{order.detail[0].productUnitPrice/100}}</span>
            </div>
            <div class="thLine">
              优惠价: <span><i>&yen;</i>{{order.detail[0].productRealUnitPrice/100}}</span>
            </div>
            <!-- <div class="fourthLine">省36元</div> -->
          </div>
          <div class="orderTime clearfix">
            <p class="fl">下单时间: {{order.createTime|dateFormat}}</p>
            <div class="totalPrice clearfix fr">
              <span class="fl">总价:&nbsp;&nbsp;</span>
              <span class="fl"><i>&yen;</i>{{order.detail[0].productRealTotalPrice/100}}</span>
            </div>
          </div>
          <div class="btnTwo">
            <div class="readBtn fl">
              <button @click="ShowExpress(order.orderId)">查看物流</button>
            </div>
            <!-- <div class="returnBtn fl">
              <button>退货</button>
            </div> -->
          </div>
        </li>
       
      </ul>
    </div>
    <div class="back-div" v-if="flag" @click="hideExpress()">
      <div class="panel-div">
        <div class="title">{{showExpressCompany}}</div>
        <div class="text">快递单号：{{showExpressNo}}</div>
        <!-- <div class="text">物流信息：
          <div>上海装车</div>
          <div>上海装车</div>
        </div> -->
        <div style="height: 10px;"></div>
      </div>
    </div>
  </div>
</template>
<script src="./myOrder.js"></script>
<style src="./myOrder.less" lang="less" scoped="scoped"></style>
<style>
.back-div {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #000000ba;
}
.panel-div {
  position: relative;
  width: 250px;
  margin: 100px auto;
  background-color: white;
  border-radius: 5px;
}
.panel-div .title {
  padding: 10px;
  font-size: 14px;
  text-align: center;
  font-weight: 700;
  color: white;
  border-radius: 5px;
  background-color: #ff5b00;
}
.panel-div .text {
  margin: 10px;
}
</style>


