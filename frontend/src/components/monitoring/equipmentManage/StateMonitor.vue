<template>
  <div>
    <div class="title">
      <h>设备运行状态监控</h>
    </div>
    <div>
      <el-table
        :data="tableData"
        border
        style="width: 100%">
        <el-table-column
          prop="name"
          label="设备名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="type"
          label="设备类型"
          width="180">
        </el-table-column>
        <el-table-column
          prop="date"
          label="设备生产日期">
        </el-table-column>
        <el-table-column
          prop="model"
          label="设备型号">
        </el-table-column>
        <el-table-column
          prop="parameter"
          label="详细参数">
        </el-table-column>
        <el-table-column
          prop="installposition"
          label="安装位置">
        </el-table-column>
        <el-table-column
          prop="runstate"
          label="运行状态">
        </el-table-column>
        <el-table-column
          prop="operation"
          label="操作">
          <template slot-scope="scope">
            <el-button @click="renew(scope.row)" type="text" size="small">申报维修</el-button>
            <el-button @click="out(scope.row,scope.name)" type="text" size="small">设备下架</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "StateMonitor",
    data() {
      return {
        tableData: [],
        dialogTableVisible: false,
        dialogFormVisible: false,
        form: {
          name: '',
          type: '',
          date: '',
          model: '',
          parameter: '',
          installposition: '',
          runstate: '',
        },
      }
    },
    created(){

    },
    methods: {
      submitForm:function () {

      },
      searchMessage(){

      },
      renew(index,row){

      },
      out(index,row){

      }
    }
  }
</script>

<style scoped>
  .title {
    color: #333;
    text-align: left;
    font-size: 30px;
    height: 75px;
    line-height: 75px;
  }
</style>
