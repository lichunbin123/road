<template>
  <div id="showdata">
    <h style="font-size: 30px">各监测点交通流量情况</h>
    <div id="select">
      <el-select v-model="value" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </div>
    <div id="myChart">
      <el-row>
        <el-col :span="12"><div id="jxzgChart" style="width: 1000px;height:400px;margin-top: 100px;margin-left: 200px"></div></el-col>
        <el-col :span="12"><div id="jxsxChart" style="width: 600px;height:400px;margin-top: 100px"></div></el-col>
      </el-row>
      <el-row>
        <el-col :span="12"><div id="gflChart" style="width: 600px;height:400px;margin-top: 100px;margin-left: 200px"></div></el-col>
        <el-col :span="12"><div id="xjhxChart" style="width: 600px;height:400px;margin-top: 100px"></div></el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
  let echarts = require('echarts/lib/echarts');
  export default {
    name: 'ChartViews',
    data () {
      return {
        options: [{
          value: '进贤张公',
          label: '进贤张公'
        }, {
          value: '进贤双溪',
          label: '进贤双溪'
        }, {
          value: '南昌高坊岭',
          label: '南昌高坊岭'
        }, {
          value: '新建橫溪',
          label: '新建橫溪'
        }],
        value: '',
        received_messages: [],
        connected: false
      }
      },
    mounted(){
      // this.goeasy();
      // this.drawLine();
      this.drawCurve();
    },
    methods: {
      //建立websocket连接
      // connect() {
      //   var socket = new SockJS('http://localhost:8083/websocket');
      //   stompClient = Stomp.over(socket);
      //   stompClient.connect({}, function (frame) {
      //     console.log('Connected: ' + frame);
      //     stompClient.subscribe('http://localhost:8083/', function (greeting) {
      //     });
      //   });
      //   },
      // connect () {
      //   var ws = new WebSocket('ws://localhost:8083/websocket');
      //   ws.onopen = () => {
      //     // Web Socket 已连接上，使用 send() 方法发送数据
      //     console.log('数据发送中...')
      //     ws.send('Holle')
      //     console.log('数据发送完成')
      //   }
      //   ws.onmessage = evt => {
      //     console.log('数据已接收...')
      //   }
      //   ws.onclose = function () {
      //     // 关闭 websocket
      //     console.log('连接已关闭...')
      //   }
      //   // 路由跳转时结束websocket链接
      //   this.$router.afterEach(function () {
      //     ws.close()
      //   })
      // },
      //使用goeasy
      goeasy(){
        var goEasy = new GoEasy({
          appkey: 'BC-1fd590eb9aef46a6809486626b4199a2'
        });
        goEasy.subscribe({
          channel: 'trafficFlow',
          onMessage: function(message){
            console.log("接收到的消息:"+message.content)
            let trafficData = JSON.parse(message.content)
            console.log("接收到的GCSJ:"+trafficData.GCSJ)
          // alert('接收到消息:'+message.content);//拿到了信息之后，你可以做你任何想做的事
          }
        });
      },
      //echarts异步加载数据
      drawCurve(){

        let jxzgChart = this.$echarts.init(document.getElementById('jxzgChart'))
        var num = 0;
        var base = +new Date();

        var oneSeconds = 1000;
        var date = [];

        var data = [];
        var now = new Date(base - 9000);

        var goEasy = new GoEasy({
          appkey: 'BC-1fd590eb9aef46a6809486626b4199a2'
        });
        goEasy.subscribe({
          channel: 'trafficFlow',
          onMessage: function(message){
            console.log("接收到的消息:"+message.content)
            let trafficData = JSON.parse(message.content)
            num = trafficData.total;
            console.log("接收到的total:"+num)
          }
        });

        function addDate(shift){
          now = [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/')+"\n"+[now.getHours(),now.getMinutes(),now.getSeconds()].join(':');
          date.push(now);
          if (shift) {
            date.shift();
          }
          now = new Date(+new Date(now) + oneSeconds);

        }

        function add(shift){
          data.push(2);
          if(shift){
            data.shift();
          }
        }

        function addData(shift) {
          now = [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/')+"\n"+[now.getHours(),now.getMinutes(),now.getSeconds()].join(':');
          date.push(now);
          data.push(num);

          if (shift) {
            date.shift();
            data.shift();
          }

          now = new Date(+new Date(now) + oneSeconds);
        }

        for (var i = 1; i < 10; i++) {
          addDate();
          add();
        }

        // option = {
        //   xAxis: {
        //     type: 'category',
        //     boundaryGap: false,
        //     data: date
        //   },
        //   yAxis: {
        //     boundaryGap: [0, '50%'],
        //     type: 'value'
        //   },
        //   series: [
        //     {
        //       name:'成交',
        //       type:'line',
        //       smooth:true,
        //       symbol: 'none',
        //       stack: 'a',
        //       areaStyle: {
        //         normal: {}
        //       },
        //       data: data
        //     }
        //   ]
        // };

        setInterval(function () {
          addData(true);
          jxzgChart.setOption({
            title: { text: '进贤张公' },
            tooltip: {},
            xAxis: {
              type: 'category',
              boundaryGap: false,
              data: date
            },
            yAxis: {
              boundaryGap: [0, '50%'],
              type: 'value'
            },
            series: [{
              name:'成交',
              type:'line',
              smooth:true,
              symbol: 'none',
              stack: 'a',
              areaStyle: {
                normal: {}
              },
              data: data
            }]
          });
        }, 5000);

      },
      drawLine(){
        // 基于准备好的dom，初始化echarts实例
        // let jxzgChart = this.$echarts.init(document.getElementById('jxzgChart'))
        let jxsxChart = this.$echarts.init(document.getElementById('jxsxChart'))
        let gflChart = this.$echarts.init(document.getElementById('gflChart'))
        let xjhxChart = this.$echarts.init(document.getElementById('xjhxChart'))
        // 绘制图表
        jxzgChart.setOption({
          title: { text: '进贤张公' },
          tooltip: {},
          xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
          },
          yAxis: {},
          series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
          }]
        });

        jxsxChart.setOption({
          title: { text: '进贤双溪' },
          tooltip: {},
          xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
          },
          yAxis: {},
          series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
          }]
        });

        gflChart.setOption({
          title: { text: '南昌高坊岭' },
          tooltip: {},
          xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
          },
          yAxis: {},
          series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
          }]
        });

        xjhxChart.setOption({
          title: { text: '新建横溪' },
          tooltip: {},
          xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
          },
          yAxis: {},
          series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
          }]
        });

      }
    }
  }
</script>
<style scoped>
</style>
