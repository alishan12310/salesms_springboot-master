<template>
  <div ref="goods" style="width: 100%; height: 240px"></div>
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
import { Hotsellinggoods } from "@/apis/dataSreening/salesData";
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
      Hotsellinggoods()
        .then(({ data }) => {
          var chartDom = this.$refs.goods;
          var myChart = echarts.init(chartDom);
          var option;

          option = {
            title: {
              text: "热销货物",
            },
            legend: {
              type: "scroll",
              bottom: 0,
            },
            legend: {
              type: 'scroll',
              bottom: 10,
            },
            tooltip: {
              trigger: "item",
            },
            series: [
              {
                name: "销售单数",
                type: "pie",
                radius: ["20%", "65%"],
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