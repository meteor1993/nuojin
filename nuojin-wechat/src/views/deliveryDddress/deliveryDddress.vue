<template>
  <div>
    <!-- 这个是最头部的导航 -->
    <div class="header">
      <div class="arrow" @click="goback()">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
      <div class="headerTitle">配送地址</div>
    </div>
    <div class="content">
      <ul>
        <li @click="selectionBtn(address.addressId)" class="item clearfix" v-for="(address, index) in addresslist" :key="index" >
          <div
            :class="address.chooseFlag=='1'?'circleBox':'circleBoxFalse'"
            @click="selectionBtn(address.addressId)"
          >
            <i class="iconfont icon-Tick"></i>
          </div>
          <div class="infoUser">
            <span>{{address.name}}</span>
            <span>{{address.mobile}}</span>
            <!-- <div class="default">默认</div> -->
          </div>
          <div style="width:90%" class="address showtxt">{{address.showAddress}} </div>
          <div class="imgIcon" @click="goEditAddress(address.addressId)"  style="width:40px;height:40px;">
            <img
              src="@/assets/images/编辑@2x.png"
              alt=""
            >
          </div>
        </li>

      </ul>
    </div>
    <div class="btn">
      <button class="preservation" @click="goOrder()">确认</button>
    </div>
    <div class="btn">
      <button class="delete" @click="goInsertAddress()">新增地址</button>
    </div>
  </div>
</template>
<script src="./deliveryDddress.js"></script>
<style src="./deliveryDddress.less" scoped="scoped" lang="less"></style>

<style>
.showtxt
{

 overflow:hidden;
 text-overflow:ellipsis;
 white-space:nowrap
}
</style>