<template>
  <div>
    <!--搜索框-->
    <el-row>
      <el-col :span="3" class="grid">
        <el-input v-model="input" placeholder="请输入id" size="mini"></el-input>
      </el-col>
      <el-col :span="1" class="grid">
        <el-button type="success" icon="el-icon-search" size="mini" @click.prevent="search()">搜索</el-button>
      </el-col>
    </el-row>
    <br>
    <!--表格数据及操作-->
    <!-- 加载设置 -->
    <el-table
      element-loading-text="拼命加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      fit
      :data="list" border style="width: 100%" ref="multipleTable" tooltip-effect="dark">
      <el-table-column prop="id" label="id" sortable></el-table-column>
      <el-table-column prop="title" label="标题" ></el-table-column>
      <el-table-column prop="link" label="链接" ></el-table-column>
      <el-table-column prop="date" label="日期" :formatter="dateFormat" sortable></el-table-column>
      <el-table-column prop="author" label="作者" ></el-table-column>
      <el-table-column prop="tag" label="显隐" ></el-table-column>
      <el-table-column label="编辑">
        <template slot-scope="scope">
          <router-link to="/updateBlog">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click.prevent="updateBlog(scope.$index, scope.row)">编辑</el-button>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="删除">
        <template slot-scope="scope">
          <el-button type="danger" icon="el-icon-delete" size="mini" @click.prevent="deleteBlog(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--新增按钮-->
    <el-col :span="1" class="grid">
      <router-link to="/addBlog">
        <el-button type="success" icon="el-icon-circle-plus-outline" size="mini" round>新增</el-button>
      </router-link>
    </el-col>
  </div>
</template>
<script>
  import {getListInfo, } from '@/api/api'
  export default {
    created() {
      this.listAllBlog();
    },
    data() {
      return {
        //查询输入框数据
        input: '',
        list: [] // 存放列表数据
      };
    },
    methods: {
      listAllBlog() {
        getListInfo().then((res) =>{
          console.log("进入了getListInfo");
          console.log("res==="+res)
          var result = res.responseData
          console.log("result==="+result)
          this.list = result;
        })
      },
      //时间格式化
      dateFormat: function(row, column) {
        var date = row[column.property];
        if (date == undefined) {
          return "未填";
        }
        return this.$moment(date).format("YYYY-MM-DD HH:mm:ss");
      },
      deleteBlog(index, row) {
        this.$axios.delete("deleteBlogById/" + row.id).then(result => {
          if (result.code === 200) {
            // 删除成功
            this.listAllBlog();
          } else {
            alert("删除失败！");
          }
        });
      },
      search() {
        this.$axios.get("getBlogById/" + this.input).then(result => {
          var result = result.body;
          if (result.code === 200) {
            this.list = [];
            this.list.push(result.data);
          } else {
            alert("查找失败！");
          }
        });
      },
      updateBlog(index, row) {
        this.$router.push({
          path: `/updateBlog/${row.id}`
        });
      }
    }
  };
</script>
