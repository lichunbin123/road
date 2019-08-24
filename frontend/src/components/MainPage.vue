<template>
  <div id="main" class="main">
     <el-form ref="mainForm"  class="main-container">
      <el-row>
        <el-button type="primary" @click="application()" round >平台应用管理</el-button>
        <el-button type="primary" @click="monitoring()" round>路网运行监测管理</el-button>
        <el-button type="primary" @click="disposal" round>应急管理与处置</el-button>
        <el-button type="primary" @click="publish" round>公路出行信息发布</el-button>
      </el-row>
    </el-form>
  </div>
</template>
<script>
  import axios from 'axios'
    export default {
      name: "Main",
      methods:{
        application: function () {
          const url = `http://localhost:8083/application`
          return axios.post(url).then(res => {
            console.log(res.status)
            this.$router.push("/application")
          }).catch(err => {
            console.log(err.response)
            this.$message("帐号没有权限")
          })
        },
        monitoring: function () {
          const url = `http://localhost:8083/monitoring`
          return axios.post(url).then(res => {
            console.log(res.status)
            this.$router.push("/monitoring")
          }).catch(err => {
            console.log(err.response)
            this.$message("帐号没有权限")
          })
        }
      }
    }
</script>

<style scoped>
  #main{
    background:url("/static/6.gif");
    background-size: 100% 100%;
    height: 100%;
    position: fixed;
    width: 100%
  }
  /*.main{*/
    /*background:url("/static/katong.jpg");*/
  /*}*/
  .el-form {
    text-align: center;
  }
  .el-button {
    font-size: 20px;
    padding: 30px;
    margin: 40px;
  }
  .main-container {
    margin-top: 18%;
    border-radius: 30px
  }


</style>
