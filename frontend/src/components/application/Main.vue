<template>
  <div id="main">
    <div class="header">
      <h>平台应用管理</h>
      <!--<el-badge is-dot class="item" v-if="username" style="margin-left:1700px; font-size: 20px;">{{ username }}</el-badge>-->
        <el-dropdown trigger="hover" v-if="username">
          <span style="margin-left:1670px; font-size:20px; color:#fff;">{{username}}<img style="width:40px; height:40px;border-radius: 20px; margin-left:10px; margin-top:10px; margin-bottom" src="/static/12.jpg" /></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>我的消息</el-dropdown-item>
            <el-dropdown-item>设置</el-dropdown-item>
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      <el-link v-else="username" style="margin-left:1600px; font-size: 20px;"><router-link to="/">您当前未登录,点击登录!</router-link></el-link>
      <el-link :underline="false" style="float: right; margin-right: 30px; font-size: 20px"><router-link to="/main">系统首页</router-link></el-link>
    </div>
    <div class="medium">
      <div class="menu" style="float: left; width: 15%">
        <el-row >
        <el-col :span="20">
          <el-menu    default-active="2"
                      class="el-menu-vertical-demo"
                      @open="handleOpen"
                      @close="handleClose"
                      background-color="#545c64"
                      text-color="#fff"
                      active-text-color="#ffd04b">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-s-platform"></i>
                <span>系统管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="1-1" @click="click">用户权限管理</el-menu-item>
                <el-menu-item index="1-2">基础数据管理</el-menu-item>
                <el-menu-item index="1-3">基础参数设置</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-s-custom"></i>
                <span>值守排班管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="2-1" @click="eventmanage">值守管理</el-menu-item>
                <el-menu-item index="2-2" @click="clsclick">排班管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-s-home"></i>
                <span>公路事件管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="3-1" @click="roadeventmanage">公路事件管理</el-menu-item>
                <el-menu-item index="3-2" @click="eventanalysis">事件统计分析</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-col>
        </el-row>
      </div>
      <div style="float: right; width: 85%">
        <UserList v-if="flag"></UserList>
        <ClassManage v-if="cls"></ClassManage>
        <EventManage v-if="toevent"></EventManage>
        <RoadEventManage v-if="toroadevent"></RoadEventManage>
        <EventAnalysis v-if="toanalysis"></EventAnalysis>
      </div>
    </div>
  </div>

</template>
<script>
  import Cookies from 'js-cookie';
  import UserList from "./systemManage/UserList.vue";
  import ChartViews from "../monitoring/analysisSystem/ChartViews.vue";
  import ClassManage from "./schedulingManage/ClassManage.vue";
  import EventManage from "./schedulingManage/EventManage";
  import RoadEventManage from "./eventManage/RoadEventManage";
  import EventAnalysis from "./eventManage/EventAnalysis";
  export default {
    name: "Main",
    components: { UserList, ChartViews, ClassManage, EventManage, RoadEventManage, EventAnalysis },
    data () {
      return {
        flag: false,
        cls: false,
        toevent: false,
        toroadevent: false,
        toanalysis: false,
        username: Cookies.get('username')
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      logout: function() {
        console.log("正在退出");
        delete localStorage.accessToken;
        Cookies.remove('username');
        this.$message({
          message: '正在退出登录',
          type: 'warning'
        });
        this.$router.push('/');
      },
      click() {
        this.flag = true;
        this.cls = false;
        this.toevent = false;
        this.toroadevent = false;
        this.toanalysis = false;
        console.log("click is useful")
      },
      clsclick() {
        this.cls = true;
        this.flag = false;
        this.toevent = false;
        this.toroadevent = false;
        this.toanalysis = false;
      },
      eventmanage() {
        this.toevent = true;
        this.cls = false;
        this.flag = false;
        this.toroadevent = false;
        this.toanalysis = false;
      },
      roadeventmanage() {
        this.toroadevent = true;
        this.cls = false;
        this.flag = false;
        this.toevent = false;
        this.toanalysis = false;
      },
      eventanalysis() {
        this.toanalysis = true;
        this.cls = false;
        this.flag = false;
        this.toevent = false;
        this.toroadevent = false;
      }
    }
  }
</script>

<style scoped>
  .header {
    background-color:  #B3C0D1;
    color: #333;
    text-align: left;
    font-size: 30px;
    height: 75px;
    line-height: 75px;
  }
  .medium {
  }
  .menu {
  }
  .el-col {
  }
  .el-row {
  }
  .el-menu {
  }
  .el-menu-vertical-demo {
    height: 930px;
  }
  .el-icon-s-platform {
    margin-right: 30px;
  }
  .el-menu-item {
    margin-left: 30px;
  }
</style>
