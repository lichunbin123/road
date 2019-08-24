<template>
  <div>
  <div class="title">
    <h>人员值班表</h>
  </div>
  <div>
    <el-table
      :data="tableData"
      border
      style="width: 100%" ref="multipleTable"
      id="workertable">
      <el-table-column prop="classname" label="班次名称" width="180">
      </el-table-column>
      <el-table-column prop="name" label="员工名称" width="180">
      </el-table-column>
      <el-table-column prop="job" label="工作岗位" width="180">
      </el-table-column>
      <el-table-column prop="monday" label="星期一" width="180">
      </el-table-column>
      <el-table-column prop="tuesday" label="星期二" width="180">
      </el-table-column>
      <el-table-column prop="wednesday" label="星期三" width="180">
      </el-table-column>
      <el-table-column prop="thursday" label="星期四" width="180">
      </el-table-column>
      <el-table-column prop="friday" label="星期五" width="180">
      </el-table-column>
      <el-table-column prop="saturday" label="星期六" width="180">
      </el-table-column>
      <el-table-column prop="sunday" label="星期日" width="180">
      </el-table-column>
    </el-table>
    <el-button @click="exportExcel" style="margin-top: 2px;" size="medium" type="success">导出</el-button>
  </div>
  </div>
</template>
<script>
  import axios from 'axios'
  import FileSaver from 'file-saver'
  import XLSX from 'xlsx'
  export default {
    name: "ClassWithWorkers",
    data() {
      return {
        tableData: [],
      }
    },
    created(){
      this.searchAll();
    },
    methods: {
      searchAll: function () {
        var classname = this.$route.query.classname;
        console.log("classname==="+classname);
        const url = `http://localhost:8083/application/searchworker`
        return axios({
          method: 'post',
          url: url,
          params:{
            classname: classname
          }
        }).then(res => {
          if (res.status == 200){
            this.$message("查询员工信息成功");
          }else {
            this.$message("查询员工信息失败");
          }
          console.log(res.status)
          var result = res.data;
          console.log(result)
          this.tableData = result.responseData;
        })
      },
      exportExcel: function () {
        var wb = XLSX.utils.table_to_book(document.querySelector('#workertable'))

        var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
        try {
          FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '员工值班信息表.xlsx')
        } catch (e) {
          if (typeof console !== 'undefined')
            console.log(e, wbout)
        }
        return wbout
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
