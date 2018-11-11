import router from './router'
import { getToken, setToken } from '@/utils/auth' // 验权
import { getOauthUrl } from '@/api/wechatUser'
// import { Dialog } from 'vant'

router.beforeEach((to, from, next) => {
  if (getToken()) { // 如果token存在，直接next
    next()
  } else { // 如果token不存，则去服务端取一个，存入cookie
    alert(to.path)
    alert(to.query.token)
    if (to.query.token !== null && to.query.token !== undefined && to.query.token !== '') {
      setToken(to.query.token)
      next()
    } else {
      getOauthUrl('NA2i760YXSgfsiOlQl8z4ps5Zll73FfM').then(response => {
        if (response.resultCode === '1') {
          window.location.href = response.resultData.url
        }
      })
    }
    // getTokens('NA2i760YXSgfsiOlQl8z4ps5Zll73FfM').then(response => {
    //   if (response.resultCode === '1') {
    //     setToken(response.resultData.token)
    //     next()
    //   } else {
    //     Dialog.alert({
    //       message: response.resultMsg
    //     }).then(() => {
    //       next()
    //     })
    //   }
    // })
  }
})
