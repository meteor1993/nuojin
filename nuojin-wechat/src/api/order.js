import request from '@/utils/request'

export function submitOrder(id, useNum, payType) {
  return request({
    url: '/mp/wechatOrder/submitOrder',
    method: 'post',
    data: {
      id,
      useNum,
      payType
    }
  })
}

export function getOrderList() {
  return request({
    url: '/mp/wechatOrder/getOrderList',
    method: 'post'
  })
}
