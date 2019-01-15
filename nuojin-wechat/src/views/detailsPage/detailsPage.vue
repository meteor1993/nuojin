<template>
  <div>
    <div class="swi">
      <!-- <Swiper class="swiper">
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
      </Swiper> -->
<video-player  class="video-player vjs-custom-skin"
     ref="videoPlayer"
     :playsinline="true"
     :options="playerOptions"
></video-player>

      <div class="arrowIcon">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
    </div>
    <div class="wine">
      <p>{{model.productName}}&nbsp;&nbsp;({{model.productSpec}})</p>
    </div>
    <div class="content">
      <div class="firstLine clearfix">
        <p class="fl"><i>&yen;</i>{{model.productNowPrice/100}}</p>
        <p class="fr firstRight">
          月销量<i>???</i>瓶
        </p>
      </div>
      <!-- <div class="secondLine clearfix">
        <p class="fl">快递: 10元</p>
        <p class="fr"><i class="iconfont locationAdd icon-location"></i>&nbsp;上海</p>
      </div> -->
    </div>
    <div class="listDetails">
      <ul>
        <li class="item clearfix">
          <p class="fl">品牌</p>
          <p class="fl">{{model.productBrand}}</p>
        </li>
        <li class="item clearfix">
          <p class="fl">净含量</p>
          <p class="fl">{{model.productVolume}}</p>
        </li>
        <li class="item clearfix">
          <p class="fl">净重量</p>
          <p class="fl">{{model.productWeight}}</p>
        </li>
        <li class="item clearfix">
          <p class="fl">生产日期</p>
          <p class="fl">{{model.productDate}}</p>
        </li>
      </ul>
    </div>
    <div class="imgList">
      <img :src="model.productDescImgUrl"
        alt=""
      >
    </div>
    <div class="btn">
      <button @click="goPayOrder(model.productId)">购买</button>
    </div>
    <div style="height:70px;"></div>
    <div class="nav clearfix">
      <div class="leftNav fl">
        <img
          src="@/assets/images/商城选中@2x.png"
          alt=""
        >
        <p>商城</p>
      </div>
      <div class="rightNav fr" @click="goUserInfo()">
        <img
          src="@/assets/images/我的未选中@2x.png"
          alt=""
        >
        <p>个人中心</p>
      </div>
    </div>


  </div>
  
</template>
<script src="./detailsPage.js"></script>
<style src="./detailsPage.less" lang="less" scoped="scoped"></style>

