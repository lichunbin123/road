<template>
  <div id="EventAnalysis" style="margin-top:50px">
    <div>
    <span class="demonstration">月份</span>
    <el-date-picker
      v-model="value1"
      type="month"
      value-format="MM"
      placeholder="选择月">

    </el-date-picker>
      <el-button type="success" icon="el-icon-search" @click="searchformonth">搜索</el-button>
      <span class="demonstration">年</span>
      <el-date-picker
        v-model="value2"
        type="year"
        value-format="yyyy"
        placeholder="选择年">
      </el-date-picker>
      <el-button type="success" icon="el-icon-search" @click="searchforyear">搜索</el-button>
    </div>
    <div id="myChart" style="width: 1000px;height:600px;margin-top: 200px;margin-left: 450px">

    </div>
  </div>
</template>
<script>
  import axios from 'axios'
  import Cookies from 'js-cookie'
  let echarts = require('echarts/lib/echarts');
  export default {
    name: 'EventAnalysis',
    data() {
      return {
        event1: '',
        event2: '',
        event3: '',
        event4: '',
        value1: '',
        value2: ''
      }
    },
    created() {
      this.searchfordefault();
    },
    // mounted() {
    //   this.echartanalysis();
    // },
    methods: {
      echartanalysis(){
        var eventone = this.event1;
        var eventtwo = this.event2;
        var eventthree = this.event3;
        var eventfour = this.event4;
        console.log("eventone==="+eventone);
        console.log(eventtwo);
        console.log(eventthree);
        console.log(eventfour);
        let Chart = this.$echarts.init(document.getElementById('myChart'));
        Chart.setOption({
          title: { text: '事件分析图' },
          tooltip: {},
          xAxis: {
            data: ["咨询","投诉","反馈","事故"]
          },
          yAxis: {},
          series: [{
            name: '数量',
            type: 'bar',
            data: [eventtwo, eventthree, eventfour, eventone]
          }]
        });
      },
      searchformonth(){
        var month = this.value1;
        return axios.post("http://localhost:8083/application/eventanalysis/searchformonth/" + month).then(res => {
          if (res.status == 200){
            var result = res.data;
            var Data = result.responseData[0];
            var JSONData = JSON.stringify(Data);
            var PARSEData = JSON.parse(JSONData);
            this.event1 = PARSEData.eventone;
            this.event2 = PARSEData.eventtwo;
            this.event3 = PARSEData.eventthree;
            this.event4 = PARSEData.eventfour;
            this.echartanalysis();
          }
        }).catch(err => {
          console.log(err.response)
          this.$message("统计失败")
        })
      },
      searchforyear(){
        var year = this.value2;
        return axios.post("http://localhost:8083/application/eventanalysis/searchforyear/" + year).then(res => {
          if (res.status == 200){
            var result = res.data;
            var Data = result.responseData[0];
            var JSONData = JSON.stringify(Data);
            var PARSEData = JSON.parse(JSONData);
            this.event1 = PARSEData.eventone;
            this.event2 = PARSEData.eventtwo;
            this.event3 = PARSEData.eventthree;
            this.event4 = PARSEData.eventfour;
            this.echartanalysis();
          }
        }).catch(err => {
          console.log(err.response)
          this.$message("统计失败")
        })
      },
      searchfordefault(){
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var now = year+"-0"+month;
        console.log(now);
        return axios.post("http://localhost:8083/application/eventanalysis/searchfordefault/" + now).then(res => {
          if (res.status == 200){
            var result = res.data;
            var Data = result.responseData[0];
            var JSONData = JSON.stringify(Data);
            var PARSEData = JSON.parse(JSONData);
            console.log(PARSEData.eventone);
            this.event1 = PARSEData.eventone;
            this.event2 = PARSEData.eventtwo;
            this.event3 = PARSEData.eventthree;
            this.event4 = PARSEData.eventfour;
            this.echartanalysis();
          }
        }).catch(err => {
          console.log(err.response)
          this.$message("统计失败")
        })
      }
    }
  }
</script>
