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
