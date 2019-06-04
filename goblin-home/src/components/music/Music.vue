<template>
	<div>
		<div class="music-top">
			<el-carousel :interval="5000" arrow="always" height="300px">
				<el-carousel-item v-for="(url,index) in banners" :key="index">
					<img :src="url" alt="" width="100%">
				</el-carousel-item>
			</el-carousel>
		</div>
		<div class="song-list-wraper">
			<el-row>
				<div class="recomend-list">
					<h3 class="title">推荐歌单</h3>
					<ul class="rl-musiclist">
						<li v-for="(musicItem,index) in recomendList" :key="index">
							<a href="/djradio?id=792169404" class="rl-cover">
								<img :src="musicItem.cover" alt="">
							</a>
							<div class="rl-desc">
								<h3 class="rl-owner">
									<a href="/djradio?id=792169404" :title="musicItem.owner">{{musicItem.owner}}</a>
								</h3>
								<p class="rl-desc-content">{{musicItem.desc}}</p>
							</div>
						</li>
					</ul>
				</div>
			</el-row>
			<el-row>
				<div class="">
					<h3 class="title">我喜欢</h3>
					<el-table :data="musicList">
						<el-table-column type="index" width="50"></el-table-column>
						<el-table-column>
							<template slot="header" slot-scope="scope">
								<el-button :icon="scope.$index===activeIndex?pauseBtnStyle:playBtnStyle" circle @click="playAllmusic(scope)" size="mini"></el-button>
							</template>
							<template slot-scope="scope">
								<el-button :icon="scope.$index===activeIndex?pauseBtnStyle:playBtnStyle" circle @click="playmusic(scope)" size="mini"></el-button>
							</template>
						</el-table-column>
						<el-table-column prop="name" label="歌曲标题">
						</el-table-column>
						<el-table-column prop="songTime" label="时长">
						</el-table-column>
						<el-table-column prop="singer" label="歌手">
						</el-table-column>
						<el-table-column prop="album" label="专辑">
						</el-table-column>
					</el-table>
					<el-pagination class="pagination" layout="prev, pager, next" :total="total" :current-page="currentPage" :page-size="pageSize">
					</el-pagination>
				</div>
			</el-row>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'music',
		created() {
			this.queryMusicList()
		},
		data() {
			return {
				currentPage: 1,
				pageSize: 10,
				total: 0,
				activeIndex: -1,
				playBtnStyle: 'fa fa-lg fa-play-circle-o',
				pauseBtnStyle: 'fa fa-lg fa-pause-circle-o',
				playMethod: 'play',
				banners: ['../../../static/img/music/music1.jpg', '../../../static/img/music/music2.jpg',
					'../../../static/img/music/music3.jpg'
				],
				recomendList: [{
						cover: 'http://p2.music.126.net/FeeIdxtPLAKDq9lve3AHjw==/109951163997028717.jpg?param=140y140',
						desc: '如果你要离开，请不要叫醒装睡的我',
						owner: 'DJ顾念晨'
					},
					{
						cover: 'http://p2.music.126.net/FeeIdxtPLAKDq9lve3AHjw==/109951163997028717.jpg?param=140y140',
						desc: '如果你要离开，请不要叫醒装睡的我',
						owner: 'DJ顾念晨'
					},
					{
						cover: 'http://p2.music.126.net/FeeIdxtPLAKDq9lve3AHjw==/109951163997028717.jpg?param=140y140',
						desc: '如果你要离开，请不要叫醒装睡的我',
						owner: 'DJ顾念晨'
					},
					{
						cover: 'http://p2.music.126.net/FeeIdxtPLAKDq9lve3AHjw==/109951163997028717.jpg?param=140y140',
						desc: '如果你要离开，请不要叫醒装睡的我',
						owner: 'DJ顾念晨'
					},
					{
						cover: 'http://p2.music.126.net/FeeIdxtPLAKDq9lve3AHjw==/109951163997028717.jpg?param=140y140',
						desc: '如果你要离开，请不要叫醒装睡的我',
						owner: 'DJ顾念晨'
					}
				],
				musicList: []
			}
		},
		methods: {
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
			playmusic(scope) {
				var row = scope.row
				var musicList = []
				if (this.playMethod === 'play') {
					musicList.push({
						url: row.url,
						name: row.name,
						artist: row.singer,
						cover: row.albumArt,
						lrc: row.lyrics
					})
					this.showMusicPlayer = true
					this.playMethod = 'pause'
					// 控制播放按钮的样式
					this.activeIndex = scope.$index
					this.$store.commit('setPlaying', true)
					this.$store.commit('setPlayList', musicList)
				} else if (this.playMethod === 'pause') {
					this.playMethod = 'play'
					this.activeIndex = -1
					this.$store.commit('setPlaying', false)
				}

			},
			playAllmusic(scope) {
				var audioList = []
				if (this.playMethod === 'play') {
					var musicList = this.musicList
					for(var i=0; i<musicList.length; i++){
						var music = musicList[i]
						audioList.push({
							url: music.url,
							name: music.name,
							artist: music.singer,
							cover: music.albumArt,
							lrc: music.lyrics
						})
					}
					
					this.showMusicPlayer = true
					this.playMethod = 'pause'
					// 控制播放按钮的样式
					this.activeIndex = scope.$index
					this.$store.commit('setPlaying', true)
					this.$store.commit('setPlayList', audioList)
				} else if (this.playMethod === 'pause') {
					this.playMethod = 'play'
					this.activeIndex = -1
					this.$store.commit('setPlaying', false)
				}
			}
		}
	}
</script>

<style scoped="scoped">
	p {
		margin: 0;
		padding: 0;
	}

	.song-list-wraper {
		text-align: left;
		padding-left: 5px;
		background-color: #fff;
	}

	.song-list-wraper h3.title {
		font-size: 18px;
		font-weight: normal;
	}

	.song-list-wraper .title:before {
		content: '';
		border-left: 5px solid #f66;
		padding-right: 15px;
	}

	.pl-container:after {
		content: '';
		display: block;
		clear: both;
	}


	/* 推荐歌单 */
	.rl-musiclist:after {
		content: '';
		display: block;
		clear: both;
	}

	.rl-musiclist li {
		float: left;
		width: 345px;
		height: 120px;
		margin-right: 25px;
		padding: 20px 0px;
		border-bottom: 1px solid rgb(231, 231, 231);
	}

	.rl-cover {
		display: inline-block;
		float: left;
	}

	.rl-cover img {
		width: 120px;
	}

	.rl-desc {
		width: 200px;
		float: left;
		overflow: hidden;
		text-overflow: ellipsis;
		margin-left: 15px;
	}

	.rl-owner {
		color: rgb(51, 51, 51);
		margin: 16px 0px 20px;
		font-size: 16px;
	}

	.rl-desc-content {
		font-size: 13px;
		line-height: 20px;
		color: rgb(153, 153, 153);
	}

	/* 我喜欢 */
	.songlist__item {
		height: auto;
	}

	.songlist-cover {
		float: left;
		display: inline-block;
	}

	.songlist__pic {
		width: 90px;
	}

	.songlist__cont {
		float: left;
		width: 238px;
	}

	.songlist__cont h3 {
		font-size: 15px;
	}

	.pagination {
		text-align: right;
	}
</style>
