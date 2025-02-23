import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    tabFlag: true,
    // user数据
    nowUser:{}
  },
  mutations: {
    chageTabFlag(state, tabFlag) {
      state.tabFlag = tabFlag;
    },
    chageNowUser(state, data) {
      state.nowUser = data;
    },
  },
  actions: {
  },
  modules: {

  }
})
