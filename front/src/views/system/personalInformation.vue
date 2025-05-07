<template>
  <el-card>
    <div slot="header" class="cardClass_header">
      <h3>个人信息管理</h3>
      <div>
        <el-button
          type="primary"
          size="mini"
          @click="dialogVisible = true"
          style="float: right"
          >修改个人信息</el-button
        >
      </div>
    </div>
    <div>
      <el-descriptions
        class="margin-top"
        :column="3"
        size="small"
        slot="header"
        border
      >
        <el-descriptions-item>
          <template slot="label"> 姓名 </template>
          {{ this.userInformation.userName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 性别 </template>
          {{ this.userInformation.userGender }}
        </el-descriptions-item>
        <!-- <el-descriptions-item>
        <template slot="label"> 用户头像路径 </template>
        {{ userInformation.userImg }}
      </el-descriptions-item> -->
        <el-descriptions-item>
          <template slot="label"> 用户电话（账号） </template>
          {{ this.userInformation.userTel }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 用户密码 </template>
          {{ this.userInformation.userPassword }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 电子邮箱 </template>
          {{ this.userInformation.userEmail }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 备注 </template>
          {{ this.userInformation.userNotes }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 工作店铺 </template>
          {{
            !this.userInformation.userWorkstore
              ? "未分配"
              : this.userInformation.userWorkstore
          }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 职务 </template>
          {{
            !this.userInformation.userAppointment
              ? "未分配"
              : this.userInformation.userAppointment
          }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 状态 </template>
          <el-tag size="small">{{
            this.userInformation.userState == 0 ?"在职":"离职"
          }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 用户身份 </template>
          <el-tag size="small">{{
              this.userInformation.userIdentity == 0 ? "管理员" : "销售员"
          }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 创建时间 </template>
          {{ this.userInformation.userCreatedate }}
        </el-descriptions-item>
      </el-descriptions>
    </div>
    <el-dialog
      title="修改个人信息"
      :visible.sync="dialogVisible"
      width="70%"
      :before-close="handleClose"
    >
      <el-form ref="ruleForm" :model="ruleForm" label-width="100px">
        <el-row :gutter="20">
          <el-col :xl="8" :lg="8">
            <el-form-item label="姓名:" prop="userName">
              <el-input v-model="ruleForm.userName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xl="8" :lg="8">
            <el-form-item label="性别:" prop="userGender">
              <el-input v-model="ruleForm.userGender"></el-input>
            </el-form-item>
          </el-col>
          <!-- <el-col :xl="8" :lg="8">
            <el-form-item label="用户头像路径:" prop="userImg">
              <el-input v-model="ruleForm.userImg"></el-input>
            </el-form-item>
          </el-col> -->
          <el-col :xl="8" :lg="8">
            <el-form-item label="用户电话:" prop="userTel">
              <el-input v-model="ruleForm.userTel"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xl="8" :lg="8">
            <el-form-item label="备注:" prop="userNotes">
              <el-input v-model="ruleForm.userNotes"></el-input>
            </el-form-item>
          </el-col>
          <el-descriptions-item>
            <template slot="label"> 状态 </template>
            <el-tag size="small">{{ displayUserState }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label"> 用户身份 </template>
            <el-tag size="small">{{ displayUserIdentity }}</el-tag>
          </el-descriptions-item>

          <el-col :xl="8" :lg="8">
            <el-form-item label="用户密码:" prop="userPassword">
              <el-input v-model="ruleForm.userPassword"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xl="8" :lg="8">
            <el-form-item label="电子邮箱:" prop="userEmail">
              <el-input v-model="ruleForm.userEmail"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xl="8" :lg="8">
            <el-form-item label="开始日期" prop="userCreatedate">
              <el-date-picker
                v-model="ruleForm.userCreatedate"
                type="date"
                placeholder="选择开始日期"
                value-format="yyyy-MM-dd"
                style="width: 100%"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-form-item class="dialog-footer">
            <el-button type="primary" @click="update">确 定</el-button>
            <el-button @click="dialogVisible = false">取 消</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </el-dialog>
    <div class="tubiao">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="cardClass_header">
          <h4>当月销售数据总览</h4>
        </div>
        <div
          ref="myChart"
          style="width: calc(100vh - 150px); height: calc(100vh - 415px)"
        />
      </el-card>
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="cardClass_header">
          <h4>当年销售数据总览</h4>
        </div>
        <div style="position: relative">
          <div
            ref="myChartTwo"
            style="width: calc(100vh - 150px); height: calc(100vh - 415px)"
          />
        </div>
      </el-card>
    </div>
  </el-card>
</template>

<script>
import * as echarts from "echarts/core";
import {
  TooltipComponent,
  GridComponent,
  ToolboxComponent,
  LegendComponent,
} from "echarts/components";
import { BarChart, PieChart } from "echarts/charts";
import { LabelLayout } from "echarts/features";
import { CanvasRenderer } from "echarts/renderers";

echarts.use([
  TooltipComponent,
  GridComponent,
  BarChart,
  CanvasRenderer,
  PieChart,
  LabelLayout,
  ToolboxComponent,
  LegendComponent,
]);

import {
  searchuser,
  updatePersonalInformation,
  getFormatYear,
  getFormatDay,
} from "@/apis/system/personalInformation.js";
export default {
  data() {
    return {
      displayUserState: '',
      displayUserIdentity: '',

      flag: true, // 比哦之
      dialogVisible: false,
      size: "",
      userInformation: "",
      ruleForm: {
        userAppointment: null,
        userCreatedate: "",
        userEmail: "",
        userGender: "",
        userIdentity: "",
        userImg: "",
        userId: "",
        userName: "",
        userNotes: "",
        userPassword: "",
        userState: "",
        userTel: "",
        userWorkstore: null,
      },
      form: {
        id: "",
        userId: "",
      },
    };

  },

  created() {
    const _this = this;
    _this.getuser();
  },
  mounted() {
    this.$nextTick(() => {
      this.drawBar();
      this.drawpie();
    });
  },
  methods: {
    //:更新信息
    update() {
      const _this = this;
      if (this.ruleForm.userState == "离职") {
        this.ruleForm.userState = 0;
      } else {
        this.ruleForm.userState = 1;
      }
      if (this.ruleForm.userIdentity == "管理员") {
        this.ruleForm.userIdentity = 0;
      } else {
        this.ruleForm.userIdentity = 1;
      }
      updatePersonalInformation(_this.ruleForm)
        .then((res) => {
          if (res.code === 0) {
            _this.$alert("信息" + res.msg, "提示", {
              confirmButtonText: "确定",
              callback: (action) => {},
            });
          }
        })
        .catch(() => {});
      this.dialogVisible = false;
    },
    //:获取信息
    getuser() {
      const user = localStorage.getItem("userData");
      searchuser(user).then((res) => {
        this.userInformation = res.data;
        console.log(this.userInformation.userState+'---------0')

        this.userInformation = res.data;
        this.ruleForm = res.data;

        this.displayUserState = res.data.userState == 0 ? "在职" : "离职";
        this.displayUserIdentity = res.data.userIdentity == 0 ? "管理员" : "销售员";

      });
    },
    //:关闭对话框
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    async drawBar(id) {
      const user = localStorage.getItem("userData");
      var date = new Date();
      var month = date.getMonth();
      if (this.flag) {
        this.form.id = month;
        this.flag = false;
      } else {
        this.form.id = id;
      }
      // if (id && id!==0) {
      //   this.form.id = id;
      // } else {
      //   this.form.id = month;
      // }

      this.form.userId = user;
      await getFormatDay(this.form)
        .then((res) => {
          const myChart = echarts.init(this.$refs.myChart);
          // 绘制图表
          var option;
          option = {
            tooltip: {
              trigger: "axis",
              axisPointer: {
                type: "shadow",
              },
            },
            grid: {
              left: "3%",
              right: "4%",
              bottom: "3%",
              containLabel: true,
            },
            xAxis: [
              {
                type: "category",
                data: res.data.day,
                axisTick: {
                  alignWithLabel: true,
                },
              },
            ],
            yAxis: [
              {
                type: "value",
              },
            ],
            series: [
              {
                name: "月销售量",
                type: "bar",
                barWidth: "60%",
                data: res.data.value,
              },
            ],
          };

          myChart.setOption(option);
          window.addEventListener("resize", function () {
            myChart.resize();
          });
        })
        .catch(() => {});
      // 基于准备好的dom，初始化echarts实例
      // 全局使用 echarts 初始化（this.$echarts）
    },
    async drawpie() {
      const myChartTwo = echarts.init(this.$refs.myChartTwo);
      var option;
      var datas = [];

      const user = localStorage.getItem("userData");
      await getFormatYear(user)
        .then((res) => {
          datas = res.data;
          option = {
            color: [
              "#1E90FF",
              "#4169E1",
              "#7B68EE",
              "#ADD8E6",
              "#BA55D3",
              "#DC143C",
              "#F0E68C",
              "#FF00FF",
              "#FFA500",
              "#FFFF00",
              "#DEB887",
              "#9ACD32",
            ],
            legend: {
              type: "scroll",
              bottom: 10,
            },
            toolbox: {
              show: true,
              feature: {
                mark: { show: true },
              },
            },
            tooltip: {
              trigger: "item",
              formatter: "{b} : {c} ({d}%)",
            },
            series: [
              {
                name: "Nightingale Chart",
                type: "pie",
                radius: [50, 140],
                center: ["50%", "45%"],
                // roseType: "area",
                label: {
                  position: "inner",
                  show: false,
                },
                itemStyle: {
                  borderRadius: 8,
                },
                data: datas,
              },
            ],
          };
        })
        .catch(() => {});
      myChartTwo.setOption(option);
      window.addEventListener("resize", function () {
        myChartTwo.resize();
      });
      myChartTwo.on("mouseover", (params) => {
        const _this = this;
        _this.drawBar(params.dataIndex);
      });
    },
  },
};
</script>

<style scoped>
.box-card {
  width: 49%;
}

.clearfix {
  padding: 20px 0;
}

.dialog-footer {
  float: right;
  margin: 20px 20px;
}

.tubiao {
  display: flex;
  justify-content: space-between;
  margin-top: 15px;
}

/deep/ .tubiao .el-card__body {
  padding: 0px;
}
</style>
