<template>
  <div ref="total" style="width: 105%; height:  calc(100vh - 380px)"></div>
  
</template>

<script>
import * as echarts from "echarts/core";
import {
  TitleComponent,
  GridComponent,
  DataZoomComponent,
} from "echarts/components";
import { BarChart } from "echarts/charts";
import { CanvasRenderer } from "echarts/renderers";
import { getNowAlltotalSales } from "@/apis/dataSreening/salesData";

echarts.use([
  TitleComponent,
  GridComponent,
  DataZoomComponent,
  BarChart,
  CanvasRenderer,
]);
export default {
  mounted() {
    this.drawtotal();
  },
  methods: {
   async  drawtotal() {
      var chartDom = this.$refs.total;
      var myChart = echarts.init(chartDom);
      var option;
     await getNowAlltotalSales()
        .then((res) => {
 // prettier-ignore
      let dataAxis = res.data.day
      // prettier-ignore
      let data = res.data.value
      let yMax = 500;
      let dataShadow = [];
      for (let i = 0; i < data.length; i++) {
        dataShadow.push(yMax);
      }
      option = {
        title: {
          text: "年销售总额统计（ 滚轮/点击缩放）",
          // top:20,
          // left:"35%"
        },
        xAxis: {
          data: dataAxis,
          axisLabel: {
            inside: true,
            color: "#fff",
          },
          axisTick: {
            show: false,
          },
          axisLine: {
            show: false,
          },
          z: 10,
        },
        yAxis: {
          axisLine: {
            show: false,
          },
          axisTick: {
            show: false,
          },
          axisLabel: {
            color: "#999",
          },
        },
        dataZoom: [
          {
            type: "inside",
          },
        ],
        series: [
          {
            type: "bar",
            showBackground: true,
            itemStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                { offset: 0, color: "#83bff6" },
                { offset: 0.5, color: "#188df0" },
                { offset: 1, color: "#188df0" },
              ]),
            },
            emphasis: {
              itemStyle: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  { offset: 0, color: "#2378f7" },
                  { offset: 0.7, color: "#2378f7" },
                  { offset: 1, color: "#83bff6" },
                ]),
              },
            },
            data: data,
          },
        ],
      };
      // Enable data zoom when user click bar.
      const zoomSize = 6;
      myChart.on("click", function (params) {
        myChart.dispatchAction({
          type: "dataZoom",
          startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
          endValue:
            dataAxis[
              Math.min(params.dataIndex + zoomSize / 2, data.length - 1)
            ],
        });
      });

      myChart.setOption(option);
        })
        .catch(() => {});
     
    },
  },
};
</script>

<style>
</style>