import Vue from "vue";
import Vuex from "vuex";
Vue.use(Vuex);

const store = new Vuex.Store({
    state:{
			// 控制导航条是否显示
      showNavbars: true,
	  // 存储token
	  authorization: ''
    },
    getters:{
        getShowNavbars(state){
			var data = state.showNavbars
			if(data === null || data === ''){
				data = localStorage.getItem('showNavbars')
				return data
			}
            return data
        },
		getAuthToken(state){
			var data = state.authorization
			if(data === null || data === ''){
				data = localStorage.getItem('authorization')
				return data
			}
			return data
		}
    },
    mutations:{
        //改变是否显示导航条
        changeShowNavbars(state, param){
			// 同步保存数据到localStorage
			localStorage.setItem("showNavbars", param)
            state.showNavbars = param
        },
		changeAuthToken(state, param){
			// 同步保存数据到localStorage
			localStorage.setItem("authorization", param)
			state.authorization = param
		}
    },
    actions:{
        //提交是否显示导航条的改变
        changeShowNavbars(ctx,param){
            ctx.commit('changeShowNavbars',param)
        },
		// 提交登陆口令的改变
		changeAuthToken(ctx, param){
			 ctx.commit('changeAuthToken',param)
		}
    },
    modules:{}
});

export default store