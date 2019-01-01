<template>
  <div>
    <!-- 这个是最头部的导航 -->
    <div class="header">
      <div class="arrow ">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
      <div class="headerTitle">流水记录</div>
    </div>
    <!-- 这个是主要内容 -->
    <div class="content">
      <ul>
        <li class="item">
          <img
            src="@/assets/images/提现@2x.png"
            alt=""
          >
          <p class="info">提现</p>
          <p class="transaction">交易时间: 2018-11-30 20:40:45</p>
          <div class="price">-240</div>
        </li>
        <li class="item">
          <img
            src="@/assets/images/提现@2x.png"
            alt=""
          >
          <p class="info">奖励金</p>
          <p class="transaction">交易时间: 2018-11-30 20:40:45</p>
          <div class="price active">+300</div>
        </li>
        <li class="item">
          <img
            src="@/assets/images/提现@2x.png"
            alt=""
          >
          <p class="info">奖励金</p>
          <p class="transaction">交易时间: 2018-11-30 20:40:45</p>
          <div class="price active">+300</div>
        </li>
        <li class="item">
          <img
            src="@/assets/images/提现@2x.png"
            alt=""
          >
          <p class="info">奖励金</p>
          <p class="transaction">交易时间: 2018-11-30 20:40:45</p>
          <div class="price active">+300</div>
        </li>
      </ul>
    </div>
  </div>
</template>
<script src="./pipelineRecord.js"></script>
<style lang="less" scoped="scoped" src="./pipelineRecord.less"></style>
