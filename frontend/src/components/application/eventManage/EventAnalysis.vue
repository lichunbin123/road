<template>
  <div id="EventAnalysis" style="margin-top:50px">
    <el-row>
      <el-col :span="6" style="width: 40%">
        <div id="showAll" class="showAll">
          <div id="select" class="select">
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
          <div id="myChart" style="width: 800px;height:600px;margin-top: 200px">

          </div>
        </div>
      </el-col>
      <el-col :span="6" style="width: 60%">
        <div id="showEvery" class="showEvery">
          <div id="selectforevery">
            <span class="demonstration">年</span>
            <el-date-picker
              v-model="value3"
              type="year"
              value-format="yyyy"
              placeholder="选择年">
            </el-date-picker>
            <el-button type="success" icon="el-icon-search" @click="searchforevery">搜索</el-button>
          </div>
          <div id="chartForEvery" class="chartForEvery" style="width: 1000px;height:600px;margin-top: 200px;margin-left: 70px">

          </div>
        </div>
      </el-col>
    </el-row>
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
        value2: '',
        value3: '',
        data: [],
      }
    },
    mounted() {
      this.searchfordefault();
      this.searchforeverydefault();
    },
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
      },
      searchforeverydefault(){
        var date = new Date();
        var year = date.getFullYear();
        console.log(year);
        return axios.post("http://localhost:8083/application/eventanalysis/searchforeverydefault/" + year).then(res => {
          if (res.status == 200){
            var result = res.data;
            var Data = result.responseData;
            var JSONData = JSON.stringify(Data);
            var PARSEData = JSON.parse(JSONData);
            this.data = PARSEData;
            this.echartforevery();
          }
        }).catch(err => {
          console.log(err.response)
          this.$message("统计失败")
        })
      },
      echartforevery:function () {
        let ChartForEvery = this.$echarts.init(document.getElementById('chartForEvery'));
        var data = this.data;
        ChartForEvery.setOption({
          title: {
          text: '交通流量',
            subtext: '默认'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data:['咨询','投诉','反馈','事故']
        },
        toolbox: {
          show: true,
            feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            dataView: {readOnly: false},
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
          }
        },
        xAxis:  {
          type: 'category',
            boundaryGap: false,
            data: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
        },
        yAxis: {
          type: 'value',
            axisLabel: {
            formatter: '{value}'
          }
        },
        series: [
          {
            name:'咨询',
            type:'line',
            data:[data[0].eventtwo, data[1].eventtwo, data[2].eventtwo, data[3].eventtwo, data[4].eventtwo, data[5].eventtwo, data[6].eventtwo, data[7].eventtwo, data[8].eventtwo, data[9].eventtwo, data[10].eventtwo, data[11].eventtwo],
            markPoint: {
              data: [
                {type: 'max', name: '最大值'},
                {type: 'min', name: '最小值'}
              ]
            },
            markLine: {
              data: [
                {type: 'average', name: '平均值'}
              ]
            }
          },
          {
            name:'投诉',
            type:'line',
            data:[data[0].eventthree, data[1].eventthree, data[2].eventthree, data[3].eventthree, data[4].eventthree, data[5].eventthree, data[6].eventthree, data[7].eventthree, data[8].eventthree, data[9].eventthree, data[10].eventthree, data[11].eventthree],
            markPoint: {
              data: [
                {type: 'max', name: '最大值'},
                {type: 'min', name: '最小值'}
              ]
            },
            markLine: {
              data: [
                {type: 'average', name: '平均值'}
              ]
            }
          },
          {
            name:'反馈',
            type:'line',
            data:[data[0].eventfour, data[1].eventfour, data[2].eventfour, data[3].eventfour, data[4].eventfour, data[5].eventfour, data[6].eventfour, data[7].eventfour, data[8].eventfour, data[9].eventfour, data[10].eventfour, data[11].eventfour],
            markPoint: {
              data: [
                {type: 'max', name: '最大值'},
                {type: 'min', name: '最小值'}
              ]
            },
            markLine: {
              data: [
                {type: 'average', name: '平均值'}
              ]
            }
          },
          {
            name:'事故',
            type:'line',
            data:[data[0].eventone, data[1].eventone, data[2].eventone, data[3].eventone, data[4].eventone, data[5].eventone, data[6].eventone, data[7].eventone, data[8].eventone, data[9].eventone, data[10].eventone, data[11].eventone],
            markPoint: {
              data: [
                {type: 'max', name: '最大值'},
                {type: 'min', name: '最小值'}
              ]
            },
            markLine: {
              data: [
                {type: 'average', name: '平均值'},
              ]
            }
          }
        ]})
      },
      searchforevery(){
        var year = this.value3;
        return axios.post("http://localhost:8083/application/eventanalysis/searchforevery/" + year).then(res => {
          if (res.status == 200){
            var result = res.data;
            var Data = result.responseData;
            var JSONData = JSON.stringify(Data);
            var PARSEData = JSON.parse(JSONData);
            this.data = PARSEData;
            console.log("PARSEData==="+PARSEData[0].eventone);
            this.echartforevery();
          }
        }).catch(err => {
          console.log(err.response)
          this.$message("统计失败")
        })

      }
    }
  }
</script>
<style scoped>
  /*.showAll{*/
    /*float: right;*/
    /*width: 50%;*/
  /*}*/
  /*.showEvery{*/
    /*float: left;*/
    /*width: 50%;*/
  /*}*/
</style>
