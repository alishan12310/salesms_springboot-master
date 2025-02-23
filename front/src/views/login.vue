<template>
  <div style="margin-top:10%">
    <el-card class="box-card">
      <p style="font-size:20px;font-weight: bolder;">用户登录</p>
      <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
      <el-form ref="ruleForm" :rules="rules" :model="ruleForm">
        <el-form-item prop="username">
          <el-input v-model="ruleForm.username" placeholder="用户名（电话号码）" clearable></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="ruleForm.password" placeholder="密码" clearable show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-radio-group v-model="ruleForm.flag" class="radio_flex">
            <el-radio :label="0">系统管理员</el-radio>
            <el-radio :label="1">销售员</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-row :gutter="10" class="btn">
            <el-col :span="18">
              <el-button type="primary" @click="onSubmit" :loading="loading">登录</el-button>
            </el-col>
            <el-col :span="6">
              <el-button type="warning" @click="resetForm('ruleForm')">重置</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { login } from '@/apis/login.js'
import { getAllTwoMenuList } from '@/apis/system/menuManage.js'

export default {
  data() {
    return {
      loading: false,
      ruleForm: {
        username: '',
        password: '',
        flag: 0,
      },
      rules: {
        username: [{ required: true, message: '请输入用户名（电话号码）', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
      },
    }
  },
  mounted() {
  },
  created() {
    // 登录缓存检测
    if (window.localStorage.getItem('userData')) {
      this.$router.replace({ path: "/index" });
    }
  },
  methods: {
    onSubmit() {
      // 系统管理员登录接口
      const _this = this;
      _this.loading = true;
      _this.$refs.ruleForm.validate(async (valid) => {
        if (valid) {
          await login(_this.ruleForm).then(async function (res) {
            // console.log(res);
            if (res.code == -1) {
              _this.$alert('用户名或密码错误', '提示', {
                confirmButtonText: '确定'
              })
            } else {
               await getAllTwoMenuList(_this.ruleForm.flag).then((res) => {
                localStorage.setItem('menuList', JSON.stringify(res.data));
              })
              localStorage.setItem('userData', res.data.userId);
              _this.$router.replace({ path: '/index' });
            }
          }).catch(function (error) {
            console.log(error);
          });
        }
        _this.loading = false;
      })
    },
    // 表单重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style>
.box-card {
  padding: 0 15px;
  margin: 0 auto;
  width: 400px;
}

.btn .el-button {
  width: 100%;
}

.radio_flex {
  display: flex;
  justify-content: space-around;
}
</style>