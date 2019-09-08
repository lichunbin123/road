<template>
  <div>
  <div id="title" class="title" style="text-align:center;font-size:30px">
    <h>事件信息编辑</h>
  </div>
  <div id="body" class="body">
  <el-form ref="form" :model="form" :rules="rules" label-width="100px">
    <el-form-item label="信息分类" prop="classification" style="width: 300px">
      <el-select v-model="form.classification" clearable placeholder="请选择" :rules="rules.classification">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="信息标题" prop="title" style="width: 500px">
      <el-input v-model="form.title" :rules="rules.title" placeholder="请输入标题" auto-complete="on"></el-input>
    </el-form-item>
    <el-form-item label="信息作者" prop="author" style="width: 500px">
      <el-input v-model="form.author" :rules="rules.author" placeholder="请输入作者" auto-complete="on"></el-input>
    </el-form-item>
    <el-form-item label="信息来源" prop="origin" style="width: 500px">
      <el-input v-model="form.origin" :rules="rules.origin" placeholder="请输入信息来源" auto-complete="on"></el-input>
    </el-form-item>
    <el-form-item label="详细内容">
      <quill-editor
        class="ql-editor"
        v-model="form.content"
        ref="myQuillEditor"
        :options="editorOption"
        @blur="onEditorBlur($event)"
        @focus="onEditorFocus($event)"
        @change="onEditorChange($event)"></quill-editor>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit('form')">立即发布</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
  </div>
  </div>
</template>
<script>
  import axios from 'axios'
  import { quillEditor, Quill } from "vue-quill-editor";
  export default {
    components: {
      quillEditor
    },
    data() {
      return {
        form: {
          classification: '',
          title: '',
          author: '',
          origin: '',
          content: '',
        },
        rules: {
          classification: [
            {required: true, message: '请输入类型', trigger: 'blur'}
          ],
          title: [
            {required: true, message: '请输入标题', trigger: 'blur'}
          ],
          author: [
            {required: true, message: '请输入作者名', trigger: 'blur'}
          ],
          origin: [
            {required: true, message: '请输入信息来源', trigger: 'blur'}
          ],
          content: [
            {required: true, message: '请输入详细内容', trigger: 'blur'}
          ],
        },
        options: [{
          value: '新闻',
          label: '新闻'
        }, {
          value: '通知',
          label: '通知'
        }, {
          value: '公告',
          label: '公告'
        }],
        value: '',
        content:" ",
        editorOption: {}
      }
    },
    methods: {
      onSubmit: function() {
        let data = this.form;
        console.log(data.classification);
        console.log(data.content);
        if (data.classification=="" || data.author=="" || data.content=="" || data.title=="" || data.origin==""){
          this.$message("必填内容不能为空")
        }else {
          const url = `http://localhost:8083/publish/edit`
          return axios({
            method: 'post',
            url: url,
            data: data
          }).then(res => {
            if (res.status == 200){
              this.$message("信息发布成功")
            }else {
              this.$message("信息发布失败")
            }
          }).catch(err => {
            console.log(err.response)
            this.$message("登录失败")
          })
        }
      },
      onEditorBlur(){//失去焦点事件
      },
      onEditorFocus(){//获得焦点事件
      },
      onEditorChange(){//内容改变事件
      }
    }
  }
</script>
<style scoped>

</style>

