<template>
  <el-card class="cardClass">
    <div slot="header" class="cardClass_header">
      <h3>订单管理</h3>
      <div>
        <el-button
          size="mini"
          type="success"
          icon="el-icon-plus"
          @click="open('创建订单')"
          >新增</el-button
        >
        <el-button
          size="mini"
          type="primary"
          icon="el-icon-search"
          @click="searchHidden()"
          >搜索</el-button
        >
      </div>
    </div>
    <!-- 查询开始 -->
    <el-collapse-transition>
      <el-form
        :model="ruleForm"
        :inline="true"
        :rules="rules"
        ref="ruleForm"
        label-width="80px"
        v-if="hidden"
      >
        <el-form-item label="字段：" prop="key">
          <el-select
            v-model="ruleForm.key"
            placeholder="请选择查询字段"
            size="small"
          >
            <el-option
              v-for="item in keyOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="值：" prop="value">
          <el-input
            v-model="ruleForm.value"
            placeholder="请输入值"
            size="small"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            @click="submitForm('ruleForm')"
            size="small"
            icon="el-icon-search"
            >查询</el-button
          >
          <el-button
            @click="resetForm('ruleForm')"
            size="small"
            icon="el-icon-refresh"
            >重置</el-button
          >
        </el-form-item>
      </el-form>
    </el-collapse-transition>
    <!-- 查询结束 -->

    <!-- 表格开始 -->
    <el-table
      :data="tableData"
      :row-key="getRowKeys"
      stripe
      :expand-row-keys="expands"
      @expand-change="expandChange"
      v-loading="loading"
      size="small"
      border
      style="width: 100%"
    >
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-card shadow="never" style="margin: 5px 15px">
            <el-tabs v-model="activeName">
              <el-tab-pane label="订单详情" name="first">
                <detailsOrder ref="detailsOrderRef"></detailsOrder>
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </template>
      </el-table-column>
      <el-table-column
        prop="id"
        type="index"
        label="序号"
        width="50px"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="orderNumber"
        label="订单编号"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="orderCorporation"
        label="厂家"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="orderGoods"
        label="货物"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="orderDate"
        label="创建日期"
        align="center"
      ></el-table-column>
      <el-table-column prop="orderState" label="状态" align="center">
        <template slot-scope="scope">
          <el-tag size="small" type="success" v-if="scope.row.orderState === 2"
            >完成</el-tag
          >
          <el-tag
            size="small"
            type="info"
            v-else-if="scope.row.orderState === 0"
            >未开始</el-tag
          >
          <el-tag
            size="small"
            type="warning"
            v-else-if="scope.row.orderState === 1"
            >运输开始</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            type="warning"
            size="mini"
            icon="el-icon-edit"
            @click="open('修改订单', scope.row)"
            >修改</el-button
          >
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="del(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 表格结束 -->

    <!-- 分页开始 -->
    <el-pagination
      background
      layout="total, prev, pager, next, jumper"
      :hide-on-single-page="true"
      :total="total"
      :page-size="ruleForm.pageSize"
      v-show="!loading"
      :current-page="ruleForm.pageNum"
      @current-change="changePage"
      class="pageClass"
    ></el-pagination>
    <!-- 分页结束 -->

    <!-- 新增修改组件开始 -->
    <addOrder ref="addOrderRef"></addOrder>
    <!-- 新增修改组件结束 -->
  </el-card>
</template>

<script>
import addOrder from "@/components/purchase/addOrder";
import detailsOrder from "@/components/purchase/detailsOrder";
import { queryOrder, deleteOrder } from "@/apis/purchase/orderManagement.js";

export default {
  name: "transportationManagement",
  components: {
    addOrder,
    detailsOrder,
  },
  data() {
    return {
      total: null, // 分页
      loading: true, // 加载
      hidden: false, // 控制搜索表单显示状态
      // 表格开始
      tableData: [],
      activeName: "first",
      expands: [],
      getRowKeys: (row) => {
        // console.log(row)
        return row.orderId;
      },
      // 表格结束
      ruleForm: {
        key: "order_number",
        value: "",
        flag: 0,
        pageNum: 1,
        pageSize: 10,
      },
      keyOptions: [
        { value: "order_number", label: "订单编号" },
        { value: "order_corporation", label: "厂家" },
        { value: "order_goods", label: "采购商品" },
      ],
      rules: {
        key: [{ required: true, message: "请选择查询字段", trigger: "blur" }],
      },
    };
  },
  created() {
    this.search();
  },
  methods: {
    // 搜索隐藏
    searchHidden() {
      this.hidden = !this.hidden;
    },
    // 新增/修改弹窗
    open(title, row) {
      this.$refs.addOrderRef.show(title, row);
    },
    // 搜索方法
    search() {
      const _this = this;
      _this.loading = true;
      queryOrder(_this.ruleForm).then((res)=> {
        _this.tableData = res.data.data;
        _this.total = res.data.total;
        _this.loading = false;
      });
    },
    // 搜索
    submitForm(formName) {
      const _this = this;
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          _this.ruleForm.pageNum = 1; // 查询重置页数
          _this.search(_this.ruleForm);
        }
      });
    },
    // 重置
    resetForm(formName) {
      let _this = this;
      this.$refs[formName].resetFields();
      this.search(_this.ruleForm);
      this.ruleForm.pageNum = 1; // 查询重置页数
    },
    // 展开详情
    expandChange(row, rowList) {
      let _this = this;
      if (rowList.length) {
        _this.expands = [];
        if (row) {
          _this.expands.push(row.orderId);
          setTimeout(() => {
            _this.$refs["detailsOrderRef"].getData(row);
          }, 100);
        }
      } else {
        _this.expands = [];
      }
    },
    // 删除
    del(row) {
      console.log(row);
      const _this = this;
      _this
        .$confirm("确认删除订单“" + row.orderNumber + "”吗？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(() => {
          deleteOrder(row.orderId).then(function (res) {
            // console.log(res);
            if (res.code === 0) {
              _this.$message({
                type: "success",
                message: "订单“" + row.orderNumber + "”" + res.msg,
              });
              _this.search();
              _this.ruleForm.pageNum = 1; // 查询重置页数
            } else {
              _this.$message({
                type: "error",
                message: "订单“" + row.orderNumber + "”" + res.msg,
              });
            }
          });
        })
        .catch(() => {});
    },
    // 分页
    changePage(pageNum) {
      this.ruleForm.pageNum = pageNum;
      this.search();
    },
  },
};
</script>

<style>
</style>