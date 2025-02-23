<template>
  <el-card class="cardClass">
    <div slot="header" class="cardClass_header">
      <h3>货物登记</h3>
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
    <!-- 库房tab -->
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick" style="min-height: calc(100vh - 200px);"
      ref="tabRef">
      <el-tab-pane v-for="(item, index) in storehouse" :key="index" :label="item.storehouseName" v-loading="loading"
        :name="String(item.storehouseId)">
        <div class="table_top">
          <div class="table_top_inside"><span>库房：</span><el-input size="small" v-model="item.storehouseName"
              :disabled="true"></el-input></div>
          <div class="table_top_inside"><span>地址：</span><el-input size="small" v-model="item.storehouseAddress"
              :disabled="true"></el-input></div>
          <div class="table_top_inside"><span>备注：</span><el-input size="small" v-model="item.storehouseNotes"
              :disabled="true"></el-input></div>
          <el-button class="table_top_btn" size="mini" type="success" icon="el-icon-plus"
            @click="open('货物登记')">新增</el-button>
        </div>
        <goodsTable ref="goodsTableRef" v-if="activeName == item.storehouseId" :storehouse="item.storehouseName">
        </goodsTable>
        <!-- 分页 -->
          <el-pagination background layout="total, prev, pager, next, jumper" :hide-on-single-page="true" :total="total"
            :page-size="ruleForm.pageSize" v-show="!loading" :current-page="ruleForm.pageNum" @current-change="changePage"
            class="pageClass"></el-pagination>
      </el-tab-pane>
    </el-tabs>
    <addGoods ref="addGoodsRef"></addgoods>
  </el-card>
</template>

<script>
import goodsTable from '@/components/stock/goodsTable';
import addGoods from '@/components/stock/addGoods';
import { queryGoods } from '@/apis/stock/goodsManagement.js';
import { queryStorehouse } from '@/apis/stock/storehouseManagement.js';

export default {
  name: "storeManagement",
  components: {
    goodsTable,
    addGoods,
  },
  data() {
    return {
      total: null, // 分页
      loading: true, // 加载
      activeName: null,
      tableHidden: true, // 延迟表格加载    
      hidden: false, // 控制搜索表单显示状态  
      storehouse: null,
      ruleForm: {
        key: 'goods_name',
        value: '',
        flag: null,
        pageNum: 1,
        pageSize: 8,
      },
      keyOptions: [
        { value: 'goods_name', label: '货物名' },
        { value: 'goods_category', label: '类别' },
      ],
      rules: {
        key: [{ required: true, message: '请选择查询字段', trigger: 'blur' }]
      },
    }
  },
  created() {
    // console.log(this);
  },
  provide() {
    return {
      searchGoods: this.searchGoods,
      open: this.open,
    }
  },
  mounted() {
    const _this = this;
    _this.searchStorehouse();
    setTimeout(() => {
      _this.ruleForm.flag = _this.$refs.tabRef?.currentName
      _this.searchGoods()
    }, 100);
  },
  methods: {
    // 分页
    changePage(pageNum) {
      this.ruleForm.pageNum = pageNum;
      this.searchGoods()
    },
    // tab切换
    handleClick(tab) {
      this.ruleForm.flag = tab.name
      this.searchGoods();
      this.ruleForm.pageNum = 1
    },
    // 查库房
    searchStorehouse() {
      const _this = this;
      let StorehousFrom = {
        key: "storehouse_id",
        value: "",
        pageNum: 1,
        pageSize: 100,
      }
      queryStorehouse(StorehousFrom).then(function (res) {
        _this.storehouse = res.data.data
        _this.activeName = String(res.data.data[0].storehouseId)
      })
    },
    // 查货物
    searchGoods(pageNum) {
      const _this = this;
      _this.loading = true
      // console.log(Number(pageNum));
      if (pageNum === NaN) {
        _this.ruleForm.pageNum = pageNum;
      }
      queryGoods(_this.ruleForm).then(function (res) {
        _this.$refs.goodsTableRef?.forEach(e => {
          e.tableData = res.data.data
          _this.total = res.data.total
        });
        setTimeout(() => {
          _this.loading = false
        }, 100);
      })
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
          _this.searchGoods(_this.ruleForm)
        }
      });
    },
    // 新增/修改弹窗
    open(title, row) {
      let item = null
      const _this = this
      for (var e of _this.storehouse) {
        if (e.storehouseId == _this.activeName) {
          item = e
          break;
        }
      }
      this.$refs["addGoodsRef"].show(title, item, row)
    },
    // 重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.searchGoods(_this.ruleForm)
      this.ruleForm.pageNum = 1 // 查询重置页数
    },
  }
}
</script>

<style scoped>
.table_top {
  display: flex;
  margin-bottom: 14px;
}

.table_top_inside {
  display: flex;
  align-items: center;
  margin: 0 25px 0 0;
}

.table_top_btn {
  margin-left: auto;
}

.table_top span {
  min-width: 50px;
}

/deep/ .el-card__body,
.el-main {
  padding: 16px;
}

.pageClass {
  float: right;
  margin: 15px 0px 5px;
}
</style>