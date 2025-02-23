<template>
  <el-dialog :title="title" :visible.sync="open" width="70%" :before-close="handleClose" class="dialogClass">
    <el-form ref="ruleForm" :rules="rules" :model="ruleForm" label-width="80px">
      <el-row :gutter="20">        
        <el-col :xl="8" :lg="8">
          <el-form-item label="店铺名称" prop="storemanageName">
            <el-input size="big" v-model="ruleForm.storemanageName" placeholder="请输入店铺名" clearable></el-input>
          </el-form-item>
        </el-col>      
        <el-col :xl="8" :lg="8">
          <el-form-item label="营业范围" prop="storemanageContent">
            <el-input size="big" v-model="ruleForm.storemanageContent" placeholder="请输入营业范围" clearable></el-input>
          </el-form-item>
        </el-col>        
        <el-col :xl="8" :lg="8">
          <el-form-item label="地址" prop="storemanageAddress">
            <el-input size="big" v-model="ruleForm.storemanageAddress" placeholder="请输入地址" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="24" :lg="24">
          <el-form-item label="备注" prop="storemanageNotes">
            <el-input size="big" type="textarea" v-model="ruleForm.storemanageNotes" placeholder="请输入备注"
              :autosize="{ minRows: 5, maxRows: 8 }" clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" size="big" @click="onSubmit('ruleForm')" :loading="loading">{{ submitButton }}</el-button>
      <el-button size="big" @click="resetForm('ruleForm')">重置</el-button>
      <el-button size="big" @click="close('ruleForm')">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { saveStore,updateStore } from '@/apis/store/storeManagement.js'

export default {
  name: "addStore",
  data() {
    return {
      // 提交按钮
      submitButton:'',
      // 控制弹窗
      open: false,
      // 控制按钮加载状态
      loading: false,
      // 弹窗标题
      title: '',
      // 表单项
      ruleForm: {
        storemanageName: '',
        storemanageContent: '',
        storemanageAddress: '',
        storemanageNotes: '',
      },
      nullruleForm: {
        storemanageName: '',
        storemanageContent: '',
        storemanageAddress: '',
        storemanageNotes: '',
      },

      // 表单校验
      rules: {
        storemanageName: [{ required: true, message: '请输入店铺名', trigger: 'blur' }],
        storemanageContent: [{ required: true, message: '请输入营业范围', trigger: 'blur' }],
        storemanageAddress: [{ required: true, message: '请输入地址', trigger: 'blur' }],
        storemanageNotes: [{ required: false, }],
      },

    }
  },
  methods: {
    // 控制弹窗
    show(title, row) {
      const _this = this
      const classData = []
      _this.title = title;
      _this.open = true;
      if (row) {
        _this.submitButton = "修改";
        _this.ruleForm = JSON.parse(JSON.stringify(row));
      }else{
        _this.submitButton = "确定登记";        
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
          if (_this.title == '店铺登记') {
            // console.log(_this.ruleForm);
            saveStore(_this.ruleForm).then(function (res) {
              // console.log("res: ", res.code);
              if (res.code === 0) {
                _this.$alert(_this.ruleForm.storemanageName + res.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.close('ruleForm');
                    _this.$parent.$parent.search()
                  }
                })
              }
            })
          } else {            
            updateStore(_this.ruleForm).then(function (res) {
              // console.log("res: ", res.code);
              if (res.code === 0) {
                _this.$alert(_this.ruleForm.storemanageName + res.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.close('ruleForm');
                    _this.$parent.$parent.search()
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

    }
  }
}
</script>

<style scoped>
</style>