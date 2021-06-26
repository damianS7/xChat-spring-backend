import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

//require('./bootstrap');
Vue.use(BootstrapVue)
window.Vue = require('vue');

Vue.config.productionTip = false
new Vue({
  render: h => h(App), store, router
}).$mount('#app')
