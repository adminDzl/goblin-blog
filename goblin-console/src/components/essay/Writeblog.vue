<template>
	<div>
		<el-row>
			<el-col :span="12">
				<div class="writelog-title">发博文</div>
			</el-col>
			<el-col :span="12">
				<el-button type="success" size="small" class="publish-btn">发布文章</el-button>
			</el-col>
		</el-row>
		<el-row>
			<el-col :span="24">
				<div class="mdeditor">
					<el-row>
						<el-col :span="21" :offset="1">
							<el-form :model="articleForm" :rules="rules" ref="articleForm" label-width="100px" class="essayForm">
								<el-form-item label="标题" prop="title" required>
									<el-input v-model="articleForm.title"></el-input>
								</el-form-item>
								<el-form-item label="正文" prop="article" required>
									<mavon-editor ishljs="true" @imgAdd="imgUpload" @save="saveArticle" v-model="articleForm.article" class="mavon-editor" />
								</el-form-item>
								<el-form-item label="封面和摘要" required>
									<el-row class="cover-digest">
										<el-col :span="5">
											<el-upload class="coverpicture-uploader" action="https://jsonplaceholder.typicode.com/posts/"
											 :show-file-list="false" :on-success="handleCoverPictureSuccess" :before-upload="beforeCoverPictureUpload">
												<img v-if="articleForm.coverpictureURL" :src="articleForm.coverpictureURL" class="cover-picture">
												<i v-else class="el-icon-plus avatar-uploader-icon"></i>
											</el-upload>
										</el-col>
										<el-col :span="19">
											<el-input type="textarea" :rows="4" resize="none" placeholder="请输入文章摘要不填写默认摘取文章前54字" v-model="articleForm.articledigest">
											</el-input>
										</el-col>
									</el-row>
								</el-form-item>
								<el-form-item label="原创声明" prop="delivery">
									<el-switch v-model="articleForm.isOriginal" active-color="#13ce66" inactive-color="#dcdfe6">
									</el-switch>
								</el-form-item>
								<el-form-item label="文章设置" prop="articleSetting">
									<el-popover placement="top" width="400" v-model="popoverVisible">
										<el-input placeholder="请输入原文链接" v-model="articleForm.originalURL">
											<template slot="prepend"><i class="fa fa-link"></i></template>
										</el-input>
										<div class="originurl-popover">
											<el-button size="mini" type="text" @click="cancelPutOriginalURL">取消</el-button>
											<el-button type="success" size="mini" @click="surePutOriginalURL">确定</el-button>
										</div>
										<!-- 如果存在原文链接将button的风格设置为success否则为默认 -->
										<el-button slot="reference" :type="hasOriginalURL">
											原文链接
											<i class="el-icon-arrow-right"></i>
										</el-button>
									</el-popover>
									<span class="original-url">{{articleForm.originalURL}}</span>
								</el-form-item>
							</el-form>
						</el-col>
					</el-row>
				</div>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				popoverVisible: false,
				hasOriginalURL: '', // 是否存在原文链接
				articleForm: {
					title: '',
					article: '',
					coverpictureURL: '',
					articledigest: '',
					isOriginal: false,
					originalURL: ''
				},
				rules: {
					title: [{
							required: true,
							message: '请输入文章标题',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 100,
							message: '长度在 1 到 100 个字符',
							trigger: 'blur'
						}
					],
					coverpictureURL: [{
						required: true,
						message: '请上传文章封面',
						trigger: 'blur'
					}]
				}
			};
		},
		methods: {
			// form表单操作方法
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						alert('submit!');
					} else {
						console.log('error submit!!');
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			handleCoverPictureSuccess(res, file) {
				this.imageUrl = URL.createObjectURL(file.raw);
			},
			beforeCoverPictureUpload(file) {
				const isJPG = file.type === 'image/jpeg';
				const isLt2M = file.size / 1024 / 1024 < 2;

				if (!isJPG) {
					this.$message.error('上传头像图片只能是 JPG 格式!');
				}
				if (!isLt2M) {
					this.$message.error('上传头像图片大小不能超过 2MB!');
				}
				return isJPG && isLt2M;
			},
			changeIsOriginal() {
				// 声明原创
				this.articleForm.isOriginal = !this.articleForm.isOriginal
			},
			cancelPutOriginalURL() {
				this.popoverVisible = false
				// 取消输入原文链接时，将原文链接的内容置空
				if (this.hasOriginalURL != 'success' && this.articleForm.originalURL == '') {
					this.articleForm.originalURL = ''
					this.hasOriginalURL = ''
				} else {
					this.articleForm.originalURL = ''
				}
			},
			surePutOriginalURL() {
				// 隐藏输入框
				this.popoverVisible = false
				// 确定原文链接,将原文链接的按钮设置为success样式
				if (this.articleForm.originalURL != '') {
					this.hasOriginalURL = 'success'
				} else {
					this.hasOriginalURL = ''
				}
			},
			// 图片上传方法绑定@imgAdd event
			imgUpload(pos, $file) {
				// 第一步.将图片上传到服务器.
				var formdata = new FormData();
				formdata.append('image', $file);
				axios({
					url: 'server url',
					method: 'post',
					data: formdata,
					headers: {
						'Content-Type': 'multipart/form-data'
					},
				}).then((url) => {
					// 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
					/**
					 * $vm 指为mavonEditor实例，可以通过如下两种方式获取
					 * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
					 * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
					 */
					$vm.$img2Url(pos, url);
				})
			},
			saveArticle(){
				// ctrl + s保存文章
			}
		}
	}
</script>

<style scoped="scoped">
	.writelog-title {
		font-size: 16px;
		margin-bottom: 20px;
	}

	.mdeditor {
		min-height: 900px;
		background-color: #fff;
	}

	.mdeditor .essayForm {
		margin-top: 30px;
	}

	.mavon-editor {
		height: 470px;
	}

	/* 文章封面样式 */
	.cover-digest {
		height: 90px;
	}

	.coverpicture-uploader {
		height: 100%;
	}

	.coverpicture-uploader .el-upload {
		height: 100%;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.coverpicture-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		width: 211.5px;
		height: 95px;
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		font-size: 28px;
		color: #8c939d;
		line-height: 90px;
		text-align: center;
	}

	.cover-picture {
		width: 211.5px;
		height: 90px;
		display: block;
	}

	.article-digest {
		height: 90px;
		width: 100%;
		resize: none;
		font-family: inherit;
		margin: 0;
		font-size: 14px;
		color: #606266;
		border-radius: 4px;
		border: 1px solid #dcdfe6;
	}

	.originurl-popover {
		text-align: right;
		margin: 5px 0 0 0;
	}

	.original-url {
		margin-left: 15px;
		max-width: 536px;
		display: inline-block;
		overflow: hidden;
		vertical-align: bottom;
		text-overflow: ellipsis;
		white-space: nowrap;
		color: #8d8d8d;
		text-align: left;
	}

	.publish-btn {
		float: right;
	}
</style>
