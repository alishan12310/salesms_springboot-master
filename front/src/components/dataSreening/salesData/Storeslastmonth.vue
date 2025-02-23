<template>
  <div ref="store" style=" width: 100%; height: 300px"></div>
</template>

<script>
import * as echarts from "echarts/core";
import {
  TooltipComponent,
  LegendComponent,
  TitleComponent,
} from "echarts/components";
import { PieChart } from "echarts/charts";
import { LabelLayout } from "echarts/features";
import { CanvasRenderer } from "echarts/renderers";
import { Storeslastmonth } from "@/apis/dataSreening/salesData";
echarts.use([
  TooltipComponent,
  LegendComponent,
  PieChart,
  CanvasRenderer,
  LabelLayout,
  TitleComponent,
]);

export default {
  data() {
    return {};
  },
  mounted() {
    this.drawpie();
  },
  methods: {
    drawpie() {
      Storeslastmonth()
        .then(({ data }) => {
          var chartDom = this.$refs.store;
          var myChart = echarts.init(chartDom);
          var option;

          option = {
            title: {
              text: "上月店铺收入",
              show: true,
              right: "5%",
            },
            legend: {
              show: true,
              left:'0',
              orient:'vertical',
              textStyle:{
                fontSize: "13px",
              }
            },
            tooltip: {
              trigger: "item",
            },
            series: [
              {
                name: "销售额",
                type: "pie",
                left: "50%",
                radius: ["30%", "95%"],
                avoidLabelOverlap: false,
                itemStyle: {
                  borderRadius: 10,
                  borderColor: "#fff",
                  borderWidth: 2,
                },
                label: {
                  show: false,
                  position: "center",
                },
                labelLine: {
                  show: false,
                },
                data,
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