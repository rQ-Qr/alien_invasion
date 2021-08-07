<template>
  <div>
    <!--<div style="width: 100%;height: 1px;background-color: #20a0ff;margin-top: 8px;margin-bottom: 0px"></div>-->
    <el-table
      ref="multipleTable"
      :data="comments"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390"
      v-loading="loading">
      <el-table-column
        label="Comment"
        width='650%' align="center" v-if="showDelete">
        <template slot-scope="scope"><span style="color: #409eff;cursor: pointer" @click="itemClick(scope.row)">{{ scope.row.content}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="Comment"
        width='750%' align="center" v-if="!showDelete">
        <template slot-scope="scope"><span style="color: #409eff;cursor: pointer" @click="itemClick(scope.row)">{{ scope.row.content}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="nickname"
        label="User"
        width="150%"
        v-if="showUser" align="center">
        <template slot-scope="scope">{{ scope.row.username }}</template>
      </el-table-column>
      <el-table-column
        label="Edit Time" width="150%" align="center">
        <template slot-scope="scope">{{ scope.row.editTime }}</template>
      </el-table-column>
      <el-table-column label="Operation" align="center" v-if="showEdit || showDelete">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)" v-if="showEdit">Edit
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row.id)" v-if="showDelete">Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="blog_table_footer">
      <span></span>
      <el-pagination
        background
        :page-size="pageSize"
        layout="prev, pager, next"
        :total="totalCount" @current-change="currentChange" v-show="totalCount>0">
      </el-pagination>
    </div>

    <el-container v-loading="loading" class="post-article" v-if="showEdit">
      <el-main class="main">
        <div>
          <el-input
            class="input-new-tag"
            type="textarea"
            rows="5"
            v-model="comment.content"
            ref="input"
            placeholder="please input comment"
          >
          </el-input>
        </div>
        <div style="display: flex;align-items: center; margin-top: 15px; justify-content: flex-end">
          <div>
            <el-button type="danger" right="30px" v-if="editComment" @click="cancelEdit()">Cancel Edit</el-button>
            <el-button type="primary" @click="saveComment()">{{ button }}</el-button>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import {postRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  import {deleteRequest} from '../utils/api'
  import {getRequest} from '../utils/api'
  import {isNotNullORBlank} from '../utils/utils'

  export default{
    data() {
      return {
        comments: [],
        loading: false,
        currentPage: 1,
        totalCount: -1,
        pageSize: 6,
        button: 'Submit Comment',
        editComment: false,

        isAdmin: sessionStorage.getItem("role")!=null,
        comment: {
          id: '-1',
          content: '',
          uid: sessionStorage.getItem("uid"),
          username: sessionStorage.getItem("username")
        }
      }
    },
    mounted: function () {
      var _this = this;
      _this.loading = true;
      _this.loadComments(1, _this.pageSize);
      if(this.showDelete) _this.comment_width = '650%';
      window.bus.$on('commentTableReload', function () {
        _this.loading = true;
        _this.loadComments(_this.currentPage, _this.pageSize);
      })
    },
    methods: {
      handleEdit(index, data) {
        var _this = this;
        _this.comment.content = data.content;
        _this.comment.id = data.id;
        _this.button = 'Submit Edit';
        _this.editComment = true;
        _this.$refs.input.focus();
      },
      cancelEdit() {
        var _this = this;
        _this.comment.content = '';
        _this.comment.id = '-1';
        _this.button = 'Submit Comment';
        _this.editComment = false;
      },
      saveComment(){
        if (!(isNotNullORBlank(this.comment.content))) {
          this.$message({type: 'error', message: 'Input is empty!'});
          return;
        }
        var _this = this;
        _this.loading = true;
        var newComment = {
          id: _this.comment.id,
          content: _this.comment.content,
          uid: _this.comment.uid,
          username: _this.comment.username
        }
        postRequest("/comment/", newComment)
        .then(resp=> {
          _this.loading = false;
          if (resp.status == 200 && resp.data.status == 'success') {
            _this.comment.id = resp.data.msg;
            _this.$message({type: 'success', message: 'Submit success!'});
            _this.comment.content = '';
            _this.button = 'Submit Comment';
            _this.loadComments(_this.currentPage, _this.pageSize);
            _this.$forceUpdate();

          }
        }, resp=> {
          _this.loading = false;
          _this.$message({type: 'error', message: 'Submit fail!'});
        });

      },

      searchClick(){
        this.loadComments(1, this.pageSize);
      },

      //翻页
      currentChange(currentPage){
        this.currentPage = currentPage;
        this.loading = true;
        this.loadComments(currentPage, this.pageSize);
      },

      loadComments(page, count){
        var _this = this;
        var url = "/comment/all?state=" + this.state + "&page=" + page + "&count=" + count;
        if(_this.showEdit) url = url + "&uid=" + sessionStorage.getItem("uid");
        getRequest(url).then(resp=> {
          _this.loading = false;
          if (resp.status == 200) {
            _this.comments = resp.data.comments;
            _this.totalCount = resp.data.totalCount;
          } else {
            _this.$message({type: 'error', message: 'Data load fail!'});
          }
        }, resp=> {
          _this.loading = false;
          if (resp.response.status == 403) {
            _this.$message({type: 'error', message: resp.response.data});
          } else {
            _this.$message({type: 'error', message: 'Data load fail!'});
          }
        }).catch(resp=> {
          //no server
          _this.loading = false;
          _this.$message({type: 'error', message: 'Data load fail!'});
        })
      },

      handleDelete(index, id){
        var _this = this;
        this.$confirm('Do you want to delete the comment?', 'Warning', {
          confirmButtonText: 'Cinfirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          _this.loading = true;
          var url = '/comment/'+id;
          deleteRequest(url).then(resp=> {
            if (resp.status == 200) {
              var data = resp.data;
              _this.$message({type: data.status, message: data.msg});
              if (data.status == 'success') {
                window.bus.$emit('commentTableReload')
              }
            } else {
              _this.$message({type: 'error', message: 'Delete fail!'});
            }
            _this.loading = false;
          }, resp=> {
            _this.loading = false;
            _this.$message({type: 'error', message: 'Delete fail!'});
          });
        }).catch(() => {
          _this.$message({
            type: 'info',
            message: 'Cancel the delete'
          })
        });
      }
    },

    props: ['state', 'showEdit', 'showDelete', 'activeName', 'showRestore', 'showUser']
  }
</script>


<style>
  .post-article > .main {
    /*justify-content: flex-start;*/
    display: flex;
    flex-direction: column;
    padding-left: 30px;
    padding-right: 30px;
    background-color: white;
    padding-top: 20px;
  }

  .post-article > .header > .input-new-tag {
    width: 50px;
    margin-left: 20px;
    vertical-align: bottom;
  }
  .post-article {
  }
</style>

<style type="text/css">
  .blog_table_footer {
    display: flex;
    box-sizing: content-box;
    padding-top: 10px;
    padding-bottom: 0px;
    margin-bottom: 0px;
    justify-content: space-between;
  }
</style>
