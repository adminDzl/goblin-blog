<template>
	<div class="content-left">
		<div class="user-avatar whitebkcolor">
			<div class="user-box">
				<img class="topbar-user-avatar" src="https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg">
			</div>
			<div class="user-name">聽見下雨的聲音</div>
			<div class="profile">这是一段个人说明这是一段个人说明这是一段个人说明</div>
			<div class="site-state-body">
				<nav class="site-state motion-element">
					<div class="site-state-item site-state-posts">
						<a href="/archives">
							<span class="site-state-item-count">183</span>
							<span class="site-state-item-name">博文</span>
						</a>
					</div>
					<div class="site-state-item site-state-categories">
						<a href="/categories/index.html">
							<span class="site-state-item-count">6</span>
							<span class="site-state-item-name">分类</span>
						</a>
					</div>
					<div class="site-state-item site-state-tags">
						<a href="/tags/index.html">
							<span class="site-state-item-count">111</span>
							<span class="site-state-item-name">动态</span>
						</a>
					</div>
				</nav>
			</div>
		</div>
		<!-- 博客分类列表 -->
		<div class="article-category whitebkcolor">
			<h3 class="common-title">博文分类</h3>
			<ul class="category-list">
				<li v-for="(category, index) in categoryList" :key="index">
					<a href="#"><i class=""></i>{{category.name}}</a>
				</li>
			</ul>
		</div>
		<!-- 最近在听 -->
		<div class="recently-listen whitebkcolor">
			<h3 class="common-title">最近在听</h3>
			<ul class="music-list">
				<li v-for="(song,index) in songs" :key="index">
					<div class="tt">
						<a :href="song.url" class="song-cover">
							<img class="music-pic" :src="song.src">
						</a>
						<div class="song-player">
							<a href="javascript:;" hidefocus="true" data-action="play" class="fa fa-play-circle-o" title="播放/暂停(p)"></a>
							<a href="#" class="song-name">{{song.name}}</a>
						</div>
					</div>
				</li>
			</ul>
			<div class="more">
				<a href="#">查看更多</span></a>
			</div>
		</div>
		<!-- 最近在听 -->
		<div class="links whitebkcolor">
			<h3 class="common-title">社交媒体</h3>
			<div class="links-author">
				<span class="links-of-author-item" v-for="(link,index) in mylinks" :key="index">
					<a :href="link.url" target="_blank" :title="link.name">
						<i :class="link.iconFont"></i>
						{{link.name}}
					</a>
				</span>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'content-left',
		created() {
			this.queryCategory()
			this.queryAllMyLinks()
		},
		data() {
			return {
				userActar: 'https://i.loli.net/2017/08/21/599a521472424.jpg',
				songs: [{
						url: '/song?id=1359595520',
						src: 'http://p2.music.126.net/YDrUWvNAGLDEKs1R8ROH3w==/109951164024853448.jpg?param=50y50&quality=100',
						name: '夜风无意作情歌'
					},
					{
						url: '/song?id=1358397818',
						src: 'http://p2.music.126.net/Tw-6B_s2-JSWjiX8QqykQw==/109951164023312140.jpg?param=50y50&quality=100',
						name: 'Drugs & The Internet'
					}
				],
				mylinks: [],
				categoryList: []
			}
		},
		methods: {
			queryCategory() {
				this.$ajax.get(this.base_url + '/category/list').then((response) => {
					if (response.status == 200) {
						this.categoryList = response.data
					}
				})
			},
			queryAllMyLinks() {
				var url = this.base_url + '/mylink/list?currentPage=' + 1 + '&pageSize=' + 100
				this.$ajax.get(url).then((response) => {
					if (response.status === 200) {
						this.total = response.data.total
						this.mylinks = response.data.pageResult
					} else {
						this.$message({
							message: '抱歉服务器故障,请稍后重试',
							type: 'error'
						})
					}
				})
			}
		}
	}
</script>

<style scoped="scoped">
	/* .content-left {
		background-color: #fff;
	} */
	.whitebkcolor {
		background-color: #fff;
	}

	.clearfix {
		content: '';
		display: block;
		clear: both;
	}

	.user-avatar {
		padding-top: 15px;
	}

	.user-box .topbar-user-avatar {
		width: 52px;
		height: 52px;
		vertical-align: middle;
		border-radius: 26px;
	}

	.topbar-userheader-avatar {
		width: 52px;
		height: 52px;
		vertical-align: middle;
		border-radius: 26px;
		margin-right: 10px;
	}

	.user-name {
		color: #444;
		font-size: 14px;
		text-align: center;
		font-weight: 400;
		margin-top: 8px;

	}

	.profile {
		font-size: 13px;
		color: #999;
		margin: 8px 10px;
	}

	.site-state-body {
		margin-top: 25px;
		padding-bottom: 25px;
	}

	.site-state-item {
		display: inline-block;
		padding: 0 15px;
		border-left: 1px solid #eee;
	}

	.site-state-item:first-child {
		border: none;
	}

	.site-state-item a {
		color: #555;
	}

	.site-state-item-count {
		color: #555;
		display: block;
		text-align: center;
		color: inherit;
		font-weight: 600;
		font-size: 16px;
	}

	.site-state-item-name {
		font-size: 13px;
		color: #999;
	}

	/* 文字分类 */
	.article-category {}

	.category-list {
		padding: 0px;
		margin: 0px;
		text-align: left;
		list-style: none;
	}

	.common-title {
		background-color: #4e9ec3;
		color: #fff;
		font-size: 13px;
		margin: 10px 0 0 0;
		padding: 5px 0;
		font-weight: 400;
	}

	.category-list li {
		height: 30px;
		line-height: 30px;
		padding: 0px 10px;
		color: #3f3f3f;
		font-size: 13px;
		border-bottom: 1px solid #fee4c3;
	}

	/* 最近在听 */
	.music-list {
		text-align: left;
	}

	.music-list li {
		height: 50px;
		padding: 5px 0px 5px 10px;
		border-bottom: 1px solid #fee4c3;
	}

	.music-list a {
		display: block;
	}

	.song-cover {
		float: left;
	}

	.music-pic {
		float: left;
		width: 45px;
		height: 45px;
		margin-right: 14px;
	}

	.song-player {
		height: 100%;
	}

	.song-player:after {
		content: '';
		display: block;
		clear: both;
	}

	.song-player a {
		display: block;
		float: left;
		margin-right: 5px;
		line-height: 45px;
		color: #6b6565;
	}

	.song-player .song-name {
		width: 130px;
		font-size: 13px;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}

	.more {
		width: 100%;
		font-size: 12px;
		text-align: right;
		padding: 2px 0px;
	}

	.more a {
		color: #615d5d;
		padding-right: 10px;
	}

	.more a:hover {
		color: #222222;
	}

	/* 社交媒体链接 */
	.links-author{
		padding-bottom: 15px;
	}
	.links-author:after{
		content: '';
		display: block;
		clear: both;
	}
	
	.links-of-author-item {
		float: left;
		text-align: left;
		margin: 15px 0 0;
		width: 50%;
	}

	.links-of-author-item a {
		font-size: 13px;
		vertical-align: middle;
		text-decoration: none;
		box-sizing: border-box;
		display: inline-block;
		margin-right: 0;
		margin-bottom: 0;
		padding: 0 10px;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}
</style>
