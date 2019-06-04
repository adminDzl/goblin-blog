<template>
	<div>
		<div class="breadcrumb">
			微博
		</div>
		<div class="micro-blog-wrapper">
			<el-form label-position="right" :model="microBlog" class="micro-blog-form">
				<el-input type="textarea" v-model="microBlog.content" :rows="7" resize="none"></el-input>
				<div class="upload-image">
					<el-upload :action="uploadImgAction" ref="uploadImage" name="image" list-type="picture-card" :on-preview="handlePictureCardPreview"
					 :on-remove="handleRemove" :on-success="handleCoverPictureSuccess">
						<i class="el-icon-plus"></i>
					</el-upload>
					<el-dialog :visible.sync="dialogVisible">
						<img width="100%" :src="dialogImageUrl" alt="">
					</el-dialog>
				</div>
				<el-button type="success" @click="publishMicroBlog">发送</el-button>
			</el-form>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'write_microblog',
		data() {
			return {
				microBlog: {
					content: '',
					images: []
				},
				uploadImgAction: this.base_url + '/oss/upload_pic',
				dialogImageUrl: '',
				dialogVisible: false
			}
		},
		methods: {
			handleRemove(file, fileList) {
				console.log(this.microBlog.images)
				var index = this.microBlog.images.indexOf(file.response.data)
				// 删除元素
				this.microBlog.images.splice(index, 1)
			},
			handlePictureCardPreview(response) {
				this.dialogVisible = true
			},
			handleCoverPictureSuccess(res, file) {
				if (res.code === 20000) {
					this.dialogImageUrl = res.data
					this.microBlog.images.push(res.data)
					this.$message({
						type: 'success',
						message: '上传成功'
					})
				} else {
					this.$message({
						type: 'error',
						message: '抱歉服务器故障，请稍后重试'
					})
				}
			},
			publishMicroBlog() {
				var formData = new FormData()
				formData.append('content', this.microBlog.content)
				formData.append('images', this.microBlog.images)
				this.$ajax.post(this.base_url + '/microblog/save', formData).then((response)=>{
					if(response.data.code === 20000){
						this.$message({
							type: 'success',
							message: '发送成功'
						})
						// 重置表单
						this.resetMicroBlogForm()
					}else{
						this.$message({
							type: 'error',
							message: '抱歉服务器故障，请稍后重试'
						})
					}
				})
			},
			resetMicroBlogForm() {
				this.microBlog.content = ''
				this.microBlog.images = []
				this.dialogImageUrl = ''
				this.$refs.uploadImage.clearFiles()
			}
		}
	}
</script>

<style scoped="scoped">
	.breadcrumb {
		margin-bottom: 15px;
	}

	.micro-blog-wrapper {
		min-height: 580px;
		background-color: #fff;
	}

	.micro-blog-form {
		width: 600px;
		margin: 0 auto;
		padding-top: 35px;
	}
	.upload-image{
		margin-top: 15px;
		margin-bottom: 15px;
	}
</style>
