<template>
  <div>
  <div id="header">
    <h>路口实时监控视频</h>
  </div>
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
  <div id="body">
  <video id="myPlayer" width="600" height="400" controls playsinline webkit-playsinline autoplay>
    <source :src="rtmp_url"/>
  </video>
  </div>
  </div>
</template>
<script>
  export default {
    name: 'Video',
    data(){
      return{
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
        player:"",
        rtmp_url:"",
        http_url:"",
        message:'加载中...',
      }
    },
    mounted(){
      this.GetLiveUrl();
    },
    methods:{
      GetLiveUrl(){
        this.rtmp_url="rtmp://rtmp01open.ys7.com/openlive/f01018a141094b7fa138b9d0b856507b";
      }
    },
    updated() {
      if(this.rtmp_url!=""){
        //如果在mounted中声明，直播地址还未取到，导致视频不显示。所以放在了这里
        this.player = new EZUIPlayer('myPlayer');
      }
    }
  }
</script>
<style scoped>
  #header {
    background-color: gainsboro;
    color: #333;
    text-align: center;
    font-size: 20px;
    height: 50px;
    line-height: 50px;
  }
  #body {
    margin-top: 50px;
  }
</style>
