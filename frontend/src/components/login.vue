<template>
  <div id="login">
    <el-form ref="loginForm" :model="loginForm" :rules="rules" label-position="left" class="demo-ruleForm login-container">
      <h2>登录</h2>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" :rules="rules.username"  placeholder="请输入用户名/手机号" auto-complete="on"></el-input>
      </el-form-item>

      <el-form-item prop="userpassword">
        <el-input  type="password" v-model="loginForm.userpassword" :rules="rules.userpassword"  placeholder="请输入密码" auto-complete="on"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="login('loginForm')">登录</el-button>
        <router-link :to="{ path: '/register'}">尚未注册？</router-link>
      </el-form-item>
    </el-form>
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
    margin-left: 38%;
  }


</style>

<script>
  import { loginProc} from '@/api/api'
  export default {
    name: "#login",
    data() {
      return {
        loginForm: {
          username: '',
          userpassword: '',
        },
        rules: {
          username: [
            {required: true, message: '请输入账号', trigger: 'blur'}
          ],
          userpassword: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ]
        },

      }
    },
    methods: {
      login:function () {
        loginProc(this.loginForm).then(res => {
          this.$router.push('/')
      })
      }
      },

  }
</script>

