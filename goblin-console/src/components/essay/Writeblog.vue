<template>
	<div>
		<el-row>
			<el-col :span="12">
				<div class="writelog-title">发博文</div>
			</el-col>
			<el-col :span="12">
				<el-button type="success" size="small" class="publish-btn" @click="publish">发布文章</el-button>
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
									<mavon-editor ref=md :ishljs="true" @imgAdd="imgUpload" @save="saveArticle" v-model="articleForm.content"
									 class="mavon-editor" />
								</el-form-item>
								<el-form-item label="封面和摘要" required>
									<el-row class="cover-digest">
										<el-col :span="5">
											<el-upload class="coverpicture-uploader" name="image" :action="this.base_url + '/oss/upload_pic'"
											 :show-file-list="false" :on-success="handleCoverPictureSuccess" :before-upload="beforeCoverPictureUpload">
												<img v-if="articleForm.coverUrl" :src="articleForm.coverUrl" class="cover-picture">
												<i v-else class="el-icon-plus avatar-uploader-icon"></i>
											</el-upload>
										</el-col>
										<el-col :span="19">
											<el-input type="textarea" :rows="4" resize="none" placeholder="请输入文章摘要不填写默认摘取文章前54字" v-model="articleForm.summary">
											</el-input>
										</el-col>
									</el-row>
								</el-form-item>
								<el-form-item label="原创声明" prop="delivery">
									<el-switch v-model="is_origin" active-color="#13ce66" inactive-color="#dcdfe6">
									</el-switch>
								</el-form-item>
								<el-form-item label="文章设置" prop="articleSetting">
									<el-popover placement="top" width="400" v-model="popoverVisible">
										<el-input placeholder="请输入原文链接" v-model="articleForm.sourceUrl">
											<template slot="prepend"><i class="fa fa-link"></i></template>
										</el-input>
										<div class="originurl-popover">
											<el-button size="mini" type="text" @click="cancelPutSourceURL">取消</el-button>
											<el-button type="success" size="mini" @click="surePutSourceURL">确定</el-button>
										</div>
										<!-- 如果存在原文链接将button的风格设置为success否则为默认 -->
										<el-button slot="reference" :type="hasSourceURL">
											原文链接
											<i class="el-icon-arrow-right"></i>
										</el-button>
									</el-popover>
									<span class="original-url">{{articleForm.sourceUrl}}</span>
								</el-form-item>
								<el-form-item label="文章标签" prop="tag">
									<el-tag :key="tag" v-for="tag in dynamicTags" closable :disable-transitions="false" @close="handleClose(tag)">
										{{tag}}
									</el-tag>
									<el-input class="input-new-tag" v-if="tagInputVisible" v-model="tagInputValue" ref="saveTagInput" size="small"
									 @keyup.enter.native="handleInputConfirm" @blur="handleInputConfirm">
									</el-input>
									<el-button v-else class="button-new-tag" size="small" @click="showTagInput" v-show="newTagFlag">+ New Tag</el-button>
								</el-form-item>
								<el-form-item label="所属分类">
									<el-checkbox-group v-model="categoryChecked" size="mini">
										<el-checkbox :label="category.id" border v-for="(category,index) in categoryList" :key="index" class="categoryCheckbox">{{category.name}}</el-checkbox>
									</el-checkbox-group>
								</el-form-item>
								<el-form-item label="是否公开">
									<el-radio-group v-model="visible">
										<el-radio :label="1">公开</el-radio>
										<el-radio :label="0">私密</el-radio>
									</el-radio-group>
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
	import ImageCrop from '@/components/common/ImageCrop'
	// 图片裁剪组件
	export default {
		components: {
			'image-crop': ImageCrop
		},
		created() {
			this.queryCategory()
		},
		mounted() {
			var articelId = this.$route.query.id
			if (articelId !== 'undefined' && articelId !== undefined && articelId !== '') {
				this.methodName = 'update'
				this.$ajax.get(this.base_url + '/article/get/' + articelId)
					.then((response) => {
						if (response.status === 200) {
							var data = response.data
							console.log(data)
							// 查询文章内容回显到表单
							this.articleForm.title = data.title
							this.articleForm.content = data.content
							this.articleForm.summary = data.summary
							this.articleForm.coverUrl = data.coverUrl
							this.articleForm.source = data.source
							this.articleForm.sourceUrl = data.sourceUrl
							this.dynamicTags = eval(data.tag)
							this.categoryChecked = eval(data.categoryId)
							this.visible = data.visible
							if (data.source === 1) {
								// 回显是否原创
								this.is_origin = true
							} else {
								this.is_origin = false
							}
							if (data.sourceUrl !== '') {
								// 回显是否原创
								this.hasSourceURL = 'success'
							} else {
								this.is_origin = ''
							}
						} else {
							this.$message({
								type: 'error',
								message: '抱歉初始化数据出错了，请稍后再试'
							})
						}
					})
			} else {
				// 不是编辑，则清空表单
				this.resetFormHandler()
			}
		},
		beforeDestroy() {
			console.log("销毁")
		},
		data() {
			return {
				methodName: 'save', // 默认请求后端的方法名称
				is_origin: true, // 是否原创
				visible: 1, // 是否公开
				categoryChecked: [], // 选中的博客分类id
				dynamicTags: [],
				articleForm: {
					id: this.$route.params.id,
					title: '',
					content: '', // 文章内容
					summary: '', // 文章摘要
					coverUrl: '', // 封面图片
					source: '', // 是否原创
					sourceUrl: '', // 原文链接
					tag: '', // 标签
					categoryId: '', // 文章分类
					visible: '' // 是否公开
				},
				hasSourceURL: '', // 是否存在原文链接
				tagInputVisible: false,
				tagInputValue: '',
				newTagFlag: true,
				popoverVisible: false,
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
				},
				categoryList: [] // 服务器查询到的分类数据
			}
		},
		methods: {
			queryCategory() {
				this.$ajax({
					url: this.base_url + '/category/list',
					method: 'get',
					headers: {
						'Content-Type': 'application/json'
					}
				}).then((response) => {
					if (response.status === 200) {
						this.categoryList = response.data
					} else {
						this.$message({
							type: 'error',
							message: '抱歉查询文章分类出错，请稍后重试'
						})
					}
				})
			},
			// form表单操作方法
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						alert('submit!')
					} else {
						console.log('error submit!!')
						return false
					}
				})
			},
			resetForm(formName) {
				this.$refs[formName].resetFields()
			},
			handleCoverPictureSuccess(res, file) {
				console.log(res)
				if (res.code === 20000) {
					this.articleForm.coverUrl = res.data
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
			beforeCoverPictureUpload(file) {
				var isImg = false
				if (file.type === 'image/jpeg' || file.type === 'image/jpg' || file.type === 'image/png' || file.type ===
					'image/bmp') {
					isImg = true
				}
				var isLt10M = file.size / 1024 / 1024 < 10

				if (!isImg) {
					this.$message.error('封面图片只能是 JPG 格式!')
				}
				if (!isLt10M) {
					this.$message.error('封面图片大小不能超过 10MB!')
				}
				return isImg && isLt10M
			},
			changeIsOriginal() {
				// 声明原创
				this.is_origin = !this.is_origin
			},
			cancelPutSourceURL() {
				this.popoverVisible = false
				if (this.articleForm.sourceUrl === '') {
					this.hasSourceURL = ''
				}
			},
			surePutSourceURL() {
				// 隐藏输入框
				this.popoverVisible = false
				// 确定原文链接,将原文链接的按钮设置为success样式
				if (this.articleForm.sourceUrl !== '') {
					this.hasSourceURL = 'success'
				} else {
					this.hasSourceURL = ''
				}
			},
			// 图片上传方法绑定@imgAdd event
			imgUpload(pos, $file) {
				// 第一步.将图片上传到服务器.
				var formdata = new FormData()
				formdata.append('image', $file)
				this.$ajax({
					url: this.base_url + '/oss/upload_pic',
					method: 'post',
					data: formdata,
					headers: {
						'Content-Type': 'multipart/form-data'
					}
				}).then((response) => {
					// 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
					/**
					 * $vm 指为mavonEditor实例，可以通过如下两种方式获取
					 * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
					 * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
					 */
					var responseObject = response.data
					if (responseObject.code === 20000) {
						var MavonEditor = this.$refs.md
						MavonEditor.$img2Url(pos, responseObject.data)
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
				})
			},
			saveArticle() {
				// ctrl + s保存文章
				// this.test = this.marked(this.articleForm.content)
			},
			publish() {
				// 发布文章
				var h = this.$createElement
				this.$msgbox({
					title: '消息',
					message: h('p', null, [
						h('span', null, '确认发布文章吗? ')
					]),
					showCancelButton: true,
					confirmButtonText: '发布',
					cancelButtonText: '取消',
					beforeClose: (action, instance, done) => {
						if (action === 'confirm') {
							done()
							// 同步数据
							this.handlerParamSyncBind()
							this.$http.post(this.base_url + '/article/' + this.methodName, this.articleForm)
								.then(function(response) {
									var responseObj = JSON.stringify(response)
									var result = JSON.parse(responseObj)
									if (result.status === 200) {
										// 还原表单
										this.resetFormHandler()
										this.$message({
											type: 'success',
											message: '发布成功'
										})
									} else {
										this.$message({
											type: 'error',
											message: '抱歉出错了，请稍后再试'
										})
									}
								})
						} else {
							done()
						}
					}
				})
			},
			handleClose(tag) {
				this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1)
			},
			showTagInput() {
				if (this.dynamicTags.length < 5) {
					this.tagInputVisible = true
					this.$nextTick(_ => {
						this.$refs.saveTagInput.$refs.input.focus()
					})
				} else {
					this.tagInputVisible = false
					this.newTagFlag = false
				}
			},
			handleInputConfirm() {
				var tagInputValue = this.tagInputValue
				if (tagInputValue) {
					this.dynamicTags.push(tagInputValue)
					this.tagInputValue = ''
					this.tagInputVisible = false
				} else {
					this.tagInputVisible = false
					this.tagInputValue = ''
				}
			},
			handlerParamSyncBind() {
				this.articleForm.tag = this.dynamicTags
				this.articleForm.categoryId = this.categoryChecked
				this.articleForm.visible = this.visible
				if (this.is_origin) {
					this.articleForm.source = 1
				} else {
					this.articleForm.source = 0
				}
			},
			resetFormHandler() {
				this.methodName = 'save'
				// 查询文章内容回显到表单
				this.articleForm.title = ''
				this.articleForm.content = ''
				this.articleForm.summary = ''
				this.articleForm.coverUrl = ''
				this.articleForm.source = 1
				this.articleForm.sourceUrl = ''
				this.dynamicTags = []
				this.categoryChecked = []
				this.visible = 1
				this.is_origin = true
				// 回显是否原创按钮的样式
				this.is_origin = ''
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

	/* 标签样式 */
	.el-tag+.el-tag {
		margin-left: 10px;
	}

	.button-new-tag {
		margin-left: 10px;
		height: 32px;
		line-height: 30px;
		padding-top: 0;
		padding-bottom: 0;
	}

	.input-new-tag {
		width: 90px;
		margin-left: 10px;
		vertical-align: bottom;
	}

	/* 博客分类样式 */
	.categoryCheckbox {
		margin: 0px;
		vertical-align: middle;
	}
</style>
