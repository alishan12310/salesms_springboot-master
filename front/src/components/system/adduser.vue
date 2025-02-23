<template>
  <el-dialog
    :title="title"
    :visible.sync="open"
    width="70%"
    :before-close="handleClose"
    class="dialogClass"
  >
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="80px">
      <el-row :gutter="20">
        <el-col :xl="8" :lg="8">
          <el-form-item label="姓名" prop="userName">
            <el-input
              :xl="8"
              v-model="ruleForm.userName"
              placeholder="请输姓名"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="8" :lg="8">
          <el-form-item label="性别" prop="userGender">
            <el-input
              :xl="8"
              v-model="ruleForm.userGender"
              placeholder="请输入性别"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="8" :lg="8">
          <el-form-item label="用户身份" prop="userIdentity">
            <el-select v-model="ruleForm.userIdentity" placeholder="请选择用户身份" style="width:100%">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <!-- <el-col :xl="8" :lg="8">
          <el-form-item label="用户头像路径" prop="userImg" label-width="100px">
            <el-input
              :xl="8"
              v-model="ruleForm.userImg"
              placeholder="请输入用户头像路径"
            ></el-input>
          </el-form-item>
        </el-col> -->
        <el-col :xl="24" :lg="24">
          <el-form-item label="账号" prop="userTel">
            <el-input
              :xl="8"
              v-model="ruleForm.userTel"
              placeholder="请输入手机号"
              :autosize="{ minRows: 5, maxRows: 8 }"
              clearable
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="24" :lg="24">
          <el-form-item label="用户密码" prop="userPassword">
            <el-input
              :xl="8"
              v-model="ruleForm.userPassword"
              placeholder="请输入密码"
              :autosize="{ minRows: 5, maxRows: 8 }"
              clearable
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="24" :lg="24">
          <el-form-item label="电子邮箱" prop="userEmail">
            <el-input
              :xl="8"
              v-model="ruleForm.userEmail"
              placeholder="请输入邮箱"
              :autosize="{ minRows: 5, maxRows: 8 }"
              clearable
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :xl="24" :lg="24">
          <el-form-item label="备注" prop="userNotes">
            <el-input
              :xl="8"
              type="textarea"
              v-model="ruleForm.userNotes"
              placeholder="请输入备注"
              :autosize="{ minRows: 5, maxRows: 8 }"
              clearable
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div style="display: flex; justify-content: flex-end">
      <el-button
        type="primary"
        size="big"
        @click="onSubmit('ruleForm')"
        :loading="loading"
        >{{ submitButton }}</el-button
      >
      <el-button size="big" @click="resetForm('ruleForm')">重置</el-button>
      <el-button size="big" @click="close('ruleForm')">取消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { updateUser, addUser } from "@/apis/system/userManagement.js";

export default {
  name: "adduser",
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
      // 表单项
      ruleForm: {
        userAppointment: null,
        userEmail: "",
        userGender: "",
        userIdentity: "",
        userImg: "",
        userName: "",
        userNotes: "",
        userPassword: "",
        userState: null,
        userTel: "",
        userWorkstore:NaN ,
      },
      nullruleForm: {
        userAppointment: "",
        userCreatedate: null,
        userEmail: "",
        userGender: "",
        userIdentity: "",
        userImg: "",
        userName: "",
        userNotes: "",
        userPassword: "",
        userState: null,
        userTel: "",
        userWorkstore: NaN
      },
      options: [
        {
          value: 0,
          label: "管理员",
        },
        {
          value: 1,
          label: "售货员",
        },
      ],
      // 表单校验
        rules: {
          userName: [
            { required: true, message: "请输入姓名", trigger: "blur" },
          ],
          userGender: [
            { required: true, message: "请输入性别", trigger: "blur" },
          ],
          userIdentity: [
            { required: true, message: "请输入用户身份", trigger: "blur" },
          ],
          userTel: [
            { required: true, message: "请输入联系电话", trigger: "blur" },
          ],
          userPassword: [
            { required: true, message: "请输入密码", trigger: "blur" },
          ],
          userEmail: [
            { required: true, message: "请输入邮箱", trigger: "blur" },
          ],
          userNotes: [
            { required: true, message: "请输入备注", trigger: "blur" },
          ],
        },
    };
  },
  methods: {
    // 控制弹窗
    show(title, row) {
      const _this = this
      _this.title = title;
      _this.open = true;
      if (row) {
        _this.submitButton = "修改";
        _this.ruleForm = JSON.parse(JSON.stringify(row));
      } else {
        _this.submitButton = "确定登记";
      }
    },
    close(formName) {
      this.open = false;
      this.ruleForm = this.nullruleForm
    },
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
    onSubmit(formName) {
      const _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
              console.log(_this.title);

            if (_this.title == '用户登记') {
              // console.log(_this.ruleForm);
              addUser(_this.ruleForm).then(function (res) {
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
              this.ruleForm.userState = null;
              this.ruleForm.userWorkstore = NaN;
              this.ruleForm.userAppointment = null;
              updateUser(_this.ruleForm).then(function (res) {
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
              // console.log(this.ruleForm, '修改成功');
            }
        }
      });
    },
    // 表单重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.ruleForm = this.nullruleForm
    },
  },
};
</script>
