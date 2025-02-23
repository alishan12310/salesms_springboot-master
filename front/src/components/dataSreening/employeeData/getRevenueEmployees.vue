<template>
  <div ref="Employees" style="width: 100%; height:calc(100vh - 500px)"></div>
</template>

<script>
import * as echarts from "echarts/core";
import {
  TooltipComponent,
  GridComponent,
  TitleComponent,
} from "echarts/components";
import { BarChart } from "echarts/charts";
import { CanvasRenderer } from "echarts/renderers";
import {
  getRevenueEmployees
} from "@/apis/dataSreening/employeeData";
echarts.use([
  TooltipComponent,
  GridComponent,
  BarChart,
  CanvasRenderer,
  TitleComponent,
]);

export default {
  mounted() {
    this.drawbar();
  },
  methods: {
    drawbar(key) {
      getRevenueEmployees(key).then(({ data }) => {
        var chartDom = this.$refs.Employees;
        var myChart = echarts.init(chartDom);
        var option;
        option = {
          color: [
            "rgb(64, 158, 255)"
          ],
          title: {
            text: "员工年销售总额",
            show: true,
          },
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "shadow",
            },
          },
          grid: {
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true,
          },
          xAxis: [
            {
              type: "category",
              data: data.day,
              axisTick: {
                alignWithLabel: true,
              },
            },
          ],
          yAxis: [
            {
              type: "value",
            },
          ],
          series: [
            {
              name: "Direct",
              type: "bar",
              barWidth: "60%",
              data: data.value,
            },
          ],
        };

        option && myChart.setOption(option);
      }).catch(() => {

      })

    },
  },
};
</script>

<style></style>