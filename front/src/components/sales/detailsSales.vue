<template>
  <div>
    <el-descriptions size="small" direction="vertical" :column="4" border v-loading="loading" style="margin: 25px 0 10px">
      <el-descriptions-item label="订单编号">{{
        detailsData.recordOrder
      }}</el-descriptions-item>
      <el-descriptions-item label="销售人员">{{
        detailsData.recordSalesperson
      }}</el-descriptions-item>
    </el-descriptions>
    <el-table :data="detailsData.children" v-loading="loading" size="small" border style="width: 100%">
      <el-table-column prop="id" type="index" label="序号" width="60px" align="center"></el-table-column>
      <el-table-column prop="recordName" label="商品名字" :show-overflow-tooltip="true" align="center"></el-table-column>
      <el-table-column prop="recordNum" label="售出数量" :show-overflow-tooltip="true" align="center"></el-table-column>
      <el-table-column prop="recordPrice" label="总价" :show-overflow-tooltip="true" align="center"></el-table-column>

      <el-table-column label="操作" align="center" width="180px">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { delsalesRecords } from "@/apis/sales/salesRecordManagement.js";
export default {
  name: "detailsSalesRef",
  data() {
    return {
      loading: true,
      detailsData: "",
    };
  },
  inject: ['search'],
  methods: {
    getData(row) {
      this.detailsData = row;
      this.loading = false;
    },
    // 删除
    del(row) {
      const _this = this;
      _this
        .$confirm("确认删除此条商品“" + row.recordName + "”吗？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(() => {
          delsalesRecords(row.recordId).then(function (res) {
            if (res.code === 0) {
              _this.$message({
                type: "success",
                message: "商品" + row.recordName + "”" + res.msg,
              });
              _this.search()
            } else {
              _this.$message({
                type: "warning",
                message: "商品" + row.recordName + "”" + res.msg,
              });
            }
          });
        })
        .catch(() => { });
    },
  },
};
</script>

<style></style>