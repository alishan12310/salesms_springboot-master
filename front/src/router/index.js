import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: '登录',
    component: () => import('@/views/login.vue')
  },
  {
    path: '/index',
    name: '首页',
    component: () => import('@/views/index.vue'),
    children: []
  },

]



const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

// if (localStorage.getItem('menuList')) {
//   JSON.parse(localStorage.getItem('menuList')).forEach(e => {
//     router.addRoute('首页', {
//       path: e.menuIndex,
//       name: e.menuTitle,
//       component: (resolve) => require([`@/views${e.menuPath}`], resolve)
//     })
//   })
// }

//定义变量判断是否已经动态添加过，如果刷新后load永远为 0
let load = 0
//导航守卫
router.beforeEach((to, from, next) => {
  // 获取菜单
  let nowMenuList = [] 
  nowMenuList = JSON.parse(localStorage.getItem('menuList'))
  //非登录、有菜单数据、 没有进行添加（或者刷新了）
  if (load === 0 && routes.length === 2 && to.name !== '登录') {
    load++
    //再次调用存储菜单数据(前提是在存储的地方有调用添加路由规则)、或者直接调用动态添加路由规则事件
    nowMenuList.forEach(e => {
      router.addRoute('首页', {
        path: e.menuIndex,
        name: e.menuTitle,
        component: (resolve) => require([`@/views${e.menuPath}`], resolve)
      })

    })
    //添加后跳转到应访问的地址
    next({ path: to.fullPath })
  } else {
    next()
  }
})


export default router