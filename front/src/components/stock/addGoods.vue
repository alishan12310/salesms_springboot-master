<template>
  <el-dialog :title="title" :visible.sync="open" width="70%" :before-close="handleClose" class="dialogClass">
    <el-form ref="ruleForm" :rules="rules" :model="ruleForm" label-width="80px">
      <el-row :gutter="20">
        <el-col :xl="6" :lg="8">
          <el-form-item label="货物名称" prop="goodsName">
            <el-input size="big" v-model="ruleForm.goodsName" placeholder="请输入货物名" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="6" :lg="8">
          <el-form-item label="货物类别" prop="goodsCategory">
            <el-select v-model="ruleForm.goodsCategory" placeholder="请选择货物类别" style="width:100%" filterable clearable>
              <el-option v-for="item,index in category" :key="index" :label="item.categoryName" :value="item.categoryName"></el-option>
            </el-select>
            <!-- <el-input size="big" v-model="ruleForm.goodsCategory" placeholder="请输入货物类别" clearable></el-input> -->
          </el-form-item>
        </el-col>
        <el-col :xl="6" :lg="8">
          <el-form-item label="货物单价" prop="goodsPrice">
            <el-input size="big" v-model.number="ruleForm.goodsPrice" placeholder="请输入货物单价" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="6" :lg="8">
          <el-form-item label="货物数量" prop="goodsQuantity">
            <el-input size="big" v-model.number="ruleForm.goodsQuantity" placeholder="请输入货物数量" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="6" :lg="8">
          <el-form-item label="单位" prop="goodsUnit">
            <el-select v-model="ruleForm.goodsUnit" placeholder="请选择单位" style="width:100%" filterable clearable>
              <el-option v-for="item,index in unit" :key="index" :label="item.unitName" :value="item.unitName"></el-option>
            </el-select>
            <!-- <el-input size="big" v-model="ruleForm.goodsUnit" placeholder="请输入单位" clearable></el-input> -->
          </el-form-item>
        </el-col>
        <el-col :xl="6" :lg="8">
          <el-form-item label="存储库房" prop="goodsStorehouse">
            <el-input size="big" v-model="storehouse.storehouseName" disabled></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" size="big" @click="onSubmit('ruleForm')" :loading="loading">{{
        submitButton
      }}</el-button>
      <el-button size="big" @click="resetForm('ruleForm')">重置</el-button>
      <el-button size="big" @click="close('ruleForm')">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { saveGoods, updateGoods } from '@/apis/stock/goodsManagement.js'
import { queryAllUnit } from '@/apis/stock/unitManagement.js'
import { queryAllCategory } from '@/apis/stock/categoryManagement.js'

export default {
  name: "addStore",
  data() {
    return {
      // 提交按钮
      submitButton: '',
      // 控制弹窗
      open: false,
      // 控制按钮加载状态
      loading: false,
      // 弹窗标题
      title: '',
      // 当前库房
      storehouse: {
        storehouseName: ''
      },
      // 单位
      unit: null,
      // 类别
      category: null,
      // 表单项
      ruleForm: {
        goodsName: '',
        goodsCategory: '',
        goodsPrice: null,
        goodsQuantity: null,
        goodsUnit: '',
        goodsStorehouse: '',
      },
      nullruleForm: {
        goodsName: '',
        goodsCategory: '',
        goodsPrice: null,
        goodsQuantity: null,
        goodsUnit: '',
        goodsStorehouse: '',
      },

      // 表单校验
      rules: {
        goodsName: [{ required: true, message: '请输入货物名', trigger: 'blur' }],
        goodsCategory: [{ required: true, message: '请选择货物类别', trigger: 'change' }],
        goodsPrice: [{  required: true, message: '请输入货物单价（数字）', trigger: 'blur' }],
        goodsQuantity: [{ type:'number' , required: true, message: '请输入货物数量（数字）', trigger: 'blur' }],
        goodsUnit: [{ required: true, message: '请选择单位', trigger: 'change' }],
      },

    }
  },
  created() {
    this.searchCategory();
    this.searchUnit();
  },
  inject: ["searchGoods"],
  methods: {
    // 控制弹窗
    show(title, item, row) {
      const _this = this
      _this.storehouse = item
      _this.ruleForm.goodsStorehouse = item.storehouseId
      _this.title = title;
      _this.open = true;
      if (row) {
        _this.submitButton = "修改";
        _this.ruleForm = JSON.parse(JSON.stringify(row));
      } else {
        _this.submitButton = "确定";
      }
    },
    close(formName) {
      this.open = false;
      this.resetForm(formName);
    },
    handleClose(done) {
      const _this = this;
      _this.$confirm('系统可能不会保存您的更改', '确认关闭？')
        .then(_ => {
          done();
          _this.resetForm('ruleForm');
        })
        .catch(_ => { });
    },
    onSubmit(formName) {
      const _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (_this.title == '货物登记') {
            // console.log(_this.ruleForm);
            saveGoods(_this.ruleForm).then(function (res) {
              // console.log("res: ", res.code);
              if (res.code === 0) {
                _this.$alert(_this.ruleForm.goodsName + res.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.close('ruleForm');
                    _this.searchGoods();
                  }
                })
              }
            })
          } else {
            updateGoods(_this.ruleForm).then(function (res) {
              // console.log("res: ", res.code);
              if (res.code === 0) {
                _this.$alert(_this.ruleForm.goodsName + res.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.close('ruleForm');
                    _this.searchGoods();
                  }
                })
              }
            })
            // console.log(this.ruleForm, '修改成功');
          }
        }
      });
    },
    // 表单重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.ruleForm =this.nullruleForm

    },
    // 查找单位
    searchUnit() {
      const _this = this;
      queryAllUnit().then(function (res) {
        _this.unit = res.data;
        // console.log('_this.unit:', _this.unit);

      })
    },

    // 查找类别
    searchCategory() {
      const _this = this;
      queryAllCategory().then(function (res) {
        _this.category = res.data
        // console.log('_this.category:', _this.category);
      })
    },
  }
}
</script>

<style scoped>
</style>