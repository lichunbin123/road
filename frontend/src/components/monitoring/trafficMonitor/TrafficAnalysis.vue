<template>
  <div id="TrafficAnalysis">
    <el-row>
      <el-col :span="6" style="width: 60%">
        <div id="select" class="select">
            <div id="selectplace" class="selectplace">
              <span class="road">选择路段</span>
              <el-select v-model="value" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </div>

            <div id="selectdate" class="selectdate">
              <span class="year">年</span>
              <el-date-picker
                v-model="value2"
                type="year"
                value-format="yyyy"
                placeholder="选择年">
              </el-date-picker>
              <el-button type="success" icon="el-icon-search" @click="searchforyear">查看年流量</el-button>
              <span class="month">月份</span>
              <el-date-picker
                v-model="value1"
                type="month"
                value-format="MM"
                placeholder="选择月">
              </el-date-picker>
              <el-button type="success" icon="el-icon-search" @click="searchformonth">查看月流量</el-button>
              <span class="day">日</span>
              <el-date-picker
                v-model="value3"
                type="date"
                value-format="yyy-MM-dd"
                placeholder="选择日">
              </el-date-picker>
              <el-button type="success" icon="el-icon-search" @click="searchforday">查看日流量</el-button>
            </div>
        </div>

        <div id="show" class="show">
          <el-table
            :data="tableData"
            border
            style="width: 100%">
            <el-table-column
              prop="bq1"
              label="中小客车">
            </el-table-column>
            <el-table-column
              prop="bq2"
              label="小型货车">
            </el-table-column>
            <el-table-column
              prop="bq3"
              label="大客车">
            </el-table-column>
            <el-table-column
              prop="bq4"
              label="中型货车">
            </el-table-column>
            <el-table-column
              prop="bq5"
              label="大型货车">
            </el-table-column>
            <el-table-column
              prop="bq6"
              label="特大型货车">
            </el-table-column>
            <el-table-column
              prop="bq7"
              label="集装箱车">
            </el-table-column>
            <el-table-column
              prop="mtc"
              label="摩托车">
            </el-table-column>
            <el-table-column
              prop="tlj"
              label="拖拉机">
            </el-table-column>
          </el-table>
        </div>
      </el-col>
      <el-col :span="6" style="width: 40%">
        <div id="pie" class="pie" style="width: 1000px;height:400px;margin-top: 100px;margin-left: 100px">
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  let echarts = require('echarts/lib/echarts');
  export default {
    name: 'TrafficAnalysis',
    data() {
      return {
        tableData: [],
        value1: '',
        value2: '',
        value3: '',
      }
    },
    mounted() {
      this.showChart();
    },
    methods: {
      searchforyear() {

      },
      searchformonth() {

      },
      searchforday() {

      },
      showChart:function () {
        let pie = this.$echarts.init(document.getElementById('pie'));
        console.log(pie)
        pie.setOption({
          title : {
            text: '路段交通流量',
            subtext: '默认为总流量',
            x:'center'
          },
          tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: ['中小客车','大客车','小型货车','中型货车','大型货车','特大型货车','集装箱车','摩托车','拖拉机']
          },
          series : [
            {
              name: '访问来源',
              type: 'pie',
              radius : '55%',
              center: ['50%', '60%'],
              data:[
                {value:335, name:'中小客车'},
                {value:310, name:'大客车'},
                {value:234, name:'小型货车'},
                {value:135, name:'中型货车'},
                {value:548, name:'大型货车'},
                {value:48, name:'特大型货车'},
                {value:68, name:'集装箱车'},
                {value:248, name:'摩托车'},
                {value:70, name:'拖拉机'},
              ],
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        })
      },

    }
  }
</script>
<style scoped>
  .select{
    margin-top: 30px;
  }
  .selectplace {
    margin-top: 20px;
    margin-right: 980px;
  }
  .selectdate{
    margin-top: 20px;
  }
  .show{
    margin-top: 50px;
  }
  .road{
    font-weight: bold;
  }
  .year{
    font-weight: bold;
  }
  .month{
    font-weight: bold;
  }
  .day{
    font-weight: bold;
  }
  .el-col {
    border-radius: 4px;
  }
  .pie{
    border-radius: 4px;
  }
</style>
