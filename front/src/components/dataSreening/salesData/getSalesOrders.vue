<template>
  <div style="position: relative">
    <div ref="Orders" style=" width: 100%; height: 300px "></div>
  </div>
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
import { getSalesOrders } from "@/apis/dataSreening/salesData";
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
      getSalesOrders()
        .then(({ data }) => {
          var chartDom = this.$refs.Orders;
          var myChart = echarts.init(chartDom);
          var option;

          option = {
            title: {
              text: "上月各店铺单数",
              show: true,
              right: "0%",
            },
            legend: {
              show: true,
              left: "0",
              orient: "vertical",
              textStyle: {
                fontSize: "13px",
              },
            },
            tooltip: {
              trigger: "item",
            },
            series: [
              {
                name: "销售单数",
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