<template>
  <el-container v-loading.fullscreen.lock="pageloading" element-loading-background="#fff" element-loading-text="拼命加载中">
    <!-- 页头 -->
    <el-header class="outHeader" height="50px" v-show="!pageloading">
      <div style="display:flex; font-size:25px;">
        <i class="el-icon-s-fold" style="margin:2px 15px 0 0;" @click="closeAside()" v-show="asideStyleFlag"></i>
        <i class="el-icon-s-unfold" style="margin:2px 15px 0 0" @click="closeAside()" v-show="!asideStyleFlag"></i>
        <div class="title">连锁门店管理系统</div>
      </div>
      <el-dropdown>
        <span class="el-dropdown-link">
          <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" size="small"
            style="margin-right:10px;"></el-avatar>
          欢迎，{{ nowUser.userName }} <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>
    <el-main style="padding:unset" v-show="!pageloading">
      <el-container style="height: calc(100vh - 50px);">
        <!-- 侧边栏 -->
        <el-collapse-transition>
          <el-aside :style="asideStyle">
            <el-menu router :default-active="this.$route.path" @open="handleOpen" @close="handleClose"
              style="height:100%;overflow-x:hidden">
              <el-menu-item index="/index" @click="addTab(editableTabsValue, { menuTitle: '系统首页', menuIndex: '/index' })">
                <i class="el-icon-setting"></i>
                <span slot="title">系统首页</span>
              </el-menu-item>
              <el-submenu :index="String(vforIndex + 1)" v-for="(item, vforIndex) in submenuList" :key="vforIndex"
                v-show="item.menuShow == 1">
                <template slot="title">
                  <i :class="item.menuIcon"></i>
                  <span>{{ item.menuTitle }}</span>
                </template>
                <el-menu-item :index="item.menuIndex" v-for="(item, vforIndex) in item.children" :key="vforIndex"
                  @click="addTab(editableTabsValue, item)" v-show="item.menuShow == 1">
                  <i :class="item.menuIcon"></i>{{ item.menuTitle }}
                </el-menu-item>
              </el-submenu>
              <div v-show="asideStyleFlag">
                <img src="@/assets/keli.jpeg" alt="2233" style="width: 150px;display: block; margin: 40px auto 0;">
                <p style="margin-top: 10px ;font-size: 13px;text-align: center;">ALISHAN</p>
                <p style="margin-top: 10px ;font-size: 13px;text-align: center;">2024 © ALISHAN</p>
              </div>
            </el-menu>
          </el-aside>
        </el-collapse-transition>
        <!-- 主体 -->
        <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab" @tab-click="clickTab"
          style="flex: 1;overflow: hidden;">
          <el-tab-pane v-for="(item) in editableTabs" :key="item.name" :label="item.title" :closable="item.closable"
            :name="item.name">
            <keep-alive>
              <div style="background-color:rgb(245, 245, 245)">
              <div v-if="item.name == '/index'">
                <indexComponent ref="indexComponentRef"></indexComponent>
              </div>
              <el-container v-else style="height: calc(100vh - 91px);">
                <el-main style="overflow-y: scroll;padding: 15px;"><router-view /></el-main>
              </el-container>

              </div>
            </keep-alive>
          </el-tab-pane>
        </el-tabs>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>

import { queryMenu } from "@/apis/system/menuManage.js";
import { searchuser } from "@/apis/system/personalInformation.js";
import indexComponent from "@/components/indexComponent.vue";


export default {
  name: "index",
  components: {
    indexComponent
  },
  data() {
    return {
      nowUser: {},
      zhutiStyle: "width: 100%",
      asideStyle: "width:200px",
      asideStyleFlag: true,
      submenuList: [],
      // 默认tab
      editableTabsValue: '/index',
      // tab列表
      editableTabs: [
        {
          title: '首页',
          name: '/index',
        }
      ],
      pageloading: true,
    }
  },
  created() {
    let _this = this
    _this.searchUser()
    _this.search()
  },
  mounted() {
  },
  beforeUpdate() {
    let _this = this
    // 刷新时路由不是登录或首页时强制跳首页
    if ((_this.$route.path !== '/') && (_this.$route.path !== '/index')) {
      for (var item of _this.submenuList) {
        let arr = item.children.filter(v => v.menuIndex == _this.$route.path)
        if (arr.length == 1) {
          arr.forEach(e => {
            _this.addTab(_this.editableTabsValue, e);
          });
          break
        }
      }
    }
  },
  methods: {
    handleOpen(key, keyPath) {
    },
    handleClose(key, keyPath) {
    },
    // 查用户
    async searchUser() {
      let _this = this
      await searchuser(localStorage.getItem('userData')).then(function (res) {
        _this.nowUser = res.data;
        _this.$store.commit('chageNowUser', res.data)
      }).catch((err) => {
        console.log(err);
      })
    },
    // 查菜单
    async search() {
      const _this = this
      setTimeout(() => {
        queryMenu(_this.$store.state.nowUser.userIdentity).then((res) => {
          _this.submenuList = res.data
          this.pageloading = false

        })
      }, 500);
    },
    // 关闭侧边栏
    closeAside() {
      let _this = this
      if (_this.asideStyleFlag) {
        _this.asideStyle = `    
          width:0px;
          animation-name: asideStyleKF;
          animation-duration: 0.3s;  
        `
      } else {
        _this.asideStyle = `
          width:200px;     
          animation-name: asideStyleKFB;
          animation-duration: 0.3s;     
        `
      }
      _this.asideStyleFlag = !_this.asideStyleFlag
    },
    // 登出方法
    logout() {
      let _this = this;
      _this.$confirm('确认注销登录？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        localStorage.removeItem('menuList')
        localStorage.removeItem('userData')
        _this.$router.replace({ path: '/' })
      })
    },
    // 打开tab
    addTab(targetName, item) {
      const _this = this;
      if (!_this.editableTabs.some(v => v.name == item.menuIndex)) {
        _this.editableTabs.push({
          title: item.menuTitle,
          name: item.menuIndex,
        });
        _this.editableTabsValue = item.menuIndex;
      } else {
        _this.editableTabsValue = item.menuIndex;
      }

    },
    // 点击tab
    clickTab(tab) {
      const _this = this;
      if (_this.$route.path != tab.name) {
        _this.$router.replace({ path: tab.name })
      }
    },
    // 关闭tab
    removeTab(targetName) {
      const _this = this;
      let tabs = _this.editableTabs;
      let activeName = _this.editableTabsValue;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
      }
      _this.editableTabsValue = activeName;
      _this.$router.replace({ path: activeName });
      _this.editableTabs = tabs.filter(tab => tab.name !== targetName);
    }
  }
}
</script>

<style>
* {
  padding: 0;
  margin: 0;
}

@keyframes asideStyleKF {
  from {
    width: 200px;
  }

  to {
    width: 0px;
  }
}

@keyframes asideStyleKFB {
  from {
    width: 0px;
  }

  to {
    width: 200px;
  }
}

/* 页头开始 */

.outHeader {
  background-image: linear-gradient(to right, #4DAFF8, #70c1ff);
}

.el-header {
  display: flex;
  justify-content: space-between;
  color: #fff;
  align-items: center;
}

.el-header .title {
  font-size: 20px;
  font-weight: bolder;
}

/* 下拉 */
.el-dropdown-link {
  cursor: pointer;
  color: #eee;
  font-size: 15px;
  display: flex;
  align-items: center;
}

.el-icon-arrow-down {
  font-size: 15px;
}

/* 页头结束 */
.el-tabs__header {
  margin: unset;
}

/* 设置首页tab无法关闭 */
.el-tabs__nav .el-tabs__item:nth-child(1) span {
  display: none;
}

.el-tabs--card>.el-tabs__header .el-tabs__nav:nth-child(1) {
  border-left: unset;
}

.el-dropdown {
  display: flex;
  align-items: center;
}
</style>