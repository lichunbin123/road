<template>
  <div id="main">
    <div class="header">
      <h>应急管理与处置</h>
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
                  <span>风险隐患管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="1-1" @click="record">风险隐患记录</el-menu-item>
                  <el-menu-item index="1-2" @click="search">风险隐患查询</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title">
                  <i class="el-icon-s-custom"></i>
                  <span>应急资源管理</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="2-1" @click="material">物资装备管理</el-menu-item>
                  <el-menu-item index="2-2" @click="team">专业队伍管理</el-menu-item>
                  <el-menu-item index="2-3" @click="info">应急信息管理</el-menu-item>
                  <el-menu-item index="2-4" @click="plan">应急预案管理</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="3">
                <template slot="title">
                  <i class="el-icon-s-home"></i>
                  <span>应急辅助决策</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="3-1">应急辅助决策</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-submenu index="4">
                <template slot="title">
                  <i class="el-icon-s-home"></i>
                  <span>应急培训演练</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="4-1" >培训演练计划制定</el-menu-item>
                  <el-menu-item index="4-2" >过程记录</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
            </el-menu>
          </el-col>
        </el-row>
      </div>
      <div style="float: right; width: 85%">
        <MapManage v-if="toMap"></MapManage>
        <RiskMessageManage v-if="toRiskRecord"></RiskMessageManage>
        <RiskSearch v-if="toRiskSearch"></RiskSearch>
        <MaterialManage v-if="toMaterialManage"></MaterialManage>
        <TeamManage v-if="toTeam"></TeamManage>
        <InformationManage v-if="toInformation"></InformationManage>
        <PlanManage v-if="toPlan"></PlanManage>
      </div>
    </div>
  </div>

</template>
<script>
  import Cookies from 'js-cookie';
  import RiskMessageManage from './riskManage/RiskRecord';
  import RiskSearch from './riskManage/RiskSearch';
  import MaterialManage from './resourcesManage/MaterialManage';
  import TeamManage from './resourcesManage/TeamManage';
  import InformationManage from './resourcesManage/InformationManage';
  import PlanManage from './resourcesManage/PlanManage';
  import MapManage from './resourcesManage/MapManage';
  export default {
    name: "Main",
    components: { RiskMessageManage , RiskSearch, MaterialManage, TeamManage, InformationManage, PlanManage, MapManage},
    data () {
      return {
        toRiskRecord: false,
        toRiskSearch: false,
        toMaterialManage: false,
        toTeam: false,
        toInformation: false,
        toPlan: false,
        toMap: true,
        username: Cookies.get('username')
      }
    },
    mounted(){
      // this.initBmap();
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      initBmap(){
        // 百度地图API功能
        const map = new BMap.Map("Bmap");    // 创建Map实例
        // 创建点坐标
        const point = new BMap.Point(115.80633819273, 28.668198507504);

        // 初始化地图，设置中心点坐标和地图级别
        map.centerAndZoom(point, 15);
        map.setCurrentCity("南昌");
        map.enableScrollWheelZoom(true);
      },
      record(){
        this.toRiskRecord = true;
        this.toRiskSearch = false;
        this.toMaterialManage = false;
        this.toTeam = false;
        this.toInformation = false;
        this.toPlan = false;
        this.toMap = false;
      },
      search(){
        this.toRiskSearch = true;
        this.toRiskRecord = false;
        this.toMaterialManage = false;
        this.toTeam = false;
        this.toInformation = false;
        this.toPlan = false;
        this.toMap = false;
      },
      material(){
        this.toMaterialManage = true;
        this.toRiskRecord = false;
        this.toRiskSearch = false;
        this.toTeam = false;
        this.toInformation = false;
        this.toPlan = false;
        this.toMap = false;
      },
      team(){
        this.toTeam = true;
        this.toMaterialManage = false;
        this.toRiskRecord = false;
        this.toRiskSearch = false;
        this.toInformation = false;
        this.toPlan = false;
      },
      info(){
        this.toInformation = true;
        this.toTeam = false;
        this.toRiskSearch = false;
        this.toMaterialManage = false;
        this.toRiskRecord = false;
        this.toPlan = false;
        this.toMap = false;
      },
      plan(){
        this.toPlan = true;
        this.toInformation = false;
        this.toRiskSearch = false;
        this.toRiskRecord = false;
        this.toMaterialManage = false;
        this.toTeam = false;
        this.toMap = false;
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
    /*background-color: #545c64;*/
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
    margin-right: 5px;
  }
  .el-menu-item {
    margin-left: 30px;
  }
</style>
