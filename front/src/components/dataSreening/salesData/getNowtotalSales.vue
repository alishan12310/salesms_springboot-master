<template>
  <dv-digital-flop :config="config" style="width:200px;height:50px;" />
</template>

<script>
import { getNowtotalSales } from "@/apis/dataSreening/salesData";

export default {
  data() {
    return {
      config: {
        number: [0],//初始数据
        content: '{nt} 元',
        style: {
          //这里可以修改默认样式
          fontSize: 35,//字体大小
          fill: '#333',//字体颜色
          // fontWeight:600,
        },
        formatter(number) {
          const numbers = number.toString().split('').reverse()
          const segs = []
          while (numbers.length) segs.push(numbers.splice(0, 3).join(''))
          return segs.join(',').split('').reverse().join('')
        }
      }
    };
  },
  created() {
  },
  mounted() {
    this.power();
  },
  methods: {
    async power() {
      let num = 0
      await getNowtotalSales()
        .then(({ data }) => {
          num = data;
        })
        .catch(() => { });
      this.config.number[0] = num
      this.config = { ...this.config };
    },
  },
};
</script>

<style scoped></style>