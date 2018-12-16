<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-setting"></i> 首页</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div>
      <el-form>
        <el-form-item>
          Clicked: {{ count }} times, count is {{ evenOrOdd }}.
          <el-button @click="increment">增加</el-button>
        </el-form-item>
      </el-form>
      hello world hello world hello world hello world<br>
      hello world hello world hello world hello world<br>
      hello world hello world hello world hello world<br>
      hello world hello world hello world hello world<br>
    </div>

  </div>
</template>
<script>
  import { mapGetters, mapActions } from 'vuex';
  import store from '../store/modules/tab'

  export default {

//    computed:mapGetters([
//      'count',
//      'evenOrOdd'
//    ]),
    computed:{
      count(){
        console.log(store)
        return store.state.count;
      },
      evenOrOdd(){
        return this.$store.getters.evenOrOdd;
      }
    },
//    methods:mapActions([
//      'increment'
//    ])
    methods:{
      increment(){
        this.$store.dispatch('increment');
      }
    }
  }
</script>
