<template>
  <el-card class="cardClass">
    <div slot="header" class="cardClass_header">
      <h3>人员分配</h3>
      <div>
        <el-button size="mini" type="primary" icon="el-icon-search" @click="searchHidden()">搜索</el-button>
      </div>
    </div>
    <!-- 查询 -->
    <el-collapse-transition>
      <el-form :model="ruleForm" :inline="true" :rules="rules" ref="ruleForm" label-width="80px" v-if="hidden">
        <el-form-item label="字段：" prop="key">
          <el-select v-model="ruleForm.key" placeholder="请选择查询字段" size="small">
            <el-option v-for="item in keyOptions" :key="item.value" :label="item.label" :value="item.value"></el-option>
            <el-option v-if="ruleForm.flag == 2" label="店铺名称" value="user_workstore"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="值：" prop="value">
          <el-input v-model="ruleForm.value" placeholder="请输入值" size="small" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')" size="small" icon="el-icon-search">查询</el-button>
          <el-button @click="resetForm('ruleForm')" size="small" icon="el-icon-refresh">重置</el-button>
        </el-form-item>
      </el-form>
    </el-collapse-transition>
    <!-- 分配 -->
    <el-tabs v-model="activeName" tab-position="left" type="border-card" @tab-click="handleClick" style="min-height: calc(100vh - 215px);">
      <el-tab-pane label="未分配" name="未分配">
        <delegatePage ref="delegatePageRef" v-if="ruleForm.flag == 1"/>
      </el-tab-pane>
      <el-tab-pane label="已分配" name="已分配">
        <delegatePage ref="delegatePageRef" v-if="ruleForm.flag == 2"/>
      </el-tab-pane>
    </el-tabs>
  </el-card>
</template>

<script>
import delegatePage from '@/components/store/delegatePage';

export default {
  name: "storeManagement",
  components: {
    delegatePage,
  },
  data() {
    return { 
      activeName:"未分配", // 控制tabs
      tableHidden:true, // 延迟表格加载    
      hidden: false, // 控制搜索表单显示状态    
      ruleForm: {
        key: 'user_tel',
        value: '',
        flag: 1, // tab标签 1（未分配页面）2（已分配页面）0（员工管理页面）
        userIdentity: 1,
        pageNum: 1,
        pageSize: 9,
      },
      keyOptions: [
        { value: 'user_tel', label: '员工电话' },
        { value: 'user_name', label: '员工姓名' },
      ],
      rules: {
        key: [{ required: true, message: '请选择查询字段', trigger: 'blur' }]
      },
    }
  },
  mounted() {
    this.$refs.delegatePageRef.search(this.ruleForm);
  },
  methods: {
    // tab切换
    handleClick(tab) {
      if (tab.label == "未分配") {
        this.ruleForm.flag = 1
      } else {
        this.ruleForm.flag = 2
      }
      this.ruleForm.key = 'user_tel';
      this.ruleForm.value = '';
      this.ruleForm.pageNum = 1;
      this.$store.commit('chageTabFlag',this.ruleForm.flag)
      setTimeout(() => {
        this.$refs.delegatePageRef.search(this.ruleForm);        
      }, 100);
    },
    // 搜索隐藏
    searchHidden() {
      this.hidden = !this.hidden;
    },
    // 搜索
    submitForm(formName) {
      const _this = this;
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          _this.ruleForm.pageNum = 1 // 查询重置页数
          _this.$refs.delegatePageRef.search(_this.ruleForm)
        }
      });
    },
    // 重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$refs.delegatePageRef.search(this.ruleForm)
      this.ruleForm.pageNum = 1 // 查询重置页数
    },
  }
}
</script>

<style scoped>
/deep/ .el-tabs__nav-wrap{
  min-height:  calc(100vh - 215px);
}
</style>