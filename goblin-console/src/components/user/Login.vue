<template>
	<div>
		<div class="login-form-wrapper">
			<el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px">
				<el-form-item label="用户名" prop="username">
					<el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="password">
					<el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="submitForm('ruleForm')" class="login-btn">登陆</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'login',
		mounted() {
			// 不显示导航条
			this.$store.dispatch('changeShowNavbars', false)
		},
		data() {
			return {
				ruleForm: {
					username: '',
					password: ''
				},
				rules: {
					username: [{
							required: true,
							message: '请输入用户名',
							trigger: 'blur'
						}
					],
					password: [{
						required: true,
						message: '请输入密码',
						trigger: 'blur',
						}
					]
				}
			}
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						var formData = new FormData();
						formData.append('username', this.ruleForm.username);
						formData.append('password', this.ruleForm.password);
						this.$ajax({
							url: this.base_url + '/login',
							method: 'post',
							contentType: ' application/x-www-form-urlencoded',
							data: formData
						}).then((response) => {
							if (response.data.code === 20000) {
								// 存储token
								this.$store.commit('changeAuthToken', response.data.token)
								this.$store.commit('changeShowNavbars', true)
								// 登陆成功跳转到首页
								this.$router.push('/')
							} else {
								this.$message({
									message: response.data.message,
									type: 'error'
								})
							}
						})
					} else {
						console.log('error submit!!')
						return false
					}
				})
			}
		}
	}
</script>

<style scoped="scoped">
	.login-form-wrapper {
		width: 500px;
		padding: 25px 35px 15px 0px;
		margin: 0 auto;
		margin-top: 10%;
		background-color: #fff;
	}

	.login-btn {
		width: 100%;
	}
</style>
