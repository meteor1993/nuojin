import request from '@/utils/request'

  export function InsertOrder(params) {
    return request({
      url:'/mp/orderController/InsertOrder',
      method:'post',
      data:params
    })
  }

  export function getOrderListByOpenId(params) {
    return request({
      url:'/mp/orderController/getOrderListByOpenId',
      method:'post'
     
    })
  }