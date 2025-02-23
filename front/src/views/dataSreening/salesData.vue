<template>
  <div class="allPage">
    <el-row :gutter="15">
      <el-col :span="6">
        <el-card class="box-card-top">
          <div slot="header" class="cardClass_header">
            <h4>当年总销售额</h4>
          </div>
          <getNowtotalSales style="display:flex;width: 100%;height:160px; align-items: center;justify-content: center;">
          </getNowtotalSales>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card-top">
          <getSalesOrders></getSalesOrders>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card-top">
          <Storeslastmonth></Storeslastmonth>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card-top">
          <sellingoods></sellingoods>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="15">
      <el-col :span="12">
        <el-card class="box-card-bottom">
          <div>
            <Newsalestrends></Newsalestrends>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card-bottom">
          <div>
              <totalsales></totalsales>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Storeslastmonth from "@/components/dataSreening/salesData/Storeslastmonth";
import sellingoods from "@/components/dataSreening/salesData/sellingoods";
import Newsalestrends from "@/components/dataSreening/salesData/Newsalestrends";
import totalsales from "@/components/dataSreening/salesData/totalsales";
import getNowtotalSales from "@/components/dataSreening/salesData/getNowtotalSales";
import getSalesOrders from "@/components/dataSreening/salesData/getSalesOrders";
export default {
  name: "salesData",
  components: {
    Storeslastmonth,
    sellingoods,
    Newsalestrends,
    totalsales,
    getNowtotalSales,
    getSalesOrders,
  },
};
</script>

<style scoped>
.box-card-top {
  height: 260px;
  margin-bottom: 15px;
}

.box-card-bottom {
  height: calc(100vh - 400px);
}
</style>