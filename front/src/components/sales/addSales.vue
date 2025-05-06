<template>
  <el-dialog :title="title" :visible.sync="open" width="70%" :before-close="handleClose" class="dialogClass">
    <el-form ref="ruleForm" :rules="rules" :model="ruleForm" label-width="80px">
      <el-row :gutter="20">
        <el-col :xl="6" :lg="8">
          <el-form-item label="订单编号" prop="recordOrder">
            <el-input size="small" v-model="ruleForm.recordOrder" placeholder="请输入订单编号" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="6" :lg="8">
          <el-form-item label="销售人员" prop="recordsalesperson">
            <el-input size="small" v-model="ruleForm.recordsalesperson" clearable :disabled="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <!-- 表格开始 -->
    <el-table :data="tableData" stripe size="mini" border style="width: 100%">
      <el-table-column prop="id" type="index" label="序号" width="50px" align="center"></el-table-column>
      <el-table-column label="选择货物" align="center">
        <template slot-scope="scope">
          <el-cascader size="mini" @change="change(scope.row.recordGoods, scope.$index)" v-model="scope.row.recordGoods"
            :options="scope.row.options" :show-all-levels="false" :props="{
                value: 'goodsId',
                label: 'goodsName',
                children: 'children',
                emitPath: false,
              }">
          </el-cascader>
        </template>
      </el-table-column>
      <el-table-column prop="goodsCategory" label="类别" align="center">
        <template slot-scope="scope">
          {{ scope.row.goodsCategory.length == 0 ? "暂无数据" : scope.row.goodsCategory }}
        </template>
      </el-table-column>
      <el-table-column prop="goodsPrice" label="单价（元）" align="center">
        <template slot-scope="scope">
          {{ scope.row.goodsPrice.length == 0 ? "暂无数据" : scope.row.goodsPrice }}
        </template>
      </el-table-column>
      <el-table-column prop="goodsQuantity" label="数量" align="center" width="80px">
        <template slot-scope="scope">
          {{ scope.row.goodsQuantity.length == 0 ? "暂无数据" : scope.row.goodsQuantity }}
        </template>
      </el-table-column>
      <el-table-column prop="goodsUnit" label="单位" align="center" width="80px">
        <template slot-scope="scope">
          {{ scope.row.goodsUnit.length == 0 ? "暂无数据" : scope.row.goodsUnit }}
        </template>
      </el-table-column>
      <el-table-column prop="storemanageId" label="店铺" align="center">
        <template slot-scope="scope">
<!--          <el-input size="small" v-model="scope.row.storemanageId" placeholder="店铺id" clearable></el-input>-->
          <el-select
              v-model="scope.row.storemanageId"
              placeholder="请选择店铺"
              size="small"
              style="width: 100%"
              filterable
              clearable
          >
            <el-option
                v-for="item in storeOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>

        </template>
      </el-table-column>
      <el-table-column prop="recordName" label="售出数量" align="center">
        <template slot-scope="scope">
          <el-input-number size="mini" v-model.number="scope.row.recordNum" controls-position="right" :min="1"
            :max=Number(scope.row.goodsQuantity) style="width: 100%"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column prop="recordName" label="总价（元）" align="center">
        <template slot-scope="scope">
          {{ scope.row.recordNum * scope.row.goodsPrice }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" min-width="80px">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" icon="el-icon-delete" :disabled="tableData.length == 1"
            @click="deleteRow(scope.$index, tableData)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="" size="small" icon="el-icon-plus" @click="addRow()" class="addBtn">添加待售货物</el-button>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" size="big" @click="onSubmit('ruleForm')" :loading="loading">确定</el-button>
      <el-button size="big" @click="resetForm('ruleForm')">重置</el-button>
      <el-button size="big" @click="close('ruleForm')">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
import {
  addsalesRecords,
  queryGoodsDetails,
  queryAllGoods,
} from "@/apis/sales/salesRecordManagement.js";
import {queryStoreName} from "@/apis/store/storeManagement";

export default {
  name: "addStore",
  data() {
    return {
      storeOptions: [], // 用于下拉显示店铺
      goodsId: [],
      // 控制弹窗
      open: false,
      // 控制按钮加载状态
      loading: false,
      // 弹窗标题
      title: "",
      // 表单项
      ruleForm: {
        recordsalesperson: "",
        recordOrder: "",
      },
      //货物类别列表
      options: [],
      // 货物项
      goods: {
        options: [],
        recordOrder: "", // 订单id
        recordGoods: "", // 商品id
        recordName: "", //商品名字
        recordNum: "", // 售出数量
        recordPrice: 0, // 总价
        recordSalesperson: this.$store.state.nowUser.userId, // 销售人员id
        goodsStorehouse: "",
        goodsUnit: "",
        goodsQuantity: "",
        goodsPrice: "",
        goodsCategory: "",
        storemanageId:""
      },
      // 货物列表
      tableData: [
      ],
      // 表单校验
      rules: {
        recordOrder: [
          { required: true, message: "请输入订单编号", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    queryAllGoods()
      .then(({ data }) => {
        this.tableData[0].options = data;
        this.goods.options = data;
      })
      .catch(() => { });
    // this.queryCategory();
    // this.searchUnit();

    queryAllGoods().then(({ data }) => {
      this.goods.options = data;
    });

    queryStoreName().then(({ data }) => {
      this.storeOptions = data.map(store => ({
        label: store.storemanageName,
        value: store.storemanageId
      }));
    });
  },
  mounted() {
    // 货物列表
    let goods = JSON.parse(JSON.stringify(this.goods));
    this.tableData.push(goods);
  },
  methods: {
    change(id, index) {
      queryGoodsDetails(id)
        .then(({ data }) => {
          // console.log(data);
          let nowData = data;
          nowData["options"] = this.options;
          this.tableData[index].goodsCategory = data.goodsCategory;
          this.tableData[index].recordName = data.goodsName;
          this.tableData[index].goodsPrice = data.goodsPrice;
          this.tableData[index].goodsQuantity = data.goodsQuantity;
          this.tableData[index].goodsStorehouse = data.goodsStorehouse;
          this.tableData[index].goodsUnit = data.goodsUnit;
        })
        .catch((err) => console.log(err));
    },
    //* 控制弹窗
    show(row) {
      const _this = this;
      _this.ruleForm.recordsalesperson = _this.$store.state.nowUser.userName;
      _this.open = true;
    },
    //* 删除货物项
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },
    //* 新增货物项
    addRow() {
      const _this = this;
      let goods = JSON.parse(JSON.stringify(_this.goods));
      // goods.options = this.options;
      _this.tableData.push(goods);
      // _this.findGoods(_this.tableData.length);
    },
    close(formName) {
      this.open = false;
      this.resetForm(formName);
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
        .catch((_) => { });
    },
    //*修改
    onSubmit(formName) {
      const _this = this;
      _this.tableData.forEach(e => {
        e.recordOrder = _this.ruleForm.recordOrder
        e.recordPrice = e.goodsPrice * e.recordNum;
      });
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addsalesRecords(_this.tableData).then(function (res) {
            if (res.code === 0) {
              _this.$alert("商品" + res.msg, "提示", {
                confirmButtonText: "确定",
                callback: (action) => {
                  _this.close("ruleForm");
                  _this.$parent.$parent.search();
                },
              });
            }
          });

        }
      });
      _this.close("ruleForm");
    },
    //* 表单重置
    resetForm(formName) {
      const _this = this;
      _this.$refs[formName].resetFields();
      _this.tableData = [_this.goods];
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
