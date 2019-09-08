<template>
  <div id="newscontent" class="newscontent">
    <el-card class="content" shadow="hover" style="width: 1000px;height: 900px;margin-left: 550px;margin-top: 50px">
      <div id="title" class="title">
      <h>{{title}}</h>
      </div>
      <div id="about" class="about">
        <span>发布时间：{{date}}</span>
        <span>作者：{{author}}</span>
        <span>来源：{{origin}}</span>
      </div>
      <div id="content" class="content">
      <span v-html="content"></span>
      </div>
    </el-card>
  </div>
</template>
<script>
  import axios from 'axios'
  export default {
    data() {
      return {
        classification: '',
        title: '',
        author: '',
        origin: '',
        content: '',
        date: '',
      }
    },
    created() {
      this.loadnews();
    },
    methods: {
      loadnews(){
        var title = this.$route.query.id;
        console.log("title==="+title);
        return axios.post("http://localhost:8083/publish/loadnewscontent/"+title).then(res => {
          if (res.status == 200){
            let result = res.data;
            let data = result.responseData[0];
            this.classification = data.classification;
            this.title = data.title;
            this.author = data.author;
            this.origin = data.origin;
            this.content = data.content;
            this.date = data.date;
            console.log("data==="+data.content);
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
  .title{
    font-size: 25px;
    text-align: center;
    color: #1c6ca1;
    font-weight: bold;
  }
  .about{
    margin-top: 30px;

  }
  .content{
    text-align: center;
    font-size: 18px;
  }
  #newscontent{
    background:url("/static/16.png");
    background-size: 100% 100%;
    height: 100%;
    position: fixed;
    width: 100%;
  }
</style>
