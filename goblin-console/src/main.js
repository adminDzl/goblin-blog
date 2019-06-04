// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'

// 导入axios请求库
import axios from 'axios'

// 引入资源请求插件
import VueResource from 'vue-resource'

// 引入element-ui组件
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 引入全局样式
import './assets/css/style.css'
// 导入字体图标库
import './assets/font-awesome/css/font-awesome.min.css'

// 全局注册mavonEditor编辑器
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import marked from 'marked'
import hljs from 'highlight.js'

// 将markdown语法解析为html
Vue.prototype.marked = marked

// 封装成一个指令 使用示例<div v-highlight v-html="content"></div>
Vue.directive('highlight', (el) => {
	var blocks = el.querySelectorAll('pre code')
	blocks.forEach((block) => {
		hljs.highlightBlock(block)
	})
})

Vue.prototype.$ajax = axios
// Vue.use(axios)

Vue.use(ElementUI)
// 使用VueResource插件
Vue.use(VueResource)

// use
Vue.use(mavonEditor)

Vue.config.productionTip = false

// 根路径
Vue.prototype.base_url = 'http://localhost:8081/goblin'

// 添加一个请求拦截器
axios.interceptors.request.use(function (config) {
	//这里经常搭配token使用，将token值配置到tokenkey中，将tokenkey放在请求头中
	config.headers['Authorization'] = localStorage.getItem('authorization')
	
	return config
})

// 响应拦截器
axios.interceptors.response.use((response) => {
	if(response.data.code === 20002){
		localStorage.removeItem('authorization')
	}
	if(response.data.code === 20003){
		alert('无权访问！！')
	}
	//获取返回的TOKEN
    var token = response.headers['Authorization']
    if(token) {
        //将续期的token存起来
        localStorage.setItem("authorization",token)
    }
    // 这里是填写处理信息
    return response
})

// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {  // 判断该路由是否需要登录权限
		var token = localStorage.getItem('authorization')
        if (token !== null && token!== '') {  // 通过vuex state获取当前的token是否存在
            next()
        }
        else {
            next({
                path: '/login',
                query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
            })
        }
    }
    else {
        next()
    }
})

/* eslint-disable no-new */
new Vue({
	el: '#app',
	store,
	router,
	components: {
		App
	},
	template: '<App/>'
})
