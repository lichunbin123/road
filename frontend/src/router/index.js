import VueRouter from 'vue-router'
import AddBlog from '@/components/addBlog'
import UpdateBlog from '@/components/updateBlog'
import ListBlog from '@/components/ListBlog'
import HomeBlog from '@/components/HomeBlog'
import Login from '@/components/login'
import Register from '@/components/register'
import LoginOut from '@/components/loginout'
// 创建路由对象
var router = new VueRouter({
  routes: [
    { path: '/listBlog', component: ListBlog },
    { path: '/addBlog', component: AddBlog },
    { path: '/updateBlog/:id', component: UpdateBlog },
    { path: '/', component: HomeBlog },
    { path: '/login', component: Login},
    { path: '/register', component: Register},
    { path: '/loginout', component:LoginOut}
  ]
})

// 把路由对象暴露出去
export default router
