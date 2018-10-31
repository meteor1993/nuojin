import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/',
    name: 'home',
    component: () => import('@/views/home/index')
  }
]

export default new Router({
  routes: constantRouterMap
})
