<template>
	<div>
		编辑我的链接
		<div class="save-mylinks-wrapper">
			<el-form :model="mylinks" :rules="rules" ref="mylinks" label-width="100px" class="mylinks-form">
				<el-form-item label="名称" prop="name">
					<el-input v-model="mylinks.name"></el-input>
				</el-form-item>
				<el-form-item label="iconfont" prop="iconFont">
					<el-input v-model="mylinks.iconFont" :suffix-icon="mylinks.iconFont"></el-input>
				</el-form-item>
				<el-form-item label="url" prop="url">
					<el-input v-model="mylinks.url"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="submitForm('mylinks')">立即提交</el-button>
					<el-button @click="resetForm('mylinks')">重置</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'save_mylinks',
		created() {
			var params = this.$route.query
			var id = params.id
			this.method = params.method
			if(params.method === 'edit'){
				this.mylinks.id = id
				// 查询链接信息，回显编辑
				this.$ajax.get(this.base_url + '/mylink/get/'+id).then((response)=>{
					if(response.data.code === 20000){
						this.mylinks = response.data.data
						console.log(response.data.data)
					}else{
						this.$message({
							message: '抱歉服务器故障,请稍后重试',
							type: 'error'
						})
					}
				})
			}
		},
		data() {
			return {
				method: '',
				mylinks: {
					id: null,
					name: '',
					iconFont: '',
					url: ''
				},
				rules: {
					name: [{
							required: true,
							message: '请输入链接名称',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 10,
							message: '长度在 1 到 10 个字符',
							trigger: 'blur'
						}
					],
					url: [{
						required: true,
						message: '请输入链接地址',
						trigger: 'blur'
					}]
				}
			}
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						var url = this.base_url + '/mylink/'
						if(this.method === 'edit' && this.mylinks.id !== null){
							url = url + 'update'
						}else if(this.method === 'add'){
							url = url + 'save'
						}
						this.$ajax.post(url, this.mylinks).then((response) => {
							if (response.data.code === 20000) {
								this.$message({
									message: '提交成功',
									type: 'success'
								})
								// 跳转到列表
								this.$router.push({
									path: '/links/mylinks'
								})
							}else{
								this.$message({
									message: '抱歉添加失败了，请稍后重试',
									type: 'success'
								})
							}
						})
					} else {
						this.$message({
							message: '请将表单填写完整',
							type: 'warning'
						})
						return false
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields()
			}
		}
	}
</script>

<style scoped="scoped">
	.save-mylinks-wrapper {
		margin-top: 15px;
		background-color: #fff;
		min-height: 560px;
		padding: 15px;
	}

	.mylinks-form {
		width: 70%;
		margin: 0 auto;
		margin-top: 35px;
	}

	.mylinks-form-ops {
		text-align: center;
	}
</style>
