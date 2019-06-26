<template>
  <el-form :model="dynamicValidateForm" ref="dynamicValidateForm" label-width="100px" class="demo-dynamic">
    <el-form-item label="班次类型">
      <el-select v-model="tag" placeholder="请选择">
        <el-option label="领导" value="1"></el-option>
        <el-option label="普通员工" value="0"></el-option>
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
      <el-input v-model="domain.value"></el-input><el-button @click.prevent="removeDomain(domain)">删除</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('dynamicValidateForm')">提交</el-button>
      <el-button @click="addDomain">增加成员</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
    export default {
      name: "AddClass",
      data() {
        return {
          dynamicValidateForm: {
            domains: [{
              value: ''
            }],
          }
        };
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

</style>
