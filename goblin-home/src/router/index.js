import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import ArticleContent from '@/components/home/ArticleContent'
import BlogList from '@/components/home/BlogList'
import MicroBlog from '@/components/microblog/MicroBlog'
import Music from '@/components/music/music'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: BlogList
    },
		{
		  path: '/blog',
		  name: 'blog',
		  component: BlogList
		},
		{
		  path: '/microblog',
		  name: 'microblog',
		  component: MicroBlog
		},
		{
		  path: '/music',
		  name: 'music',
		  component: Music
		},
		{
			name: 'articleDetail',
			path: '/article_detail',
			component: ArticleContent
		}
  ]
})
