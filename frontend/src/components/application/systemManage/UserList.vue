<template>
  <div>
    <div class="title">
      <h>用户管理</h>
    </div>
    <div class="table">
  <el-table
    :data="tableData"
    style="width: 100%" ref="multipleTable" tooltip-effect="dark">
    <el-table-column prop="id" label="用户ID" width="180">
    </el-table-column>
    <el-table-column prop="username" label="用户名" width="180">
    </el-table-column>
    <el-table-column prop="password" label="用户密码" width="180">
    </el-table-column>
    <el-table-column prop="role" label="用户类型" width="180">
    </el-table-column>
    <el-table-column prop="time" label="用户注册时间" width="180" :formatter="dateFormat" sortable>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleUpdate(scope.$index, scope.row)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
    <!--新增按钮-->
    <el-col :span="1" class="grid">
      <el-button type="success" icon="el-icon-circle-plus-outline" size="mini" @click="dialogFormVisible = true" round>新增</el-button>
      <el-dialog title="增加用户信息" :visible.sync="dialogFormVisible" style="margin-left:500px; width: 900px">
        <el-form :model="form" style="width: 300px">
          <el-form-item prop="username" label="用户名称" :label-width="formLabelWidth">
            <el-input type="text" v-model="form.username" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item prop="password" label="用户密码" :label-width="formLabelWidth">
            <el-input type="password" v-model="form.password" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item prop="role" label="用户类型" :label-width="formLabelWidth">
            <el-select v-model="form.role" placeholder="请选择">
              <el-option label="领导" value="admin"></el-option>
              <el-option label="普通员工" value="user"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addUserMessage('form')">确 定</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </el-col>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    created(){
      console.log("enter created");
      this.getUserMessage();
      console.log("had enter getUerMessage");
    },
    data() {
      return {
        input: '',
        tableData: [],//存放列表数据
        dialogFormVisible: false,
        FormVisible: false,
        form: {
          name: '',
          password: '',
          role: '',
        },
        formLabelWidth: '100px'
      }
    },
    methods: {
      //查询全部用户信息
      getUserMessage(){
        const url = `http://localhost:8083/getUserMessage`
        return axios.post(url).then(res => {
          console.log(res.status)
          var result = res.data;
          console.log(result)
          this.tableData = result.responseData;
        }).catch(err => {
          console.log(err.response)
          this.$message("获取用户信息失败")
        })
      },
      //时间格式化
      dateFormat: function(row,column) {
        var date = row[column.property];
        console.log("date === "+date);
        if (date == undefined){
          return "未填";
        }
        return this.$moment(date).format("YYYY-MM-DD HH:mm:ss");
      },
      //修改用户信息
      handleUpdate(index, row) {
       console.log(index,row);
       console.log("row.id==="+row.id);
       this.$router.push({
         path: '/updateuser/'+row.id
       })
      },
      //删除用户记录
      handleDelete(index, row) {
        this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            return axios.post("/deleteUserMessage/" + row.id).then(res => {
                if (res.status == 200){
                  this.getUserMessage();
                }
              }).catch(err => {
                console.log(err.response)
                this.$message("删除用户信息失败")
              })
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
          },
      //增加用户
      addUserMessage(){
        let data = this.form;
        this.dialogFormVisible = false;
        const url = `http://localhost:8083/addUserMessage`
        return axios({
          method: 'post',
          url: url,
          data: data
        }).then(res => {
         if (res.status == 200){
           this.getUserMessage();
         }
        }).catch(err => {
          console.log(err.response)
          this.$message("添加失败")
        })

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
  },
  .table {
  }
</style>
