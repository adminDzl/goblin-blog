<template>
	<div>
		我的链接
		<div class="mylinks-wrapper">
			<el-button type="success" size="medium" plain @click="saveMylinksHandler">添加链接</el-button>
			<el-table :data="mylinks" style="width: 100%">
				<el-table-column type="index">
				</el-table-column>
				<el-table-column label="名称">
					<template slot-scope="scope">
						<span>{{ scope.row.name }}</span>
					</template>
				</el-table-column>
				<el-table-column label="iconfont">
					<template slot-scope="scope">
						<i :class="scope.row.iconFont"></i>
						<span>{{ scope.row.iconFont }}</span>
					</template>
				</el-table-column>
				<el-table-column label="url">
					<template slot-scope="scope">
						<el-popover trigger="hover" placement="top">
							<el-tag size="medium">{{ scope.row.url }}</el-tag>
							<div slot="reference" class="name-wrapper">
								<el-tag size="medium">{{ scope.row.url }}</el-tag>
							</div>
						</el-popover>
					</template>
				</el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
						<el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination layout="prev, pager, next" :total="total" :current-page="currentPage" :page-size="pageSize" class="pagination">
			</el-pagination>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'mylinks',
		created() {
			this.queryAllMyLinks()
		},
		data() {
			return {
				currentPage: 1,
				pageSize: 10,
				total: 0,
				mylinks: []
			}
		},
		methods: {
			saveMylinksHandler() {
				this.$router.push({
					path: '/links/save_mylinks',
					query: {
						method: 'add'
					}
				})
			},
			handleEdit(index, row) {
				this.$router.push({
					path: '/links/save_mylinks',
					query: {
						id: row.id,
						method: 'edit'
					}
				})
			},
			handleDelete(index, row) {
				this.$confirm('此操作将永久删除该记录, 是否继续？', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$ajax.get(this.base_url + '/mylink/delete/' + row.id).then((response) => {
						if (response.data.code === 20000) {
							this.$message({
								message: '删除成功',
								type: 'success'
							})
							this.$router.push('/refresh')
						} else {
							this.$message({
								message: '抱歉服务器故障,请稍后重试',
								type: 'error'
							})
						}
					})
				}).catch(() => {})
			},
			queryAllMyLinks() {
				var url = this.base_url + '/mylink/list?currentPage=' + this.currentPage + '&pageSize=' + this.pageSize
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
	.mylinks-wrapper {
		min-height: 560px;
		margin-top: 15px;
		padding: 15px;
		background-color: #fff;
	}

	.pagination {
		margin-top: 25px;
		text-align: center;
	}
</style>
