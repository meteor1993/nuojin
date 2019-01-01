<template>
  <div>
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
    <div class="content clearfix">
      <ul class="fl">
        <li class="leftBox" @click="changeShowIndex(0)">
           <img src="@/assets/images/红酒@2x.png" alt="">
        </li>
        <li class="leftBox" @click="changeShowIndex(1)">
           <img src="@/assets/images/红酒@2x.png" alt="">
        </li>
        <li class="leftBox" @click="changeShowIndex(2)">
           <img src="@/assets/images/红酒@2x.png" alt="">
        </li>
      </ul>
      <ul class="rightBox fl" v-show="showIndex == 0">
        <li class="item clearfix" v-for="(product, index) in productList" :key="index" @click="goProductDetail(product.id)">
          <div class="leftImg fl">
            <img src="@/assets/images/星空版图片@2x.png" alt="">
          </div>
          <div class="rightMess fl">
            <p class="title">{{ product.title }}</p>
            <p class="amount">月销量<i>{{ product.payByMonth }}</i>瓶</p>
            <p class="infoMess">{{ product.infoMess }}</p>
            <div class="price">
              <p class="originalPrice">价格 <i>{{ product.originalPrice }}</i></p>
              <p class="nowPrice"><i>&yen; </i>{{ product.nowPrice }}</p>
            </div>
          </div>
        </li>
      </ul>
      <ul class="rightBox fl" v-show="showIndex == 1">
        <li class="item clearfix">
          <div class="leftImg fl">
            <img src="@/assets/images/星空版图片@2x.png" alt="">
          </div>
          <div class="rightMess fl">
            <p class="title">星空版</p>
            <p class="amount">月销量<i>99</i>瓶</p>
            <p class="infoMess">一款好酒</p>
            <div class="price">
              <p class="originalPrice">价格 <i>564</i></p>
              <p class="nowPrice"><i>&yen; </i>240</p>
            </div>
          </div>
        </li>
        <li class="item clearfix">
          <div class="leftImg fl">
            <img src="@/assets/images/星空版图片@2x.png" alt="">
          </div>
          <div class="rightMess fl">
            <p class="title">星空版</p>
            <p class="amount">月销量<i>100</i>瓶</p>
            <p class="infoMess">一款好酒</p>
            <div class="price">
              <p class="originalPrice">价格 <i>564</i></p>
              <p class="nowPrice"><i>&yen; </i>240</p>
            </div>
          </div>
        </li>
        <li class="item clearfix">
          <div class="leftImg fl">
            <img src="@/assets/images/星空版图片@2x.png" alt="">
          </div>
          <div class="rightMess fl">
            <p class="title">星空版</p>
            <p class="amount">月销量<i>100</i>瓶</p>
            <p class="infoMess">一款好酒</p>
            <div class="price">
              <p class="originalPrice">价格 <i>564</i></p>
              <p class="nowPrice"><i>&yen; </i>240</p>
            </div>
          </div>
        </li>
      </ul>
      <ul class="rightBox fl" v-show="showIndex == 2">
        <li class="item clearfix">
          <div class="leftImg fl">
            <img src="@/assets/images/星空版图片@2x.png" alt="">
          </div>
          <div class="rightMess fl">
            <p class="title">星空版</p>
            <p class="amount">月销量<i>122200</i>瓶</p>
            <p class="infoMess">一款好酒</p>
            <div class="price">
              <p class="originalPrice">价格 <i>564</i></p>
              <p class="nowPrice"><i>&yen; </i>240</p>
            </div>
          </div>
        </li>
        <li class="item clearfix">
          <div class="leftImg fl">
            <img src="@/assets/images/星空版图片@2x.png" alt="">
          </div>
          <div class="rightMess fl">
            <p class="title">星空版</p>
            <p class="amount">月销量<i>100</i>瓶</p>
            <p class="infoMess">一款好酒</p>
            <div class="price">
              <p class="originalPrice">价格 <i>564</i></p>
              <p class="nowPrice"><i>&yen; </i>240</p>
            </div>
          </div>
        </li>
        <li class="item clearfix">
          <div class="leftImg fl">
            <img src="@/assets/images/星空版图片@2x.png" alt="">
          </div>
          <div class="rightMess fl">
            <p class="title">星空版</p>
            <p class="amount">月销量<i>100</i>瓶</p>
            <p class="infoMess">一款好酒</p>
            <div class="price">
              <p class="originalPrice">价格 <i>564</i></p>
              <p class="nowPrice"><i>&yen; </i>240</p>
            </div>
          </div>
        </li>
      </ul>
    </div>
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
<script src="./shoppingMall.js"></script>
<style scoped="scoped" lang="less" src="./shoppingMall.less"></style >
