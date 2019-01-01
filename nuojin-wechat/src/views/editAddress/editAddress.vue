<template>
  <div>
    <div class="header">
      <div class="arrow ">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
      <div class="headerTitle">编辑地址</div>
    </div>
    <div class="content">
      <ul>
        <li class="item">
          <span class="fl">收货人 :</span>
          <input
            type="text"
            placeholder="请输入收货人"
          >
        </li>
        <li class="item">
          <span class="fl">手机号码 :</span>
          <input
            type="text"
            placeholder="请输入手机号码"
          >
        </li>
        <li class="item">
          <span class="fl">所在地区 :</span>
          <input
            type="text"
            placeholder="请输入所在地区"
          >
          <i class="iconfont addresArrow icon-zuojiantou"></i>
        </li>
        <li class="item">
          <span class="fl">详细地址 :</span>
          <textarea placeholder="请输入详细地址"></textarea>
        </li>
        <li class="item">
          <span class="fl">邮政编码 :</span>
          <input
            type="text"
            placeholder="请输入邮政编码"
          >
        </li>
      </ul>
    </div>
    <div class="btn">
      <button class="preservation">保存</button>
      <button class="delete" v-if="btnShowFlag">删除地址</button>
    </div>
  </div>
</template>
<script src="./editAddress.js"></script>

<style src="./editAddress.less" scoped="scoped" lang="less"></style>

