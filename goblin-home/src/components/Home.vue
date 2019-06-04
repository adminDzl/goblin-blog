<template>
	<div>
		<el-container>
			<el-header class="header">
				<el-menu :default-active="activeIndex" router class="top-menu" text-color="#4e9ec3" active-text-color="#494949">
					<el-row>
						<el-col :span="4">&nbsp;</el-col>
						<el-col :span="16">
							<el-row :gutter="20">
								<el-col :span="5">
									<h1 class="topbar-logo">
										<img src="../../static/img/topbar.png" alt="" width="100%">
									</h1>
								</el-col>
								<el-col :span="navObject.navColWith" v-for="(menu,index) in navObject.menuNames">
									<el-menu-item :index="menu.path">{{menu.name}}</el-menu-item>
								</el-col>
							</el-row>
						</el-col>
						<el-col :span="4">&nbsp;</el-col>
					</el-row>
				</el-menu>
			</el-header>
			<el-main class="main-body">
				<el-row>
					<el-col :span="4">
						<div class="grid-content bg-purple-light">&nbsp;</div>
					</el-col>
					<el-col :span="16">
						<el-row :gutter="15">
							<el-col :span="6">
								<div>
									<ContentLeft></ContentLeft>
								</div>
							</el-col>
							<el-col :span="18">
								<div class="grid-content-body">
									<router-view />
								</div>
							</el-col>
						</el-row>
					</el-col>
					<el-col :span="4">
						<div class="grid-content bg-purple-light">&nbsp;</div>
					</el-col>
				</el-row>
			</el-main>
			<el-footer class="footer">
				<el-row>
					<el-col :span="8">
						<aplayer :audio="$store.state.playList" fixed mini :lrcType="3" ref="musicplayer"/>
						<!-- <aplayer :music="$store.state.currentPlay" :list="$store.state.playlist" ref="musicplayer" class="music-player"></aplayer> -->
					</el-col>
				</el-row>
			</el-footer>
		</el-container>
	</div>
</template>

<script>
	import ContentLeft from './home/ContentLeft'
	export default {
		name: 'home',
		components: {
			ContentLeft
		},
		data() {
			return {
				topBackgroundImgUrl: '../static/img/topbk.jpg',
				navObject: {
					navColWith: 3,
					menuNames: [{
							id: 1,
							name: '首页',
							path: '/blog'
						},
						{
							id: 2,
							name: '动态',
							path: '/microblog'
						},
						{
							id: 3,
							name: '音乐',
							path: '/music'
						},
						{
							id: 4,
							name: '成就',
							path: '/3'
						},
						{
							id: 5,
							name: '关于我',
							path: '/4'
						},
						{
							id: 6,
							name: '搜索',
							path: '/5'
						}
					]
				},
				activeIndex: '1',
			}
		},
		methods: {

		},
		computed: {
			playStatus() {
				return this.$store.state.playing
			}
		},
		watch: {
			playStatus: function(oldVal, newVal) {
				if(newVal === false){
					this.$refs.musicplayer.play()
				}else{
					this.$refs.musicplayer.pause()
				}
			}
		}
	}
</script>

<style scoped="scoped">
	.header {
		padding: 0px;
	}

	.main-body {
		padding: 0px;
	}

	/**
	 * 正式样式
	 */
	.topbar-logo {
		height: 56px;
		line-height: 56px;
		padding: 0px;
		margin: 0px;
		display: block;
	}

	.topbar-logo img {
		display: inline-block;
		vertical-align: middle;
	}

	.footer {
		width: 100%;
		position: fixed;
		bottom: 0px;
		left: 0px;
		padding: 0px;
	}
</style>
