import request from '@/utils/request'

export function insertAddress(params) {
    
    return request({
      url: '/mp/customerController/insertAddress',
      method: 'post',
      data:params
    })
  }


  export function getAddressList() {
    return request({
      url: '/mp/customerController/getAddressList',
      method: 'post'
   
    })
  }

  export function getCustomer() {
    return request({
      url: '/mp/customerController/getCustomer',
      method: 'post'
    })
  }

  export function getAddressById(addressId) {
    return request({
      url: '/mp/customerController/getAddressById',
      method: 'post',
      data: {
        addressId
      }
    })
  }