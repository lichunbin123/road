<template>
  <div class="addBlog">
    <el-form ref="form" label-width="80px" label-position="center">
      <el-form-item label="用户名">
        <el-input v-model="username"></el-input>
      </el-form-item>
      <el-form-item label="用户密码">
        <el-input v-model="password"></el-input>
      </el-form-item>
      <el-form-item label="用户类型">
        <el-select v-model="tag" placeholder="请选择">
          <el-option label="领导" value="1"></el-option>
          <el-option label="普通员工" value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addBlog">立即添加</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
      name: "AddUserList",
      data() {
        return {
          username: "",
          password: "",
          tag: ""
        };
      },
    }
</script>

<style scoped>
 .addBlog {
  text-align: center;
   margin: 40%;
   width: 300px;
 }
</style>
