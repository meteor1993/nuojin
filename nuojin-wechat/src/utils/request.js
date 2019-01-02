import axios from 'axios'
import { getToken } from '@/utils/auth'

const service = axios.create({
  // baseURL: 'http://fuhui.kaixindaka.com/', // api 的 base_url
  baseURL: 'http://yy0325.oicp.net',
  timeout: 5000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(
  config => {
    if (getToken()) {
      config.headers['token'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    /**
     * code为非20000是抛错 可结合自己业务进行修改
     */
    const res = response.data
    if (res.resultCode === '0') {
      // 如果接口调用错误，msg先console出来，后续优化解决方案
      console.log(res.resultMsg)
      return response.data
    } else {
      return response.data
    }
  },
  error => {
    console.log('err' + error) // for debug
    return Promise.reject(error)
  },
  error => {
    console.log('err' + error) // for debug
    return Promise.reject(error)
  }
)

export default service
