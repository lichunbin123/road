<template>
  <div>
    <div class="title">
      <h>风险隐患点记录</h>
    </div>
    <div>
      <el-button type="primary" icon="el-icon-circle-plus" style="float: right" @click="dialogFormVisible = true">添加记录</el-button>
      <el-dialog title="风险隐患点信息" :visible.sync="dialogFormVisible" style="margin-left:500px; width: 1000px">
        <el-form :model="riskmessage" ref="riskmessage"  label-width="100px" class="demo-dynamic">
          <el-form-item label="风险隐患点">
            <el-input v-model="riskmessage.name"></el-input>
          </el-form-item>
          <el-form-item label="隐患点类型">
            <el-select v-model="riskmessage.type" placeholder="请选择">
              <el-option label="重要路段" value="road"></el-option>
              <el-option label="桥梁" value="bridge"></el-option>
              <el-option label="隧道" value="tunnel"></el-option>
              <el-option label="事故多发点" value="accidentpoint"></el-option>
              <el-option label="互通立交" value="interchange"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="主要风险因素">
            <el-input v-model="riskmessage.riskfactors"></el-input>
          </el-form-item>
          <el-form-item label="风险事件(存在填)">
            <el-input v-model="riskmessage.riskevent" type="textarea"></el-input>
          </el-form-item>
          <el-form-item label="事件发生时间(存在填)">
            <el-date-picker
              v-model="riskmessage.eventdate"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('riskmessage')">提交</el-button>
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
          label="风险隐患点"
          width="180">
        </el-table-column>
        <el-table-column
          prop="type"
          label="隐患点类型"
          width="180">
        </el-table-column>
        <el-table-column
          prop="riskfactors"
          label="主要风险因素">
        </el-table-column>
        <el-table-column
          prop="riskevent"
          label="风险事件">
        </el-table-column>
        <el-table-column
          prop="eventdate"
          label="风险发生时间"
          width="180">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "RiskMessageManage",
    data() {
      return {
        tableData: [],
        dialogTableVisible: false,
        dialogFormVisible: false,
        riskmessage: {
          name: '',
          type: '',
          riskfactors: '',
          riskevent: '',
          eventdate: '',
        },
      }
    },
    created(){
      this.searchMessage();
    },
    methods: {
      submitForm:function () {
        let data = this.riskmessage;
        this.dialogFormVisible = false;
        const url = `http://localhost:8083/disposal/addrisk`
        return axios({
          method: 'post',
          url: url,
          data: data
        }).then(res => {
          if (res.status == 200){
            this.$message("增加记录成功");
            this.searchMessage();
          }
        })
      },
      searchMessage(){
        const url = `http://localhost:8083/disposal/searchriskrecord`
        return axios({
          method: 'post',
          url: url,
        }).then(res => {
          if (res.status == 200){
            var result = res.data;
            console.log("result.responseData===="+result.responseData)
            this.tableData = result.responseData;
            this.$message("查询记录信息成功");
          }
        }).catch(err =>{
          console.log(err.response);
          this.$message("获取记录信息错误")
        })
      },
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
