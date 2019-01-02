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