import { createStore } from 'vuex'
import user from '@/store/user'
import admin from '@/store/admin'
import createPersistedstate from 'vuex-persistedstate'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {

  },
  actions: {
  },
  modules: {
    user,admin
  }
})
