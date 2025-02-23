<template>
  <div ref="day" style="width: 100%; height: 250px"></div>
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
import { getFormatDay } from "@/apis/dataSreening/employeeData";
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
      const date = new Date();
      const mouth = date.getMonth() - 1;
      var data = {
        id: mouth,
        userId: key,
      };
      getFormatDay(data)
        .then(({ data }) => {
          var chartDom = this.$refs.day;
          var myChart = echarts.init(chartDom);
          var option;
          option = {
            color:[
              "rgb(64, 158, 255)"
            ],
            title: {
              text: "员工上月销售额",
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
        })
        .catch(() => {});
    },
  },
};
</script>

<style>
</style>