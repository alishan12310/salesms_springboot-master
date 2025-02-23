<template>
  <el-card class="cardClass">
    <div slot="header" class="cardClass_header">
      <h3>物流管理</h3>
      <div>
        <el-button
          size="mini"
          type="success"
          icon="el-icon-plus"
          @click="open('物流信息登记')"
          >新增</el-button
        >
        <el-button
          size="mini"
          type="primary"
          icon="el-icon-search"
          @click="searchHidden()"
          >搜索</el-button
        >
      </div>F
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
      size="small"
      border
      style="width: 100%"
    >
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-card shadow="never" style="margin: 5px 15px">
            <el-tabs v-model="activeName">
              <el-tab-pane label="物流详情" name="first">
                <detailsTransports
                  ref="detailsTransportsRef"
                ></detailsTransports>
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </template>
      </el-table-column>
      <el-table-column
        prop="transportOrdernumber"
        label="订单编号"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="transportDriver"
        label="司机名字"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="transportDrivertel"
        label="司机电话"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="transportCarnumber"
        label="车牌号"
        align="center"
      ></el-table-column>
      <el-table-column prop="transportState" label="运输状态" align="center">
        <template slot-scope="scope">
          <el-tag
            size="small"
            :type="scope.row.transportState == 1 ? 'success' : 'warning'"
            >{{ scope.row.transportState == 1 ? "完成" : "未完成" }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
          <el-button
            type="warning"
            size="mini"
            icon="el-icon-edit"
            @click="open('修改物流信息', scope.row)"
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
    <addTransports ref="addTransportsRef"></addTransports>
  </el-card>
</template>

<script>
import detailsTransports from "@/components/purchase/detailsTransports";
import {
  querylogistics,
  deletelogistics,
} from "@/apis/purchase/transportationManagement.js";
import addTransports from "@/components/purchase/addTransports.vue";

export default {
  name: "transportationManagement",
  components: {
    detailsTransports,
    addTransports,
  },
  data() {
    return {
      total: null, // 分页
      loading: true, // 加载
      hidden: false, // 控制搜索表单显示状态
      // 表格开始
      tableData: [],
      expands: [],
      activeName: "first",
      getRowKeys: (row) => {
        // console.log(row)
        return row.transportId;
      },
      // 表格结束
      ruleForm: {
        key: "transport_ordernumber",
        value: "",
        pageNum: 1,
        pageSize: 5,
      },
      keyOptions: [
        { value: "transport_ordernumber", label: "订单编号" },
        { value: "transport_driver", label: "司机姓名" },
        { value: "transport_drivertel", label: "司机电话" },
        { value: "transport_carnumber", label: "车牌号" },
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
    //: 新增/修改弹窗
    open(title, row) {
      this.$refs["addTransportsRef"].show(title, row);
    },
    //: 搜索隐藏
    searchHidden() {
      this.hidden = !this.hidden;
    },
    //: 搜索方法
    search() {
      const _this = this;
      _this.loading = true;
      querylogistics(_this.ruleForm).then(function (res) {
        _this.tableData = res.data;
        _this.total = res.total;
        _this.loading = false;
      });
    },
    //: 搜索
    submitForm(formName) {
      const _this = this;
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          _this.ruleForm.pageNum = 1; // 查询重置页数
          _this.search(_this.ruleForm);
        }
      });
    },
    //: 展开详情
    expandChange(row, rowList) {
      let _this = this;
      if (rowList.length) {
        _this.expands = [];
        if (row) {
          _this.expands.push(row.transportId);
          setTimeout(() => {
            _this.$refs["detailsTransportsRef"].getData(row);
          }, 100);
        }
        //  this.tablaData(row.eqId)  这里可以调用接口数据渲染
      } else {
        _this.expands = [];
      }
    },
    //: 分页
    changePage(pageNum) {
      this.ruleForm.pageNum = pageNum;
      this.search();
    },
    //: 删除
    del(row) {
      console.log(row);
      const _this = this;
      _this
        .$confirm(
          "确认删除物流订单“" + row.transportOrdernumber + "”吗？",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          }
        )
        .then(() => {
          deletelogistics({
            transportId: row.transportId,
            transportOrdernumber: row.transportOrdernumber,
          }).then(function (res) {
            console.log(res);
            if (res.code === 0) {
              _this.$message({
                type: "success",
                message: "物流订单“" + row.transportOrdernumber + "”" + res.msg,
              });
              _this.search();
              _this.ruleForm.pageNum = 1; // 查询重置页数
            } else {
              _this.$message({
                type: "error",
                message: "物流订单“" + row.transportOrdernumber + "”" + res.msg,
              });
            }
          });
        })
        .catch(() => {});
    },
  },
};
</script>

<style>
</style>