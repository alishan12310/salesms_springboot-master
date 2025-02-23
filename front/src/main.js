import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './assets/style/global.css'
import Util from './common/util.js'
// 可视化DataV
import { scrollRankingBoard,digitalFlop } from '@jiaminghi/data-view'
Vue.use(scrollRankingBoard)
Vue.use(digitalFlop)

import moment from 'moment'; //导入模块
moment.locale('zh-cn'); //设置语言 或 moment.lang('zh-cn'); 
Vue.prototype.$moment = moment;//赋值使用

Vue.prototype.util = Util;
Vue.config.productionTip = false
// 引入echarts
// import * as echarts from 'echarts'
// // 设置全局 echarts
// Vue.prototype.$echarts = echarts 
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

// 初始标签栏文字
document.title = "连锁门店管理系统"

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.name) {
    const title = "连锁门店管理系统"
    document.title = to.name + " - " + title
  }
  next()
})

