import router from './router'
import { getToken, setToken } from '@/utils/auth' // 验权
// import { setToken } from '@/utils/auth' // 验权
// import { Dialog } from 'vant'

router.beforeEach((to, from, next) => {
  if (getToken()) { // 如果token存在，直接next
    next()
  } else {
    if (to.query.token !== null && to.query.token !== undefined && to.query.token !== '') {
      setToken(to.query.token)
      next()
    }
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
})
