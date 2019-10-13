<template>
  <div>
    <div class="title">
      <h>应急预案管理</h>
    </div>
    <div>
      <el-button type="primary" icon="el-icon-circle-plus" style="float: right" @click="dialogFormVisible = true">预案上传
      </el-button>
      <el-dialog title="应急预案上传" :visible.sync="dialogFormVisible" style="margin-left:500px; width: 1000px">
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :file-list="fileList"
          :show-file-list = "true"
          :before-upload="beforeUpload"
          list-type="picture">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传doc/pdf/jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-dialog>
      <el-table
        :data="tableData"
        border
        style="width: 100%">
        <el-table-column
          prop="name"
          label="预案名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="date"
          label="预案上传时间">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="success" size="small">查看详情</el-button>
            <el-button @click="download(scope.row)" type="success" size="small">下载</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'
  export default {
    name: "PlanManage",
    data() {
      return {
        tableData: [],
        dialogTableVisible: false,
        dialogFormVisible: false,
        fileList: [],
      }
    },
    created() {
      this.selectPlan();
    },
    methods: {
      selectPlan(){
        return axios.post("http://localhost:8083/disposal/selectPlan").then(res => {
          if (res.status == 200) {
            var result = res.data;
            console.log(result);
            this.tableData = result.responseData;
          }
        }).catch(err => {
          console.log(err.response)
          this.$message("统计失败")
        })
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      download(row){
        console.log(row.name);
        var firstname = row.name;
        var index = firstname.lastIndexOf("\.");
        var secondname = firstname.substring(index+1,firstname.length);
        console.log("secondname==="+secondname);
        var name = firstname.replace(".","%23");
        console.log(name);
        const url = `http://localhost:8083/disposal/download/`+name;
        return axios({
          method: 'post',
          url: url,
          responseType: 'blob'
        }).then(res => {
          if (res.status == 200){
            var result = res.data;
            console.log(result);
            if (secondname == "doc"){
              let blob = new Blob([result],{type : 'application/msword; charset=utf-8'});
              let downloadElement = document.createElement('a');
              let href = window.URL.createObjectURL(blob); //创建下载的链接
              downloadElement.href = href;
              downloadElement.download = row.name; //下载后文件名
              document.body.appendChild(downloadElement);
              downloadElement.click(); //点击下载
              document.body.removeChild(downloadElement); //下载完成移除元素
              window.URL.revokeObjectURL(href); //释放掉blob对象
            } else if (secondname == "jpg"){
              let blob = new Blob([result],{type : 'image/jpeg'});
              let downloadElement = document.createElement('a');
              let href = window.URL.createObjectURL(blob); //创建下载的链接
              downloadElement.href = href;
              downloadElement.download = row.name; //下载后文件名
              document.body.appendChild(downloadElement);
              downloadElement.click(); //点击下载
              document.body.removeChild(downloadElement); //下载完成移除元素
              window.URL.revokeObjectURL(href); //释放掉blob对象
            }else if (secondname == "pdf"){
              let blob = new Blob([result],{type : 'application/pdf'});
              let downloadElement = document.createElement('a');
              let href = window.URL.createObjectURL(blob); //创建下载的链接
              downloadElement.href = href;
              downloadElement.download = row.name; //下载后文件名
              document.body.appendChild(downloadElement);
              downloadElement.click(); //点击下载
              document.body.removeChild(downloadElement); //下载完成移除元素
              window.URL.revokeObjectURL(href); //释放掉blob对象
            }else if (secondname == "png"){
              let blob = new Blob([result],{type : 'image/png'});
              let downloadElement = document.createElement('a');
              let href = window.URL.createObjectURL(blob); //创建下载的链接
              downloadElement.href = href;
              downloadElement.download = row.name; //下载后文件名
              document.body.appendChild(downloadElement);
              downloadElement.click(); //点击下载
              document.body.removeChild(downloadElement); //下载完成移除元素
              window.URL.revokeObjectURL(href); //释放掉blob对象
            }
          }
        }).catch(err => {
          console.log(err.response)
          this.$message("下载出错")
        })

      },
      beforeUpload(file){
        console.log(file);
        let fd = new FormData();
        fd.append('file', file);
        const url = `http://localhost:8083/disposal/insertPlan`
        return axios({
          method: 'post',
          url: url,
          data: fd
        }).then(res => {
          if (res.status == 200){
            this.$message("正在上传");
          }
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
  }
</style>
