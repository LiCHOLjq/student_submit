import Vue from 'vue';

//配置路由
import VueRouter from 'vue-router';
Vue.use(VueRouter);


//1.创建组件
import Home from '../components/Home.vue';
import User from '../components/User.vue';
import Login from '../components/Login.vue';
//2.配置路由   注意：名字

const routes = [
    {
        path: '/home',
        component: Home,
        redirect: '/home/user',
        children: [
            { path: 'user', component: User },
        ]
    },
    { path: '/login/:code', component: Login, },   /*默认跳转路由*/
    { path: '*', redirect: '/home/user' }   /*默认跳转路由*/
]


//3.实例化VueRouter  注意：名字

const router = new VueRouter({
    mode: 'history',   /*hash模式改为history*/
    base: '/submitUser',
    routes // （缩写）相当于 routes: routes
})

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

//5 <router-view></router-view> 放在 App.vue

export default router;






