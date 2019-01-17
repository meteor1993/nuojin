import request from '@/utils/request'

export function ajaxJsapiSignature(locaHref) {
    return request({
      url: '/mp/wechatUser/ajaxJsapiSignature',
      method: 'post',
      data: {
        locaHref
      }
    })
  }
