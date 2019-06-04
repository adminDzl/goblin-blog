<template>
	<div id="app">
		<Home></Home>
		<!-- <router-view /> -->
	</div>
</template>

<script>
	import Home from './components/Home'
	export default {
		name: 'App',
		components: {
			Home
		}
	}
</script>

<style>
	#app {
		font-family: 'Avenir', Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		text-align: center;
		color: #2c3e50;
	}

	body {
		font-family: 'Monda', "PingFang SC", "Microsoft YaHei", sans-serif;
		background: #f5f7f9;
	}
	a{
		text-decoration: none;
		color: #3f3f3f;
	}
	ul{
		margin: 0px;
		padding: 0px;
	}
	ul li{
		list-style: none;
	}
	h2{
		margin: 0;
	}
	p{
		margin: 0;
		padding: 0;
	}
</style>
