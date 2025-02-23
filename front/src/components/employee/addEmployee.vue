<template>
  <el-dialog :title="title" :visible.sync="open" width="70%" :before-close="handleClose" class="dialogClass">
    <el-form ref="ruleForm" :rules="rules" :model="ruleForm" label-width="80px">
      <el-row :gutter="20">
        <el-col :xl="8" :lg="8">
          <el-form-item label="电话号码" prop="userTel">
            <el-input size="big" v-model.number="ruleForm.userTel" placeholder="请输入电话号码（登陆账号）" :disabled="!controlShow"
              clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="8" :lg="8" v-if="controlShow">
          <el-form-item label="密码" prop="userPassword">
            <el-input size="big" v-model="ruleForm.userPassword" placeholder="请输入密码" clearable show-password
              autocomplete="off"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="8" :lg="8" v-if="controlShow">
          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input size="big" v-model="ruleForm.confirmPassword" clearable placeholder="请确认密码" show-password
              autocomplete="off"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="8" :lg="8">
          <el-form-item label="姓名" prop="userName">
            <el-input size="big" v-model="ruleForm.userName" placeholder="请输入姓名" :disabled="!controlShow"
              clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="8" :lg="8" v-if="controlShow">
          <el-form-item label="性别" prop="userGender">
            <el-radio-group v-model="ruleForm.userGender" :disabled="!controlShow">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :xl="8" :lg="8">
          <el-form-item label="电子邮箱" prop="userEmail">
            <el-input size="big" v-model="ruleForm.userEmail" placeholder="请输入电子邮箱" :disabled="!controlShow"
              clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="8" :lg="8" v-if="!controlShow">
          <el-form-item label="店铺" prop="userWorkstore">
            <el-select v-model="ruleForm.userWorkstore" filterable placeholder="请选择店铺" style="width:100%">
              <el-option v-for="item, index in options" :key="index" :label="item.storemanageName"
                :value="item.storemanageId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xl="8" :lg="8" v-if="!controlShow">
          <el-form-item label="职务" prop="userAppointment">
            <el-input size="big" v-model="ruleForm.userAppointment" clearable placeholder="请输入职务"></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="24" :lg="24">
          <el-form-item label="备注" prop="userNotes">
            <el-input size="big" type="textarea" v-model="ruleForm.userNotes" placeholder="请输入备注"
              :autosize="{ minRows: 5, maxRows: 8 }" clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" size="big" @click="onSubmit('ruleForm')" :loading="loading">{{
        submitButton
      }}</el-button>
      <el-button size="big" @click="resetForm('ruleForm')" v-if="restBtn">重置</el-button>
      <el-button size="big" @click="close('ruleForm')">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { addEmployee, updateEmployee,delegateEmployee } from '@/apis/employee/employeeManagement.js'
import { queryStoreName } from '@/apis/store/storeManagement.js'

export default {
  name: "addEmployee",
  data() {
    let checkTel = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('电话号码不能为空'));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'));
        } else {
          callback();
        }
      }, 500);
    };
    let inputPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.confirmPassword !== '') {
          this.$refs.ruleForm.validateField('confirmPassword');
        }
        callback();
      }
    };
    let confirmPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.userPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      // 委派和员工的显示切换标志
      controlShow: true,
      // 重置按钮的显示切换标志
      restBtn: true,
      // 提交按钮
      submitButton: '',
      // 控制弹窗
      open: false,
      // 控制按钮加载状态
      loading: false,
      // 弹窗标题
      title: '',
      // 店铺下拉
      options: null,
      // 表单项
      ruleForm: {
        userId: '',
        userTel: '',
        userPassword: '',
        confirmPassword: '',
        userWorkstore: '',
        userAppointment: '',
        userName: '',
        userGender: '男',
        userEmail: '',
        userNotes: '',
        userIdentity: 1,
      },
      nullruleForm: {
        userId: '',
        userTel: '',
        userPassword: '',
        confirmPassword: '',
        userWorkstore: '',
        userAppointment: '',
        userName: '',
        userGender: '男',
        userEmail: '',
        userNotes: '',
        userIdentity: 1,
      },

      // 表单校验
      rules: {
        userTel: [{ validator: checkTel, required: true, trigger: 'blur' }],
        userPassword: [{ validator: inputPassword, required: true, trigger: 'blur' }],
        confirmPassword: [{ validator: confirmPassword, required: true, trigger: 'blur' }],
        userName: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        userGender: [{ required: true }],
        userEmail: [{ required: true, message: '请输入电子邮箱', trigger: 'blur' }],
        userWorkstore: [{ required: true, message: '请选择店铺', trigger: 'change' }],
        userAppointment: [{ required: true, message: '请输入职务', trigger: 'blur' }],
        userNotes: [{ required: true, }],
      },

    }
  },
  created() {
    this.findQueryStoreName();
  },
  methods: {
    // 控制弹窗
    show(title, row) {
      const _this = this
      const classData = []
      _this.title = title;
      _this.open = true;
      if (row) {
        _this.submitButton = "确定";
        _this.ruleForm = JSON.parse(JSON.stringify(row));
        if (title.includes("委派")) {
          _this.controlShow = false;
        } else {
          _this.controlShow = true;
        }
        _this.restBtn = false;
      } else {
        setTimeout(() => {
          _this.resetForm('ruleForm');
        }, 100);
        _this.submitButton = "注册";
      }
    },
    findQueryStoreName() {
      const _this = this
      queryStoreName().then(function (e) {
        _this.options = e.data;
      })
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
          if (_this.title == '员工登记') {
            // console.log(_this.ruleForm);
            addEmployee(_this.ruleForm).then(function (res) {
              // console.log("res: ", res.code);
              if (res.code === 0) {
                _this.$alert(_this.ruleForm.userName + res.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.close('ruleForm');
                    _this.$parent.$parent.search()
                  }
                })
              }
            })
          } else {
            let search = null;
            let msg = null;
            if (_this.title.includes("委派")) {
              delegateEmployee(_this.ruleForm).then(function (res) {
                msg = res.msg
                if (res.code === 0) {
                  _this.$alert(_this.ruleForm.userName + msg, '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                      _this.close('ruleForm');
                      _this.$parent.search()
                    }
                  })
                }
              })
            } else {
              updateEmployee(_this.ruleForm).then(function (res) {
                msg = res.msg
                if (res.code === 0) {
                  _this.$alert(_this.ruleForm.userName + msg, '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                      _this.close('ruleForm');
                      _this.$parent.$parent.search()
                    }
                  })
                }
              })
            }
          }
        }
      });
    },
    // 表单重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.ruleForm = this.nullruleForm
    }
  }
}
</script>
