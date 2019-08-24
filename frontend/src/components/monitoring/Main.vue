<template>
  <div id="main">
    <div class="header">
    <h >路网运行监测管理</h>
      <el-dropdown trigger="hover" v-if="username">
        <span style="margin-left:1670px; font-size:20px; color:#fff;">{{username}}<img style="width:40px; height:40px;border-radius: 20px; margin-left:10px; margin-top:10px;" src="/static/12.jpg" /></span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>我的消息</el-dropdown-item>
          <el-dropdown-item>设置</el-dropdown-item>
          <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-link v-else="username" style="margin-left:1600px; font-size:20px;"><router-link to="/">您当前未登录,点击登录!</router-link></el-link>
      <el-link :underline="false" style="float: right; font-size: 20px"><router-link to="/mainofuser">系统首页</router-link></el-link>
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
        <el-menu-item index="3" @click="tovideo()">运行视频监控</el-menu-item>
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
      <Video v-if="to"></Video>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import ChartViews from "./analysisSystem/ChartViews.vue";
  import BaiduMap from "./basicInfoManage/BaiduMap";
  import Video from "./videoMonitor/Video";
  import Cookies from 'js-cookie'
  export default {
    name: 'Main',
    components: { ChartViews,BaiduMap,Video },
    data () {
      return {
        flag: false,
        cls: false,
        to: false,
        username: Cookies.get('username'),
      }
    },
    methods: {
      click() {
        this.flag = true;
        this.cls = false;
        this.to = false;
        console.log("click is useful")
      },
      trafficclick: function(){
        this.cls = true;
        this.flag = false;
        this.to = false;
        const url = `http://localhost:8083/trafficMonitor`
        return axios.post(url).then(res => {
          console.log(res.status)
          console.log("发送trafficMonitor")
        }).catch(err => {
          console.log(err.response)
        })
      },
      tovideo: function () {
        this.to = true;
        this.flag = false;
        this.cls = false;
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
</style>
