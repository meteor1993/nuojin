<template>
  <div>
    <!-- 这个是最头部的导航 -->
    <div class="header">
      <div class="arrow ">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
      <div class="headerTitle">我的订单</div>
    </div>
    <div class="content">
      <ul>
        <li class="item clearfix">
          <div class="orderNumber clearfix">
            <p class="fl">订单号: 201801107002123</p>
            <p class="fr">已完成</p>
          </div>
          <div class="contentBox">
            <div class="listImg">
              <img
                src="@/assets/images/星空版图片@2x.png"
                alt=""
              >
            </div>
            <div class="firstLine clearfix">
              <span class="fl">普通版</span>
              <span class="fr">x3</span>
            </div>
            <div class="secondLine">
              原单价: <span>&yen;564</span>
            </div>
            <div class="thLine">
              优惠价: <span><i>&yen;</i>240</span>
            </div>
            <div class="fourthLine">省36元</div>
          </div>
          <div class="orderTime clearfix">
            <p class="fl">下单时间: 2018-12-01 23:20:00</p>
            <div class="totalPrice clearfix fr">
              <span class="fl">总价:&nbsp;&nbsp;</span>
              <span class="fl"><i>&yen;</i>240</span>
            </div>
          </div>
          <div class="btnTwo">
            <div class="readBtn fl">
              <button>查看物流</button>
            </div>
            <div class="returnBtn fl">
              <button>退货</button>
            </div>
          </div>
        </li>
        <li class="item clearfix">
          <div class="orderNumber clearfix">
            <p class="fl">订单号: 201801107002123</p>
            <p class="fr orderNumberSpe">退货中</p>
          </div>
          <div class="contentBox">
            <div class="listImg">
              <img
                src="@/assets/images/星空版图片@2x.png"
                alt=""
              >
            </div>
            <div class="firstLine clearfix">
              <span class="fl">普通版</span>
              <span class="fr">x3</span>
            </div>
            <div class="secondLine">
              原单价: <span>&yen;564</span>
            </div>
            <div class="thLine">
              优惠价: <span><i>&yen;</i>240</span>
            </div>
            <div class="fourthLine">省36元</div>
          </div>
          <div class="orderTime clearfix">
            <p class="fl">下单时间: 2018-12-01 23:20:00</p>
            <div class="totalPrice clearfix fr">
              <span class="fl">总价:&nbsp;&nbsp;</span>
              <span class="fl"><i>&yen;</i>240</span>
            </div>
          </div>
          <div class="btnTwo">
            <div class="readBtn fl">
              <button>查看物流</button>
            </div>
            <!-- <div class="returnBtn fl">
              <button>退货</button>
            </div> -->
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>
<script src="./myOrder.js"></script>
<style src="./myOrder.less" lang="less" scoped="scoped"></style>


