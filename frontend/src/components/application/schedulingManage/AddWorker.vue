<template>
  <div>
    <div class="title">
      <h>人员值班编排</h>
    </div>
  <div>
    <el-button type="primary" @click="addline" icon="el-icon-circle-plus" style="margin-right: 2100px">添加一条</el-button>
    <el-table
      ref="filterTable"
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        prop="classname"
        label="班次名称"
        width="180"
      >
        <template slot-scope="scope">
          <el-input v-if="scope.row.status" v-model="scope.row.classname"  :disabled="true"></el-input>
          <span v-else>{{scope.row.classname}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="员工名称"
        width="180"
      >
        <template slot-scope="scope">
          <el-input v-if="scope.row.status" v-model="scope.row.name"></el-input>
          <span v-else>{{scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="job"
        label="岗位"
        width="180">
        <template slot-scope="scope">
          <el-input v-if="scope.row.status" v-model="scope.row.job"></el-input>
          <span v-else>{{scope.row.job}}</span>
        </template>
      </el-table-column>

      <el-table-column
        prop="monday"
        label="星期一"
        :formatter="formatter">
        <template slot-scope="scope">
          <el-input v-if="scope.row.status" v-model="scope.row.monday"></el-input>
          <span v-else>{{scope.row.monday}}</span>
        </template>
      </el-table-column>

      <el-table-column
        prop="tuesday"
        label="星期二"
        :formatter="formatter">
        <template slot-scope="scope">
          <el-input v-if="scope.row.status" v-model="scope.row.tuesday"></el-input>
          <span v-else>{{scope.row.tuesday}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="wednesday"
        label="星期三"
        :formatter="formatter">
        <template slot-scope="scope">
          <el-input v-if="scope.row.status" v-model="scope.row.wednesday"></el-input>
          <span v-else>{{scope.row.wednesday}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="thursday"
        label="星期四"
        :formatter="formatter">
        <template slot-scope="scope">
          <el-input v-if="scope.row.status" v-model="scope.row.thursday"></el-input>
          <span v-else>{{scope.row.thursday}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="friday"
        label="星期五"
        :formatter="formatter">
        <template slot-scope="scope">
          <el-input v-if="scope.row.status" v-model="scope.row.friday"></el-input>
          <span v-else>{{scope.row.friday}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="saturday"
        label="星期六"
        :formatter="formatter">
        <template slot-scope="scope">
          <el-input v-if="scope.row.status" v-model="scope.row.saturday"></el-input>
          <span v-else>{{scope.row.saturday}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="sunday"
        label="星期日"
        :formatter="formatter">
        <template slot-scope="scope">
          <el-input v-if="scope.row.status" v-model="scope.row.sunday"></el-input>
          <span v-else>{{scope.row.sunday}}</span>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="primary" @click="submit">提交</el-button>

  </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    data() {
      return {
        tableData: [],
      }
    },
    methods: {
      addline:function ()  {
        var classname = this.$route.query.classname;
        console.log("classname==="+classname);
        this.tableData.map(item=>{
          if(item.status){
            item.status = 0;
          }
          return item;
        })
        this.tableData.push({
          classname: classname,
          name: '',
          job: '',
          monday: '',
          tuesday: '',
          wednesday: '',
          thursday: '',
          friday: '',
          saturday: '',
          sunday: '',
          status: 1 });
      },
      submit:function () {
        console.log("this.tabledata==="+this.tableData.length);
        let data = this.tableData;
        const url = `http://localhost:8083/application/addworker`
        return axios({
          method: 'post',
          url: url,
          data: data
        }).then(res => {
          if (res.status == 200){
            this.$message("提交成功");
          }
        }).catch(err =>{
          console.log(err.response);
          this.$message("提交错误")
        })

      }
    }
  }
</script>

<style scoped>
  .title {
    color: #333;
    text-align: center;
    font-size: 30px;
    height: 75px;
    line-height: 75px;
    background-color: gainsboro;
  }
</style>
