<template>
  <div>
    <!-- 这个是最头部的导航 -->
    <div class="header">
      <div class="arrow ">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
      <div class="headerTitle">提交退货</div>
    </div>
    <div class="content">
      <ul>
        <li class="item clearfix">
          <p class="fl">订单号</p>
          <p class="fl">2018829388392</p>
        </li>
        <li class="item clearfix">
          <p class="fl">物流公司</p>
          <p class="fl">退货承运公司</p>
        </li>
        <li class="item clearfix">
          <p class="fl">物流单号</p>
          <p class="fl">快递单号</p>
        </li>
        <li class="item clearfix">
          <p class="fl">物流单号</p>
          <textarea
            class="textAr"
            placeholder="请输入退货原因"
          ></textarea>
        </li>
      </ul>
      <p class="drCode">退货之前请于我们的客服进行联系， 客服确认后的退货订单我们才予以接受，详情扫描二维码</p>
      <!-- 这个是到时候放二维码的 -->
      <div class="drCodeImg">
        <img
          src="@/assets/images/地址@2x.png"
          alt=""
        >
      </div>
      <div class="btn">
        <button>保存</button>
      </div>
    </div>
  </div>
</template>
<script src="./submitReturns.js"></script>
<style lang="less" scoped="scoped" src="./submitReturns.less"></style>
