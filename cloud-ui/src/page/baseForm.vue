<template>
  <el-form ref="form" :model="form" label-width="120px">
    <el-row :gutter="12">
      <el-col :span="12">
        <el-form-item label="账户" prop="account">
          <el-input v-model="form.account"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="formSubmit">提交</el-button>
        </el-form-item>
      </el-col>

    </el-row>

  </el-form>
</template>
<script>
  export default{
    data(){
      return{
        form:{
          account:"",
          password:""
        }
      }
    },
    methods:{
      formSubmit(){

      }
    }
  }
</script>
