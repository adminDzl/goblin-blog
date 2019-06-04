// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import store from './store'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import APlayer from '@moefe/vue-aplayer'

// 导入字体图标库
import './assets/font/css/font-awesome.min.css'
import './assets/iconfont/iconfont.css'

import marked from 'marked'
// 安装npm install highlight.js --save
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

// APlayer:https://aplayer.moefe.org/docs/guide/options.html#lrctype
Vue.use(APlayer, {
  defaultCover: 'https://p1.music.126.net/5zs7IvmLv7KahY3BFzUmrg==/109951163635241613.jpg?param=300y300',
  defaultUrl: 'https://cdn.moefe.org/music/mp3/kiss.mp3',
  productionTip: true,
})

Vue.use(ElementUI)

Vue.config.productionTip = false
Vue.prototype.$ajax = axios
Vue.prototype.base_url = 'http://localhost:8081/goblin'

//axios拦截器
// 超时时间
axios.defaults.timeout = 5000;
// http请求拦截器
axios.interceptors.request.use(config => {
	if (config.method == 'post') {
		config.data = QS.stringify(config.data); //防止post请求参数无法传到后台
	}
	return config
}, error => {
	alert("加载超时")
	return Promise.reject(error)
})
// http响应拦截器
axios.interceptors.response.use(data => {
	return data
}, error => {
	alert("加载失败")
	return Promise.reject(error)
})

/* eslint-disable no-new */
new Vue({
	el: '#app',
	router,
	store,
	components: {
		App
	},
	template: '<App/>'
})
