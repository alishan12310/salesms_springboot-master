<template>
  <div>
    <el-table :data="tableData" :row-key="getRowKeys" stripe :expand-row-keys="expands" @expand-change="expandChange"
      size="small" border style="width: 100%;">
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-card shadow="never" style="margin:5px 15px">
            <el-tabs v-model="activeName" @tab-click="handleClick">
              <el-tab-pane label="货物详情" name="first">
                <el-descriptions size="small" direction="vertical" :column="3" border v-loading="loading"
                  style="margin:25px 0 10px">
                  <el-descriptions-item label="货物名称">{{ scope.row.goodsName }}</el-descriptions-item>
                  <el-descriptions-item label="货物类别">{{ scope.row.goodsCategory }}</el-descriptions-item>
                  <el-descriptions-item label="货物价格（元）">{{ scope.row.goodsPrice }}</el-descriptions-item>
                  <el-descriptions-item label="剩余数量">{{ scope.row.goodsQuantity }}</el-descriptions-item>
                  <el-descriptions-item label="单位">{{ scope.row.goodsUnit }}</el-descriptions-item>
                  <el-descriptions-item label="库房">{{ storehouse }}</el-descriptions-item>
                </el-descriptions>
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </template>
      </el-table-column>
      <el-table-column prop="id" type="index" label="序号" width="60px" align="center"></el-table-column>
      <el-table-column prop="goodsName" label="货物名称" align="center" width="170px"></el-table-column>
      <el-table-column prop="goodsCategory" label="货物类别" align="center"></el-table-column>
      <el-table-column prop="goodsPrice" label="货物价格（元）" align="center"></el-table-column>
      <el-table-column prop="goodsQuantity" label="剩余数量" align="center"></el-table-column>
      <el-table-column prop="goodsUnit" label="单位" align="center"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="warning" size="mini" icon="el-icon-edit" @click="open('修改货物信息', scope.row)">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { deleteGoods } from '@/apis/stock/goodsManagement.js';

export default {
  name: "goodsTable",
  data() {
    return {
      activeName: "first",
      tableData: [],
      expands: [],
      getRowKeys: (row) => {
        // console.log(row)
        return row.goodsId
      },
    };
  },
  props:['storehouse'],
  created() {
  },
  inject: ["searchGoods", "open"],
  methods: {
    handleClick(tab, event) {
      // console.log(tab, event);
    },
    // 展开详情
    expandChange(row, rowList) {
      var _this = this
      if (rowList.length) {
        _this.expands = []
        if (row) {
          _this.expands.push(row.goodsId)
        }
        //  this.tablaData(row.eqId)  这里可以调用接口数据渲染
      } else {
        _this.expands = []
      }
    },
    // 删除
    del(row) {
      const _this = this;
      _this.$confirm('确认删除货物“' + row.goodsName + '”吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteGoods(row).then(function (res) {
          // console.log(res);
          if (res.code === 0) {
            _this.$message({
              type: 'success',
              message: '货物“' + row.goodsName + "”" + res.msg,
            })
            _this.searchGoods(1);
          } else {
            _this.$message({
              type: 'success',
              message: '货物“' + row.goodsName + "”" + res.msg,
            })
          }
        })
      }).catch(() => { })
    },
  }
}
</script>

<style scoped>

</style>