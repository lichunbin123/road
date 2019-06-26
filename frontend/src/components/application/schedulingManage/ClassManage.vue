<template>
  <div>
    <div class="title">
      <h>班次设置</h>
    </div>
    <div>
      <el-button type="primary" icon="el-icon-circle-plus" style="float: right" @click="dialogFormVisible = true">添加班次</el-button>
      <el-dialog title="班次信息" :visible.sync="dialogFormVisible" style="margin-left:500px; width: 900px">
      <el-form :model="dynamicValidateForm" ref="dynamicValidateForm"  label-width="100px" class="demo-dynamic">
        <el-form-item label="班次类型">
          <el-select v-model="dynamicValidateForm.classtype" placeholder="请选择">
            <el-option label="领导班" value="leader"></el-option>
            <el-option label="普通员工班" value="emplyee"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          v-for="(domain, index) in dynamicValidateForm.domains"
          :label="'成员' + index"
          :key="domain.key"
          :prop="'domains.' + index + '.value'"
          :rules="{
      required: true, message: '成员不能为空', trigger: 'blur'
    }"
        >
          <el-input v-model="domain.value"></el-input>
          <!--<el-button @click.prevent="removeDomain(domain)">删除</el-button>-->
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('dynamicValidateForm')">提交</el-button>
          <el-button @click="addDomain">增加成员</el-button>
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
          prop="classname"
          label="班次名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="definetype"
          label="定义类型"
          width="180">
        </el-table-column>
        <el-table-column
          prop="classtype"
          label="班次类型"
          width="180">
        </el-table-column>
        <el-table-column
          prop="date"
          label="起止时间">
        </el-table-column>
        <el-table-column
          prop="operation"
          label="操作">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            <el-button type="text" size="small">移除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
    export default {
      name: "ClassManage",
      data() {
        return {
          tableData: [{
            classname: '142012',
            definetype: '自定义',
            classtype: '领导班',
            date: '2012-2013',

          }, {
            classname: '142012',
            definetype: '自定义',
            classtype: '领导班',
            date: '2012-2013',
          }, {
            classname: '142012',
            definetype: '自定义',
            classtype: '领导班',
            date: '2012-2013',
          }, {
            classname: '142012',
            definetype: '自定义',
            classtype: '领导班',
            date: '2012-2013',
          }],
          dialogTableVisible: false,
          dialogFormVisible: false,
          dynamicValidateForm: {
            domains: [{
              value: ''
            }],
          }
        }
      },
      methods: {
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              alert('submit!');
            } else {
              console.log('error submit!!');
              return false;
            }
          });
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
