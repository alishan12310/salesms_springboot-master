<template>
  <div>
    <el-table :data="tableData" size="small" border stripe style="width: 100%;margin-top: 10px;" v-loading="loading"
      v-if="show">
      <el-table-column prop="id" type="index" label="序号" width="60px" align="center"></el-table-column>
      <el-table-column prop="userName" label="姓名" align="center"></el-table-column>
      <el-table-column prop="userTel" label="电话号码" align="center" min-width="90px"></el-table-column>
      <el-table-column prop="userGender" label="性别" align="center"></el-table-column>
      <el-table-column prop="userEmail" label="电子邮箱" align="center" min-width="100px"></el-table-column>
      <el-table-column prop="userWorkstore" label="在职单位" align="center" v-if="flag == 2"></el-table-column>
      <el-table-column prop="userAppointment" label="职务" align="center" v-if="flag == 2"></el-table-column>
      <el-table-column prop="userNotes" label="备注" :show-overflow-tooltip="true" align="center"
        min-width="100px"></el-table-column>
      <el-table-column label="操作" align="center" width="130px">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-notebook-2" v-if="flag == 1"
            @click="open('员工委派',scope.row)">委派</el-button>
          <el-button type="warning" size="mini" icon="el-icon-edit" v-else
            @click="open('修改委派信息',scope.row)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="total, prev, pager, next, jumper" :hide-on-single-page="true" :total="total"
      :page-size="pageSize" v-show="!loading" :current-page="pageNum" @current-change="changePage"
      class="pageClass"></el-pagination>
    <addEmployee ref="addEmployeeRef"></addEmployee>
  </div>
</template>

<script>
import { findEmployee } from '@/apis/employee/employeeManagement.js'
import addEmployee from '@/components/employee/addEmployee';

export default {
  name: "delegatePage",
  components: {
    addEmployee,
  },
  data() {
    return {
      loading: true,
      show: false,
      flag: 1, // tab标签 1（未分配页面）2（已分配页面）0（员工管理页面）
      total: null,
      pageSize: null,
      pageNum: null,
      tableData: null,
    }
  },
  created() {
    this.flag = this.$store.state.tabFlag
    setTimeout(() => {
      this.show = true
    }, 10);
  },
  deactivated() {
  },
  methods: {
    // 分页
    changePage(pageNum) {
      this.$parent.$parent.$parent.$parent.ruleForm.pageNum = pageNum;
      this.search()
    },
    // 委派/修改弹窗
    open(title,row) {
      this.$refs["addEmployeeRef"].show(title,row)
    },
    // 搜索方法
    search() {
      const _this = this;
      const ruleForm = this.$parent.$parent.$parent.$parent.ruleForm
      findEmployee(ruleForm).then(function (res) {
        // console.log(res.data);
        _this.tableData = res.data.data;
        _this.loading = false;
        _this.total = res.data.total;
        _this.pageNum = res.data.pageNum;
        _this.pageSize = res.data.pageSize;
        _this.loading = false;
        // tableHidden = false;
      })
    },
  }
}
</script>

<style>

</style>