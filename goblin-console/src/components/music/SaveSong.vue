<template>
	<div>
		保存歌曲
		<div class="save-song-wrapper">
			<el-form :model="songs" :rules="rules" ref="songsForm" label-width="100px" class="songs-form">
				<el-form-item label="歌曲名称" prop="name">
					<el-input v-model="songs.name"></el-input>
				</el-form-item>
				<el-form-item label="歌手" prop="singer">
					<el-input v-model="songs.singer"></el-input>
				</el-form-item>
				<el-form-item label="歌曲地址" prop="url">
					<el-input v-model="songs.url"></el-input>
					<el-upload :action="this.base_url + '/oss/upload_file'" name="multiFile" :on-preview="handlerPreviewUploadFile"
					 :on-success="handlerUploadMusicSuccess" multiple :limit="1" :on-error="uploadMusicError" class="upload-song"
					 accept="audio/mpeg,audio/x-wav,audio/x-flac">
						<el-button size="small" type="primary">点击上传</el-button>
						<div slot="tip" class="el-upload__tip">只能上传mp3/wav/flac文件，且不超过15mb，点击上传后的歌曲可自动填充歌曲名</div>
					</el-upload>
				</el-form-item>
				<el-form-item label="歌曲描述" prop="description">
					<el-input type="textarea" :autosize="{ minRows: 3, maxRows: 6}" resize="none" v-model="songs.description">
					</el-input>
				</el-form-item>
				<el-form-item label="歌词地址" prop="lyrics">
					<el-input v-model="songs.lyrics"></el-input>
				</el-form-item>
				<el-form-item label="专辑名称" prop="album">
					<el-input v-model="songs.album"></el-input>
				</el-form-item>
				<el-form-item label="专辑封面" prop="albumArt">
					<el-input v-model="songs.albumArt"></el-input>
					<el-upload class="image-uploader" :action="this.base_url + '/oss/upload_pic'" :on-success="handlerUploadImageSuccess"
					 name="image" :show-file-list="false" :on-error="uploadImageError">
						<img v-if="songs.albumArt" :src="songs.albumArt" class="album-art-img">
						<i v-else class="el-icon-plus image-uploader-icon"></i>
					</el-upload>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="submitForm('songsForm')">立即创建</el-button>
					<el-button @click="resetForm('songsForm')">重置</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
	import {secondsToMinuteColonSeconds} from '../../assets/js/datefmt.js'
	export default {
		name: 'savesong',
		created() {
			var method = this.$route.query.method
			if(method === 'edit'){
				var id = this.$route.query.id
				// 根据id查询信息，回显表单
				this.$ajax.get(this.base_url + '/music/get/'+id).then((response)=>{
					if(response.data.code === 20000){
						this.songs = response.data.data
						this.method = 'update'
					}else{
						this.$message({
							message: '抱歉服务器故障，信息加载失败，请稍后重试',
							type: 'error'
						})
					}
				})
			}
		},
		data() {
			return {
				method: 'save',
				songs: {
					id: null,
					name: '',
					songTime: '',
					singer: '',
					album: '未知专辑',
					albumArt: '',
					description: '',
					url: '',
					lyrics: ''
				},
				rules: {
					name: [{
							required: true,
							message: '请输入歌曲名称',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 40,
							message: '长度在 1 到 40 个字符',
							trigger: 'blur'
						}
					],
					singer: [{
							required: true,
							message: '请输入歌手名称',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 30,
							message: '长度在 1 到 30 个字符',
							trigger: 'blur'
						}
					],
					url: [{
							required: true,
							message: '请输入歌曲url地址或者上传歌曲',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 325,
							message: '长度在 1 到 325 个字符',
							trigger: 'blur'
						}
					],
					album: [{
							required: true,
							message: '请输入专辑名称',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 30,
							message: '长度在 1 到 30 个字符',
							trigger: 'blur'
						}
					],
					albumArt: [{
						required: true,
						message: '请填写封面地址或者上传封面',
						trigger: 'blur'
					}]
				}
			}
		},
		methods: {
			uploadImageError() {
				this.$message({
					message: '抱歉服务器故障，图片上传失败，请稍后重试',
					type: 'error'
				})
			},
			uploadMusicError() {
				this.$message({
					message: '抱歉服务器故障，文件上传失败，请稍后重试',
					type: 'error'
				})
			},
			handlerUploadMusicSuccess(response, file, fileList) {
				if (response.code === 20000) {
					// 设置音乐url地址
					this.songs.url = response.data
					var audio = new Audio(this.songs.url)
					audio.addEventListener('loadeddata', () => {
						// 获取音频时长
						var duration = audio.duration
						var musicTime = secondsToMinuteColonSeconds(duration)
						console.log(musicTime)
						this.songs.songTime = musicTime
					})
					this.$message({
						message: '上传成功',
						type: 'success'
					})
					// 重置表单
					this.resetForm('songsForm')
				}
			},
			handlerUploadImageSuccess(response, file, fileList) {
				if (response.code === 20000) {
					this.songs.albumArt = response.data
					this.$message({
						message: '上传成功',
						type: 'success'
					})
				}
			},
			handlerPreviewUploadFile(file) {
				this.songs.name = file.name
			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$ajax.post(this.base_url + '/music/'+this.method, this.songs).then((response) => {
							if (response.data.code === 20000) {
								this.$message({
									message: '提交成功',
									type: 'success'
								})
								this.$router.push('/music/mylove')
							} else {
								this.$message({
									message: '抱歉服务器故障，请稍后重试',
									type: 'error'
								})
							}
						})
					} else {
						this.$message({
							message: '请将表单填写完整',
							type: 'warning'
						})
						return false;
					}
				})
			},
			resetForm(formName) {
				this.$refs[formName].resetFields()
			}
		}
	}
</script>

<style scoped="scoped">
	.save-song-wrapper {
		min-height: 560px;
		padding: 15px;
		margin-top: 15px;
		background-color: #fff;
	}

	.songs-form {
		width: 70%;
		margin: 0 auto;
	}

	.upload-song {
		margin-top: 15px;
	}

	.image-uploader {
		width: 140px;
		height: 140px;
		margin-top: 15px;
		text-align: center;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
		border: 1px dashed #d9d9d9;
	}

	.image-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.image-uploader-icon {
		font-size: 28px;
		height: 100%;
		width: 140px;
		color: #8c939d;
		line-height: 140px;
		text-align: center;
	}

	.album-art-img {
		width: 140px;
		height: 140px;
		display: block;
	}
</style>
