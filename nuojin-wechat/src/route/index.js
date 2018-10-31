import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/',
    name: 'home',
    component: () => import('@/views/home/index')
  },
  {
    path: '/shop',
    name: '/shop',
    component: () => import('@/views/shop/index')
  },
  {
    path: '/introduction',
    name: 'introduction',
    component: () => import('@/views/introduction/index')
  },
  {
    path: '/card',
    name: 'card',
    component: () => import('@/views/card/index')
  },
  {
    path: '/cardUseLog',
    name: 'cardUseLog',
    component: () => import('@/views/card/cardUseLog')
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/home/index')
  },
  {
    path: '/bindPhone',
    name: 'bindPhone',
    component: () => import('@/views/bindPhone/index')
  },
  {
    path: '/bindCarNum',
    name: 'bindCarNum',
    component: () => import('@/views/bindCarNum/index')
  },
  {
    path: '/appointment',
    name: 'appointment',
    component: () => import('@/views/appointment/index')
  },
  {
    path: '/basketball',
    name: 'basketball',
    component: () => import('@/views/appointment/basketball')
  },
  {
    path: '/onlineCourse',
    name: 'onlineCourse',
    component: () => import('@/views/appointment/onlineCourse')
  },
  {
    path: '/appointment',
    name: 'appointment',
    component: () => import('@/views/appointment/index')
  },
  {
    path: '/qrcode',
    name: 'qrcode',
    component: () => import('@/views/qrcode/index')
  },
  {
    path: '/orderList',
    name: 'orderList',
    component: () => import('@/views/order/orderList')
  },
  {
    path: '/submitOrder',
    name: 'submitOrder',
    component: () => import('@/views/order/submitOrder')
  }

]

export default new Router({
  routes: constantRouterMap
})
