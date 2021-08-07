<template>
  <el-container class="home_container">
    <el-header>
      <div class="home_title">Alien Invasion</div>
      <div class="home_userinfoContainer">
        <span v-if="!isLogin" class="el-dropdown-link home_userinfo">
          <router-link to="/login">Sign In or Sign up</router-link>
        </span>
        <span v-else class="el-dropdown-link home_userinfo">
          Welcome, {{currentUserName}}
          <span v-on:click="logout" class="el-dropdown-link home_userinfo">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            Sign Out
          </span>
        </span>
      </div>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          default-active="0"
          class="el-menu-vertical-demo" style="background-color: #ECECEC" router>
          <template v-for="(item,index) in this.$router.options.routes" v-if="!item.hidden">
            <template v-if="isAdmin || index<6">
              <el-menu-item :index="item.children[0].path">
                <i :class="item.children[0].iconCls"></i>
                <span slot="title">{{item.children[0].name}}</span>
              </el-menu-item>
            </template>
          </template>
        </el-menu>
      </el-aside>
      <el-container>
        <router-view></router-view>
      </el-container>
    </el-container>
  </el-container>
</template>
<script>
  import {getRequest} from '../utils/api'

  export default{
    methods: {
      logout() {
        this.isLogin = false;
        this.isAdmin = false;
        sessionStorage.removeItem("username");
        sessionStorage.removeItem("role");
        this.$router.replace({path: '/home'});
      }
    },
    mounted: function () {
      currentUserName:
      sessionStorage.getItem("username")==null? "" : sessionStorage.getItem("username");
    },
    data(){
      return {
        currentUserName:
        sessionStorage.getItem("username")==null? "" : sessionStorage.getItem("username"),
        isLogin: sessionStorage.getItem("username")!=null,
        isAdmin: sessionStorage.getItem("role")!=null
      }
    }
  }
</script>
<style>
  .home_container {
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
  }

  .el-header {
    background-color: #20a0ff;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .el-aside {
    background-color: #ECECEC;
  }

  .el-main {
    background-color: #fff;
    color: #000;
    text-align: center;
  }

  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }

  .home_userinfoContainer {
    display: inline;
    margin-right: 20px;
  }
</style>
