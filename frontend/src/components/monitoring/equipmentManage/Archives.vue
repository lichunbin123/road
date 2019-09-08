<template>
  <div>
    <div class="title">
      <h>设备档案</h>
    </div>
    <div>
      <el-button type="primary" icon="el-icon-circle-plus" style="float: right" @click="dialogFormVisible = true">添加设备信息</el-button>
      <el-dialog title="设备档案" :visible.sync="dialogFormVisible" style="margin-left:500px; width: 1000px">
        <el-form :model="form" ref="form"  label-width="100px" class="demo-dynamic">
          <el-form-item label="设备名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="设备类型">
            <el-select v-model="form.type" placeholder="请选择">
              <el-option label="领导班" value="leader"></el-option>
              <el-option label="普通员工班" value="emplyee"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="设备生产日期">
            <el-date-picker
              v-model="value1"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="设备型号">
            <el-input v-model="form.model"></el-input>
          </el-form-item>
          <el-form-item label="详细参数">
            <el-input v-model="form.parameter"></el-input>
          </el-form-item>
          <el-form-item label="安装位置">
            <el-input v-model="form.installposition"></el-input>
          </el-form-item>
          <el-form-item label="运行状态">
            <el-select v-model="form.runstate" placeholder="请选择">
              <el-option label="新投入" value="new"></el-option>
              <el-option label="故障" value="down"></el-option>
              <el-option label="正常运行" value="normal"></el-option>
              <el-option label="维修" value="maintenance"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('classmessage')">提交</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
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
      </el-table>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "Archives",
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

