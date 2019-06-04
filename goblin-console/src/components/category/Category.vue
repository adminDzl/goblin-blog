<template>
	<div>
		<el-row>
			<el-col :span="24">
				<div class="header">
					分类管理
				</div>
			</el-col>
		</el-row>
		<el-row class="category-body">
			<el-col :span="24">
				<el-input size="medium" placeholder="请输入分类名" v-model="category.name" class="add-category-input">
				</el-input>
				<el-button type="success" size="medium" plain @click="saveCategory">添加分类</el-button>
				<el-table :data="categoryList" stripe class="table-category">
					<el-table-column label="排序">
						<template slot-scope="scope">
							<a href="#" class="el-icon-caret-top article-sort" :class="{'sort-move-up':isFirstRow(scope.$index)}" @click="moveUp(scope)"></a>
							<a href="#" class="el-icon-caret-bottom article-sort" :class="{'sort-move-down':isLastRow(scope.$index)}" @click="moveDownp(scope)"></a>
						</template>
					</el-table-column>
					<el-table-column prop="name" label="类别">
					</el-table-column>
					<el-table-column label="前台是否显示">
						<template slot-scope="scope">
							<el-switch v-model="scope.row.visible" active-color="#13ce66" inactive-color="#dcdfe6">
							</el-switch>
						</template>
					</el-table-column>
					<el-table-column prop="articleCount" label="文章数">
					</el-table-column>
					<el-table-column label="操作">
						<template slot-scope="scope">
							<el-button type="success" size="small" icon="el-icon-edit" circle plain @click="editCategory(scope.row)"></el-button>
							<el-button type="danger" size="small" icon="el-icon-delete" circle plain @click="deleteCategory(scope.row.id)"></el-button>
						</template>
					</el-table-column>
				</el-table>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				category: {
					id: 0,
					sortKey: 0,
					name: '',
					visible: ''
				},
				categoryList: [] // 后端获取的分类数据
			}
		},
		created() {
			this.queryAllCategory()
		},
		methods: {
			queryAllCategory() {
				this.$ajax.get(this.base_url + '/category/list').then((response) => {
					if (response.status == 200) {
						this.categoryList = response.data
					}
				})
			},
			saveCategory() {
				// 新增分类
				this.$ajax.post(this.base_url + '/category/save', this.category).then((response) => {
					if (response.data.code === 20000) {
						this.$message({
							message: '新增分类成功',
							type: 'success'
						})
						// 刷新当前页面，更新数据状态
						this.$router.push('/refresh')
					} else {
						this.$message({
							message: '抱歉服务器繁忙，请稍后再试',
							type: 'error'
						})
					}
				})
			},
			editCategory(scopeRow) {
				var rowObject = scopeRow
				this.$prompt('请输入邮箱', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					inputValue: rowObject.name
				}).then(({
					value
				}) => {
					var category = {
						id: rowObject.id,
						name: value
					}
					// 新增分类
					this.$ajax.post(this.base_url + '/category/update', category).then((response) => {
						if (response.data.code === 20000) {
							this.$message({
								type: 'success',
								message: '编辑成功'
							})
							// 刷新当前页面，更新数据状态
							this.$router.push('/refresh')
						} else {
							this.$message({
								message: '抱歉服务器繁忙，请稍后再试',
								type: 'error'
							})
						}
					})
				}).catch(() => {})
			},
			deleteCategory(id) {
				this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					// 删除分类
					this.$ajax.get(this.base_url + '/category/delete/' + id).then((response) => {
						if (response.data.code === 20000) {
							this.$message({
								message: '删除分类成功',
								type: 'success'
							})
							// 刷新当前页面，更新数据状态
							this.$router.push('/refresh')
						} else {
							this.$message({
								message: '抱歉服务器繁忙，请稍后再试',
								type: 'error'
							})
						}
					})
				}).catch(() => {})
			},
			moveUp(scope) {
				var categoryId = scope.row.id
				var rowIndex = scope.$index

				if (rowIndex !== 0) {
					// 排序方法，上移
					this.$ajax.post(this.base_url + '/category/move_up/' + categoryId).then((response) => {
						if (response.data.code === 20000) {
							this.$message({
								message: '状态更新成功',
								type: 'success'
							})
							// 刷新当前页面，更新数据状态
							this.$router.push('/refresh')
						} else {
							this.$message({
								message: '抱歉服务器繁忙，请稍后再试',
								type: 'error'
							})
						}
					})
				}
			},
			moveDownp(scope) {
				// 排序方法，下移
				var categoryId = scope.row.id
				var rowIndex = scope.$index

				if (categoryId !== 0 && rowIndex !== this.categoryList.length - 1) {
					// 如果不是最后一行才上移
					var category = {
						id: categoryId,
						sortKey: categoryId + 1
					}
					// 排序方法，上移
					this.$ajax.post(this.base_url + '/category/move_down/' + categoryId).then((response) => {
						if (response.data.code === 20000) {
							this.$message({
								message: '状态更新成功',
								type: 'success'
							})
							// 刷新当前页面，更新数据状态
							this.$router.push('/refresh')
						} else {
							this.$message({
								message: '抱歉服务器繁忙，请稍后再试',
								type: 'error'
							})
						}
					})
				}
			},
			isFirstRow(index) {
				// 判断是否是表格的第一行
				return index === 0 ? true : false
			},
			isLastRow(index) {
				// 判断是否是表格的最后一行
				return index === this.categoryList.length - 1 ? true : false
			}
		}
	}
</script>

<style scoped="scoped">
	.category-body {
		padding-left: 15px;
		padding-top: 15px;
		min-height: 580px;
		background-color: #fff;
	}

	.header {
		margin-bottom: 15px;
	}

	.article-sort {
		display: block;
		width: 14px;
	}

	.add-category-input {
		margin-right: 15px;
		width: 20%;
	}

	.table-category {
		width: 100%;
		margin-top: 15px;
	}

	.sort-move-up,
	.sort-move-down {
		color: #F0F2F5;
	}
</style>
