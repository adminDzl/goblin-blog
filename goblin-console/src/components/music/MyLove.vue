<template>
	<div>
		<div>我喜欢</div>
		<div class="mylove-wrapper">
			<div>
				<el-row>
					<el-col :span="18">
						<el-button type="success" size="medium" @click="handlerSaveSong" class="add-music-btn">添加歌曲</el-button>
					</el-col>
					<el-col :span="6">
						<aplayer  :music="musicObj" v-if="showMusicPlayer" ref="musicplayer" class="music-player"></aplayer>
					</el-col>
				</el-row>
			</div>
			<el-table :data="musicList" style="width: 100%">
				<el-table-column type="index">
				</el-table-column>
				<el-table-column label="播放">
					<template slot-scope="scope">
						<el-button :icon="scope.$index===activeIndex?pauseBtnStyle:playBtnStyle" circle  @click="playmusic(scope)" size="mini"></el-button>
					</template>
				</el-table-column>
				<el-table-column label="歌曲名称" prop="name">
				</el-table-column>
				<el-table-column label="歌曲时长">
					<template slot-scope="scope">
						<span>{{ scope.row.songTime }}</span>
					</template>
				</el-table-column>
				<el-table-column label="歌手" prop="singer">
				</el-table-column>
				<el-table-column label="专辑" prop="album">
				</el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button size="mini" @click="handleEditMusic(scope.$index, scope.row)">编辑</el-button>
						<el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination background class="pagination" layout="prev, pager, next" :total="total" :current-page="currentPage"
			 :page-size="pageSize">
			</el-pagination>
		</div>
	</div>
</template>

<script>
	import VueAplayer from 'vue-aplayer'
	export default {
		name: 'mylove',
		components: {
			'aplayer': VueAplayer
		},
		created() {
			this.queryMusicList()
		},
		data() {
			return {
				activeIndex: -1,
				playBtnStyle: 'fa fa-lg fa-play-circle-o',
				pauseBtnStyle: 'fa fa-lg fa-pause-circle-o',
				playMethod: 'play',
				showMusicPlayer: false,
				musicObj: {},
				currentPage: 1,
				pageSize: 10,
				total: 0,
				musicList: []
			}
		},
		methods: {
			handleEditMusic(index, row) {
				var id = row.id
				this.$router.push({
					path: '/music/save',
					query: {
						id: id,
						method: 'edit'
					}
				})
			},
			handleDelete(index, row) {
				var id = row.id
				this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$ajax.get(this.base_url + '/music/delete/' + id).then((response) => {
						if (response.data.code === 20000) {
							this.$message({
								message: '删除成功',
								type: 'success'
							})
							// 更新数据表格
							this.$router.push('/refresh')
						} else {
							this.$message({
								message: '抱歉服务器故障，删除失败了，请稍后重试',
								type: 'error'
							})
						}
					})
				}).catch(() => {})
			},
			handlerSaveSong() {
				this.$router.push({path:'/music/save',query:{method:'add'}})
			},
			queryMusicList() {
				var url = this.base_url + '/music/list?currentPage=' + this.currentPage + '&pageSize=' + this.pageSize
				this.$ajax.get(url).then((response) => {
					if (response.status === 200) {
						this.musicList = response.data.pageResult
						this.total = response.data.total
					} else {
						this.$message({
							message: '数据加载失败，请稍后重试',
							type: 'error'
						})
					}
				})
			},
			handlerMusicPlay(row) {
				var music = {
					src: row.url,
					title: row.name,
					artist: row.singer,
					pic: row.albumArt,
					lrc: row.lyrics
				}
				return music
			},
			playmusic(scope) {
				var row = scope.row
				if(this.playMethod === 'play'){
					var music = {
						src: row.url,
						title: row.name,
						artist: row.singer,
						pic: row.albumArt,
						lrc: row.lyrics
					}
					this.showMusicPlayer = true
					this.musicObj = music
					this.playMethod = 'pause'
					// 控制播放按钮的样式
					this.activeIndex = scope.$index
					this.$nextTick(function() {
						// 一定要使用$nextTick否则还没渲染完成就会播放到值报错没有可播放的资源
						this.$refs.musicplayer.play()
					})
				}else if(this.playMethod === 'pause'){
					this.playMethod = 'play'
					this.activeIndex = -1
					this.$refs.musicplayer.pause()
				}
				
			}
		}
	}
</script>

<style scoped="scoped">
	.mylove-wrapper {
		margin-top: 15px;
		min-height: 560px;
		padding: 5px 15px 15px 15px;
		background-color: #fff;
	}

	.pagination {
		margin-top: 15px;
		text-align: right;
	}
	.add-music-btn{
		margin-top: 15px;
	}
	.play-music-btn{
		height: 100%;
		display: block;
		color: #909399;
	}
</style>
