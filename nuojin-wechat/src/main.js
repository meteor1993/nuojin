import Vue from 'vue'
import App from './App.vue'
import { Tabbar, TabbarItem, Cell, CellGroup, NavBar, Field, Button, Panel } from 'vant'
import router from './router'

Vue.config.productionTip = false

Vue.use(Tabbar).use(TabbarItem).use(Cell).use(CellGroup).use(NavBar).use(Field).use(Button).use(Panel)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
