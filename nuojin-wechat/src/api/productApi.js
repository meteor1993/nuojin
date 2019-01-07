import request from '@/utils/request'

export function getByProductFirstType(productFirstType) {
  return request({
    url: '/mp/productController/getByProductFirstType',
    method: 'post',
    data: {
      productFirstType
    }
  })
}

export function getByProductId(productId) {
  return request({
    url: '/mp/productController/getByProductId',
    method: 'post',
    data: {
      productId
    }
  })
}

export function getPartnerPrice(productId) {
  return request({
    url: '/mp/productController/getPartnerPrice',
    method: 'post',
    data: {
      productId
    }
  })
}


