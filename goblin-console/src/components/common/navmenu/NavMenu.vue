<template>
	<div>
		<el-container>
			<el-header class="header">
				<div class="nav-top-menu">
					<el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect">
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
										<a href="#"><i class="fa fa-commenting-o"></i>信息</a>
										<el-badge class="pull-right msg-badge" :value="3" />
									</el-dropdown-item>
									<el-dropdown-item>
										<a href="#"><i class="fa fa-file-o"></i>访问日志</a></el-dropdown-item>
									<el-dropdown-item>
										<a href="#"><i class="fa fa-address-card-o"></i>个人资料</a></el-dropdown-item>
									<el-dropdown-item>
										<a href=""><i class="fa fa-unlock-alt"></i>修改密码</a></el-dropdown-item>
									<el-dropdown-item class="sign-out">
										<a href="#"><i class="fa fa-sign-out"></i>注销</a></el-dropdown-item>
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
				<el-aside width="200">
					<div class="nav-left-menu">
						<!-- 使用default-active="1-4-1" 打开菜单时展开-->
						<el-menu :default-active="this.$router.path" router class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
						 :collapse="isCollapse">
							<el-menu-item index="/dashboard">
								<i class="fa fa-dashboard"></i>
								<span slot="title">数据概况</span>
							</el-menu-item>
							<el-submenu index="1">
								<template slot="title">
									<i class="fa fa-pencil-square-o"></i>
									<span slot="title">文章管理</span>
								</template>
								<el-menu-item-group>
									<span slot="title">文章管理</span>
									<el-menu-item index="/essay/write">新增</el-menu-item>
									<el-menu-item index="/essay/edit">编辑</el-menu-item>
								</el-menu-item-group>
								<el-menu-item-group title="分组2">
									<el-menu-item index="1-3">选项3</el-menu-item>
								</el-menu-item-group>
								<el-submenu index="1-4">
									<span slot="title">选项4</span>
									<el-menu-item index="1-4-1">选项1</el-menu-item>
								</el-submenu>
							</el-submenu>
							<el-menu-item index="3">
								<i class="fa fa-cubes"></i>
								<span slot="title">分类管理</span>
							</el-menu-item>
							<el-menu-item index="4">
								<i class="fa fa-commenting-o"></i>
								<span slot="title">消息管理</span>
							</el-menu-item>
							<el-menu-item index="5">
								<i class="fa fa-user-circle"></i>
								<span slot="title">用户管理</span>
							</el-menu-item>
							<el-menu-item index="6">
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
			handleOpen(key, keyPath) {
				console.log(key, keyPath)
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath)
			},
			handleSelect(key, keyPath) {
				console.log(key, keyPath)
			},
			changeCollapse() {
				this.isCollapse = !this.isCollapse
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
