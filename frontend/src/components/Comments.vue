<template>
  <el-container class="article_list">
    <el-main class="main">
      <el-tabs v-model="activeName" @tab-click="handleClick" type="card">
        <el-tab-pane label="All Comments" name="all">
          <comment_table v-if="'all'===activeName" state="-1" :showEdit="false" :showDelete="isAdmin || false" :showUser="true" :activeName="activeName"></comment_table>
        </el-tab-pane>
        <el-tab-pane label="My Comments" name="user" v-if="isLogin">
          <comment_table v-if="'user'===activeName" state="1" :showEdit="true" :showDelete="true" :showUser="false" :activeName="activeName"></comment_table>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script>
  import CommentTable from '@/components/CommentTable'
  import {postRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  import {deleteRequest} from '../utils/api'
  import {getRequest} from '../utils/api'

  export default {

    data() {
      return {
        activeName: 'all',
        isAdmin: sessionStorage.getItem("role")!=null,
        isLogin: sessionStorage.getItem("username")!=null,
      };
    },

    mounted: function() {
    },

    methods: {
      handleClick(tab, event) {

      },
    },

    components: {
      'comment_table': CommentTable,
    }
  };
</script>
<style>
  .article_list > .header {
    background-color: #ececec;
    margin-top: 10px;
    padding-left: 5px;
    display: flex;
    justify-content: flex-start;
  }

  .article_list > .main {
    /*justify-content: flex-start;*/
    display: flex;
    flex-direction: column;
    padding-left: 0px;
    background-color: #fff;
    padding-top: 0px;
    margin-top: 8px;
  }
</style>
