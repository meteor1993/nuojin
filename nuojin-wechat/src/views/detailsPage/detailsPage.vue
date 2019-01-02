<template>
  <div>
    <div class="swi">
      <Swiper class="swiper">
        <Slide>
          <img
            src="@/assets/images/banner@2x.png"
            alt=""
          >
        </Slide>
        <Slide>
          <img
            src="@/assets/images/banner@2x.png"
            alt=""
          >
        </Slide>
      </Swiper>
      <div class="arrowIcon">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
    </div>
    <div class="wine">
      <p>红酒&nbsp;&nbsp;(星空版)</p>
    </div>
    <div class="content">
      <div class="firstLine clearfix">
        <p class="fl"><i>&yen;</i>240</p>
        <p class="fr firstRight">
          月销量<i>100</i>瓶
        </p>
      </div>
      <div class="secondLine clearfix">
        <p class="fl">快递: 10元</p>
        <p class="fr"><i class="iconfont locationAdd icon-location"></i>&nbsp;上海</p>
      </div>
    </div>
    <div class="listDetails">
      <ul>
        <li class="item clearfix">
          <p class="fl">品牌</p>
          <p class="fl">aodaliy</p>
        </li>
        <li class="item clearfix">
          <p class="fl">净含量</p>
          <p class="fl">100kg</p>
        </li>
        <li class="item clearfix">
          <p class="fl">保质期</p>
          <p class="fl">12个月</p>
        </li>
        <li class="item clearfix">
          <p class="fl">生产日期</p>
          <p class="fl">2018-01-02</p>
        </li>
      </ul>
    </div>
    <div class="imgList">
      <img
        src="@/assets/images/普通版.png"
        alt=""
      >
    </div>
    <div class="btn">
      <button @click="goPayOrder(model.id)">购买</button>
    </div>
  </div>
</template>
<script src="./detailsPage.js"></script>
<style src="./detailsPage.less" lang="less" scoped="scoped"></style>

