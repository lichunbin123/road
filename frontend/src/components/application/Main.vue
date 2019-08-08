<template>
  <div id="main">
    <div class="header">
      <i class="el-icon-s-tools"></i>
      <h>平台应用管理</h>
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
                      active-text-color="#ffd04b"
                      height="600px">
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
                <el-menu-item index="2-1">值守管理</el-menu-item>
                <el-menu-item index="2-2" @click="clsclick">排班管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-s-home"></i>
                <span>公路事件管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="3-1">公路事件管理</el-menu-item>
                <el-menu-item index="3-2">事件统计分析</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-col>
        </el-row>
      </div>
      <div style="float: right; width: 85%">
        <UserList v-if="flag"></UserList>
        <ClassManage v-if="cls"></ClassManage>
      </div>
    </div>
  </div>

</template>
<script>
  import UserList from "./systemManage/UserList.vue";
  import ChartViews from "../monitoring/analysisSystem/ChartViews.vue";
  import ClassManage from "./schedulingManage/ClassManage.vue"
  export default {
    name: "Main",
    components: { UserList, ChartViews, ClassManage },
    data () {
      return {
        flag: false,
        cls: false,
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      click() {
        this.flag = true;
        this.cls = false
        console.log("click is useful")
      },
      clsclick() {
        this.cls = true;
        this.flag = false
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
    height: 840px;
  }
  .el-icon-s-platform {
    margin-right: 30px;
  }
  .el-menu-item {
    margin-left: 30px;
  }
  .el-icon-s-tools {
    margin-left: 10px;
  }
</style>
