<template>
  <el-descriptions
    size="small"
    direction="vertical"
    :column="4"
    border
    v-loading="loading"
    style="margin: 25px 0 10px"
  >
    <el-descriptions-item label="姓名">{{
      detailsData.userName
    }}</el-descriptions-item>
    <el-descriptions-item label="性别">{{
      detailsData.userGender
    }}</el-descriptions-item>
    <el-descriptions-item label="用户头像路径">{{
      detailsData.userImg
    }}</el-descriptions-item>
    <el-descriptions-item label="账户">{{
      detailsData.userTel
    }}</el-descriptions-item>
    <el-descriptions-item label="用户密码">{{
      detailsData.userPassword
    }}</el-descriptions-item>
    <el-descriptions-item label="电子邮箱">{{
      detailsData.userEmail
    }}</el-descriptions-item>
    <el-descriptions-item label="工作店铺">{{
      !detailsData.userWorkstore ? "未分配" : detailsData.userWorkstore
    }}</el-descriptions-item>
    <el-descriptions-item label="职务">{{
      !detailsData.userAppointment ? "未委派" : detailsData.userAppointment
    }}</el-descriptions-item>
    <el-descriptions-item label="状态">
      <el-tag :type="detailsData.userState === 0 ? 'success' : 'warning'">{{
        detailsData.userState === 0 ? "在职" : "离职"
      }}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item label="用户身份">
      <el-tag :type="detailsData.userIdentity === 0 ? 'success' : 'info'">{{
        detailsData.userIdentity === 0 ? "管理员" : "销售员"
      }}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item label="备注">{{
      detailsData.userNotes
    }}</el-descriptions-item>
    <el-descriptions-item label="创建时间">{{
      detailsData.userCreatedate
    }}</el-descriptions-item>
  </el-descriptions>
</template>

<script>
export default {
  name: "detailuser",
  data() {
    return {
      loading: true,
      detailsData: "",
      title: "",
    };
  },
  methods: {
    getData(row) {
      this.detailsData = row;
      this.loading = false;
    },
  },
};
</script>

<style>
</style>
