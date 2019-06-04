<template>
	<div>
		用户列表
		<div class="userlist-body">
			<el-row>
				<el-col :span="24">
					<el-table :data="userList" style="width: 100%">
						<el-table-column label="用户名">
							<template slot-scope="scope">
								<div class="userico-name">
									<img :src="scope.row.userico" alt="" width="50px">
									<p>{{scope.row.username}}</p>
								</div>
							</template>
						</el-table-column>
						<el-table-column prop="group" label="分组" width="180">
						</el-table-column>
						<el-table-column prop="createdate" label="注册时间">
						</el-table-column>
						<el-table-column label="操作">
							<template slot-scope="scope">
								<el-button>修改备注</el-button>
							</template>
						</el-table-column>
					</el-table>
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'userlist',
		data() {
			return {
				userList: [{
					userico: 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg',
					username: '张三',
					group: '默认分组',
					createdate: '2019-4-20 15:31'
				},
				{
					userico: 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg',
					username: '张三',
					group: '默认分组',
					createdate: '2019-4-20 15:31'
				},
				{
					userico: 'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg',
					username: '张三',
					group: '默认分组',
					createdate: '2019-4-20 15:31'
				}]
			}
		}
	}
</script>

<style scoped="scoped">
	.userlist-body{
		margin-top: 15px;
		padding-left: 15px;
		min-height: 580px;
		background-color: #fff;
	}
	.userico-name:after{
		content:'';
		display: block;
		clear: both;
	}
	.userico-name img{
		display: block;
		float: left;
	}
	.userico-name p{
		margin-left: 15px;
		float: left;
	}
</style>
