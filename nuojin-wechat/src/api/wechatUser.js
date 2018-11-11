import request from '@/utils/request'

export function getOauthUrl(key) {
  return request({
    url: '/mp/wxUserController/getOauthUrl',
    method: 'post',
    data: {
      key
    }
  })
}

export function getTokens(key) {
  return request({
    url: '/mp/wxUserController/getToken',
    method: 'post',
    data: {
      key
    }
  })
}

export function getUserInfo() {
  return request({
    url: '/mp/wxUserController/getUserInfo',
    method: 'post'
  })
}

export function sendSMS(phone) {
  return request({
    url: '/mp/wxUserController/sendSMS',
    method: 'post',
    data: {
      phone
    }
  })
}

export function bindPhone(code, phone) {
  return request({
    url: '/mp/wxUserController/bindPhone',
    method: 'post',
    data: {
      code,
      phone
    }
  })
}