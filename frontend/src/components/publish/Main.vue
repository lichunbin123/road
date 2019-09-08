<template>
  <div id="main">
    <div class="header">
      <h>公路信息服务</h>
      <el-dropdown trigger="hover" v-if="username">
        <span style="margin-left:1670px; font-size:20px; color:#fff;">{{username}}<img style="width:40px; height:40px;border-radius: 20px; margin-left:10px; margin-top:10px; margin-bottom" src="/static/12.jpg" /></span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>我的消息</el-dropdown-item>
          <el-dropdown-item>设置</el-dropdown-item>
          <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-link v-else="username" style="margin-left:1600px; font-size: 20px;"><router-link to="/">您当前未登录,点击登录!</router-link></el-link>
      <el-link :underline="false" style="float: right; margin-right: 30px; font-size: 20px"><router-link to="/mainofuser">系统首页</router-link></el-link>
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
                  <span>出行信息发布</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="1-1" @click="edit">信息编辑</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
            </el-menu>
          </el-col>
        </el-row>
      </div>
      <div style="float: right; width: 85%">
        <EditNews v-if="news"></EditNews>
      </div>
    </div>
  </div>

</template>
<script>
  import EditNews from "./changeInfo/EditNews"
  import Cookies from 'js-cookie';
  export default {
    name: "Main",
    components: { EditNews},
    data () {
      return {
        news: false,
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
      edit() {
        this.news = true;
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
