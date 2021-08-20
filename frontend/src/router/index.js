import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'
import Introduction from '@/components/Introduction'
import Comments from '@/components/Comments'
import Download from '@/components/Download'
import UserMana from '@/components/UserMana'
import DataChart from '@/components/DataChart'

// add the router in global range
Vue.use(Router);

// configure the router
export default new Router({
  routes: [
    {
      path: '/',
      name: '',
      redirect: '/introduction',
      hidden: true
    }, {
      path: '/home',
      name: 'Home',
      redirect: '/introduction',
      component: Home,
      hidden: true
    }, {
      path: '/login',
      name: 'login',
      hidden: true,
      component: Login
    }, {
      path: '/home',
      component: Home,
      name: 'about game',
      iconCls: 'fa fa-file-text-o',
      children: [
        {
          path: '/introduction',
          name: 'About the Game',
          component: Introduction,
          meta: {
            keepAlive: true
          }
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: 'comments',
      children: [
        {
          path: '/comments',
          iconCls: 'fa fa-user-o',
          name: 'Comments',
          component: Comments
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: 'download',
      children: [
        {
          path: '/download',
          iconCls: 'fa fa-reorder',
          name: 'Download',
          component: Download
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: 'user',
      children: [
        {
          path: '/user',
          iconCls: 'fa fa-user-o',
          name: 'User Manager',
          component: UserMana
        }
      ]
    },{
      path: '/home',
      component: Home,
      name: 'statistics',
      iconCls: 'fa fa-bar-chart',
      children: [
        {
          path: '/statistics',
          iconCls: 'fa fa-bar-chart',
          name: 'Statistics',
          component: DataChart
        }
      ]
    }
  ]
});
