/* eslint-disable no-param-reassign */

import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {
      authToken: '',
      username: '',
      role: '',
    },
    blogPosts: [],
  },
  getters: {
    isAdmin(state) {
      return state.user.role === 'ADMIN';
    },
    isLoggedIn(state) {
      return state.user.authToken !== '';
    },
  },
  mutations: {
    setBlogPosts(state, blogPosts) {
      state.blogPosts = blogPosts;
      state.blogPosts.sort((blog1, blog2) => blog2.createdDate - blog1.createdDate);
    },
    setUser(state, user) {
      state.user.authToken = user.token;
      state.user.username = user.username;
      state.user.role = user.role;
    },
    signOut(state) {
      state.user = {
        authToken: '',
        username: '',
        role: '',
      };
    },
  },
  actions: {
    loadBlogPosts({ commit }) {
      this.$axios.get('/blogposts')
        .then(response => commit('setBlogPosts', response.data))
        .catch(() => this.$notification.open({
          type: 'is-danger',
          message: 'A problem occurred while getting the blog posts',
        }));
    },
  },
});
