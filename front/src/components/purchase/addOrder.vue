<template>
  <el-dialog
    :title="title"
    :visible.sync="open"
    width="70%"
    :before-close="handleClose"
    class="dialogClass"
  >
    <el-form ref="ruleForm" :rules="rules" :model="ruleForm" label-width="80px">
      <el-row :gutter="20">
        <el-col :xl="6" :lg="8">
          <el-form-item label="订单编号" prop="orderNumber">
            <el-input
              size="small"
              v-model="ruleForm.orderNumber"
              placeholder="请输入订单编号"
              clearable
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="6" :lg="8">
          <el-form-item label="厂家" prop="orderCorporation">
            <el-input
              size="small"
              v-model="ruleForm.orderCorporation"
              placeholder="请输入厂家"
              clearable
            ></el-input>
          </el-form-item>
        </el-col>
        <!-- <el-col :xl="6" :lg="8">
          <el-form-item label="时间" prop="orderCorporation">
            <el-input size="small" v-model="ruleForm.orderCorporation" placeholder="请输入厂家" clearable></el-input>
          </el-form-item>
        </el-col> -->
      </el-row>
    </el-form>

    <!-- 表格开始 -->
    <el-table :data="tableData" stripe size="mini" border style="width: 100%">
      <el-table-column
        prop="id"
        type="index"
        label="序号"
        width="50px"
        align="center"
      ></el-table-column>
      <el-table-column prop="name" label="货物" align="center">
        <template slot-scope="scope">
          <el-input
            size="mini"
            v-model="scope.row.name"
            placeholder="请输入货物"
            clearable
          ></el-input>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="选择货物类别" align="center">
        <template slot-scope="scope">
          <el-select
            size="mini"
            v-model="scope.row.category"
            placeholder="请选择货物类别"
          >
            <el-option
              v-for="item in options"
              :key="item.categoryId"
              :label="item.categoryName"
              :value="item.categoryName"
            >
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="number" label="数量" align="center">
        <template slot-scope="scope">
          <el-input-number
            size="mini"
            v-model.number="scope.row.number"
            controls-position="right"
            :min="1"
            style="width: 100%"
          ></el-input-number>
        </template>
      </el-table-column>
      <el-table-column prop="unit" label="单位" align="center">
        <template slot-scope="scope">
          <el-select
            size="mini"
            v-model="scope.row.unit"
            filterable
            placeholder="请选择单位"
          >
            <el-option
              v-for="(item, index) in unit"
              :key="index"
              :label="item.unitName"
              :value="item.unitName"
            ></el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column prop="unit" label="批发单价" align="center">
        <template slot-scope="scope">
          <el-input
            size="mini"
            v-model.number="scope.row.price"
            placeholder="请输入批发单价"
            clearable
          >
            <template slot="append">元</template>
          </el-input>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" min-width="50px">
        <template slot-scope="scope">
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            :disabled="tableData.length == 1"
            @click="deleteRow(scope.$index, tableData)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-button
      type=""
      size="small"
      icon="el-icon-plus"
      @click="addRow()"
      class="addBtn"
      >添加货物</el-button
    >
    <!-- 表格结束 -->
    <span slot="footer" class="dialog-footer">
      <el-button
        type="primary"
        size="big"
        @click="onSubmit('ruleForm')"
        :loading="loading"
        >{{ submitButton }}</el-button
      >
      <el-button size="big" @click="resetForm('ruleForm')">重置</el-button>
      <el-button size="big" @click="close('ruleForm')">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
import {
  saveOrder,
  updateOrder,
  queryAllCategory,
} from "@/apis/purchase/orderManagement.js";
import { queryAllUnit } from "@/apis/stock/unitManagement.js";

export default {
  name: "addStore",
  data() {
    return {
      // 提交按钮
      submitButton: "",
      // 控制弹窗
      open: false,
      // 控制按钮加载状态
      loading: false,
      // 弹窗标题
      title: "",
      //货物类别列表
      options: [],
      // 表单项
      ruleForm: {
        orderNumber: "",
        orderCorporation: "",
        orderCategory: "",
        orderGoods: "",
        orderNum: "",
        orderUnit: "",
        orderPrice: "",
        orderDate: null,
        orderState: null,
        storemanageAddress: "",
        storemanageNotes: "",
      },
      // 货物项
      goods: {
        name: "",
        number: 1,
        category: null,
        unit: "",
        price: 0,
      },
      // 货物列表
      tableData: [],
      // 货物
      name: "",
      // 单位
      unit: "",
      // 表单校验
      rules: {
        orderNumber: [
          { required: true, message: "请输入订单编号", trigger: "blur" },
        ],
        orderCorporation: [
          { required: true, message: "请输入厂家名", trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
    // 货物列表
    let goods = JSON.parse(JSON.stringify(this.goods));
    this.tableData.push(goods);
  },
  created() {
    this.queryCategory();
    // this.searchGoods();
    this.searchUnit();
  },
  methods: {
    //*查找货物类别
    queryCategory() {
      queryAllCategory()
        .then((res) => {
          this.options = res.data;
        })
        .catch(() => {});
    },
    //* 查找单位
    searchUnit() {
      const _this = this;
      queryAllUnit().then(function (res) {
        _this.unit = res.data;
      });
    },
    //* 控制弹窗
    show(title, row) {
      const _this = this;
      _this.title = title;
      _this.open = true;
      if (row) {
        _this.submitButton = "修改";
        _this.ruleForm = JSON.parse(JSON.stringify(row));
        let l_01 = _this.ruleForm.orderGoods.split(",");
        let l_02 = _this.ruleForm.orderNum.split(",");
        let l_03 = _this.ruleForm.orderUnit.split(",");
        let l_04 = _this.ruleForm.orderPrice.split(",");
        let l_05 = _this.ruleForm.orderCategory.split(",");
        for (let index = 0; index < l_01.length; index++) {
          if (index != 0) {
            let goods = JSON.parse(JSON.stringify(_this.goods));
            _this.tableData.push(goods);
          }
          setTimeout(() => {
            _this.tableData[index].name = l_01[index];
            _this.tableData[index].number = l_02[index];
            _this.tableData[index].unit = l_03[index];
            _this.tableData[index].price = l_04[index];
            _this.tableData[index].category = l_05[index];
          }, 100);
        }
      } else {
        _this.submitButton = "确定";
      }
    },
    //* 删除货物项
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },
    //* 新增货物项
    addRow() {
      const _this = this;
      let goods = JSON.parse(JSON.stringify(_this.goods));
      _this.tableData.push(goods);
    },
    close(formName) {
      this.open = false;
      this.resetForm(formName);
      this.tableData = [];
      this.addRow();
    },
    //*关闭弹窗
    handleClose(done) {
      const _this = this;
      _this
        .$confirm("系统可能不会保存您的更改", "确认关闭？")
        .then((_) => {
          done();
          _this.resetForm("ruleForm");
        })
        .catch((_) => {});
    },
    //*修改
    onSubmit(formName) {
      const _this = this;
      const name = [];
      const number = [];
      const unit = [];
      const price = [];
      const category = [];
      _this.tableData.forEach((e) => {
        name.push(e.name);
        number.push(e.number);
        unit.push(e.unit);
        price.push(e.price);
        category.push(e.category);
      });
      _this.ruleForm.orderGoods = name.join(",");
      _this.ruleForm.orderCategory = category.join(",");
      _this.ruleForm.orderNum = number.join(",");
      _this.ruleForm.orderUnit = unit.join(",");
      _this.ruleForm.orderPrice = price.join(",");
      _this.ruleForm.orderState = 0;
      _this.ruleForm.orderDate = _this.$moment().format("yyyy-MM-DD hh:mm:ss");

      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (_this.title == "创建订单") {
            saveOrder(_this.ruleForm).then(function (res) {
              if (res.code === 0) {
                _this.$alert(_this.ruleForm.orderNumber + res.msg, "提示", {
                  confirmButtonText: "确定",
                  callback: (action) => {
                    _this.close("ruleForm");
                    _this.$parent.$parent.search();
                  },
                });
              }
            });
          } else {
            updateOrder(_this.ruleForm).then(function (res) {
              if (res.code === 0) {
                _this.$alert(_this.ruleForm.orderNumber + res.msg, "提示", {
                  confirmButtonText: "确定",
                  callback: (action) => {
                    _this.close("ruleForm");
                    _this.$parent.$parent.search();
                  },
                });
              }
            });
          }
        }
      });
    },
    //* 表单重置
    resetForm(formName) {
      const _this = this;
      _this.$refs[formName].resetFields();
      _this.tableData = [_this.goods];
      this.ruleForm.orderNumber = ''
      this.ruleForm.orderCorporation=''
    },
  },
};
</script>

<style scoped>
.addBtn {
  width: 100%;
  border-top: unset;
}
</style>