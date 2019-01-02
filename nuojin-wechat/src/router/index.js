import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/',
    name: 'home',
    component: () => import('@/views/shoppingMall/shoppingMall.vue')
  },
  {
    path: '/userInfo',
    name: 'userInfo',
    component: () => import('@/views/userInfo/userInfo.vue')
  },
  {
    path: '/submitReturns',
    name: 'submitReturns',
    component: () => import('@/views/submitReturns/submitReturns.vue')
  },
  {
    path: '/shoppingMall',
    name: 'shoppingMall',
    component: () => import('@/views/shoppingMall/shoppingMall.vue')
  },
  {
    path: '/pipelineRecord',
    name: 'pipelineRecord',
    component: () => import('@/views/pipelineRecord/pipelineRecord.vue')
  },
  {
    path: '/order',
    name: 'order',
    component: () => import('@/views/order/order.vue')
  },
  {
    path: '/newAddress',
    name: 'newAddress',
    component: () => import('@/views/newAddress/newAddress.vue')
  },
  {
    path: '/myOrder',
    name: 'myOrder',
    component: () => import('@/views/myOrder/myOrder.vue')
  },
  {
    path: '/editAddress',
    name: 'editAddress',
    component: () => import('@/views/editAddress/editAddress.vue')
  },
  {
    path: '/detailsPage',
    name: 'detailsPage',
    component: () => import('@/views/detailsPage/detailsPage.vue')
  },
  {
    path: '/deliveryDddress',
    name: 'deliveryDddress',
    component: () => import('@/views/deliveryDddress/deliveryDddress.vue')
  },
  {
    path: '/agentApplication',
    name: 'agentApplication',
    component: () => import('@/views/agentApplication/agentApplication.vue')
  },
  {
    path: '/addAddress',
    name: 'addAddress',
    component: () => import('@/views/addAddress/addAddress.vue')
  }
]

export default new Router({
  mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
