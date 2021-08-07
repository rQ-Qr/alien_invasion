<template>
  <el-container v-loading="loading" class="post-article">
    <el-main class="main">
      <div>
        <el-input
          class="input-new-tag"
          type="textarea"
          rows="5"
          v-model="comment.content"
          placeholder="please input comment"
        >
        </el-input>
      </div>
      <div style="display: flex;align-items: center; margin-top: 15px; justify-content: flex-end">
        <div>
          <template>
            <el-button type="primary" @click="saveComment()">Submit</el-button>
          </template>
        </div>
      </div>
    </el-main>
  </el-container>
</template>
<script>
  import {postRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  import {deleteRequest} from '../utils/api'
  import {getRequest} from '../utils/api'
  // Local Registration
  import {isNotNullORBlank} from '../utils/utils'
  export default {
    methods: {
      saveComment(){
        if (!(isNotNullORBlank(this.comment.content))) {
          this.$message({type: 'error', message: 'Input is empty!'});
          return;
        }
        var _this = this;
        _this.loading = true;
        postRequest("/comment/", {
          id: _this.comment.id,
          content: _this.comment.content,
          uid: _this.comment.uid,
          username: _this.comment.username
        }).then(resp=> {
          _this.loading = false;
          if (resp.status == 200 && resp.data.status == 'success') {
            _this.comment.id = resp.data.msg;
            _this.$message({type: 'success', message: 'Submit success!'});
            _this.$router.replace({path: '/comments'});
          }
        }, resp=> {
          _this.loading = false;
          _this.$message({type: 'error', message: 'Submit fail!'});
        })
      },
    },
    data() {
      return {
        isAdmin: sessionStorage.getItem("role")!=null,
        loading: false,
        comment: {
          id: '-1',
          content: '',
          uid: sessionStorage.getItem("uid"),
          username: sessionStorage.getItem("username")
        }
      }
    }
  }
</script>
<style>
  .post-article > .main > #editor {
    width: 90%;
    height: 200px;
    text-align: center;
  }
  .post-article > .header {
    background-color: #ececec;
    margin-top: 10px;
    padding-left: 5px;
    display: flex;
    justify-content: flex-start;
  }
  .post-article > .main {
    /*justify-content: flex-start;*/
    display: flex;
    flex-direction: column;
    padding-left: 30px;
    padding-right: 30px;
    background-color: #ececec;
    padding-top: 20px;
  }
  .post-article > .header > .el-tag + .el-tag {
    margin-left: 10px;
  }
  .post-article > .header > .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .post-article > .header > .input-new-tag {
    width: 50px;
    margin-left: 20px;
    vertical-align: bottom;
  }
  .post-article {
  }
</style>
