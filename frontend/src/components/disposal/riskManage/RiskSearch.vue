<template>
  <div id="main">
    <div id="search" class="search">
      <el-input
        placeholder="请输入关键字"
        v-model="input"
        style="width: 180px"
        clearable>
      </el-input>
      <el-button type="success" icon="el-icon-search" @click="search">搜索</el-button>
    </div>

    <div id="body" class="body">
      <el-row>
        <el-col :span="6" style="width: 60%">
          <div id="header" class="header">
            <h style="">风险隐患点记录--表格</h>
          </div>
          <div id="result" class="ressult">
            <el-table
              :data="tableData"
              border
              style="width: 100%">
              <el-table-column
                prop="name"
                label="风险隐患点"
                width="180">
              </el-table-column>
              <el-table-column
                prop="type"
                label="隐患点类型"
                width="180">
              </el-table-column>
              <el-table-column
                prop="riskfactors"
                label="主要风险因素">
              </el-table-column>
              <el-table-column
                prop="riskevent"
                label="风险事件">
              </el-table-column>
              <el-table-column
                prop="eventdate"
                label="风险发生时间"
                width="180">
              </el-table-column>
            </el-table>
          </div>
        </el-col>
        <el-col :span="6" style="width: 40%">
          <div id="chartheader" class="chartheader">
            <h style="">风险事件图表分析</h>
          </div>
          <div id="analysis" class="analysis">
            <div id="app">
              <div id="he-plugin-standard"></div>
            </div>
            <div id="chart" class="chart">
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'
  let echarts = require('echarts/lib/echarts');
  export default {
    name: "RiskSearch",
    components: {  },
    data() {
      return{
        tableData: [],
        input: '',
        settings: '',
        month1: 0,
        month2: 0,
        month3: 0,
        month4: 0,
        month5: 0,
        month6: 0,
        month7: 0,
        month8: 0,
        month9: 0,
        month10: 0,
        month11: 0,
        month12: 0
      }
    },
    mounted(){
      this.getchart();
      let settings = '<script> const WIDGET = { CONFIG: { "layout": 1, "width": 450, "height": 150, "background": 1, "dataColor": "FFFFFF", "borderRadius": 5, "key": "51d667a6557a4482bcbeefdf7ee093b6" } } <\/script>';
      $(settings).appendTo(document.body);
      $('<script src="https://widget.heweather.net/standard/static/js/he-standard-common.js?v=1.1" type="text/javascript"><\/script>').appendTo(document.body);
    },
    methods: {
      getchart(){
        let chart = this.$echarts.init(document.getElementById('chart'));
        console.log("chart==="+chart);
        chart.setOption({
          title: {
            text: '风险事件分析',
              subtext: '默认:今年的数据分布'
          },
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data:['风险事件']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              name:'风险事件',
              type:'line',
              stack: '总量',
              data:[this.month1, this.month2, this.month3, this.month4, this.month5, this.month6, this.month7, this.month8, this.month9, this.month10, this.month11, this.month12]
            }
          ]
          });
      },
      search(){
        var input = this.input;
        console.log("input==="+input)
        return axios.post("http://localhost:8083/disposal/riskmanage/search/" + input).then(res => {
          if (res.status == 200){
            var result = res.data;
            var Data = result.responseData;
            console.log("Data[0].eventdate==="+Data[0].eventdate);
            this.tableData = result.responseData;
            for (var i=0; i < Data.length; i++){
              if (Data[i].riskevent!= ""){
                var month = Data[i].eventdate.substring(5,7);
                console.log("month==="+month);
                if (month == "01")
                  this.month1++;
                if (month == "02")
                  this.month2++;
                if (month == "03")
                  this.month3++;
                if (month == "04")
                  this.month4++;
                if (month == "05")
                  this.month5++;
                if (month == "06")
                  this.month6++;
                if (month == "07")
                  this.month7++;
                if (month == "08")
                  this.month8++;
                if (month == "09")
                  this.month9++;
                  console.log("month9==="+this.month9);
                if (month == "10")
                  this.month10++;
                if (month == "11")
                  this.month11++;
                if (month == "12")
                  this.month12++;
              }else {
                if (month == "01")
                  this.month1 = this.month1+0;
                if (month == "02")
                  this.month2 = this.month2+0;
                if (month == "03")
                  this.month3 = this.month3+0;
                if (month == "04")
                  this.month4 = this.month4+0;
                if (month == "05")
                  this.month5 = this.month5+0;
                if (month == "06")
                  this.month6 = this.month6+0;
                if (month == "07")
                  this.month7 = this.month7+0;
                if (month == "08")
                  this.month8 = this.month8+0;
                if (month == "09")
                  this.month9 = this.month9+0;
                if (month == "10")
                  this.month10 = this.month10+0;
                if (month == "11")
                  this.month11 = this.month11+0;
                if (month == "12")
                  this.month12 = this.month12+0;
              }
            }
            this.getchart();
            this.$message("查询记录信息成功");
          }
        }).catch(err => {
          console.log(err.response)
          this.$message("统计失败")
        })
      },
    }
  }
</script>
<style scoped>
  .search{
    margin-top: 20px;
    text-align: left;
  }
  .divider{
    width: 100%;
    height: 2px;
    margin-top: 20px;
    background-color: #b3e19d;
  }
  .header{
    color: #333;
    text-align: left;
    font-size: 20px;
    height: 40px;
    line-height: 40px;
    background-color:  #B3C0D1;
  }
  .chartheader{
    color: #333;
    text-align: left;
    font-size: 20px;
    height: 40px;
    line-height: 40px;
    background-color:  #B3C0D1;
    margin-left: 20px;
  }
  .body{
    margin-top: 20px;
  }
  .result{
    /*float: left;*/

  }
  .analysis{
    /*float: right;*/
  }
  .chart{
    margin-left: 50px;
    width: 600px;
    height: 400px;
    margin-top: 40px;
  }
  .wearther{
    margin-left: 30px;
  }
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 30px;
    margin-left: 100px;
  }
</style>
