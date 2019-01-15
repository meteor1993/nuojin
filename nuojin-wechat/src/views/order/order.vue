<template>
  <div>
    <!-- 这个是最头部的导航 -->
    <div class="header">
      <div class="arrow ">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
      <div class="headerTitle">填写订单</div>
    </div>
    <div class="userInfo" @click="goEditAddress()">
      <div class="user">
        <span>{{this.chooseaddress.name}}</span>
        <span>{{this.chooseaddress.mobile}}</span>
      </div>
      <div class="address">
        <p>{{this.chooseaddress.showAddress}}</p>
        <div class="default">选中</div>
      </div>
      <i class="iconfont icon-jiantou arrow"></i>
    </div>
    <div class="content">
      <ul>
        <li class="item">
          <div class="imgBox">
            <img
              src="@/assets/images/星空版图片@2x.png"
              alt=""
            >
          </div>
          <p class="titleInfo">{{productmodel.productName}}</p>
          <p class="infoContent">{{productmodel.productDescString}}</p>
          <div class="price">
            <p class="primary">价格 <span>{{productmodel.productShowPrice/100}}</span></p>
            <p class="nowPrice">&yen; <span>{{productmodel.productNowPrice/100}}</span></p>
          </div>
        </li>
      </ul>
      <div class="amount clearfix">
        <p class="fl">购买数量</p>
        <div class="fr">
          <div class="numberBox clearfix">
            <div class="reduction fl" @click="reductionNumber">-</div>
            <input type="number"  class="numberB fl" @change="numberIn" v-model="numberInput">
            <div class="add fl" @click="addNumber">+</div>
          </div>
        </div>
      </div>
      <!-- <div class="amount clearfix">
        <p class="fl">配送费</p>
        <p class="fr">&yen; 10</p>
      </div> -->
    </div>
    <div class="footer">
      <div class="total clearfix">
        <div class="totalPrice fl">
          <!-- <span>总计 </span>
          <span><i>&yen;</i>600</span> -->
        </div>
        <div class="totalDiscount fl">
        
        </div>
        <div  class="totalPriceShop fr">
          <!-- <span>实付 </span>
          <span><i>&yen;</i>310</span> -->
            <span>优惠 </span>
          <span><i>&yen;</i>300</span>
        </div>
      </div>
      <div class="moneyFoot fl">
        <div class="leftBox fl">
          <span>总计：<i>&yen;</i> {{totalmoney}}</span>
        </div>
        <div class="rightBox fl">
          <button @click='OrderSumbit()'>提交订单</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script src="./order.js"></script>
<style scoped="scoped" lang="less" src="./order.less"></style>
