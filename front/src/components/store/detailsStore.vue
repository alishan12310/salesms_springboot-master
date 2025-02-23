<template>
  <div>
    <el-descriptions size="small" direction="vertical" :column="3" border v-loading="loading" style="margin:25px 0 10px">
      <el-descriptions-item label="店名">{{ detailsData.storemanageName }}</el-descriptions-item>
      <el-descriptions-item label="营业范围">{{ detailsData.storemanageContent }}</el-descriptions-item>
      <el-descriptions-item label="地址">{{ detailsData.storemanageAddress }}</el-descriptions-item>
      <el-descriptions-item label="备注">{{ detailsData.storemanageNotes }}</el-descriptions-item>
    </el-descriptions>
    <el-table :data="tableData" size="small" border style="width: 100%" v-loading="tableLoading">
      <el-table-column prop="id" type="index" label="序号" width="60px" align="center"></el-table-column>
      <el-table-column prop="userTel" label="电话" align="center"></el-table-column>
      <el-table-column prop="userName" label="姓名" align="center"></el-table-column>
      <el-table-column prop="userGender" label="性别" align="center" width="80"></el-table-column>
      <el-table-column prop="userEmail" label="电子邮箱" align="center"></el-table-column>
      <el-table-column prop="userAppointment" label="职务" align="center"></el-table-column>
      <el-table-column prop="userIdentity" label="身份" align="center" width="100">
        <template slot-scope="scope">
          <el-tag type="success" size="small" v-if="scope.row.userIdentity==0">管理员</el-tag>
          <el-tag size="small" v-else>销售员</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="userNotes" label="备注" align="center" width="300"></el-table-column>

    </el-table>
  </div>
</template>

<script>
import { queryAllManByStorehouse } from '@/apis/store/storeManagement.js'

export default {
  name: "detailsStore",
  data() {
    return {
      loading: true,
      tableLoading: true,
      detailsData: "",
      title: "",
      tableData: [], // 员工表格
    }
  },
  created() {
    const _this = this;
    setTimeout(() => {
      queryAllManByStorehouse(this.detailsData.storemanageId).then(res => {
        _this.tableData = res.data
        console.log(res.data)
      })
      _this.tableLoading = false
    }, 200);
  },
  methods: {
    getData(row) {
      this.detailsData = row;
      this.loading = false
    }
  },
}
</script>

<style></style>