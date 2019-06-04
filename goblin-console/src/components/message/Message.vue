<template>
	<div>
		<el-row>
			<el-col :span="24">
				<div class="header">
					消息管理
				</div>
			</el-col>
		</el-row>
		<el-row class="message-body">
			<el-col :span="24">
				<el-tabs :tab-position="tabPosition" class="tab-top">
					<el-tab-pane label="全部消息">
						<MessageList type="all"></MessageList>
					</el-tab-pane>
					<el-tab-pane label="未读消息">
						<MessageList type="unread"></MessageList>
					</el-tab-pane>
					<el-tab-pane label="已读消息">
						<MessageList type="markread"></MessageList>
					</el-tab-pane>
				</el-tabs>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import MessageList from './MessageList'
	export default {
		name: 'message',
		data() {
			return {
				tabPosition: 'top'
			}
		},
		methods: {},
		components: {
			MessageList
		}
	}
</script>

<style scoped="scoped">
	p {
		margin: 0px;
	}

	.header {
		margin-bottom: 15px;
	}

	.tab-top {
		height: 100%;
		margin: 0 15px;
	}

	.message-body {
		padding-top: 15px;
		background-color: #fff;
	}
</style>
