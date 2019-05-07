import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from './router/index.js'
import axios from 'axios'
import moment from "moment"
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'
// 建立连接对象
// var socket = new SockJS('http://localhost:8083/websocket')
// // 获取STOMP子协议的客户端对象
// var stompClient = Stomp.over(socket)
Vue.prototype.$moment = moment;
Vue.prototype.$axios = axios;

// export default {
//   connect() {
//     // 向服务器发起websocket连接
//     return stompClient.connect({},
//       function (frame) {
//         console.log(frame)
//         console.log('sure connected')
//         window.connected = true
//         // 订阅服务端提供的某个queue
//         stompClient.subscribe('/queue/chat', tick => {
//           console.log(tick)
//           console.log('message subscribed got')
//           store.dispatch('chat/pushIntoMessage', JSON.parse(tick.body))//tick.body存放的是服务端发送给我们的信息
//           console.log('message subscribed got and made it ')
//         })
//         console.log('subscribed')
//       },
//       function (error) {
//         console.log(error)
//         console.log('Error occurred')
//       })
//   },
// }
// axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
axios.defaults.baseURL = 'http://localhost:8083';
// const enhanceAccessToeken = () => {
//   const {accessToken} = localStorage
//   if (!accessToken) return
//   axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`
// }
// enhanceAccessToeken()
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
  // 对响应数据做点什么
  return response;
}, function (error) {
  // 对响应错误做点什么
  return Promise.reject(error);
});

// 安装 ElementUI（ui）
Vue.use(ElementUI)

// 安装 路由（url）
Vue.use(VueRouter)

new Vue({
  el: '#app',
  render: h => h(App),
  //
  router
})
