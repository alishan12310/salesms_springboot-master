<template>
  <div>
    <el-button
      type="primary"
      size="small"
      @click="changeDepart"
      style="margin: 15px 0"
      >更改物流状态</el-button
    >
    <el-descriptions
      size="small"
      direction="vertical"
      :column="5"
      border
      v-loading="loading"
    >
      <el-descriptions-item label="订单编号">{{
        detailsData.transportOrdernumber
      }}</el-descriptions-item>
      <el-descriptions-item label="库房">{{
        detailsData.transportStoragehouse
      }}</el-descriptions-item>
      <el-descriptions-item label="运输方式">{{
        detailsData.transportMode
      }}</el-descriptions-item>
      <el-descriptions-item label="司机名字">{{
        detailsData.transportDriver
      }}</el-descriptions-item>
      <el-descriptions-item label="司机电话">{{
        detailsData.transportDrivertel
      }}</el-descriptions-item>
      <el-descriptions-item label="车牌号">{{
        detailsData.transportCarnumber
      }}</el-descriptions-item>
      <el-descriptions-item label="状态">
        <el-tag
          size="small"
          :type="detailsData.transportState == 1 ? 'success' : 'warning'"
          >{{ detailsData.transportState == 1 ? "完成" : "未完成" }}</el-tag
        >
      </el-descriptions-item>
      <el-descriptions-item label="开始日期">{{
        detailsData.transportStartdate
      }}</el-descriptions-item>
      <el-descriptions-item label="结束日期">{{
        detailsData.transportEnddate
      }}</el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
import {successPurchaseTransport} from "@/apis/purchase/transportationManagement";
export default {
  name: "detailsOrder",
  detailstatus: "",
  data() {
    return {
      loading: true,
      detailsData: "",
      title: "",
      table: [],
    };
  },
  methods: {
    getData(row) {
      this.detailsData = row;
      this.loading = false;
    },
    changeDepart() {
      successPurchaseTransport({
        transportOrdernumber: this.detailsData.transportOrdernumber,
        transportId: this.detailsData.transportId,
      })
        .then(() => {
          window.location.reload();
          _this.$parent.$parent.search();
        })
        .catch(() => {});
    },
  },
};
</script>

<style></style>