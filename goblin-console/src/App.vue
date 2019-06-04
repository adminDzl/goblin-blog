<template>
	<div id="app">
		<!-- 导航条 -->
		<navmenu></navmenu>
	</div>
</template>

<script>
	import NavMenu from './components/common/navmenu/NavMenu.vue'
	export default {
		name: 'App',
		data() {
			return {}
		},
		methods: {},
		created() {
			//在页面加载时读取sessionStorage里的状态信息
			if (sessionStorage.getItem("store")) {
				this.$store.replaceState(Object.assign({}, this.$store.state, JSON.parse(sessionStorage.getItem("store"))))
			}

			//在页面刷新时将vuex里的信息保存到sessionStorage里
			window.addEventListener("beforeunload", () => {
				sessionStorage.setItem("store", JSON.stringify(this.$store.state))
			})
		},
		components: {
			navmenu: NavMenu
		}
	}
</script>

<style scoped="scoped">
	.fade-enter-active,
	.fade-leave-active {
		transition: all 0.2s ease;
	}

	.fade-enter,
	.fade-leave-active {
		opacity: 0;
	}
</style>
