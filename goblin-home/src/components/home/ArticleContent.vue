<template>
	<div class="article-details-wrapper">
		<div v-highlight v-html="contentHtml"></div>
	</div>
</template>

<script>
	export default {
		name: 'article_content',
		created() {
			this.queryDetails()
		},
		data() {
			return {
				articleId: this.$route.query.id,
				content: ''
			}
		},
		methods: {
			queryDetails() {
				this.$ajax.get(this.base_url + '/article/get/' + this.articleId).then((response) => {
					if (response.status === 200) {
						this.content = response.data.content
					} else {
						this.$message({
							message: '抱歉获取文章内容失败了，请稍后重试',
							type: 'error'
						})
					}
				})
			}
		},
		computed: {
			contentHtml() {
				// 将文章内容渲染成html
				return this.marked(this.content)
			}
		},
	}
</script>

<style scoped="scoped">
	.article-details-wrapper {
		background-color: #fff;
		text-align: left;
		padding: 40px;
	}
</style>
