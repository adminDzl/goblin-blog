import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Writeblog from '@/components/essay/Writeblog'
import WriteMicroBlog from '@/components/microblog/Write'
import Editblog from '@/components/essay/Editblog'
import Category from '@/components/category/Category'
import Message from '@/components/message/Message'
import UserList from '@/components/user/UserList'
import Refresh from '@/components/common/Refresh'
import Login from '@/components/user/Login'
import MyLinks from '@/components/links/MyLinks'
import SaveMyLinks from '@/components/links/SaveMyLinks'
import Blogroll from '@/components/links/Blogroll'
import MyLove from '@/components/music/MyLove'
import Playlist from '@/components/music/Playlist'
import Carousel from '@/components/music/Carousel'
import SaveSong from '@/components/music/SaveSong'

Vue.use(Router)

export default new Router({
	mode: 'history',
	routes: [{
			path: '/',
			component: Home,
			meta: {
				requireAuth: true  // 添加该字段，表示进入这个路由是需要登录的
			}
		},
		{
			path: '/dashboard',
			name: 'Home',
			component: Home,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/essay/write',
			name: 'write_blog',
			component: Writeblog,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/essay/edit',
			name: 'Editblog',
			component: Editblog,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/micro_blog/write',
			name: 'write_micro_blog',
			component: WriteMicroBlog,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/category',
			name: 'Category',
			component: Category,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/message',
			name: 'Message',
			component: Message,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/user_list',
			name: 'UserList',
			component: UserList,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/refresh',
			name: 'refresh',
			component: Refresh,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/login',
			name: 'login',
			component: Login
		},
		{
			path: '/links/mylinks',
			name: 'mylinks',
			component: MyLinks
		},
		{
			path: '/links/save_mylinks',
			name: 'save_mylinks',
			component: SaveMyLinks
		},
		{
			path: '/links/blogroll', // 友情链接
			name: 'blogroll',
			component: Blogroll
		},
		{
			path: '/music/mylove', // 友情链接
			name: 'mylove',
			component: MyLove
		},
		{
			path: '/music/playlist', // 友情链接
			name: 'playlist',
			component: Playlist
		},
		{
			path: '/music/carousel', // 友情链接
			name: 'carousel',
			component: Carousel
		},
		{
			path: '/music/save', 
			name: 'savesong',
			component: SaveSong
		}
	]
})
