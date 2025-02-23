<template>
  <div>
    <!-- 表格弹窗 -->
    <el-dialog title="订单列表" :visible.sync="tableopen" width="70%">
      <div style="padding: 0px 20px 50px 20px">
        <el-table border :data="tableData" highlight-current-row @current-change="handleCurrentChange" style="width: 100%"
          size="small">
          <el-table-column type="index" label="序号" width="50px" align="center"></el-table-column>
          <el-table-column property="orderNumber" label="订单编号" align="center"> </el-table-column>
          <el-table-column property="orderCorporation" label="厂家" align="center"> </el-table-column>
          <el-table-column property="orderGoods" label="采购商品名" width="200" align="center"> </el-table-column>
          <el-table-column property="orderNum" label="采购数量" align="center"> </el-table-column>
          <el-table-column property="orderUnit" label="单位" align="center"> </el-table-column>
          <el-table-column property="orderPrice" label="批发单价" align="center"> </el-table-column>
          <el-table-column property="orderState" label="状态" width="80px" align="center">
            <template slot-scope="scope">
              <el-tag size="small" type="success" v-if="scope.row.orderState === 2">完成</el-tag>
              <el-tag size="small" type="info" v-else-if="scope.row.orderState === 0">未开始</el-tag>
              <el-tag size="small" type="warning" v-else-if="scope.row.orderState === 1">运输开始</el-tag>
            </template>
          </el-table-column>
          <el-table-column property="orderDate" label="订单日期" width="200" align="center"></el-table-column>
        </el-table>
        <el-pagination background layout="total, prev, pager, next, jumper" :hide-on-single-page="true" :total="total"
          :page-size="orderForm.pageSize" v-show="!loading" :current-page="orderForm.pageNum" @current-change="changePage"
          class="pageClass"></el-pagination>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="open" width="70%" :before-close="handleClose" class="dialogClass">
      <!-- 表格开始 -->
      <el-button type="primary" @click="searchOrder" style="margin:0 0 20px 0" size="small">选择订单</el-button>
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :xl="8" :lg="8">
            <el-form-item label="订单编号:" prop="selectedOrdernumber">
              <el-input v-model="selectedOrdernumber" :disabled="true" placeholder="请点击按钮选择订单"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xl="8" :lg="8">
            <el-form-item label="库房:" prop="transportStoragehouse">
              <el-select v-model="ruleForm.transportStoragehouse" placeholder="请选择" style="width:100%">
                <el-option v-for="item in options" :key="item.storehouseId" :label="item.storehouseName"
                  :value="item.storehouseId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :xl="8" :lg="8">
            <el-form-item label="运输方式:" prop="transportMode">
              <el-input v-model="ruleForm.transportMode"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xl="8" :lg="8">
            <el-form-item label="司机名字:" prop="transportDriver">
              <el-input v-model="ruleForm.transportDriver"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xl="8" :lg="8">
            <el-form-item label="司机电话:" prop="transportDrivertel">
              <el-input v-model="ruleForm.transportDrivertel"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xl="8" :lg="8">
            <el-form-item label="车牌号:" prop="transportCarnumber">
              <el-input v-model="ruleForm.transportCarnumber"></el-input>
            </el-form-item>
          </el-col>
          <el-col :xl="8" :lg="8">
            <el-form-item label="开始日期" prop="transportStartdate">
              <el-date-picker v-model="ruleForm.transportStartdate" type="date" placeholder="选择开始日期" style="width:100%"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :xl="8" :lg="8">
            <el-form-item label="结束日期" prop="transportEnddate">
              <el-date-picker v-model="ruleForm.transportEnddate" type="date" placeholder="选择结束日期" style="width:100%"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :xl="24" :lg="24">
            <el-form-item>
              <div style="float:right">
                <el-button type="primary" size="big" @click="onSubmit('ruleForm')" :loading="loading">{{ submitButton
                }}</el-button>
                <el-button size="big" @click="resetForm('ruleForm')">重置</el-button>
                <el-button size="big" @click="close('ruleForm')">取消</el-button>
              </div>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <!-- 表格结束 -->
    </el-dialog>
  </div>
</template>

<script>
import {
  savelogistics,
  Unshippedorders,
  updatelogistics,
  serachlogistics,
} from "@/apis/purchase/transportationManagement";
export default {
  name: "addTransports",
  data() {
    return {
      //控制按钮显示
      buttonshow: false,
      // 分页
      total: null,
      //表格数据
      tableData: [],
      //选中订单号
      selectedOrdernumber: "",
      // 提交按钮
      submitButton: "",
      // 控制弹窗
      open: false,
      // 控制表格弹窗
      tableopen: false,
      // 控制按钮加载状态
      loading: false,
      // 弹窗标题
      title: "",
      // 表单项
      ruleForm: {
        transportOrdernumber: "",
        transportStoragehouse: "",
        transportMode: "",
        transportDriver: "",
        transportDrivertel: "",
        transportCarnumber: "",
        transportStartdate: "",
        transportEnddate: null,
        transportState: 0,
      },
      nullruleForm: {
        transportOrdernumber: "",
        transportStoragehouse: "",
        transportMode: "",
        transportDriver: "",
        transportDrivertel: "",
        transportCarnumber: "",
        transportStartdate: "",
        transportEnddate: null,
        transportState: 0,
      },
      //订单数据
      orderForm: {
        key: "order_state",
        value: "",
        pageNum: 1,
        pageSize: 5,
      },
      //库房数据
      logisticsForm: {
        key: "",
        value: "",
        pageNum: 1,
        pageSize: 10,
      },
      options: [],
      rules: {
          transportStoragehouse: [
            { required: true, message: '请输入库房名称', trigger: 'blur' },
          ],
          transportMode: [
            { required: true, message: '请输入运输方式', trigger: 'blur' }
          ],
          transportDriver: [
            { required: true, message: '请输入司机名字', trigger: 'blur' }
          ],
          transportDrivertel: [
            { required: true, message: '请输入司机电话', trigger: 'blur' }
          ],
          transportCarnumber: [
            { required: true, message: '请输入车牌号', trigger: 'blur' }
          ],
          transportStartdate: [
            { required: true, message: '请选择开始日期', trigger: 'blur' }
          ],
          transportEnddate: [
            { required: true, message: '请选择结束日期', trigger: 'blur' }
          ]
        }
    };
  },
  created() { },
  methods: {
    //* 查找库房
    logistics() {
      serachlogistics(this.logisticsForm)
        .then(({ data }) => {
          this.options = data.data;
        })
        .catch(() => { });
    },
    //* 查找订单
    searchOrder() {
      this.tableopen = true;
      this.orderForm.flag = 0;
      Unshippedorders(this.orderForm).then((res) => {
        this.tableData = res.data.data;
        this.total = res.data.total;
        this.loading = false;
      });
    },
    //*当表格的当前行发生变化的时候会触发该事件
    handleCurrentChange(val) {
      setTimeout(() => {
        this.selectedOrdernumber = val.orderNumber;

      }, 100);
      this.tableopen = false;
      this.open = true;
    },
    //* 控制弹窗
    show(title, row) {
      const _this = this;
      _this.title = title;
      if (_this.title === "修改物流信息") {
        this.buttonshow = false;
        this.logistics();
      }
      if (_this.title === "物流信息登记") {
        this.buttonshow = true;
        this.logistics();
      }
      _this.open = true;
      if (row) {
        _this.submitButton = "修改";
        _this.ruleForm = JSON.parse(JSON.stringify(row));
        _this.selectedOrdernumber = _this.ruleForm.transportOrdernumber;
      } else {
        _this.submitButton = "确定";
      }
    },
    //* 关闭
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
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (_this.title == "物流信息登记") {
            _this.ruleForm.transportOrdernumber = _this.selectedOrdernumber;
            savelogistics(_this.ruleForm).then(function (res) {
              _this.open = false;
              if (res.code === 0) {
                _this.$alert(
                  _this.ruleForm.transportOrdernumber + res.msg,
                  "提示",
                  {
                    confirmButtonText: "确定",
                    callback: (action) => {
                      _this.close("ruleForm");
                      _this.$parent.$parent.search();
                    },
                  }
                );
              }
            });
          } else {
            updatelogistics(_this.ruleForm).then(function (res) {
              _this.open = false;
              if (res.code === 0) {
                _this.$alert(
                  _this.ruleForm.transportOrdernumber + res.msg,
                  "提示",
                  {
                    confirmButtonText: "确定",

                    callback: (action) => {
                      _this.resetForm("ruleForm");
                      _this.$parent.$parent.search();
                    },
                  }
                );
              }
            });
          }
        }
      });
    },
    //* 表单重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.ruleForm = this.nullruleForm
    },
    //* 分页
    changePage(pageNum) {
      this.orderForm.pageNum = pageNum;
      this.searchOrder();
    },
  },
};
</script>

<style scoped>
.pageClass {
  float: right;
  margin: 15px 0px 5px;
}
</style>