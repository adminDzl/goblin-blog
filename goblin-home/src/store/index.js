import Vue from "vue"
import Vuex from "vuex"
Vue.use(Vuex);

const store = new Vuex.Store({
    state:{
      playing: false,
	  currentPlay: {},
	  playList: []
    },
    getters:{
        getPlaying(state){
            return state.playing
        },
		getCurrentPlay(state){
		    return state.currentPlay
		},
		getplayList(state){
			return state.playList
		}
    },
    mutations:{
        setPlaying(state, param){
            state.playing = param
        },
		setCurrentPlay(state, param){
			state.currentPlay = param
		},
		setPlayList(state, param){
			state.playList = param
		}
    },
    actions:{
        commitPlaying(ctx,param){
            ctx.commit('setPlaying',param)
        },
		commitCurrentPlay(ctx, param){
			 ctx.commit('setCurrentPlay',param)
		},
		commitPlayList(ctx, param){
			 ctx.commit('setPlayList',param)
		}
    },
    modules:{}
});

export default store