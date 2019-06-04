<template>
	<div>
		<div class="header">
			<el-row>
				<el-col :span="24">
					<div class="header">编辑博文</div>
				</el-col>
			</el-row>
		</div>
		<div class="content">
			<el-tabs v-model="activeName" @tab-click="handleClick">
				<el-tab-pane name="list">
					<span slot="label"><i class="el-icon-menu"></i>&nbsp;文章管理</span>
					<ListBlog :type="tabType"></ListBlog>
				</el-tab-pane>
				<el-tab-pane name="drafts" :lazy="true">
					<span slot="label"><i class="el-icon-edit"></i>&nbsp;草稿箱</span>
					<ListBlog :type="tabType"></ListBlog>
				</el-tab-pane>
				<el-tab-pane name="recycle" :lazy="true">
					<span slot="label"><i class="el-icon-delete"></i>&nbsp;回收站</span>
					<ListBlog :type="tabType"></ListBlog>
				</el-tab-pane>
			</el-tabs>
		</div>
	</div>
</template>

<script>
	import ListBlog from './ListBlog.vue'
	export default {
		name: 'editnblog',
		data() {
			return {
				activeName: 'list',
				tabType: ''
			}
		},
		components: {
			ListBlog
		},
		methods: {
			handleClick() {
			}
		}
	}
</script>

<style scoped="scoped">
	.header{
		font-size: 16px;
		margin-bottom: 10px;
	}
	.content{
		background-color: #fff;
		padding-left: 15px;
		min-height: 580px;
	}
</style>
