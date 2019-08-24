<template>
  <div id="EventManage">
    <div>
      <el-badge :value="200" :max="99" class="item" style="margin-top: 20px; width: 160px">
      <el-button size="small" type="success" style="width: 160px" @click="allevent">历史事件</el-button>
      </el-badge>
    </div>
    <div id="eventcircle" style="border: 2px solid gray;
    border-radius: 30px;
    box-shadow: 0 10px 15px 0 rgba(0, 0, 0, 0.1); margin-top: 30px;margin-right: 50px;">
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
        <el-table-column  label="处理状态" width="180">
          <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            active-text="已处理"
            inactive-text="未处理"
            disabled>
          </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="sure(scope.$index, scope.row)">审核通过</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="cancle(scope.$index, scope.row)">审核驳回</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'
  import Cookies from 'js-cookie'
  export default {
    name: 'EventManage',
    data() {
      return {
        tableData: [],//存放列表数据
        // value1: false
        // scope.$index.value1: false
      }
    },
    created() {
      this.searcheventmessage();
    },
    methods: {
      active_text (index, row) {
        console.log(index, row)
      },
      sure(index,row) {
        console.log(row.status);
        console.log(row.eventdeal);
        const url = `http://localhost:8083/application/neweventmessage`
        return axios({
          method: 'post',
          url: url,
          data: row
        }).then(res => {
          if (res.status == 200){
            row.status = true;
            this.delete(index,row);
            this.$message("已处理一条信息")
          }
        })
      },
      cancle(index,row) {
        console.log(row.username);
        const url = `http://localhost:8083/application/cancleneweventmessage`
        return axios({
          method: 'post',
          url: url,
          data: row
        }).then(res => {
          if (res.status == 200){
            row.status = true;
            this.delete(index,row);
            this.$message("已处理一条信息")
          }
        })
      },
      delete(index,row){
        return axios.post("http://localhost:8083/application/deliniteventmessage/" + row.id).then(res => {
        })
      },
      openmessage() {

      },
      searchcount() {
        const url = `http://localhost:8083/application/searchcount`
        return axios({
          method: 'post',
          url: url,
        }).then(res => {

        })
      },
      searcheventmessage() {
        const url = `http://localhost:8083/application/searcheventmessage`
        return axios({
          method: 'post',
          url: url,
        }).then(res => {
          if (res.status == 200){
            var result = res.data;
            this.tableData = result.responseData;
            console.log("result.responseData.username==="+result.responseData.username);
          }
        }).catch(error => {
          this.$message("获取事件信息失败")
        })
      },
      allevent() {
        this.$router.push("/application/allroadevent")
      }
    }
  }
</script>
