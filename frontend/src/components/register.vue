<template>
  <div id="register">
    <el-form ref="registerForm" :model="registerForm" :rules="rules" label-position="left" class="demo-ruleForm register-container">
      <h2>注册</h2>
      <el-form-item prop="username">
        <el-input type="text" v-model="registerForm.username" :rules="rules.username"  placeholder="请输入用户名/手机号" auto-complete="on"></el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input  type="password" v-model="registerForm.password" :rules="rules.password"  placeholder="请输入密码" auto-complete="on"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="register('registerForm')">注册</el-button>
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
  .register-container {
    width: 300px;
    margin-left: 38%;
  }


</style>

<script>
  export default {
    name: "#register",
    data() {
      return {
        registerForm: {
          username: '',
          password: '',

        },
        rules: {
          username: [
            {required: true, message: '请输入账号', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ]
        },

      }
    },
    methods: {
      register: function () {
        this.$axios.post("register",{
          userName: this.registerForm.username,
          userPassword: this.registerForm.password
        }).then( res => {
          var res = res.data;
          if (res.successStatus !== true) {
            this.$message("注册失败")
          } else {
            this.$message("注册成功")
            this.$router.go(-1)
          }
        })
      },
    }

  };
</script>
