import request from '@/utils/request'

export function findBasketBallUsePeople() {
  return request({
    url: '/mp/wechatAppointment/findBasketBallUsePeople',
    method: 'post'
  })
}