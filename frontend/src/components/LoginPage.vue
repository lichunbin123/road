<template>
  <div id="login">
    <div class="header">
      <img src="/static/header.jpg" style="width: 1000px; height: 200px; align: center;"></img>
    </div>
    <div class="loginDiv">
    <el-form ref="loginForm" :model="loginForm" :rules="rules" label-position="left" class="login-container">
      <h2>登录</h2>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" :rules="rules.username"  placeholder="请输入帐号" auto-complete="on"></el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input  type="password" v-model="loginForm.password" :rules="rules.password"  placeholder="请输入密码" auto-complete="on"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="login('loginForm')">登录</el-button>
      </el-form-item>
    </el-form>
    </div>
  </div>
</template>

<style scoped>
  .el-input {
    width:300px;
  }
  .el-form {
    text-align: center;
  }
  .login-container {
    width: 300px;
    margin-left: 11%;
  }
  .loginDiv {
    margin-top: 55px;
    margin-left: 39%;
    width: 380px;
    border: 2px solid gray;
    border-radius: 30px;
    box-shadow: 0 10px 15px 0 rgba(0, 0, 0, 0.1);
  }

  #login{
  }




</style>

<script>
  import axios from 'axios'
  import { loginProc} from '@/api/api'
  export default {
    name: "#login",
    data() {
      return {
        loginForm: {
          username: '',
          password: '',
        },
        rules: {
          username: [
            {required: true, message: '请输入账号', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ],
        },

      }
    },
    methods: {
      login:function () {
        let data = this.loginForm;
        console.log("data==="+data)
        const url = `http://localhost:8083/login`
        return axios({
          method: 'post',
          url: url,
          data: data
        }).then(res => {
          localStorage.accessToken = res.data
          axios.defaults.headers.common['Authorization'] = `Bearer ${res.data}`
          console.log(res.status)
          this.$router.push('/main')
        }).catch(err => {
            console.log(err.response)
            this.$message("登录失败")
          })
      }
      },

  }
</script>

