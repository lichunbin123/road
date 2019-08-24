<template>
  <div>
    <div class="title">
      <h>班次设置</h>
    </div>
    <div>
      <el-button type="primary" icon="el-icon-circle-plus" style="float: right" @click="dialogFormVisible = true">添加班次</el-button>
      <el-dialog title="班次信息" :visible.sync="dialogFormVisible" style="margin-left:500px; width: 1000px">
      <el-form :model="classmessage" ref="classmessage"  label-width="100px" class="demo-dynamic">
        <el-form-item label="班次名称">
          <el-input v-model="classmessage.newclassname"></el-input>
        </el-form-item>
        <el-form-item label="班次类型">
          <el-select v-model="classmessage.newclasstype" placeholder="请选择">
            <el-option label="领导班" value="leader"></el-option>
            <el-option label="普通员工班" value="emplyee"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="值班时间">
          <el-date-picker
            v-model="classmessage.newdate"
            type="daterange"
            align="right"
            unlink-panels
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd"
            >
          </el-date-picker>
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
          prop="newclassname"
          label="班次名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="newclasstype"
          label="班次类型"
          width="180">
        </el-table-column>
        <el-table-column
          prop="startdate"
          label="起始时间">
        </el-table-column>
        <el-table-column
          prop="enddate"
          label="结束时间">
        </el-table-column>
        <el-table-column
          prop="operation"
          label="操作">
          <template slot-scope="scope">
            <el-button @click="search(scope.row)" type="text" size="small">查看</el-button>
            <el-button @click="edit(scope.row,scope.newclassname)" type="text" size="small">编辑</el-button>
            <el-button type="text" size="small">移除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
    import axios from 'axios'
    export default {
      name: "ClassManage",
      data() {
        return {
          tableData: [],
          dialogTableVisible: false,
          dialogFormVisible: false,
          classmessage: {
            newclassname: '',
            newclasstype: '',
            newdate: '',
          },
        }
      },
      created(){
        this.searchMessage();
      },
      methods: {
        submitForm:function () {
         let data = this.classmessage;
          this.dialogFormVisible = false;
         console.log("classmessage.newclassname==="+data.newclassname);
         console.log("classmessage.newclassname==="+data.newdate);
         console.log("classmessage.newclassname==="+data.newclasstype);
         const url = `http://localhost:8083/application/addclass`
          return axios({
            method: 'post',
            url: url,
            data: data
          }).then(res => {
            if (res.status == 200){
              this.$message("增加班次成功");
              this.searchMessage();
            }
          })
        },
        searchMessage(){
          const url = `http://localhost:8083/application/searchmessage`
          return axios({
            method: 'post',
            url: url,
          }).then(res => {
            if (res.status == 200){
              var result = res.data;
              console.log("result.responseData===="+result.responseData)
              this.tableData = result.responseData;
              this.$message("查询班次信息成功");
            }
          }).catch(err =>{
            console.log(err.response);
            this.$message("获取班次信息错误")
          })
        },
        search(row){
          var classname = row.newclassname;
          console.log("row.newclassname"+classname);
          this.$router.push({
            path: '/application/classwithworkers',
            query: {
              classname: classname
            }
          })
        },
        edit:function(row){
          var classname = row.newclassname;
          console.log("row==="+classname);
          this.$router.push({
            path: '/application/add',
            query: {
              classname: classname
            }
          })
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        },
        removeDomain(item) {
          var index = this.dynamicValidateForm.domains.indexOf(item)
          if (index !== -1) {
            this.dynamicValidateForm.domains.splice(index, 1)
          }
        },
        addDomain() {
          this.dynamicValidateForm.domains.push({
            value: '',
            key: Date.now()
          });
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
