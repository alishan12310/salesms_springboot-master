<template>
  <div ref="Trends" style="width: 100%; height: calc(100vh - 460px)"></div>
</template>

<script>
import * as echarts from "echarts/core";
import { GridComponent, TitleComponent } from "echarts/components";
import { LineChart } from "echarts/charts";
import { UniversalTransition } from "echarts/features";
import { CanvasRenderer } from "echarts/renderers";

echarts.use([
  GridComponent,
  LineChart,
  CanvasRenderer,
  UniversalTransition,
  TitleComponent,
]);
import { getSalesTrends } from "@/apis/dataSreening/employeeData";

export default {
  data() {
    return {
      day: [],
      value: [],
    };
  },
  mounted() {
    this.drawline();
  },
  methods: {
    drawline(key) {
      getSalesTrends(key)
        .then(({ data }) => {
          console.log(data);
          var chartDom = this.$refs.Trends;
          var myChart = echarts.init(chartDom);
          var option;

          const dateList = data.map(function (item) {
            return item[0];
          });
          const valueList = data.map(function (item) {
            return item[1];
          });
          option = {
          color: [
            "rgb(64, 158, 255)"
          ],
            title: {
              text: "员工新增销售额趋势",
              show: true,
            },
            xAxis: {
              type: "category",
              boundaryGap: false,
              data: dateList,
            },
            yAxis: {
              type: "value",
            },
            series: [
              {
                data: valueList,
                type: "line",
                areaStyle: {},
              },
            ],
          };

          option && myChart.setOption(option);
        })
        .catch(() => {});
    },
  },
};
</script>

<style>
</style>