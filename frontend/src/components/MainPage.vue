<template>
  <div id="main">
     <el-form ref="mainForm"  class="main-container">
      <el-row>
        <el-button type="primary" @click="application" round>平台应用管理</el-button>
        <el-button type="primary" @click="monitoring" round>路网运行监测管理</el-button>
        <el-button type="primary" @click="disposal" round>应急管理与处置</el-button>
        <el-button type="primary" @click="publish" round>公路出行信息发布</el-button>
      </el-row>
    </el-form>
  </div>
</template>
<script>
    export default {
        name: "Main"
    }
</script>

<style scoped>
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
