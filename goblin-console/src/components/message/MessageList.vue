<template>
	<div>
		<el-timeline class="timeline">
			<el-timeline-item :timestamp="data.date" placement="top" v-for="(data,index) in allMessageList" :key="index">
				<el-card>
					<el-row>
						<el-col :span="2">
							<div class="user-box">
								<img class="topbar-user-avatar" :src="data.userico">
							</div>
						</el-col>
						<el-col :span="22">
							<div class="comment-digest">
								<span class="comment-user">{{data.username}}</span>
								<p class="comment-date">{{data.date}}</p>
							</div>
						</el-col>
					</el-row>
					<el-row>
						<p class="message-content">{{data.type==1?'赞了我':data.comment}}</p>
					</el-row>
					<el-row>
						<div class="message-abstract">
							<el-col :span="4">
								<img src="https://mmbiz.qlogo.cn/mmbiz_jpg/khBLuyHe2kjmlJCv6lAmicIoaByQl0ic443RXbMOdPAnDfeLyROLPLc9zImMdbKRP9Ocga7EQQQejHrdjAW8hrxA/0?wx_fmt=jpeg"
								 alt="">
							</el-col>
							<el-col :span="19">
								<div class="message-title">
									<p>Props 改变触发哪个生命周期事件</p>
								</div>
							</el-col>
						</div>
					</el-row>
					<el-row>
						<el-col :span="24">
							<el-input size="mini" placeholder="快速回复:" v-model="messageFrom.reply" class="reply-input">
							</el-input>
							<el-button size="mini">发送</el-button>
						</el-col>
					</el-row>
				</el-card>
			</el-timeline-item>
		</el-timeline>
		<el-pagination :page-size="20" :pager-count="11" layout="prev, pager, next" :total="1000" class="pagination">
		</el-pagination>
	</div>
</template>

<script>
	export default {
		name: 'messageList',
		props: ['type'],
		data() {
			return {
				messageFrom: {
					comment: '',
					reply: ''
				},
				allMessageList: [{
						userico: 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg',
						type: '0',
						username: 'YanSen',
						title: 'Props 改变触发哪个生命周期事件',
						date: '2019-4-19 9:20',
						comment: '这是一条评论'
					},
					{
						userico: 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg',
						type: '1',
						username: '张三',
						title: 'Props 改变触发哪个生命周期事件',
						date: '2019-4-18 16:37'
					},
					{
						userico: 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg',
						type: '0',
						username: 'YanSen',
						title: 'Props 改变触发哪个生命周期事件',
						date: '2019-4-19 9:20',
						comment: '这是一条评论'
					},
					{
						userico: 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg',
						type: '1',
						username: '张三',
						title: 'Props 改变触发哪个生命周期事件',
						date: '2019-4-18 16:37'
					}
				]
			}
		}
	}
</script>

<style scoped="scoped">
	p {
		margin: 0px;
	}

	.timeline {
		width: 600px;
		margin: 0 auto;
	}

	.comment {
		width: 100%;
		height: 100%;
		background-color: #fff;
	}

	.comment-list {
		margin-left: 35px;
		margin-right: 35px;
	}

	.topbar-userheader-avatar {
		width: 52px;
		height: 52px;
		vertical-align: middle;
		border-radius: 26px;
		margin-right: 10px;
	}

	.topbar-user-avatar {
		width: 52px;
		height: 52px;
		vertical-align: middle;
		border-radius: 26px;
	}

	.comment-list li {
		margin-bottom: 10px;
	}

	.comment-digest {
		margin-left: 15px;
	}

	.comment-user {
		color: #1EB1FF;
		font-size: 14px;
		margin-right: 15px;
	}

	.comment-type {
		color: #646772;
		font-size: 14px;
	}

	.comment-date {
		margin: 0px 0px 5px 0px;
		font-size: 12px;
	}

	.message-content {
		margin: 10px 0px;
	}

	.message-abstract {
		background-color: #f0f2f5;
	}

	.message-abstract:after {
		content: '';
		display: block;
		clear: both;
	}

	.message-abstract img {
		width: 100%;
		max-height: 56px;
		float: left;
	}

	.message-title {
		height: 100%;
		padding-top: 5px;
		float: left;
	}

	.message-title p {
		width: 100%;
		height: 100%;
		color: #646772;
		font-size: 13px;
		margin-left: 15px;
		display: -webkit-box;
		-webkit-box-orient: vertical;
		-webkit-line-clamp: 2;
		overflow: hidden;
	}

	.reply-input {
		width: 88%;
		margin-top: 5px;
	}

	.pagination {
		text-align: center;
		margin-bottom: 20px;
	}
</style>
