<template>
  <div id="main" class="main">
    <div id="form" style="float:left;width:75%">
     <el-form ref="mainForm"  class="main-container">
      <el-row>
        <el-button type="primary" @click="application()" round >平台应用管理</el-button>
        <el-button type="primary" @click="monitoring()" round>路网运行监测管理</el-button>
        <el-button type="primary" @click="disposal" round>应急管理与处置</el-button>
        <el-button type="primary" @click="publish()" round>公路出行信息发布</el-button>
      </el-row>
    </el-form>
    </div>
    <div id="news" style="float:right;width:25%;margin-top:20px;">
    <el-card style="width: 400px">
      <div slot="header" class="clearfix">
        <span>最新信息</span>
      </div>
      <div class="text item">
        <ul  v-for="item in titles" >
          <li style="width:300px;	overflow: hidden;text-overflow: ellipsis;white-space:nowrap;text-align:left"><span>{{item.date}}</span><router-link :to="{path:'/publish/content',query:{id:item.title}}">{{item.title}}</router-link></li>
        </ul>
        <ul style="list-style-type:none;">
          <li style="text-align:right"><router-link to="/publish/news">更多</router-link></li>
        </ul>
      </div>
    </el-card>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'
    export default {
      name: "Main",
      data(){
        return {
          titles: [],
        }
      },
      created(){
        this.loadnewstitle();
      },
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
        },
        publish: function () {
          const url = `http://localhost:8083/publish`
          return axios.post(url).then(res => {
            console.log(res.status)
            this.$router.push("/publish")
          }).catch(err => {
            console.log(err.response)
            this.$message("帐号没有权限")
          })
        },
        loadnewstitle(){
          const url = `http://localhost:8083/publish/loadnewstitle`
          return axios.post(url).then(res => {
            if (res.status == 200){
              let result = res.data;
              this.titles = result.responseData;
            }
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
    background:url("/static/16.png");
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
    border-radius: 30px;
    margin-left: 350px;
  }
  .box-card {
    float: right;
  }
  a:hover{
    color: red;
  }
  a {
    text-decoration: none;
    color: black;
  }
</style>
