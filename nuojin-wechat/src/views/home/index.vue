<template>
  <div>
    <div>
      <video class="video_" controls>
        <source src="http://cdn.kaixindaka.com/fa1469737dd61a6fd8297d32d7c5a8e5.mp4" type="video/mp4">
      </video>
    </div>
    <div class="div-img">
      <img class="ll_fadeIn" src="//m.360buyimg.com/mobilecms/jfs/t25132/272/777079429/42670/e6ced36f/5b7a6fa1N94e7f9d9.jpg!q70.jpg.dpg" loaded="1" style="opacity: 1;width: 50%;">
    </div>
    <van-tabbar v-model="active">
      <van-tabbar-item icon="shop" url="#/home">我的商城</van-tabbar-item>
      <van-tabbar-item icon="contact" url="#/user">个人中心</van-tabbar-item>
    </van-tabbar>
  </div>
</template>
<script>
export default {
  data() {
    return {
      active: 0
    }
  }
}
</script>
<style>
.video_ {
  width: 100%
}
.div-img {
  width: 100%;
  background-color: #f6f6f6;
  text-align: center;
}
</style>
