<template>
	<div>
		<my-upload field="image" @crop-success="cropSuccess" :withCredentials="true" @crop-upload-success="cropUploadSuccess"
		 @crop-upload-fail="cropUploadFail" v-model="show" :width="width" :height="height" :url="this.base_url + '/oss/upload_pic'"
		 img-format="png"></my-upload>
		<img :src="base64ImgUrl">
	</div>
</template>

<script>
	// 图片裁剪组件
	import myUpload from 'vue-image-crop-upload'
	export default {
		components: {
			'my-upload': myUpload
		},
		props: ['height', 'width', 'show'],
		data() {
			return {
				base64ImgUrl: '' // the datebase64 url of created image
			}
		},
		methods: {
			/**
			 * crop success
			 *
			 * [param] imgDataUrl
			 * [param] field
			 */
			cropSuccess(base64ImgUrl, field) {
				this.base64ImgUrl = base64ImgUrl
			},
			/**
			 * upload success
			 * [param] jsonData  server api return data, already json encode
			 * [param] field
			 */
			cropUploadSuccess(jsonData, field) {
				console.log('-------- upload success --------')
				console.log(jsonData)
				console.log('field: ' + field)
			},
			/**
			 * upload fail
			 * [param] status    server api return error status, like 500
			 * [param] field
			 */
			cropUploadFail(status, field) {
				this.$message({
					type: 'error',
					message: '抱歉上传失败了，请稍后重试'
				})
			}
		}
	}
</script>

<style>
</style>
