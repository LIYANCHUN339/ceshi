

const state={
  count:0
}


// 更改 Vuex 的 store 中的状态的唯一方法
const mutations={
  increment(state){
    state.count++;
  }
}


// Action 提交的是 mutation，而不是直接变更状态
const actions={
  increment:({commit})=>commit('increment')
}

const getters = {
  count:state =>state.count,
  evenOrOdd: state => state.count % 2 === 0 ? 'even' : 'odd'
}


export default {
  state,
  actions,
  mutations,
  getters
}
