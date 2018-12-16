import Vue from 'vue';
import Vuex from 'vuex';
import * as actions from './actions';
import tab from './modules/tab';

Vue.use(Vuex);

export default new Vuex.Store({
  actions,
  modules: {
    tab
  }
})
