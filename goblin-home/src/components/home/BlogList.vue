<template>
	<div class="content-wrap">
		<div class="content" v-for="(article,index) in articleList">
			<h1 class="title">
				{{article.title}}
			</h1>
			<div class="post-meta">
				<span class="post-time">
					<span class="fa fa-tag"></span>
					<time title="推送时间" :datetime="article.createTime">
						{{article.createTime | formatDate}}
					</time>
				</span>
				<span class="post-category">
					<span class="post-meta-divider">|</span>
					<span class="post-meta-item-icon">
						<i class="fa fa-folder-o"></i>
					</span>
					<span class="post-meta-item-text">分类于</span>
					<span>
						<a href="#" rel="index">
							<span itemprop="name">{{article.categorie}}</span>
						</a>
					</span>
				</span>
				<span class="post-comments-count">
					<span class="post-meta-divider">|</span>
					<span class="post-meta-item-icon">
						<i class="fa fa-comment-o"></i>
						评论
					</span>
					<span class="comment-count">{{article.commentCount}}</span>
				</span>
				<span class="leancloud_visitors">
					<span class="post-meta-divider">|</span>
					<span class="post-meta-item-icon">
						<i class="fa fa-eye"></i>
					</span>
					<span class="post-meta-item-text">阅读次数 </span>
					<span class="leancloud-visitors-count">{{article.clickCount}}</span>
				</span>
			</div>
			<div class="article-wraper">
				<el-row>
					<!-- <el-col :span="10">
						<div class="article-cover">
							<img src="../../../static/img/topbk.jpg" alt="" width="100%">
						</div>
					</el-col> -->
					<el-col :span="24">
						<div class="article-abstract">
							{{article.summary}}
						</div>
					</el-col>
				</el-row>
			</div>
			<div class="post-button text-center">
				<router-link class="readmore-btn" :to="{path:'/article_detail',query:{id:article.id}}">
					阅读全文&nbsp;<span class="fa fa-fw fa-chevron-circle-right"></span>
				</router-link>
			</div>
			<div class="post-eof" v-show="index+1===articleList.length?false:true"></div>
		</div>
		<div class="pagination">
			<el-pagination background layout="prev, pager, next" :total="total" :page-size="pageSize" :current-page="currentPage"
			 class="pagination">
			</el-pagination>
		</div>
	</div>
</template>

<script>
	import {formatDate} from '../../assets/js/datefmt.js'
	export default {
		name: 'BlogList',
		created() {
			this.queryArticleList()
		},
		data() {
			return {
				pageSize: 10,
				currentPage: 1,
				total: 0,
				articleList: [{
						id: 1,
						title: '使用 Travis CI 自动更新 GitHub Pages',
						publishtime: '2016-03-22',
						categorie: '随笔',
						commentCount: 12,
						clickCount: 56,
						summary: '尽管简单易用一直是 NexT 主题的首要目标，但现实与想法总是有那么一个缝隙，难怪有人说诗要源于生活后低于生活，着实有着深刻的哲学道理。那么无论这背后是什么样的原因导致，总结起来就是 NexT	不好用。在初期使用者比较少的情况，还可以有耐心的一个个解答；后来发现一直在回答类似的问题，加上要做和想做的事情太多而无暇顾及，于是就想写一份文档缓和一下这个问题。'
					},
					{
						id: 2,
						title: '使用 Travis CI 自动更新 GitHub Pages',
						publishtime: '2016-03-22',
						categorie: '随笔',
						commentCount: 12,
						clickCount: 56,
						summary: '尽管简单易用一直是 NexT 主题的首要目标，但现实与想法总是有那么一个缝隙，难怪有人说诗要源于生活后低于生活，着实有着深刻的哲学道理。那么无论这背后是什么样的原因导致，总结起来就是 NexT	不好用。在初期使用者比较少的情况，还可以有耐心的一个个解答；后来发现一直在回答类似的问题，加上要做和想做的事情太多而无暇顾及，于是就想写一份文档缓和一下这个问题。'
					}
				]
			}
		},
		methods: {
			queryArticleList() {
				this.$ajax.get(this.base_url + '/article/list?currentPage=' + this.currentPage + '&pageSize=' + this.pageSize).then(
					(response) => {
						console.log(response)
						if (response.status === 200) {
							this.articleList = response.data.pageResult
							this.total = response.data.total
						}
					})
			}
		},
		filters: {
			formatDate: function(time) {
				var date = new Date(time)
				return formatDate(date, 'yyyy-MM-dd')
			}
		}
	}
</script>

<style scoped="scoped">
	.content-wrap {
		min-height: 700px;
		padding: 40px;
		background-color: #fff;
	}

	.content {
		margin-top: 25px;
	}

	.title {
		margin: 0px;
		padding-bottom: 10px;
		font-size: 26px;
		color: #555;
		font-family: "Montserrat", "Helvetica Neue", "Hiragino Sans GB", "LiHei Pro", Arial, sans-serif;
		text-align: center;
		word-break: break-word;
		font-weight: 400;
	}

	.post-time {
		font-family: "Montserrat", "Helvetica Neue", "Hiragino Sans GB", "LiHei Pro", Arial, sans-serif;
		font-size: 13px;
		color: #999;
		margin: 0 0 30px;
		letter-spacing: 1px;
	}

	.post-category,
	.post-comments-count,
	.leancloud_visitors {
		color: #999;
		font-family: 'Monda', "PingFang SC", "Microsoft YaHei", sans-serif;
		font-size: 12px;
		text-align: center;
	}

	/* 文章内容 */
	.article-wraper {
		margin-top: 25px;
	}

	.article-cover {
		width: 100%;
	}

	.article-abstract {
		padding-left: 15px;
		text-align: left;
		line-height: 2;
		color: #666;
		font-size: 14px;
	}

	.post-button {
		margin-top: 25px;
	}

	.readmore-btn {
		display: inline-block;
		padding: 0 20px;
		font-size: 14px;
		color: #4e9ec3;
		background: #fff;
		border: 2px solid #4e9ec3;
		text-decoration: none;
		border-radius: 2px;
		transition-property: background-color;
		transition-duration: 0.2s;
		transition-timing-function: ease-in-out;
		transition-delay: 0s;
		line-height: 2;
	}

	.post-eof {
		display: block;
		margin: 40px auto 60px;
		width: 8%;
		height: 1px;
		background: #ccc;
		text-align: center;
	}

	/* 分页 */
	.pagination {
		margin-top: 80px;
	}
</style>
