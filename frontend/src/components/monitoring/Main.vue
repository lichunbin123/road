<template>
  <div id="main">
    <div class="header">
    <i class="el-icon-video-camera-solid"></i>
    <h >路网运行监测管理</h>
      <el-link :underline="false" style="float: right; margin-right: 30px; font-size: 20px"><router-link to="/main">系统首页</router-link></el-link>
    </div>
    <div>
      <el-menu
        :default-active="activeIndex2"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
        <el-menu-item index="1" @click="click">公路基础信息</el-menu-item>
        <el-menu-item index="2" @click="trafficclick()">交通流量监测</el-menu-item>
        <el-menu-item index="3">运行视频监控</el-menu-item>
        <el-menu-item index="4">移动监测管理</el-menu-item>
        <el-menu-item index="5">运行统计分析</el-menu-item>
        <el-menu-item index="6">技术监测与预警</el-menu-item>
        <el-submenu index="7">
          <template slot="title">外场设备管理</template>
          <el-menu-item index="7-1" >设备档案</el-menu-item>
          <el-menu-item index="7-2" >设备维修记录</el-menu-item>
          <el-menu-item index="7-3" >设备运行状态监控</el-menu-item>
        </el-submenu>
      </el-menu>
    </div>
    <div>
      <ChartViews v-if="cls"></ChartViews>
      <BaiduMap v-if="flag"></BaiduMap>
      <!--<div id="myChart" style="width: 600px;height:200px;"></div>-->
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import ChartViews from "./analysisSystem/ChartViews.vue";
  import BaiduMap from "./basicInfoManage/BaiduMap";
  export default {
    name: 'Main',
    components: { ChartViews,BaiduMap },
    data () {
      return {
        flag: false,
        cls: false,
      }
    },
    methods: {
      click() {
        this.flag = true;
        this.cls = false
        console.log("click is useful")
      },
      trafficclick: function(){
        this.cls = true;
        this.flag = false;
        const url = `http://localhost:8083/trafficMonitor`
        return axios.post(url).then(res => {
          console.log(res.status)
          console.log("发送trafficMonitor")
        }).catch(err => {
          console.log(err.response)
        })
      }
    }
  }
</script>
<style scoped>
  .header {
    background-color: #B3C0D1;
    color: #333;
    text-align: left;
    font-size: 30px;
    height: 75px;
    line-height: 75px;
  }
  .el-icon-video-camera-solid {
    margin-left: 10px;
  }
</style>
