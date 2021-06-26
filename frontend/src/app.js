import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
Vue.use(BootstrapVue)

import '@babel/polyfill'
import 'mutationobserver-shim'
import './plugins/bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'



// My app
import router from './router/index'
import store from './store/index'
import App from './App.vue'

Vue.config.productionTip = false
new Vue({
  render: h => h(App), store, router
}).$mount('#app')
