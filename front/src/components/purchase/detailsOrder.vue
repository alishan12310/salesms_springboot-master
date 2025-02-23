<template>
  <div>
    <el-descriptions size="small" direction="vertical" :column="4" border v-loading="loading" style="margin: 25px 0 10px">
      <el-descriptions-item label="订单编号">{{
        detailsData.orderNumber
      }}</el-descriptions-item>
      <el-descriptions-item label="厂家">{{
        detailsData.orderCorporation
      }}</el-descriptions-item>
      <el-descriptions-item label="状态">
        <el-tag size="small" type="success" v-if="detailsData.orderState === 2">完成</el-tag>
        <el-tag size="small" type="info" v-else-if="detailsData.orderState === 0">未开始</el-tag>
        <el-tag size="small" type="warning" v-else-if="detailsData.orderState === 1">运输开始</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="订单日期" :span="6">{{
        detailsData.orderDate
      }}</el-descriptions-item>
    </el-descriptions>
    <el-table border :data="table" style="width: 100%" size="small" v-loading="loading">
      <el-table-column prop="orderGoods" label="采购商品名" align="center"></el-table-column>
      <el-table-column prop="orderCategory" label="货物类别" align="center"></el-table-column>
      <el-table-column prop="orderNum" label="采购数量" align="center"></el-table-column>
      <el-table-column prop="orderUnit" label="单位" align="center"> </el-table-column>
      <el-table-column prop="orderPrice" label="批发单价" align="center"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "detailsOrder",
  detailstatus: "",
  data() {
    return {
      loading: true,
      detailsData: "",
      title: "",
      table: [],
    };
  },
  methods: {
    getData(row) {
      let orderGoods = row.orderGoods.split(",")
      let orderNum = row.orderNum.split(",")
      let orderPrice = row.orderPrice.split(",")
      let orderUnit = row.orderUnit.split(",")
      let orderCategory = row.orderCategory.split(",")
      let tableList = []
      for (let i = 0; i < orderGoods.length; i++) {
        tableList.push({
          orderGoods: orderGoods[i],
          orderNum: orderNum[i],
          orderPrice: orderPrice[i],
          orderUnit: orderUnit[i],
          orderCategory: orderCategory[i],
        })
      }
      this.table = tableList
      this.detailsData = row;
      this.loading = false;
    },
  },
};
</script>

<style></style>