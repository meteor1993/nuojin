<template>
  <div>
    <div class="my_header_v4_wrap">
      <div class="my_header_v4">
        <div class="my_header_v4_main">
          <div class="my_header_v4_avatar">
            <div class="my_header_v4_avatar_img">
              <img id="headPic" :src="wechatHeadImg">
            </div>
          </div>
          <div class="my_header_v4_msg">
            <div class="my_header_v4_name">
              <span class="my_header_v4_name_text">{{ nickName }}</span>
            </div>
            <div v-if="isPhone" class="my_header_v4_pin">手机号：{{ phone }}</div>
            <div v-else class="my_header_v4_pin">请绑定手机</div>
          </div>
        </div>
      </div>
    </div>
    <van-cell-group>
      <van-cell v-if="!isPhone" title="手机绑定" is-link url="#/bindPhone" />
      <van-cell title="我的订单" is-link url="#/order" />
    </van-cell-group>
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
      active: 1,
      wechatHeadImg: 'http://storage.360buyimg.com/i.imageUpload/4d6574656f723139393331353134323936363537373539_mid.jpg',
      nickName: '微信名称',
      phone: '13633406174',
      isPhone: false
    }
  }
}
</script>
<style>
.my_header_v4_wrap {
  margin-bottom: 15px;
  padding: 10px;
  background-color: #fff;
}
.my_header_v4_wrap .my_header_v4 {
    overflow: hidden;
    position: relative;
    border-radius: 6px;
    font-size: 12px;
    color: #fff;
    background: -webkit-linear-gradient(left,#eb3c3c,#ff7459);
    background: linear-gradient(90deg,#eb3c3c,#ff7459);
    box-shadow: 0 2px 5px rgba(255,98,98,.4);
}
.my_header_v4_wrap .my_header_v4_main {
    position: relative;
    display: -webkit-box;
    display: -webkit-flex;
    display: flex;
    -webkit-box-align: center;
    -webkit-align-items: center;
    align-items: center;
    padding: 25px 15px;
}
.my_header_v4_wrap .my_header_v4_avatar {
    position: relative;
    margin-right: 10px;
    width: 60px;
    height: 60px;
    border: 1px solid hsla(0,0%,100%,.4);
    border-radius: 60px;
    box-shadow: 0 2px 10px rgba(0,0,0,.15);
}
.my_header_v4_wrap .my_header_v4_avatar_img {
    overflow: hidden;
    border-radius: 60px;
    padding-top: 100%;
}
.my_header_v4_wrap .my_header_v4_avatar img {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    border-radius: 60px;
}
.my_header_v4_wrap .my_header_v4_msg {
    -webkit-box-flex: 1;
    -webkit-flex: 1;
    flex: 1;
    min-width: 0;
}
.my_header_v4_wrap .my_header_v4_pin {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    color: hsla(0,0%,100%,.7);
    margin-top: 5px;
}
.my_header_v4_wrap .my_header_v4_name {
    position: relative;
    font-size: 14px;
    margin-right: 20px;
    white-space: nowrap;
}
</style>
