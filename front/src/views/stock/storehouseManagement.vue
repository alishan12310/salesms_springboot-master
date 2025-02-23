<template>
  <el-card class="cardClass">
    <div slot="header" class="cardClass_header">
      <h3>库房管理</h3>
      <div>
        <el-button size="mini" type="primary" icon="el-icon-plus" @click="addHidden()">新增</el-button>
      </div>
    </div>
    <!-- 新增表单 -->
    <el-collapse-transition>
      <el-form :model="addForm" :inline="true" :rules="rules" ref="addForm" label-width="80px" v-if="hidden">
        <el-form-item label="库房" prop="storehouseName">
          <el-input v-model="addForm.storehouseName" placeholder="请输入库房" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label="库房地址" prop="storehouseAddress">
          <el-input v-model="addForm.storehouseAddress" placeholder="请输入库房地址" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item label="库房备注" prop="storehouseNotes">
          <el-input v-model="addForm.storehouseNotes" placeholder="请输入库房备注" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" icon="el-icon-check" @click="addSubmit('addForm')">新增</el-button>
          <el-button size="small" icon="el-icon-refresh" @click="resetAddForm('addForm')">清空</el-button>
        </el-form-item>
      </el-form>
    </el-collapse-transition>
    <!-- 表格 -->
    <el-table :data="tableData" size="small" border stripe style="width: 100%;" v-loading="loading">
      <el-table-column prop="id" type="index" label="序号" width="50px" align="center"></el-table-column>
      <el-table-column prop="storehouseName" label="库房" min-width="40px" align="center"></el-table-column>
      <el-table-column prop="storehouseAddress" label="库房地址" min-width="70px" align="center"></el-table-column>
      <el-table-column prop="storehouseNotes" label="备注" align="center" min-width="70px" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column min-width="60px" align="center">
        <template slot="header" slot-scope="scope">
          <el-form :model="ruleForm" :inline="true" :rules="rules" ref="ruleForm">
            <div style="display:flex;justify-content: center;">
              <el-input v-model="ruleForm.value" size="mini" style="margin-right:10px;" placeholder="输入库房搜索"
                clearable />
              <el-button type="primary" @click="submitForm('ruleForm')" size="mini">查询</el-button>
              <el-button @click="resetForm()" size="mini">重置</el-button>
            </div>
          </el-form>
        </template>
        <template slot-scope="scope">
          <!-- 修改库房开始 -->
          <el-popover :title="'修改库房：' + scope.row.storehouseName" placement="bottom" width="270"
            :ref="'pop' + scope.$index">
            <el-form :model="updateForm" :rules="rules" ref="updateForm" label-width="80px" class="popoverClass">
              <el-form-item label="库房名称" prop="storehouseName" :show-message="false">
                <el-input v-model="updateForm.storehouseName" placeholder="请输入库房名称" size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item label="库房地址" prop="storehouseAddress" :show-message="false">
                <el-input v-model="updateForm.storehouseAddress" placeholder="请输入库房地址" size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item label="库房备注" prop="storehouseNotes">
                <el-input v-model="updateForm.storehouseNotes" placeholder="请输入库房备注" size="mini" clearable></el-input>
              </el-form-item>
              <el-form-item :show-message="false">
                <div style="text-align: right;">
                  <el-button size="mini" type="text" @click="closeTablePopover('pop' + scope.$index)">取消</el-button>
                  <el-button type="primary" size="mini" @click="delItemData(scope.row)">修改</el-button>
                </div>
              </el-form-item>
            </el-form>
            <el-button size="mini" type="warning" icon="el-icon-edit" slot="reference"
              @click="update(scope.row)">修改</el-button>
          </el-popover>
          <!-- 修改库房结束 -->
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="del(scope.row)"
            style="margin-left:10px">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="total, prev, pager, next, jumper" :hide-on-single-page="true" :total="total"
      :page-size="ruleForm.pageSize" v-show="!loading" :current-page="ruleForm.pageNum" @current-change="changePage"
      class="pageClass"></el-pagination>
  </el-card>
</template>

<script>
import { queryStorehouse, saveStorehouse, updateStorehouse, deleteStorehouse } from '@/apis/stock/storehouseManagement.js'

export default {
  name: "storehouseManagement",
  data() {
    return {
      loading: false,
      hidden: false,
      total: null,
      visible: false,
      ruleForm: {
        value: '',
        pageNum: 1,
        pageSize: 10,
      },
      updateForm: {
        storehouseName: '',
        storehouseAddress: '',
        storehouseNotes: '',
      },
      addForm: {
        storehouseName: '',
        storehouseAddress: '',
        storehouseNotes: '',
      },
      rules: {
        storehouseName: [{ required: true, message: '请输入新增库房名称', trigger: 'blur' }],
      },
      tableData: null,
    }
  },
  created() {
    this.search();
  },
  methods: {
    // 添加隐藏
    addHidden() {
      this.hidden = !this.hidden;
    },
    // 添加方法
    addSubmit(formName) {
      const _this = this;
      let type = 'success';
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          saveStorehouse(_this.addForm).then(function (res) {
            _this.search();
            if (res.code !== 0) {
              type = 'warning'
            }
            _this.$message({
              message: '库房：“' + _this.addForm.storehouseName + '”' + res.msg,
              type: type
            });
            _this.resetAddForm(formName);
          })
        }
      });
    },
    // 清空
    resetAddForm(formName) {
      this.$refs[formName].resetFields();
    },
    // 删除
    del(row) {
      const _this = this;
      _this.$confirm('确认删除库房“' + row.storehouseName + '”吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteStorehouse(row).then(function (res) {
          // console.log(res);
          if (res.code === 0) {
            _this.$message({
              type: 'success',
              message: '库房“' + row.storehouseName + "”" + res.msg,
            })
            _this.search();
            _this.ruleForm.pageNum = 1 // 查询重置页数
          } else {
            _this.$message({
              type: 'success',
              message: '库房“' + row.storehouseName + "”" + res.msg,
            })
          }
        })
      }).catch(() => { })
    },
    // 搜索方法
    search() {
      const _this = this;
      _this.loading = true
      queryStorehouse(_this.ruleForm).then(function (res) {
        // console.log(res.data);
        _this.tableData = res.data.data;
        _this.total = res.data.total;
        _this.loading = false;
      })
    },
    // 搜索按钮
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.search()
          this.ruleForm.pageNum = 1 // 查询重置页数
        }
      });
    },
    // 搜索重置
    resetForm() {
      this.ruleForm.value = "";
      this.search();
      this.ruleForm.pageNum = 1 // 查询重置页数
    },
    // 分页
    changePage(pageNum) {
      this.ruleForm.pageNum = pageNum;
      this.search()
    },
    // 修改取消
    closeTablePopover(scopeRef) {
      this.$refs[scopeRef].doClose();
    },
    // 修改确定
    delItemData() {
      this.$refs['updateForm'].validate((valid) => {
        if (valid) {
          const _this = this;
          updateStorehouse(_this.updateForm).then(function (res) {
            // console.log("res: ", res.code);
            if (res.code === 0) {
              _this.$alert(_this.updateForm.storehouseName + res.msg, '提示', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.search()
                }
              })
            }
          })
        }
      });
    },
    // 打开修改弹窗
    update(row) {
      this.updateForm = JSON.parse(JSON.stringify(row));
    }
  },
}
</script>

<style scoped>
.popoverClass .el-form-item {
  margin-bottom: unset;
}
</style>