import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'
import echarts from 'echarts'
import axios from 'axios'
import moment from "moment"

Vue.prototype.$moment = moment;
Vue.prototype.$axios = axios;

axios.defaults.baseURL = 'http://localhost:8083';

Vue.prototype.$echarts = echarts
Vue.use(ElementUI)
// 添加响应拦截器
axios.interceptors.response.use(function (response) {
  // 对响应数据做点什么
  return response;
}, function (error) {
  // 对响应错误做点什么
  return Promise.reject(error);
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

