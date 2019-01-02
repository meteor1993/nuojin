<template>
  <div>
    <!-- 这个是最头部的导航 -->
    <div class="header">
      <div class="arrow ">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
      <div class="headerTitle">配送地址</div>
    </div>
    <div class="content">
      <ul>
        <li class="item" @click="goEditAddress(1)">
          <div
            :class="selection?'circleBox':'circleBoxFalse'"
            @click="selectionBtn()"
          >
            <i class="iconfont icon-Tick"></i>
          </div>
          <div class="infoUser">
            <span>章大大</span>
            <span>139****2212</span>
            <div class="default">默认</div>
          </div>
          <p class="address">上海市徐汇区 淞虹路痛惜小区1223号</p>
          <div class="imgIcon">
            <img
              src="@/assets/images/编辑@2x.png"
              alt=""
            >
          </div>
        </li>

      </ul>
    </div>
    <div class="btn">
      <button class="preservation" @click="goEditAddress()">新增地址</button>
    </div>
  </div>
</template>
<script src="./deliveryDddress.js"></script>
<style src="./deliveryDddress.less" scoped="scoped" lang="less"></style>

