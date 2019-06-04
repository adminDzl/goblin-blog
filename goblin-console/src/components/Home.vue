<template>
	<div class="home">
		<div>整体数据概况</div>
		<div class="chart-top">
			<el-row :gutter="18">
				<el-col :span="6">
					<div class="content">
						<div class="userCount chart-outline">
							<p class="chart-title">用户总数</p>
							<p class="chart-subtitle">截止当天注册的用户总数</p>
							<div id='userCountChart' class="chart"></div>
						</div>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="content">
						<div class="fileResources chart-outline">
							<p class="chart-title">文件资源</p>
							<p class="chart-subtitle">网盘，相册，视频文件资源占比</p>
							<div id='fileChart' class="chart"></div>
						</div>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="content">
						<div class="article chart-outline">
							<p class="chart-title">文章百分比</p>
							<p class="chart-subtitle">微博，博客，笔记占比</p>
							<div id='articleChart' class="chart"></div>
						</div>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="content">
						<div class="vistlog chart-outline">
							<p class="chart-title">访问量</p>
							<p class="chart-subtitle">截至今日累计访问总数</p>
							<div id="vistlogChart" class="chart"></div>
						</div>
					</div>
				</el-col>
			</el-row>
		</div>

		<div class="visitChart-bottom">
			<el-row :gutter="18">
				<el-col :span="12">
					<div class="latestArticle">
						<p class="latestArticle-title">最新文章</p>
						<el-table :data="tableData" class="essayTable">
							<el-table-column prop="title" label="文章标题">
							</el-table-column>
							<el-table-column prop="state" label="文章状态" :formatter="fmtEssayState">
							</el-table-column>
							<el-table-column prop="date" label="日期">
							</el-table-column>
						</el-table>
						<el-pagination small layout="prev, pager, next" @current-change="essayHandleCurrentChange" :page-size="essayPageSize"
						 :total="tableData.length + testTableData.length">
						</el-pagination>
					</div>
				</el-col>
				<el-col :span="12">
					<div class="comment">
						<p class="comment-title">最新消息</p>
						<ul class="comment-list">
							<li v-for="(data,index) in commonList" :key="index">
								<router-link :to="{ path: '/message' }">
									<el-card>
										<el-col :span="2">
											<div class="user-box">
												<img class="topbar-user-avatar" src="https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg">
											</div>
										</el-col>
										<el-col :span="22">
											<div class="comment-digest">
												<span class="comment-user">{{data.username}}</span>
												<span class="comment-type">{{data.type==1?'点赞':'评论'}}了你的博文</span>
												<p class="essay-title">{{data.title}}</p>
												<p class="comment-date">{{data.date}}</p>
											</div>
										</el-col>
									</el-card>
								</router-link>
							</li>
						</ul>
						<el-pagination small layout="prev, pager, next" @current-change="commentHandleCurrentChange" :page-size="commentPageSize"
						 :total="commonList.length+commonAllList.length">
						</el-pagination>
					</div>
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
	// 使用echarts图表库
	import Echarts from 'echarts'
	export default {
		name: 'Home',
		comments: {
			Echarts
		},
		mounted() {
			this.drawLine()
		},
		data() {
			return {
				tableData: [{
						date: '2019-05-02',
						title: 'ECharts图例组件配置 数据项及图例项',
						state: '1'
					}, {
						date: '2019-05-04',
						title: 'ECharts xAxis配置 x坐标轴名称',
						address: '1'
					},
					{
						date: '2019-05-02',
						title: 'ECharts图例组件配置 数据项及图例项',
						state: '1'
					}, {
						date: '2019-05-04',
						title: 'ECharts xAxis配置 x坐标轴名称',
						address: '1'
					}
				],
				commonList: [{
						userico: '',
						type: '0',
						username: 'YanSen',
						title: 'Props 改变触发哪个生命周期事件',
						date: '2019-4-19 9:20'
					},
					{
						userico: '',
						type: '1',
						username: '张三',
						title: 'Props 改变触发哪个生命周期事件',
						date: '2019-4-18 16:37'
					}
				],
				essayCurrentPage: 1,
				essayPageSize: 4,
				commentCurrentPage: 1,
				commentPageSize: 2,
				testTableData: [{
						date: '2019-05-02',
						title: 'ECharts图例组件配置 数据项及图例项',
						state: '1'
					}, {
						date: '2019-05-04',
						title: 'ECharts xAxis配置 x坐标轴名称',
						address: '1'
					}, {
						date: '2019-05-01',
						title: 'ColorPicker 颜色选择器',
						state: '0'
					}, {
						date: '2019-05-03',
						title: 'Form 表单',
						state: '1'
					},
					{
						date: '2019-05-03',
						title: 'Form 表单',
						state: '1'
					},
					{
						date: '2019-05-03',
						title: 'Form 表单',
						state: '1'
					}
				],
				commonAllList: [{
						userico: '',
						type: '0',
						username: 'YanSen',
						title: 'Props 改变触发哪个生命周期事件',
						date: '2019-4-19 9:20'
					},
					{
						userico: '',
						type: '1',
						username: '张三',
						title: '【分类算法】Logistic原理及标准评分卡开发流程',
						date: '2019-4-18 16:37'
					},
					{
						userico: '',
						type: '1',
						username: '张三',
						title: '蚂蚁金服2019实习生面经总结(已拿口头offer)',
						date: '2019-4-17 16:38'
					},
					{
						userico: '',
						type: '1',
						username: '张三',
						title: '996 | 不谈收入谈福报，我们凭什么要喝这碗毒鸡汤！',
						date: '2019-4-16 16:39'
					},
					{
						userico: '',
						type: '1',
						username: '张三',
						title: '美国程序员从不加班，而中国程序员却加班到怀疑人生！真的是因为技术差距太大吗？',
						date: '2019-4-15 16:40'
					}
				]
			}
		},
		methods: {
			fmtEssayState(ow, column, cellValue, index) {
				return cellValue === '1' ? '已发布' : '回收站'
			},
			drawLine() {
				// 基于准备好的dom，初始化echarts实例
				var userCountChart = Echarts.init(document.getElementById('userCountChart'))
				var fileResourcesChart = Echarts.init(document.getElementById('fileChart'))
				var articleChart = Echarts.init(document.getElementById('articleChart'))
				var vistlogChart = Echarts.init(document.getElementById('vistlogChart'))
				var userCountOption = {
					color: ['#3ba1ff'],
					xAxis: {
						type: 'category',
						data: ['4/10', '4/12', '4/14', '4/16', '4/17'] // 日期
					},
					grid: {
						left: '60',
						top: '20',
						height: '160'
					},
					yAxis: {
						type: 'value'
					},
					series: [{
						data: [820, 932, 901, 934, 1290, 1330, 1320], // 注册认数
						type: 'line',
						smooth: true
					}]
				}
				var option = {
					tooltip: {
						trigger: 'item',
						formatter: '{a} <br/>{b}: {c} ({d}%)'
					},
					color: ['#e25a70', '#36a4b2', '#f0b549'],
					legend: {
						orient: 'vertical',
						x: 'right',
						data: ['网盘', '相册', '视频'],
						itemHeight: 12,
						itemWidth: 44,
						itemGap: 14,
						padding: [
							60, // 上
							70, // 右
							0, // 下
							0 // 左
						],
						align: 'left'
					},
					series: [{
						name: '访问来源',
						type: 'pie',
						radius: ['60%', '80%'],
						center: ['30%', '50%'], // 设置图的上下左右的位置
						avoidLabelOverlap: false,
						label: {
							normal: {
								show: false,
								position: 'center'
							},
							emphasis: {
								show: true,
								textStyle: {
									fontSize: '20',
									fontWeight: 'bold'
								}
							}
						},
						labelLine: {
							normal: {
								show: false
							}
						},
						data: [{
								value: 335,
								name: '网盘'
							},
							{
								value: 310,
								name: '相册'
							},
							{
								value: 234,
								name: '视频'
							}
						]
					}]
				}

				var visitlogOption = {
					color: ['#44b549'],
					grid: {
						height: '150',
						left: '60',
						top: '20'
					},
					xAxis: {
						type: 'category',
						boundaryGap: false,
						data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
					},
					yAxis: {
						type: 'value'
					},
					series: [{
						data: [820, 932, 901, 934, 1290, 1330, 1320],
						type: 'line',
						areaStyle: {}
					}]
				}

				// 绘制图表
				userCountChart.setOption(userCountOption)
				fileResourcesChart.setOption(option)
				articleChart.setOption(option)
				vistlogChart.setOption(visitlogOption)
			},
			// 分页方法（重点）
			essayHandleCurrentChange(currentPage) {
				var start = (currentPage - 1) * this.essayPageSize
				var end = currentPage * this.essayPageSize
				this.tableData = []
				this.tableData = this.testTableData.slice(start, end)
			},
			// 分页方法（重点）
			commentHandleCurrentChange(currentPage) {
				var start = (currentPage - 1) * this.commentPageSize
				var end = currentPage * this.commentPageSize
				this.commonList = []
				this.commonList = this.commonAllList.slice(start, end)
			}
		}
	}
</script>

<style scoped="scoped">
	.pull-left {
		float: left;
	}

	.pull-right {
		float: right;
	}

	.clearfix:after {
		content: '';
		display: block;
		clear: both;
	}

	.chart-top:after {
		content: '';
		display: block;
		clear: both;
	}

	/* .chart-outline {
		width: 367px;
		height: 320px;
		background-color: #fff;
	} */
	.chart-outline {
		width: 100%;
		height: 320px;
		background-color: #fff;
	}

	.chart-title {
		margin-bottom: 5px;
		padding-top: 35px;
		padding-left: 35px;
		font-size: 16px;
		font-weight: bold;
		color: #4e5058;
	}

	.chart-subtitle {
		margin: 0px;
		padding-left: 35px;
		font-size: 13px;
		color: #cbccd0;
	}

	.chart {
		width: 100%;
		height: 200px;
	}

	.visitChart-bottom {
		margin-top: 15px;
		min-height: 320px;
	}

	.latestArticle {
		width: 100%;
		height: 100%;
		background-color: #fff;
	}

	.latestArticle-title,
	.comment-title {
		padding-top: 15px;
		padding-left: 25px;
		margin: 0px;
		font-size: 16px;
		font-weight: bold;
		color: #4e5058;
	}

	.essayTable {
		padding-left: 15px;
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

	.essay-title {
		margin: 10px 0px;
	}

	.comment-date {
		margin: 0px 0px 5px 0px;
		font-size: 12px;
	}
</style>
