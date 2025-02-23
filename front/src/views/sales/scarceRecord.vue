<template>
  <el-card class="cardClass">
    <div slot="header" class="cardClass_header">
      <h3>缺货管理</h3>
      <div>
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
        <el-form-item label="剩余数量：" prop="value" label-width="100px">
          <el-select v-model="ruleForm.flag" placeholder="请选择" size="small">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
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
      stripe
      v-loading="loading"
      size="small"
      border
      style="width: 100%"
    >
      <el-table-column
        prop="id"
        type="index"
        label="序号"
        width="50px"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="goodsName"
        label="商品名"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="goodsCategory"
        label="类别"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="goodsPrice"
        label="价格"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="goodsQuantity"
        label="数量"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="goodsUnit"
        label="单位"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="goodsStorehouse"
        label="存储库房"
        align="center"
      ></el-table-column>
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
  </el-card>
</template>

<script>
import { getScarceRecord } from "@/apis/sales/salesRecordsManagement.js";

export default {
  name: "transportationManagement",
  components: {},
  data() {
    return {
      total: null, // 分页
      loading: true, // 加载
      hidden: false, // 控制搜索表单显示状态
      // 表格开始
      tableData: [],
      // 表格结束
      ruleForm: {
        key: "goods_Category",
        value: "",
        flag: 20,
        pageNum: 1,
        pageSize: 10,
      },
      //查询字段
      keyOptions: [
        { value: "goods_Category", label: "类别" },
        { value: "goods_Storehouse", label: "存储库房" },
      ],
      rules: {
        key: [{ required: true, message: "请选择查询字段", trigger: "blur" }],
      },
      //选择框数据
       options: [{
          value: '10',
          label: '10'
        }, {
          value: '20',
          label: '20'
        }, {
          value: '30',
          label: '30'
        }, {
          value: '40',
          label: '40'
        }, {
          value: '50',
          label: '50'
        },{
          value: '60',
          label: '60'
        }, {
          value: '70',
          label: '70'
        }, {
          value: '80',
          label: '80'
        }, {
          value: '90',
          label: '90'
        }, {
          value: '100',
          label: '100'
        }
        
        ],
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
    // 搜索方法
    search() {
      const _this = this;
      _this.loading = true;
      getScarceRecord(_this.ruleForm).then(function (res) {
        _this.tableData = res.data;
        // _this.total = res.data.total;
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