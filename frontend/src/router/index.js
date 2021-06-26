import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

//import Profile from '../components/Profile.vue'

export default new VueRouter({
    linkActiveClass: "active",
    linkExactActiveClass: "active",
    routes: [
        {
            path: '/',
            //component: Dashboard
        },
        {
            path: '/profile',
            //component: Profile
        },
    ]
})
