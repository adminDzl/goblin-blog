<template>
	<div>
		<el-container>
			<el-header class="header" v-show="this.$store.state.showNavbars">
				<div class="nav-top-menu">
					<el-menu class="el-menu-demo" mode="horizontal">
						<el-menu-item>
							<div class="logo">
								<img src="../../../../build/logo.png" alt="logo" height="100%">
							</div>
						</el-menu-item>
						<el-menu-item index="1" @click="changeCollapse">
							<img src="../../../assets/img/app/菜单.png" alt="" height="15px">
						</el-menu-item>
						<el-menu-item index="2" class="pull-right">
							<el-dropdown>
								<div class="user-box">
									<a href="#" class="topbar-info-dropdown-toggle topbar-btn">
										<img class="topbar-user-avatar" src="https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg">
										<span>momo.xyz</span>
									</a>
								</div>
								<el-dropdown-menu slot="dropdown" class="topbar-usermemu">
									<div class="topbar-user-header">
										<img class="topbar-userheader-avatar" src="https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg">
										<span>momo.xyz</span>
									</div>
									<p class="line"></p>
									<el-dropdown-item>
										<router-link :to="{ path: '/message' }"><i class="fa fa-commenting-o"></i>信息</router-link>
										<el-badge class="pull-right msg-badge" :value="3" />
									</el-dropdown-item>
									<el-dropdown-item>
										<a href="#"><i class="fa fa-file-o"></i>访问日志</a></el-dropdown-item>
									<el-dropdown-item>
										<a href="#"><i class="fa fa-address-card-o"></i>个人资料</a></el-dropdown-item>
									<el-dropdown-item>
										<a href=""><i class="fa fa-unlock-alt"></i>修改密码</a></el-dropdown-item>
									<el-dropdown-item class="sign-out" >
										<a href="javascript:;" @click="logout"><i class="fa fa-sign-out"></i>注销</a></el-dropdown-item>
								</el-dropdown-menu>
							</el-dropdown>
						</el-menu-item>
						<el-menu-item index="3" class="pull-right">
							<i class="fa fa-bell-o fa-lg"></i>
						</el-menu-item>
						<el-menu-item index="4" class="pull-right">
							<i class="fa fa-search fa-lg"></i>
						</el-menu-item>
					</el-menu>
				</div>
			</el-header>
			<el-container>
				<el-aside width="200" v-show="this.$store.state.showNavbars">
					<div class="nav-left-menu">
						<!-- 使用default-active="1-4-1" 打开菜单时展开-->
						<el-menu :default-active="this.$router.path" router :collapse="isCollapse" unique-opened>
							<el-menu-item index="/dashboard">
								<i class="fa fa-dashboard"></i>
								<span slot="title">数据概况</span>
							</el-menu-item>
							<el-submenu index="1">
								<template slot="title">
									<i class="fa fa-pencil-square-o"></i>
									<span slot="title">博文管理</span>
								</template>
								<el-menu-item-group>
									<span slot="title">文章管理</span>
									<el-menu-item index="/essay/write" @click="reloadCurrent">新增</el-menu-item>
									<el-menu-item index="/essay/edit">编辑</el-menu-item>
								</el-menu-item-group>
								<el-menu-item-group title="动态">
									<el-menu-item index="/micro_blog/write">写动态</el-menu-item>
									<el-menu-item index="1-4">动态管理</el-menu-item>
								</el-menu-item-group>
							</el-submenu>
							<el-menu-item index="/category">
								<i class="fa fa-cubes"></i>
								<span slot="title">分类管理</span>
							</el-menu-item>
							<el-menu-item index="/message">
								<i class="fa fa-commenting-o"></i>
								<span slot="title">消息管理</span>
							</el-menu-item>
							<el-submenu index="/content_manager" :hide-timeout="100">
								<template slot="title">
									<i class="fa fa-folder-open"></i>
									<span slot="title">内容管理</span>
								</template>
								<el-menu-item-group>
									<span slot="title">链接</span>
									<el-menu-item index="/links/mylinks">社交媒体</el-menu-item>
									<el-menu-item index="/links/blogroll">友情链接</el-menu-item>
								</el-menu-item-group>
								<el-menu-item-group>
									<span slot="title">音乐</span>
									<el-menu-item index="/music/mylove">我喜欢</el-menu-item>
									<el-menu-item index="/music/playlist">歌单推荐</el-menu-item>
									<el-menu-item index="/music/carousel">轮播图</el-menu-item>
								</el-menu-item-group>
							</el-submenu>
							<el-menu-item index="/user_list">
								<i class="fa fa-user-circle"></i>
								<span slot="title">用户管理</span>
							</el-menu-item>
							<el-menu-item index="/image_crop">
								<i class="fa fa-lock"></i>
								<span slot="title">授权管理</span>
							</el-menu-item>
							<el-menu-item index="7">
								<i class="fa fa-cogs"></i>
								<span slot="title">系统设置</span>
							</el-menu-item>
						</el-menu>
					</div>
				</el-aside>
				<el-container>
					<el-main class="router-content">
						<transition name="fade" mode="out-in">
							<router-view />
						</transition>
					</el-main>
					<!-- <el-footer>Footer</el-footer> -->
				</el-container>
			</el-container>
		</el-container>
	</div>
</template>

<script>
	export default {
		name: 'navMenu',
		data() {
			return {
				isCollapse: true
			}
		},
		methods: {
			reloadCurrent() {
				// this.$router.go(this.$route.path)
				this.$router.replace('/refresh')
			},
			changeCollapse() {
				this.isCollapse = !this.isCollapse
			},
			logout() {
				this.$ajax.get(this.base_url + '/logout').then((response)=>{
					if(response.data.code === 20000){
						this.$router.push('/login')
						this.$store.commit('changeAuthToken', '')
						localStorage.removeItem("authorization")
					}else{
						this.$message({
							message: response.data.message,
							type: 'error'
						})
					}
				})
			}
		}
	}
</script>

<style scoped="scoped">
	.el-menu-vertical-demo:not(.el-menu--collapse) {
		width: 200px;
		min-height: 400px;
	}

	/* 淡入淡出过渡动画 */
	.fade-enter {
		opacity: 0;
	}

	.fade-enter-active {
		transition: opacity .5s;
	}

	.fade-leave-active {
		transition: transform .5s;
	}

	.fade-leave-to {
		transform: translateX(10px);
	}

	.logo {
		width: 100%;
		height: 100%;
	}

	.el-menu--horizontal>.el-menu-item.is-active {
		border-bottom: none;
	}

	.pull-right {
		float: right;
	}

	.header {
		padding: 0px;
	}

	.user-box .topbar-user-avatar {
		width: 32px;
		height: 32px;
		vertical-align: middle;
		border-radius: 16px;
	}

	.topbar-userheader-avatar {
		width: 52px;
		height: 52px;
		vertical-align: middle;
		border-radius: 26px;
		margin-right: 10px;
	}

	.topbar-usermemu a {
		color: #646772;
		font-size: 12px;
	}

	.topbar-user-header {
		padding-bottom: 10px;
		margin: 0px 15px;
		color: #28292b;
	}

	.line {
		margin: 0px;
		padding: 0px;
		height: 0px;
		border-bottom: 5px solid #fbfbfb;
	}

	.sign-out a {
		font-size: 14px;
		color: #1eb1ff;
	}

	.msg-badge {
		margin-top: 5px;
	}
</style>
