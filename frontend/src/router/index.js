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
import AddWorker from '@/components/application/schedulingManage/AddWorker'
import ClassWithWorkers from '@/components/application/schedulingManage/ClassWithWokers'
import MainPageOfUser from '@/components/MainPageOfUser'
import Video from '@/components/monitoring/videoMonitor/Video'
import MainOfUser from '@/components/application/MainOfUser'
import AllRoadEvent from '@/components/application/eventManage/AllRoadEvent'
import appMainOfUser from '@/components/application/MainOfUser'
import monitorMainOfUser from '@/components/monitoring/MainOfUser'
import publishMain from '@/components/publish/Main'
import NewsMessage from '@/components/publish/NewsMessage'
import NewsContent from '@/components/publish/unchangeInfo/NewsContent'
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
    },
    {
      path: '/application/add',
      name: 'add',
      component: AddWorker
    },
    {
      path: '/application/classwithworkers',
      name: 'classwithworkers',
      component: ClassWithWorkers
    },
    {
      path: '/mainofuser',
      name: 'MainPageOfUser',
      component: MainPageOfUser
    },
    {
      path: '/monitoring/video',
      name: 'Video',
      component: Video
    },
    {
      path: '/monitoring/mainofuser',
      name: 'monitorMainOfUser',
      component: monitorMainOfUser
    },
    {
      path: '/application/mainofuser',
      name: 'MainOfUser',
      component: MainOfUser
    },
    {
      path: '/application/allroadevent',
      name: 'AllRoadEvent',
      component: AllRoadEvent
    },
    {
      path: '/application/mainofuser',
      name: 'appMainOfUser',
      component: appMainOfUser
    },
    {
      path: '/publish',
      name: 'publishMain',
      component: publishMain
    },
    {
      path: '/publish/news',
      name: 'NewsMessage',
      component: NewsMessage
    },
    {
      path: '/publish/content',
      name: 'NewsContent',
      component: NewsContent
    }
  ]
})
