<template>
  <el-card class="cardClass">
    <div slot="header" class="cardClass_header">
      <h3>商品售出管理</h3>
      <div>
        <el-button size="mini" type="success" icon="el-icon-plus" @click="open('店铺登记')">新增</el-button>
        <el-button size="mini" type="primary" icon="el-icon-search" @click="searchHidden()">搜索</el-button>
      </div>
    </div>
    <!-- 查询表单 -->
    <el-collapse-transition>
      <el-form :model="ruleForm" :inline="true" :rules="rules" ref="ruleForm" label-width="80px" v-if="hidden">
        <el-form-item label="字段：" prop="key">
          <el-select v-model="ruleForm.key" placeholder="请选择查询字段" size="small" @change="change">
            <el-option v-for="item in keyOptions" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="值：" prop="value">
          <el-select v-model="ruleForm.value" placeholder="请选择" v-show="selectshow!=0" size="small">
            <el-option v-for="item in options" :key="item.userId" :label="item.userName" :value="item.userId">
            </el-option>
          </el-select>
          <el-input v-model="ruleForm.value" placeholder="请输入值" size="small" clearable v-show="selectshow!=1"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')" size="small" icon="el-icon-search">查询</el-button>
          <el-button @click="resetForm('ruleForm')" size="small" icon="el-icon-refresh">重置</el-button>
        </el-form-item>
      </el-form>
    </el-collapse-transition>
    <!-- 表格 -->
    <el-table :data="tableData" stripe :row-key="tableData.recordOrder" @expand-change="expandChange" v-loading="loading"
      size="small" border style="width: 100%">
      <el-table-column type="expand" width="60px">
        <template slot-scope="scope">
          <el-card shadow="never" style="margin: 5px 15px">
            <el-tabs v-model="activeName" @tab-click="handleClick">
              <el-tab-pane label="员工售出详细" name="first">
                <detailsSales ref="detailsSalesRef" :search="search"></detailsSales>
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </template>
      </el-table-column>
      <el-table-column prop="id" type="index" label="序号" width="60px" align="center"></el-table-column>
      <el-table-column prop="recordOrder" label="订单号" align="center"></el-table-column>
      <el-table-column prop="recordSalesperson" label="销售人员" align="center"></el-table-column>
      <el-table-column prop="recordDate" label="订单日期" align="center"></el-table-column>
      <el-table-column label="操作" align="center" width="180">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="total, prev, pager, next, jumper" :hide-on-single-page="true" :total="total"
      :page-size="ruleForm.pageSize" v-show="!loading" :current-page="ruleForm.pageNum" @current-change="changePage"
      class="pageClass"></el-pagination>
    <addSales ref="addSalesRef"></addSales>
  </el-card>
</template>

<script>
import {
  getsalesRecords,
  delallsalesRecords,
  queryAllPeople,
} from "@/apis/sales/salesRecordManagement.js";
import detailsSales from "@/components/sales/detailsSales";
import addSales from "@/components/sales/addSales";
export default {
  name: "storeManagement",
  components: {
    detailsSales,
    addSales,
  },
  provide() {
    return {
      search: this.search,
    };
  },
  data() {
    return {
      loading: true,
      hidden: false,
      total: null,
      activeName: "first",
      ruleForm: {
        key: "record_Order",
        value: "",
        pageNum: 1,
        pageSize: 10,
      },
      keyOptions: [
        { value: "record_Order", label: "订单号" },
        { value: "record_salesperson", label: "销售人员" },
      ],
      rules: {
        key: [{ required: true, message: "请选择查询字段", trigger: "blur" }],
      },
      expands: [],
      tableData: [],
      //所有员工数据
      options: [],
      //选择框显示
      selectshow: 0,
    };
  },
  created() {
    this.search();
  },
  methods: {
    //控制显示选择框
    change() {
      if (this.ruleForm.key === "record_Order") {
        this.selectshow = 0;
      }
      if (this.ruleForm.key === "record_salesperson") {
        this.selectshow = 1;
      }
    },
    //查所有员工
    queryPeople() {
      queryAllPeople().then(({ data }) => {
        this.options = data;
      });
    },
    // 新增/修改弹窗
    open(title, row) {
      this.$refs["addSalesRef"].show(row);
    },
    // 搜索隐藏
    searchHidden() {
      this.hidden = !this.hidden;
      this.queryPeople();
    },
    // 搜索方法
    search() {
      const _this = this;
      _this.loading = true;
      getsalesRecords(_this.ruleForm).then(({ data, total }) => {
        _this.tableData = data;
        _this.total = total;
        _this.loading = false;
      });
    },
    // 搜索
    submitForm(formName) {
      // console.log(this.ruleForm);
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.search();
          this.ruleForm.pageNum = 1; // 查询重置页数
        }
      });
    },
    // 分页
    changePage(pageNum) {
      this.ruleForm.pageNum = pageNum;
      this.search();
    },
    // 重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.search();
      this.ruleForm.pageNum = 1; // 查询重置页数
    },
    // 展开详情
    expandChange(row, rowList) {
      var _this = this;
      if (rowList.length) {
        _this.expands = [];
        if (row) {
          _this.expands.push(row.recordOrder);
          setTimeout(() => {
            _this.$refs["detailsSalesRef"].getData(row);
          }, 1);
        }
        //  this.tablaData(row.eqId)  这里可以调用接口数据渲染
      } else {
        _this.expands = [];
      }
    },
    handleClick(tab, event) {
      // console.log(tab, event);
    },
    // 删除
    del(row) {
      // console.log(row);
      const _this = this;
      _this
        .$confirm("确认删除此条记录“" + row.recordOrder + "”吗？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(() => {
          delallsalesRecords(row.recordOrder).then(function (res) {
            if (res.code === 0) {
              _this.$message({
                type: "success",
                message: "员工出售记录“" + row.recordOrder + "”" + res.msg,
              });
              _this.search();
              _this.ruleForm.pageNum = 1; // 查询重置页数
            } else {
              _this.$message({
                type: "warning",
                message: "员工出售记录" + row.recordOrder + "”" + res.msg,
              });
            }
          });
        })
        .catch(() => { });
    },
  },
};
</script>

<style scoped></style>