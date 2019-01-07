import Vue from "vue"
import App from "./App.vue"
import router from './router'
import "@/assets/index.css";
import "@/assets/font/font/iconfont.css"

Vue.config.productionTip = false
import VueToast    from 'vue-toast-m'
Vue.use(VueToast)
new Vue({
  router,
  render: h => h(App)
}).$mount("#app")
