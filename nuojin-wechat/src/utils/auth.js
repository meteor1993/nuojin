const sessionKey = 'wechat-key'

export function getToken() {
  return sessionStorage.getItem(sessionKey)
}

export function setToken(token) {
  return sessionStorage.setItem(sessionKey, token)
}

export function removeToken() {
  return sessionStorage.removeItem(sessionKey)
}
