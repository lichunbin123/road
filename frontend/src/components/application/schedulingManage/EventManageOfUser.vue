<template>
  <div id="EventManage">
    <div>
      <el-badge :value="count1" class="item" style="margin-top: 20px; width: 160px">
        <el-button size="small" type="success" style="width: 160px" @click="eventofsuccess">审核通过事件</el-button>
      </el-badge>
      <el-badge :value="count2" class="item" style="margin-top: 20px; width: 160px">
        <el-button size="small" type="success" style="width: 160px" @click="eventoffail">驳回事件</el-button>
      </el-badge>
    </div>
    <div id="eventcircle" style="border: 2px solid gray;
    border-radius: 30px;
    box-shadow: 0 10px 15px 0 rgba(0, 0, 0, 0.1); margin-top: 30px;margin-right: 50px;">
      <el-form ref="eventform" :model="eventform" label-width="120px" style="float: left; width: 75%; margin-bottom: 20px;margin-top: 20px">
      <el-form-item label="申报人">
        <el-input v-model="eventform.username" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="事件时间">
          <el-date-picker type="date" placeholder="选择日期" v-model="eventform.eventdate" style="width: 100%;"></el-date-picker>
      </el-form-item>
      <el-form-item label="事件性质">
        <el-radio-group v-model="eventform.eventtype">
          <el-radio label="咨询"></el-radio>
          <el-radio label="反馈"></el-radio>
          <el-radio label="投诉"></el-radio>
          <el-radio label="事故"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否核实过事件">
        <el-radio-group v-model="eventform.eventresource">
          <el-radio label="是"></el-radio>
          <el-radio label="否"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="事件详情">
        <el-input type="textarea" v-model="eventform.eventdesc"></el-input>
      </el-form-item>
      <el-form-item label="处理方法">
        <el-input type="textarea" v-model="eventform.eventdeal"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">报送</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>

      <el-dialog title="审核通过事件" :visible.sync="dialogFormVisible1" style=" width: 2500px">
        <el-button
          type="warning"
          plain
          @click="clearsuccess">
          清空
        </el-button>
        <el-table
          v-loading="loading"
          :data="tableData1"
          border
          style="width: 100%" ref="multipleTable"
          id="workertable">
          <el-table-column prop="username" label="申报人" width="120">
          </el-table-column>
          <el-table-column prop="eventdate" label="申报时间" width="180">
          </el-table-column>
          <el-table-column prop="eventtype" label="事件类型" width="120">
          </el-table-column>
          <el-table-column prop="eventresource" label="是否核实过事件" width="140">
          </el-table-column>
          <el-table-column prop="eventdesc" label="事件详情" width="280">
          </el-table-column>
          <el-table-column prop="eventdeal" label="处理方法" width="280">
          </el-table-column>
          <el-table-column prop="eventsure" label="处理状态" width="120">
          </el-table-column>
        </el-table>
      </el-dialog>

      <el-dialog title="审核未通过事件" :visible.sync="dialogFormVisible2" style="width: 2500px">
        <el-button
          type="warning"
          plain
          @click="clearfail">
          清空
        </el-button>
        <el-table
          v-loading="loading"
          :data="tableData2"
          border
          style="width: 100%" ref="multipleTable"
          id="workertable">
          <el-table-column prop="username" label="申报人" width="120">
          </el-table-column>
          <el-table-column prop="eventdate" label="申报时间" width="180">
          </el-table-column>
          <el-table-column prop="eventtype" label="事件类型" width="120">
          </el-table-column>
          <el-table-column prop="eventresource" label="是否核实过事件" width="140">
          </el-table-column>
          <el-table-column prop="eventdesc" label="事件详情" width="280">
          </el-table-column>
          <el-table-column prop="eventdeal" label="处理方法" width="280">
          </el-table-column>
          <el-table-column prop="eventsure" label="处理状态" width="120">
          </el-table-column>
        </el-table>
      </el-dialog>
    </div>
    <div class="block" style="float: right; width: 25%; margin-right: 30px">
      <el-timeline>
        <el-timeline-item timestamp="上次提交记录" placement="top">
          <el-card>
            <h4>日期:{{lastdate}}</h4>
            <p>事件类型:{{lasttype}}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'
  import Cookies from 'js-cookie'
  export default {
    name: 'EventManageOfUser',
    inject: ["reload"],
    data() {
      return {
        eventform: {
          username: Cookies.get('username'),
          eventdate: '',
          eventtype: '',
          eventresource: '',
          eventdesc: '',
          eventdeal: ''
        },
        count1: '',
        count2: '',
        lseventdate: '',
        lseventtype: '',
        lastdate: '',
        lasttype: '',
        dialogFormVisible1: false,
        dialogFormVisible2: false,
        tableData1: [],
        tableData2: [],
      }
    },
    created() {
      this.searchEventCountOfSuccess();
      this.searchEventCountOfFail();
      this.lastdate = localStorage.getItem('lseventdate');
      this.lasttype = localStorage.getItem('lseventtype');
    },
    methods: {
      onSubmit() {
        let data = this.eventform;
        const url = `http://localhost:8083/application/initeventmessage`
        return axios({
          method: 'post',
          url: url,
          data: data
        }).then(res => {
          if (res.status == 200){
            var lseventdate = data.eventdate;
            var lseventtype = data.eventtype;
            localStorage.setItem('lseventdate',lseventdate);
            localStorage.setItem('lseventtype',lseventtype);
            this.reload();
            this.$message({
              message: '事件信息提交成功',
              type: 'success'
            });
          }
        })
      },
      searchEventCountOfSuccess() {
        const url = `http://localhost:8083/application/searchcountofsuccess`
        return axios({
          method: 'post',
          url: url,
        }).then(res => {
          if (res.status == 200){
           var result = res.data;
           var num = result.count;
           this.count1 = num;
          }
        })
      },
      searchEventCountOfFail() {
        const url = `http://localhost:8083/application/searchcountoffail`
        return axios({
          method: 'post',
          url: url,
        }).then(res => {
          if (res.status == 200){
            var result = res.data;
            var num = result.count;
            this.count2 = num;
          }
        })
      },
      eventofsuccess() {
        const url = `http://localhost:8083/application/searchlistofsuccess`
        return axios({
          method: 'post',
          url: url,
        }).then(res => {
          if (res.status == 200){
            this.dialogFormVisible1 = true;
            var result = res.data;
            this.tableData1 = result.responseData;
          }
        })
      },
      eventoffail() {
        const url = `http://localhost:8083/application/searchlistoffail`
        return axios({
          method: 'post',
          url: url,
        }).then(res => {
          if (res.status == 200){
            this.dialogFormVisible2 = true;
            var result = res.data;
            this.tableData2 = result.responseData;
          }
        })
      },
      clearsuccess() {
        const url = `http://localhost:8083/application/deletelistofsuccess`
        return axios({
          method: 'post',
          url: url,
        }).then(res => {
          if (res.status == 200){
            this.$notify({
              title: '警告',
              message: '您已清空记录',
              type: 'warning'
            });
            this.searchEventCountOfSuccess();
          }
        })
      },
      clearfail() {
        const url = `http://localhost:8083/application/deletelistoffail`
        return axios({
          method: 'post',
          url: url,
        }).then(res => {
          if (res.status == 200){
            this.$notify({
              title: '警告',
              message: '您已清空记录',
              type: 'warning'
            });
            this.searchEventCountOfFail();
          }
        })
      }
    }
  }
</script>
