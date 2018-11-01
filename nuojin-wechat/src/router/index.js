import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/',
    component: () => import('@/views/home/index')
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/home/index')
  },
  {
    path: '/user',
    name: 'user',
    component: () => import('@/views/user/index')
  },
  {
    path: '/bindPhone',
    name: 'bindPhone',
    component: () => import('@/views/bindPhone/index')
  },
  {
    path: '/order',
    name: 'order',
    component: () => import('@/views/order/index')
  }
]

export default new Router({
  routes: constantRouterMap
})
