<template>
    <div class="news">
      <el-card class="content" shadow="hover" style="width: 1000px;height: 900px;margin-left: 550px;margin-top: 50px">
        <span style="font-size:30px">要闻动态</span>
        <el-col v-for="item in news" :key="item">
          <el-row style="text-align: left;padding:10px"><span>{{item.date}}</span><span>&#12288</span><router-link :to="{path:'/publish/content',query:{id:item.title}}">{{item.title}}</router-link></el-row>
        </el-col>
      </el-card>
    </div>
</template>
<script>
  import axios from 'axios'
  export default {
    data() {
      return {
        news: [],
      }
    },
    created() {
      this.loadnews();
    },
    methods: {
      loadnews(){
        const url = `http://localhost:8083/publish/loadnews`
        return axios.post(url).then(res => {
          if (res.status == 200){
            let result = res.data;
            this.news = result.responseData;
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
  .news{
    background:url("/static/17.png");
    background-size: 100% 100%;
    height: 100%;
    position: fixed;
    width: 100%;
  }
  .router-link-active {
    text-decoration: none;
    color: black;
  }
  a:hover{
    color: red;
  }
  a {
    text-decoration: none;
    color: black;
  }
</style>
