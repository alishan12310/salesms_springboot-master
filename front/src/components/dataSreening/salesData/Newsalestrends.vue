<template>
  <div ref="strends" style=" width: 105%; height: calc(100vh - 390px) "></div>
</template>

<script>
import * as echarts from "echarts/core";
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  VisualMapComponent,
} from "echarts/components";
import { LineChart } from "echarts/charts";
import { UniversalTransition } from "echarts/features";
import { CanvasRenderer } from "echarts/renderers";
import { Newsalestrends } from "@/apis/dataSreening/salesData";

echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  VisualMapComponent,
  LineChart,
  CanvasRenderer,
  UniversalTransition,
]);

export default {
  mounted() {
    this.drawcontinuous();
  },
  methods: {
    drawcontinuous() {
      var chartDom = this.$refs.strends;
      var myChart = echarts.init(chartDom);
      var option;
      Newsalestrends()
        .then((res) => {
          // prettier-ignore
          const data = res.data;
          const dateList = data.map(function (item) {
            return item[0];
          });
          const valueList = data.map(function (item) {
            return item[1];
          });
          option = {
            // Make gradient line here
            visualMap: [
              {
                show: false,
                type: "continuous",
                seriesIndex: 0,
                min: 0,
                max: 400,
              },
            ],
            title: [
              {
                text: "新增销售趋势",
              },
            ],
            tooltip: {
              trigger: "axis",
            },
            xAxis: [
              {
                data: dateList,
              },
            ],
            yAxis: [{}],
            series: [
              {
                type: "line",
                showSymbol: false,
                data: valueList,
              },
            ],
          };

         myChart.setOption(option);
        })
        .catch(() => {});
    },
  },
};
</script>

<style>
</style>