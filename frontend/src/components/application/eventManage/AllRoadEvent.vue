<template>
  <div id="EventShow">
    <div id="eventcircle">
      <el-table
        :data="tableData"
        border
        style="width: 100%" ref="multipleTable"
        id="workertable">
        <el-table-column prop="username" label="申报人" width="120">
        </el-table-column>
        <el-table-column prop="eventdate" label="申报时间" width="180">
        </el-table-column>
        <el-table-column prop="eventtype" label="事件类型" width="120">
        </el-table-column>
        <el-table-column prop="eventresource" label="是否核实过事件" width="100">
        </el-table-column>
        <el-table-column prop="eventdesc" label="事件详情" width="410">
        </el-table-column>
        <el-table-column prop="eventdeal" label="处理方法" width="410">
        </el-table-column>
        <el-table-column prop="eventsure" label="处理状态" width="180">
        </el-table-column>
      </el-table>
      <el-button @click="exportExcel" style="margin-top: 2px;" size="medium" type="success">导出</el-button>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'
  import Cookies from 'js-cookie'
  import FileSaver from 'file-saver'
  import XLSX from 'xlsx'
  export default {
    name: 'EventShow',
    data() {
      return {
        tableData: [],//存放列表数据
      }
    },
    created() {
      this.alleventmessage();
    },
    methods: {
      alleventmessage() {
        const url = `http://localhost:8083/application/alleventmessage`
        return axios({
          method: 'post',
          url: url,
        }).then(res => {
          var result = res.data;
          this.tableData = result.responseData;
        }).catch(error => {
          this.$message("获取事件信息失败")
        })
      },
      exportExcel: function () {
        var wb = XLSX.utils.table_to_book(document.querySelector('#workertable'))

        var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
        try {
          FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '公路事件信息表.xlsx')
        } catch (e) {
          if (typeof console !== 'undefined')
            console.log(e, wbout)
        }
        return wbout
      }
    }
  }
</script>
