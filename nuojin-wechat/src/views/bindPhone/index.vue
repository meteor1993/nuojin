<template>
  <div>
    <van-nav-bar
      title="手机绑定"
      left-text="返回"
      right-text="我的"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <div style="height: 10px;" />
    <van-cell-group>
      <van-field v-model="phone" placeholder="请输入您的手机号" maxlength="11" />
    </van-cell-group>
    <van-cell-group>
      <van-field
        v-model="code"
        center
        clearable
        label="短信验证码"
        placeholder="请输入短信验证码"
      >
        <van-button slot="button" :loading="smsLoad" size="small" type="primary" style="background-color: #38f; border: 1px solid #38f;" @click="sendSMS">发送验证码</van-button>
      </van-field>
    </van-cell-group>
    <div class="btn-div">
      <van-button slot="button" :loading="isload" size="large" type="primary" style="background-color: #38f; border: 1px solid #38f; height: 40px; line-height: 38px;" @click="bindPhone">确认提交</van-button>
    </div>
  </div>
</template>
<script>
import { Dialog } from 'vant'
import { sendSMS, bindPhone } from '@/api/wechatUser'
import { Toast } from 'vant'

export default {
  data() {
    return {
      phone: '',
      code: '',
      smsLoad: false,
      isload: false
    }
  },
  methods: {
    sendSMS() {
      if (this.phone.length !== 11) {
        Toast('请输入正确的手机号')
        return
      }
      sendSMS(this.phone).then(response => {
        Toast(response.resultMsg)
      })
    },
    bindPhone() {
      if (this.phone.length !== 11) {
        Toast('请输入正确的手机号')
        return
      }
      if (this.code.length !== 6) {
        Toast('请输入正确的验证码')
        return
      }
      bindPhone(this.code, this.phone).then(response => {
        console.log(response)
        if (response.resultCode === '1') {
          Toast(response.resultMsg)
          this.$router.push({
            path: '/home'
          })
        } else {
          Toast(response.resultMsg)
        }
      })
    },
    onClickLeft() {
      this.$router.go(-1)
    },
    onClickRight() {
      this.$router.push({
        path: '/home'
      })
    }
  }
}
</script>
<style>
.btn-div {
  position: absolute;
  bottom: 30px;
  width: 90%;
  text-align: center;
  left: 5%;
}
</style>

