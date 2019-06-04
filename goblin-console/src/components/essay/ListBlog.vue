<template>
	<div>
		<el-row>
			<span class="category-title">个人分类：</span>
			<el-tag v-for="category in categoryList" :key="category.id" class="categoryTag">{{category.name}}</el-tag>
		</el-row>
		<el-row>
			<el-table :data="articleList" style="width: 100%">
				<el-table-column prop="coverpictureURL" label="内容">
					<template slot-scope="scope">
						<div class="article-img-title">
							<img :src="scope.row.coverUrl" alt="" class="article-cover">
							<h1 class="title">{{scope.row.title}}</h1>
						</div>
					</template>
				</el-table-column>
				<el-table-column prop="modifyTime" label="更新时间">
					<template slot-scope="scope">
						<span>{{scope.row.modifyTime | formatDate}}</span>
					</template>
				</el-table-column>
				<el-table-column>
					<template slot="header" slot-scope="scope">
						<el-input v-model="searchKey" size="mini" placeholder="输入关键字搜索" />
					</template>
					<template slot-scope="scope">
						<router-link :to="{ path: '/essay/write',query:{id:scope.row.id}}">
							<el-button type="primary" class="fa fa-edit" circle></el-button>
						</router-link>
						<el-button type="success" icon="el-icon-view" circle></el-button>
						<el-button type="danger" class="fa fa-trash-o" circle></el-button>
					</template>
				</el-table-column>
			</el-table>
			<div class="pagination">
				<el-pagination background layout="prev, pager, next" :total="50">
				</el-pagination>
			</div>
		</el-row>
	</div>
</template>

<script>
	import {formatDate} from '../../assets/js/datefmt.js'
	export default {
		name: 'listblog',
		props:['type'],
		data() {
			return {
				searchKey: '',
				currentPage: 1,
				pageSize: 10,
				articleList: [],
				total: 0,
				categoryList: []
			}
		},
		mounted() {
			this.queryArticleList()
			this.queryCategory()
			console.log(this.categoryList)
		},
		methods: {
			queryArticleList() {
				this.$ajax({
					url: this.base_url + '/article/list?currentPage=' + this.currentPage + '&pageSize=' + this.pageSize,
					method: 'get',
				}).then((response) => {
					if (response.status === 200) {
						this.articleList = response.data.pageResult
						this.total = response.data.total
					} else {
						this.$message({
							type: 'error',
							message: '抱歉查询文章列表出错，请稍后重试'
						})
					}
				})
			},
			queryCategory(){
				this.$ajax({
					url: this.base_url + '/category/list',
					method: 'get',
					headers: {
						'Content-Type': 'application/json'
					}
				}).then((response) => {
					if(response.status === 200){
						this.categoryList = response.data
					}else{
						this.$message({
							type: 'error',
							message: '抱歉查询文章分类出错，请稍后重试'
						})
					}
				})
			}
		},
		filters: {
			formatDate: function(time) {
				var date = new Date(time)
				return formatDate(date, 'yyyy-MM-dd hh:mm')
			}
		}
		
	}
</script>

<style scoped="scoped">
	.category-title {
		font-size: 14px;
		color: #303133;
	}
	.categoryTag{
		margin-right: 10px;
	}
	.article-cover {
		width: 200px;
		height: 100px;
		display: inline-block;
		margin-right: 20px;
		margin-left: 20px;
		float: left;
	}

	.title {
		font-size: 16px;
		font-weight: normal;
		margin: 0px;
		line-height: 100px;
		float: left;
	}

	.article-img-title:after {
		content: '';
		display: block;
		clear: both;
	}

	.pagination {
		text-align: right;
	}
</style>
