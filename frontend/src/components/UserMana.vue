<template>
  <div v-loading="loading">
    <div style="margin-top: 20px;display: flex;justify-content: center">
      <el-input
        placeholder="searching user by username..."
        prefix-icon="el-icon-search"
        v-model="keywords" style="width: 400px" size="small">
      </el-input>
      <el-button type="primary" icon="el-icon-search" size="small" style="margin-left: 3px" @click="searchClick">Search
      </el-button>
    </div>
    <div style="display: flex;justify-content: space-around;flex-wrap: wrap">
      <el-card style="width:330px;margin-top: 20px;margin-left: 20px;" v-for="(user,index) in users" :key="index"
      >
        <div>
          <el-button style="float: right; padding: 0px 0;color: #ff0509" type="text" icon="el-icon-delete"
                     @click="deleteUser(user.id)">Delete
          </el-button>
          <div style="text-align: left;color:#20a0ff;font-size: 12px;margin-top: 13px">
            <span>User Name:&nbsp&nbsp</span><span>{{user.username}}</span>
          </div>
          <div style="text-align: left;color:#20a0ff;font-size: 12px;margin-top: 13px">
            <span>Best Score:&nbsp&nbsp&nbsp</span><span>{{user.score}}</span>
          </div>
          <div style="text-align: left;color:#20a0ff;font-size: 12px;margin-top: 13px">
            <span>Reg Time:&nbsp&nbsp&nbsp&nbsp&nbsp</span><span>{{user.regTime | formatDateTime}}</span>
          </div>
          <div style="text-align: left;color:#20a0ff;font-size: 12px;margin-top: 13px">
            <form>
              <span>User Role:&nbsp&nbsp&nbsp</span>
              <el-select v-model="user.role" style="width:42%" size="mini">
                <el-option
                  v-for="item in roles"
                  :key="item.role"
                  :label="item.label"
                  :value="item.value"
                  selected="selected">
                </el-option>
              </el-select>
              <el-button type="text" icon="el-icon-edit-outline" style="padding-top: 0px" slot="reference"
                         @click="updateRole(user.role, user.id, index)">Change</el-button>
            </form>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
  import {getRequest} from '../utils/api'
  import {putRequest} from '../utils/api'
  import {deleteRequest} from '../utils/api'

  export default{

    mounted: function () {
      this.loading = true;
      this.loadUserList();
      this.cardloading = Array.apply(null, Array(20)).map(function (item, i) {
        return false;
      });
      this.eploading = Array.apply(null, Array(20)).map(function (item, i) {
        return false;
      });
    },

    methods: {
      updateRole(role, id, index){
        var _this = this;
        if (id==sessionStorage.getItem("uid")) {
          _this.$message({type: 'error', message: 'You cannot change your role!'});
          return;
        }
        _this.cardloading.splice(index, 1, true)
        putRequest("/admin/user/role", {role: role, id: id}).then(resp=> {
          if (resp.status == 200 && resp.data.status == 'success') {
            _this.$message({type: resp.data.status, message: resp.data.msg});
            _this.loadOneUserById(id, index);
          } else {
            _this.cardloading.splice(index, 1, false)
            _this.$message({type: 'error', message: 'Update fail!'});
          }
        }, resp=> {
          _this.cardloading.splice(index, 1, false)
          if (resp.response.status == 403) {
            var data = resp.response.data;
            _this.$message({type: 'error', message: data});
          }
        });
      },

      deleteUser(id){
        var _this = this;
        if (id==sessionStorage.getItem("uid")) {
          _this.$message({type: 'error', message: 'You cannot delete yourself!'});
          return;
        }
        this.$confirm('Are you sure to delete the user?', 'Warning', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          _this.loading = true;
          deleteRequest("/admin/user/" + id).then(resp=> {
            if (resp.status == 200 && resp.data.status == 'success') {
              _this.$message({type: 'success', message: 'Deletion success!'})
              _this.loadUserList();
              return;
            }
            _this.loading = false;
            _this.$message({type: 'error', message: 'Deletion fail!'})
          }, resp=> {
            _this.loading = false;
            _this.$message({type: 'error', message: 'Deletion fail!'})
          });
        }).catch(() => {
          _this.$message({
            type: 'info',
            message: 'Cancel deletion'
          });
        });
      },

      loadUserList(){
        var _this = this;
        getRequest("/admin/user?username="+this.keywords).then(resp=> {
          _this.loading = false;
          if (resp.status == 200) {
            _this.users = resp.data;
            console.log(_this.users);
          } else {
            _this.$message({type: 'error', message: 'Data load fail!'});
          }
        }, resp=> {
          _this.loading = false;
          if (resp.response.status == 403) {
            var data = resp.response.data;
            _this.$message({type: 'error', message: data});
          }
        });
      },
      searchClick(){
        this.loading = true;
        this.loadUserList();
      }
    },
    
    data(){
      return {
        loading: false,
        eploading: [],
        cardloading: [],
        keywords: '',
        users: [],
        allRoles: [],
        roles: [
        {value: 0, label: 'regular user'},
        {value: 1, label: 'premium user'},
        {value: 2, label: 'administrator'}],
        cpRoles: []
      }
    }
  }
</script>
