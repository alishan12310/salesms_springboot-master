<template>
  <div>
    <el-button type="primary" size="small" @click="changeDepart()" style="margin:10px 0;">更改在职状态</el-button>
    <el-descriptions size="small" direction="vertical" :column="4" border v-loading="loading" style="margin:0px 0 10px">
      <el-descriptions-item label="电话号码">{{ detailsData.userTel }}</el-descriptions-item>
      <el-descriptions-item label="员工密码">{{ detailsData.userPassword }}</el-descriptions-item>
      <el-descriptions-item label="员工姓名">{{ detailsData.userName }}</el-descriptions-item>
      <el-descriptions-item label="员工性别">{{ detailsData.userGender }}</el-descriptions-item>
      <el-descriptions-item label="电子邮箱">{{ detailsData.userEmail }}</el-descriptions-item>
      <el-descriptions-item label="在职单位">{{ detailsData.userWorkstore == null ? "未分配" : detailsData.userWorkstore
      }}</el-descriptions-item>
      <el-descriptions-item label="职务">{{ detailsData.userAppointment == null ? "未委派" : detailsData.userAppointment
      }}</el-descriptions-item>
      <el-descriptions-item label="状态"><el-tag size="small" :type="detailsData.userState === 0 ? 'success' : 'warning'"
          disable-transitions>{{ detailsData.userState == 0 ? "在职" : "离职" }}</el-tag></el-descriptions-item>
      <el-descriptions-item label="备注" :span="6">{{ detailsData.userNotes }}</el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>

import { depart } from '@/apis/employee/employeeManagement.js'

export default {
  name: "detailsEmployee",
  data() {
    return {
      loading: true,
      detailsData: "",
      title: "",
    }
  },
  methods: {
    getData(row) {
      this.detailsData = row;
      this.loading = false
    },
    changeDepart() {
      const _this = this;
      _this
        .$confirm("确认更改员工“" + _this.detailsData.userName + "”的在职状态吗？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(() => {
          depart(this.detailsData.userId).then(res => {
            // console.log(res);
            if (res.code === 0) {
              _this.$message({
                type: "success",
                message: "员工“" + _this.detailsData.userName + "”" + res.msg,
              });
              _this.$router.go(0)
            } else {
              _this.$message({
                type: "error",
                message: "员工“" + _this.detailsData.userName + "”" + res.msg,
              });
            }
          });
        }).catch(() => { });
    }
  },
}
</script>

<style></style>
