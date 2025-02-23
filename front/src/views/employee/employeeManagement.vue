<template>
  <el-card class="cardClass">
    <div slot="header" class="cardClass_header">
      <h3>员工管理</h3>
      <div>
        <el-button size="mini" type="success" icon="el-icon-plus" @click="open('员工登记')">新增</el-button>
        <el-button size="mini" type="primary" icon="el-icon-search" @click="searchHidden()">搜索</el-button>
      </div>
    </div>
    <!-- 查询表单 -->
    <el-collapse-transition>
      <el-form :model="ruleForm" :inline="true" :rules="rules" ref="ruleForm" label-width="80px" v-if="hidden">
        <el-form-item label="字段：" prop="key">
          <el-select v-model="ruleForm.key" placeholder="请选择查询字段" size="small">
            <el-option v-for="item in keyOptions" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="值：" prop="value">
          <el-input v-model="ruleForm.value" placeholder="请输入值" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')" size="small" icon="el-icon-search">查询</el-button>
          <el-button @click="resetForm('ruleForm')" size="small" icon="el-icon-refresh">重置</el-button>
        </el-form-item>
      </el-form>
    </el-collapse-transition>
    <!-- 表格 -->
    <el-table :data="tableData" :row-key="getRowKeys" stripe :expand-row-keys="expands" @expand-change="expandChange"
      v-loading="loading" size="small" border style="width: 100%;">
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-card shadow="never" style="margin:5px 15px">
            <el-tabs v-model="activeName" @tab-click="handleClick">
              <el-tab-pane label="员工详情" name="first">
                <detailsEmployee ref="detailsEmployeeRef" ></detailsEmployee>
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </template>
      </el-table-column>
      <el-table-column prop="id" type="index" label="序号" width="60px" align="center"></el-table-column>
      <el-table-column prop="userTel" label="电话号码（员工账号）" align="center"></el-table-column>
      <el-table-column prop="userName" label="姓名" align="center" width="150px"></el-table-column>
      <el-table-column prop="userGender" label="性别" align="center" width="150px"></el-table-column>
      <el-table-column prop="userEmail" label="电子邮箱" align="center"></el-table-column>
      <el-table-column prop="userState" label="状态" width="140" align="center">
        <template slot-scope="scope">
          <el-tag size="small" :type="scope.row.userState === '0' ? 'success' : 'danger'" disable-transitions>{{
            scope.row.userState == 0 ? "在职" : "离职"
          }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="warning" size="mini" icon="el-icon-edit" @click="open('修改员工信息', scope.row)">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="total, prev, pager, next, jumper" :hide-on-single-page="true" :total="total"
      :page-size="ruleForm.pageSize" v-show="!loading" :current-page="ruleForm.pageNum" @current-change="changePage"
      class="pageClass"></el-pagination>
    <addEmployee ref="addEmployeeRef"></addEmployee>
  </el-card>
</template>

<script>
import addEmployee from '@/components/employee/addEmployee';
import detailsEmployee from '@/components/employee/detailsEmployee';
import { findEmployee, deleteEmployee } from '@/apis/employee/employeeManagement.js'

export default {
  name: "employeeManagement",
  components: {
    addEmployee,
    detailsEmployee,
  },
  data() {
    return {
      loading: true,
      hidden: false,
      total: null,
      activeName: "first",
      ruleForm: {
        key: 'user_tel',
        value: '',
        flag: 0, // tab标签 1（未分配页面）2（已分配页面）0（员工管理页面）
        userIdentity: 1,
        pageNum: 1,
        pageSize: 10,
      },
      keyOptions: [
        { value: 'user_tel', label: '电话号码（员工账号）' },
        { value: 'user_name', label: '员工姓名' },
        { value: 'user_email', label: '电子邮箱' },
      ],
      rules: {
        key: [{ required: true, message: '请选择查询字段', trigger: 'blur' }]
      },
      expands: [],
      getRowKeys: (row) => {
        // console.log(row)
        return row.userId
      },
      tableData: [],
    };
  },
  created() {
    this.search();
  },
  methods: {
    // 新增/修改弹窗
    open(title, row) {
      this.$refs["addEmployeeRef"].show(title, row)
    },
    // 搜索隐藏
    searchHidden() {
      this.hidden = !this.hidden;
    },
    // 搜索方法
    search() {
      const _this = this;
      _this.loading = true
      findEmployee(_this.ruleForm).then(function (res) {
        // console.log(res.data);
        _this.tableData = res.data.data;
        _this.total = res.data.total;
        _this.loading = false;
      })
    },
    // 搜索
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.search()
          this.ruleForm.pageNum = 1 // 查询重置页数
        }
      });
    },
    // 分页
    changePage(pageNum) {
      this.ruleForm.pageNum = pageNum;
      this.search()
    },
    // 重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.search()
      this.ruleForm.pageNum = 1 // 查询重置页数
    },

    // 展开详情
    expandChange(row, rowList) {
      var _this = this
      if (rowList.length) {
        _this.expands = []
        if (row) {
          _this.expands.push(row.userId)
          setTimeout(() => {
            _this.$refs["detailsEmployeeRef"].getData(row)
          }, 1);
        }
        //  this.tablaData(row.eqId)  这里可以调用接口数据渲染
      } else {
        _this.expands = []
      }
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    // 删除
    del(row) {
      const _this = this;
      _this.$confirm('确认删除员工“' + row.userName + '”吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteEmployee(row.userId).then(function (res) {
          // console.log(res);
          if (res.code === 0) {
            _this.$message({
              type: 'success',
              message: '员工“' + row.userName + "”" + res.msg,
            })
            _this.search();
            _this.ruleForm.pageNum = 1 // 查询重置页数
          } else {
            _this.$message({
              type: 'success',
              message: '员工“' + row.userName + "”" + res.msg,
            })
          }
        })
      }).catch(() => { })
    },
  }
}
</script>

<style scoped>

</style>