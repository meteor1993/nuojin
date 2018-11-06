<template>
  <div>
    <van-nav-bar
      title="我的订单"
      left-text="返回"
      right-text="我的"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <div v-if="flag">
      <div style="height: 15px;" />
      <div v-for="order in orderList" :key="order.id">
        <van-panel :title="order.commodityName" status="已完成">
          <div class="panel-div">
            购买时间：{{ order.paySuccessTime | dataFormat }}
          </div>
          <div class="panel-money">
            实付款：<span class="panel-money-span">￥{{ order.orderMoney / 100 | numberFormat }}</span>
          </div>
        </van-panel>
        <div style="height: 15px;" />
      </div>
    </div>
    <div v-else class="no-data-div">
      暂无数据
    </div>
  </div>
</template>
<script>
import moment from 'moment'
import { getOrderList } from '@/api/order'

export default {
  filters: {
    dataFormat: function(value) {
      return moment(value).format('YYYY-MM-DD HH:mm:ss')
    },
    numberFormat: function(money) {
      if (money && money != null) {
        money = String(money)
        const left = money.split('.')[0]
        let right = money.split('.')[1]
        right = right ? (right.length >= 2 ? '.' + right.substr(0, 2) : '.' + right + '0') : '.00'
        const temp = left.split('').reverse().join('').match(/(\d{1,3})/g)
        return (Number(money) < 0 ? '-' : '') + temp.join(',').split('').reverse().join('') + right
      } else if (money === 0) { // 注意===在这里的使用，如果传入的money为0,if中会将其判定为boolean类型，故而要另外做===判断
        return '0.00'
      } else {
        return ''
      }
    }
  },
  data() {
    return {
      flag: true,
      orderList: []
    }
  },
  created() {
    this.getOrderList()
  },
  methods: {
    getOrderList() {
      getOrderList().then(response => {
        if (response.resultCode === '1') {
          const list = response.resultData.list
          console.log(list.length)
           if (list.length !== "0") {
             this.flag = true
             this.orderList = list
          }
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
.van-panel__content .panel-div {
  padding: 5px 15px;
  font-size: 12px;
  line-height: 1.2;
  color: #666;
}
.panel-money {
  padding: 5px 15px;
  font-size: 12px;
  line-height: 1.2;
  text-align: right
}
.panel-money-span {
  font-size: 14px;
}
.no-data-div {
  padding: 20px;
  text-align: center;
  font-size: 14px;
  color: #999;
}
</style>
