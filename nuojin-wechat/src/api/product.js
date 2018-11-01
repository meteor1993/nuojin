import request from '@/utils/request'

export function getProductList() {
  return request({
    url: '/mp/wechatProduct/getProductList',
    method: 'post'
  })
}

export function getProductById(id) {
  return request({
    url: '/mp/wechatProduct/getProductById',
    method: 'post',
    data: {
      id
    }
  })
}

export function getProductConfigByProductId(productId) {
  return request({
    url: '/mp/wechatProduct/getProductConfigByProductId',
    method: 'post',
    data: {
      productId
    }
  })
}
