import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/components/LoginPage'
import MainPage from '@/components/MainPage'
import appMain from '@/components/application/Main'
import monitorMain from '@/components/monitoring/Main'
import AddUserList from '@/components/application/systemManage/AddUserList'
import UpdateBlog from '@/components/application/systemManage/UpdateBlog'
import BaiduMap from '@/components/monitoring/basicInfoManage/BaiduMap'
import ChartViews from '@/components/monitoring/analysisSystem/ChartViews'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: '/main',
      name: 'MainPage',
      component: MainPage
    },
    {
      path: '/application',
      name: 'appMain',
      component: appMain
    },
    {
      path: '/monitoring',
      name: 'monitorMain',
      component: monitorMain
    },
    {
      path: '/adduser',
      name: 'AddUserList',
      component: AddUserList
    },
    {
      path: '/updateuser/:id',
      name: 'UpdateBlog',
      component: UpdateBlog
    },
    {
      path: '/bdmap',
      name: 'BaiduMap',
      component: BaiduMap
    },
    {
      path: '/chartview',
      name: 'ChartViews',
      component: ChartViews
    }
  ]
})
