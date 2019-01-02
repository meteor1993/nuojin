<template>
  <div class="layOut">
    <div class="header">
      <div class="arrow ">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
      <div class="headerTitle">代理人申请</div>
    </div>
    <div class="content">
      <ul>
        <li class="item">
          <span class="fl">姓名</span>
          <input
            type="text"
            placeholder="申请人姓名"
          >
        </li>
        <li class="item">
          <span class="fl">手机号</span>
          <input
            type="text"
            placeholder="申请人号码"
          >
        </li>
        <li class="item">
          <span class="fl">邀请人姓名</span>
          <input
            type="text"
            placeholder="邀请人姓名"
          >
        </li>
        <li class="item">
          <span class="fl">邀请人手机号</span>
          <input placeholder="邀请人手机号">
        </li>
      </ul>
    </div>
    <div class="btn">
      <button class="preservation">保存</button>
    </div>
  </div>
</template>
<script src="./agentApplication.js"></script>
<style lang="less" scoped="scoped" src="./agentApplication.less"></style>
