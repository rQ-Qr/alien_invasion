<template>
  <div class="login">
    <div>
      <el-input placeholder="please input username" v-model="name" clearable class="input_style"></el-input>
      <span v-if="error.name" class="err-msg">{{error.name}}</span>
    </div>
    <div>
      <el-input placeholder="please input password" v-model="pwd" show-password class="input_style"></el-input>
      <span v-if="error.pwd" class="err-msg">{{error.pwd}}</span>
    </div>
    <div>
      <el-button type="primary" @click="reg" class="login_style">Register</el-button>
    </div>
    <div>
      <el-button type="primary" @click="login" class="login_style">Login</el-button>
    </div>
  </div>
</template>

<script>
  import {postRequest} from '../utils/api'
  import {putRequest} from '../utils/api'

  export default {
    name: "Login",
    data(){
      return {
        name: '',
        pwd : '',
        role: 0,
        error : {
          name: '',
          pwd : ''
        }
      }
    },
    methods:{
      reg(){
        var _this = this;
        const { name, pwd, role, $router} = this

        postRequest('/reg',{
            username: name,
            password: pwd,
            role: role
        })
        .then(resp=> {
          if (resp.status == 200) {
            //success
            var json = resp.data;
            if (json.status == 'success') {
              console.log(json.uid);
              if(json.role==2)
                sessionStorage.setItem("role", "admin");
              sessionStorage.setItem("username", json.username);
              sessionStorage.setItem("uid", json.uid);
              _this.$router.replace({path: '/home'});
            } else {
              _this.$alert(json.msg, 'Fail!');
            }
          } else {
            //fail
            _this.$alert('Register Fail!', 'Fail!');
          }
        }, resp=> {
          _this.loading = false;
          _this.$alert('server doesn\'t exist\\⊙﹏⊙∥!', 'Fail!');
        });
      },
      login(){
        var _this = this;
        const { name, pwd, $router} = this

        postRequest('/login',{
            username: name,
            password: pwd,
        })
        .then(resp => {
          if (resp.status == 200) {
            //success
            var json = resp.data;
            if (json.status == 'success') {
              if(json.role==2)
                sessionStorage.setItem("role", "admin");
              sessionStorage.setItem("username", json.username);
              sessionStorage.setItem("uid", json.uid);
              _this.$router.replace({path: '/home'});
            } else {
              _this.$alert(json.msg, 'Fail!');
            }
          } else {
            //fail
            _this.$alert('Login Fail!', 'Fail!');
          }
        }, resp=> {
          _this.loading = false;
          _this.$alert('server doesn\'t exist\\⊙﹏⊙∥!', 'Fail!');
        });
      }
    }
  }
</script>

<style>
  .login{
    margin-top: 200px;
  }
  .input_style{
    width: 200px;
    margin-bottom: 10px;
  }
  .login_style{
    width: 200px;
  }
</style>
