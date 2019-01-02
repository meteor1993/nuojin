<template>
  <div>
    <!-- 这个是上面的头部 -->
    <div class="headerLayout">
      <!-- 这个是最头部的导航 -->
      <div class="header">
        <div class="arrow ">
          <i class="iconfont icon-zuojiantou"></i>
        </div>
        <div class="headerTitle">个人中心</div>
      </div>
      <!-- 这个是接下来的用户头像 名称 -->
      <div class="user clearfix">
        <div class="userLeft fl">
          <img
            src="@/assets/images/头像@2x.png"
            alt=""
          >
        </div>
        <div class="userRight fl">
          <div class="userName clearfix">
            <p class="">
              我是大侠
              <img
                class=""
                src="@/assets/images/会员@2x.png"
                alt=""
              >
            </p>
          </div>
          <div class="agent">
            <p>
              <img
                src="@/assets/images/角色@2x.png"
                alt=""
              >
              代理商
            </p>
          </div>
        </div>
      </div>
    </div>
    <!-- 这个才是主要的内容 -->
    <div class="content">
      <ul>
        <li class="item">
          <img
            class="iconImg"
            src="@/assets/images/余额@2x.png"
            alt=""
          >
          <span class="myPrice">我的余额: </span>
          <span class="price">
            <i>100</i>
            元
          </span>
          <div class="withdrawal">
            点击提现
            <i class="iconfont icon-jiantou"></i>
          </div>
        </li>
        <li class="item" @click="goMyOrder()">
          <img
            class="iconImg"
            src="@/assets/images/订单@2x.png"
            alt=""
          >
          <span class="myPrice">我的订单 </span>
          <div class="withdrawal">
            <i class="iconfont icon-jiantou"></i>
          </div>
        </li>
        <li class="item" @click="goPipelineRecord()">
          <img
            class="iconImg"
            src="@/assets/images/提现@2x.png"
            alt=""
          >
          <span class="myPrice">提现记录 </span>

          <div class="withdrawal">
            <i class="iconfont icon-jiantou"></i>
          </div>
        </li>
        <li class="item" @click="goDeliveryDddress()">
          <img
            class="iconImg"
            src="@/assets/images/地址管理@2x.png"
            alt=""
          >
          <span class="myPrice">地址管理 </span>
          <div class="withdrawal">
            <i class="iconfont icon-jiantou"></i>
          </div>
        </li>
      </ul>
    </div>

    <div class="nav clearfix">
      <div class="leftNav fl" @click="goShoppingMall()">
        <img
          src="@/assets/images/商城未选中@2x.png"
          alt=""
        >
        <p>商城</p>
      </div>
      <div class="rightNav fr">
        <img
          src="@/assets/images/我的选中@2x.png"
          alt=""
        >
        <p>个人中心</p>
      </div>
    </div>
  </div>
</template>
<script src="./userInfo.js"></script>
<style lang="less" scoped="scoped" src="./userInfo.less"></style>


