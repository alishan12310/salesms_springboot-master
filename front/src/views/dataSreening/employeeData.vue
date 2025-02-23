<template>
  <div class="allPage">
    <el-card class="box-card-left" shadow="never">
      <div slot="header" class="cardClass_header">
        <h4>员工数据</h4>
        <div>当前员工：{{ firstUser }}</div>
      </div>
      <el-row :gutter="15">
        <el-col :span="12">
          <el-card shadow="hover">
            <div slot="header" class="cardClass_header">
              <h4>员工年销售数据</h4>
            </div>
            <div>
              <dv-scroll-ranking-board :config="configDataV" style="width:100%; height: 179px" v-if="flag" />
            </div>
          </el-card>
        </el-col>

        <el-col :span="12">
          <el-card class="top_card" shadow="hover">
            <div slot="header" class="cardClass_header">
              <getFormatDay ref="day"></getFormatDay>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-row :gutter="15" style="margin-top: 15px">
        <el-col :span="12">
          <el-card class="bottom_card" shadow="hover">
            <getSalesTrends ref="Trends"></getSalesTrends>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card class="bottom_card" shadow="hover">
            <getRevenueEmployees ref="Employees"></getRevenueEmployees>
          </el-card>
        </el-col>
      </el-row>
    </el-card>
    <el-menu default-active="1" class="el-menu-vertical-demo" :collapse="true" @select="handleSelect">
      <el-submenu :index="String(item.storemanageId)" v-for="(item, index) in userlist" :key="index">
        <template slot="title">
          <i class="el-icon-s-shop" style="color: #409eff"></i>
        </template>
        <el-menu-item-group>
          <template slot="title">{{ item.storemanageName }}</template>
          <el-menu-item v-for="(user, indexs) in item.children" :index="String(user.userId)" :key="indexs"><i
              class="el-icon-user"></i>{{ user.userName }}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
import {
  getObtainEmployees,
  getEmployeeData,
  getUser
} from "@/apis/dataSreening/employeeData";
import getRevenueEmployees from "@/components/dataSreening/employeeData/getRevenueEmployees";
import getSalesTrends from "@/components/dataSreening/employeeData/getSalesTrends";
import getFormatDay from "@/components/dataSreening/employeeData/getFormatDay";
export default {
  name: "employeeData",
  components: {
    getRevenueEmployees,
    getSalesTrends,
    getFormatDay
  },
  data() {
    return {
      user: "",
      flag: false,
      activeNames: ["1"],
      userlist: [],
      firstUser: "",
      defaultactive: null,
      configDataV: {
        data: [],
        waitTime: 2000,
        unit: "￥",
      },
    };
  },
  created() {
    this.getuser();
    this.getList();
  },
  methods: {
    getList() {
      getEmployeeData().then(({ data }) => {
        this.configDataV.data = data;
        this.flag = true;
      });
    },
    getuser() {
      getObtainEmployees().then(({ data }) => {
        this.userlist = data;
        let flag = true;
        this.firstUser = this.userlist[0].children[0].userName;
        this.user = this.userlist[0].children[0].userId;
        this.$refs.Trends.drawline(this.user);
        this.$refs.Employees.drawbar(this.user);
        this.$refs.day.drawbar(this.user);
      });
    },
    handleSelect(key, keyPath) {
      getUser(keyPath[1]).then(res => {
        this.firstUser = res.data.userName
      })
      this.$refs.Trends.drawline(keyPath[1]);
      this.$refs.Employees.drawbar(keyPath[1]);
      this.$refs.day.drawbar(keyPath[1]);
    },
  },
};
</script>

<style scoped>
/deep/ .dv-scroll-ranking-board .ranking-column .inside-column {
  background-color: rgb(64, 158, 255);
}

/deep/ .dv-scroll-ranking-board .ranking-info .rank {
  width: 40px;
  color: rgb(64, 158, 255);
}

/deep/ .dv-scroll-ranking-board {
  width: 100%;
  height: 100%;
  color: #888;
  overflow: hidden;
}

.top_card {
  height: 255px;
}

.bottom_card {
  height: calc(100vh - 470px);
}

.allPage {
  display: flex;
  justify-content: space-between;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.allPage .box-card-left {
  width: 100%;
  height: calc(100vh - 125px);
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 240px;
  min-height: 400px;
}

.is-never-shadow {
  border: solid 1px #e6e6e6;
}
</style>