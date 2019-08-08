<template>
  <div id="UpdateBlog">
    <el-form ref="updateform" label-width="80px" label-position="center" class="updateform">
      <h2>用户信息更改</h2>
      <el-form-item prop="username" label="用户名">
        <el-input v-model="updateform.username"></el-input>
      </el-form-item>
      <el-form-item prop="password" label="用户密码">
        <el-input v-model="updateform.password"></el-input>
      </el-form-item>
      <el-form-item prop="role" label="用户类型">
        <el-select v-model="updateform.role" placeholder="请选择">
          <el-option label="领导" value="admin"></el-option>
          <el-option label="普通员工" value="user"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateBlog('updateform')">立即修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
      name: "UpdateBlog",
      data(){
        return{
          updateform: {
            username: '',
            password: '',
            role: '',
          },
        };

      },
      created(){
        var id = this.$route.params.id;
        console.log("this.$route.params.id ==="+id);
        return axios.post("/getBlogById/" + id).then(res => {
          let result = res.data;
          var data = result.responseUser;
          if (res.status == 200){
            this.updateform.username = data.username;
            this.updateform.password = data.password;
            this.updateform.role = data.role;
          } else {
            this.$message("获取数据失败");
          }
        })
      },
      methods: {
        updateBlog() {
          let data = this.updateform;
          console.log("data ==="+data);
          const url = `http://localhost:8083/updateUserMessage`
          return axios({
            method: 'post',
            url: url,
            data: data
          }).then(res => {
            if (res.status == 200){
              this.$message("修改成功");
            }
          })
        }
      }
    }
</script>

<style scoped>
  #UpdateBlog {

  }
  .el-input {
    width: 300px;
  }
  .el-select {
    width: 200px;
  }
  .el-form {
    text-align: center;
  }
  .el-form-item {
    width: 200px;
  }
  .updateform {
    width: 500px;
    height: 600px;
    margin-top: 7%;
    margin-left: 39%;
    border: 2px solid gray;
    border-radius: 30px;
    box-shadow: 0 10px 15px 0 rgba(0, 0, 0, 0.1);

  }
</style>
