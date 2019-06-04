<template>
	<div class="micro-blog-wraper">
		<div class="weibo-item" v-for="(weibo,index) in weibos" :class="index===1?'weibo-item-margin':''">
			<el-row>
				<el-col :span="22">
					<el-card>
						<el-carousel trigger="click" :height="getHeight(weibo.images)" :arrow="showCarouselArrow(weibo.images)">
							<el-carousel-item v-for="(url,imgindex) in weibo.images" :key="imgindex">
								<img :src="url" alt="" width="100%">
							</el-carousel-item>
						</el-carousel>
						<div class="weibo-content">
							{{weibo.content}}
						</div>
						<div class="weibo-footer">
							<div class="heart-count">{{weibo.heartCount}}次赞</div>
							<section class="ops-list">
								<a class="iconfont icon-love"></a>
								<a class="iconfont icon-comment"></a>
								<a class="iconfont icon-share"></a>
							</section>
						</div>
						<div class="comment-list">
							<ul>
								<li v-for="(comment,cindex) in weibo.comment" :key="cindex">
									<div class="comment-wraper">
										<h3 class="comment-author">{{comment.author}}</h3><span>{{comment.content}}</span>
									</div>
								</li>
							</ul>
							<div class="publish-time">{{weibo.publishTime | formatPublishDate}}</div>
						</div>
						<el-input placeholder="输入评论" v-model="comment" clearable class="comment-input" size="small">
							<el-button slot="append" class="send-comment-btn" size="small">发送</el-button>
						</el-input>
					</el-card>
				</el-col>
			</el-row>
		</div>
		<el-pagination background class="pagination" layout="prev, pager, next" :total="total" :page-size="pageSize" :current-page="currentPage">
		</el-pagination>
	</div>
</template>

<script>
	import {
		formatDate
	} from '../../assets/js/datefmt.js'
	export default {
		name: 'micro-blog',
		created() {
			this.queryAllMicroBlog()
		},
		data() {
			return {
				weibos: [{
					images: ['../../../static/img/1.jpg', '../../../static/img/2.jpg', '../../../static/img/3.jpg'],
					content: '我想象中很甜的恋爱是不需要借口的。不是因为天气冷才要把手放进你的口袋里，不是因为想要确认有多在意才撒娇发脾气，也不是因为觉得委屈才需要一个拥抱。有太多不好意思的，羞于表达的，需要借口的感情。但希望和你在一起的时候，所有的都是自然而然，是本能，就像喜欢你一样。',
					heartCount: 21388,
					comment: [{
						author: 'perfect_worldplaces',
						content: 'That looks nice 😊👍That looks nice 😊👍That looks nice 😊👍That looks nice 😊👍That looks nice 😊👍That looks nice 😊👍'
					}],
					publishTime: new Date().getTime()
				}, {
					images: ['../../../static/img/1.jpg', '../../../static/img/2.jpg', '../../../static/img/3.jpg'],
					content: '我想象中很甜的恋爱是不需要借口的。不是因为天气冷才要把手放进你的口袋里，不是因为想要确认有多在意才撒娇发脾气，也不是因为觉得委屈才需要一个拥抱。有太多不好意思的，羞于表达的，需要借口的感情。但希望和你在一起的时候，所有的都是自然而然，是本能，就像喜欢你一样。',
					heartCount: 0,
					comment: [{
						author: 'perfect_worldplaces',
						content: 'That looks nice 😊👍'
					}],
					publishTime: new Date().getTime()
				}],
				comment: '',
				currentPage: 1,
				pageSize: 10,
				total: 0
			}
		},
		methods: {
			getHeight(images) {
				// 动态计算高度
				var img = new Image()
				img.src = images[0]
				var minHeight = Math.ceil(img.height / img.width * 640)

				for (var i = 0; i < images.length; i++) {
					var url = images[i]
					var img = new Image()
					img.src = url
					var height = Math.ceil(img.height / img.width * 640)
					if (height < minHeight) {
						minHeight = height
					}
				}

				return minHeight + 'px'
			},
			queryAllMicroBlog() {
				var url = this.base_url + '/microblog/query_list?currentPage=' + this.currentPage + '&pageSize=' + this.pageSize
				this.$ajax.get(url).then((response) => {
					if (response.status === 200) {
						this.total = response.data.total
						var result = response.data.pageResult
						for (var i = 0; i < result.length; i++) {
							var weiboObject = result[i]
							
							var microblog = weiboObject.microblog
							var comment = weiboObject.comment
							var images = microblog.images.split(',')
							this.weibos.push({
								content: microblog.content,
								publishTime: microblog.createTime,
								images: images,
								heartCount: microblog.heartCount,
								comment: comment
							})
						}
					} else {
						this.$message({
							type: 'error',
							message: '抱歉加载动态出错，请稍后重试'
						})
					}
				})
			},
			showCarouselArrow(images) {
				return images.length>1?'always':'never'
			}
		},
		filters: {
			formatPublishDate: function(time) {
				var date = new Date(time)
				var minute = 1000 * 60
				var hour = minute * 60
				var day = hour * 24
				var halfamonth = day * 15
				var month = day * 30
				var now = new Date().getTime()
				var diffValue = now - time
				if (diffValue < 0) {
					return
				}
				var monthC = diffValue / month;
				var weekC = diffValue / (7 * day)
				var dayC = diffValue / day
				var hourC = diffValue / hour
				var minC = diffValue / minute
				var result = ''
				if (monthC >= 1) {
					result = formatDate(date, 'yyyy-MM-dd')
				} else if (weekC >= 1) {
					result = formatDate(date, 'yyyy-MM-dd')
				} else if (dayC >= 1) {
					result = "" + parseInt(dayC) + "天前"
				} else if (hourC >= 1) {
					result = "" + parseInt(hourC) + "小时前"
				} else if (minC >= 1) {
					result = "" + parseInt(minC) + "分钟前"
				} else{
					result = "刚刚"
				}
				return result
			}
		}
	}
</script>

<style scoped="scoped">
	h3 {
		font-weight: normal;
	}

	.micro-blog-wraper {
		min-height: 750px;
	}

	.weibo-item-margin {
		margin-top: 15px;
	}

	.weibo-content {
		font-size: 14px;
		line-height: 2;
		text-align: left;
		margin-top: 15px;
	}

	.weibo-footer {
		margin-top: 10px;
		margin-bottom: 10px;
	}

	.weibo-footer:after {
		content: '';
		display: block;
		clear: both;
	}

	.weibo-footer .ops-list {
		float: right;
	}

	.heart-count {
		float: left;
		font-size: 14px;
		font-weight: 500;
	}

	.weibo-footer .ops-list a {
		font-size: 22px;
		margin-right: 15px;
	}

	.comment-list {
		margin-bottom: 15px;
	}

	.comment-list ul li {
		white-space: nowrap;
		text-overflow: ellipsis;
		overflow: hidden;
		font-size: 14px;
	}

	.comment-wraper {
		text-align: left;
	}

	.comment-author {
		font-size: 14px;
		color: #555;
		margin: 0px;
		font-weight: 600;
		padding-right: 5px;
		display: inline-block;
	}

	.publish-time {
		text-align: left;
		font-size: 10px;
		color: #8c8987;
		margin-top: 5px;
	}
	.pagination{
		text-align: center;
		margin-top: 15px;
	}
</style>
