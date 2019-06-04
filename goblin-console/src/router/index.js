import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Writeblog from '@/components/essay/Writeblog'
import Editblog from '@/components/essay/Editblog'

Vue.use(Router)

export default new Router({
	mode: 'history',
	routes: [{
			path: '/',
			name: 'Home',
			component: Home
		},
		{
			path: '/dashboard',
			name: 'Home',
			component: Home
		},
		{
			path: '/essay/write',
			name: 'Writeblog',
			component: Writeblog
		},
		{
			path: '/essay/edit',
			name: 'Editblog',
			component: Editblog
		}
	]
})
