import Vue from 'vue';
import Vuex from 'vuex';
import Buefy from 'buefy';
import VeeValidate from 'vee-validate';
import axios from 'axios';
import App from './App.vue';
import router from './router';
import store from './store';
import './assets/scss/app.scss';

Vue.use(Buefy);
Vue.use(VeeValidate);

const axiosApi = axios.create({
  baseURL: process.env.VUE_APP_BASE_API_URL,
});

Vue.prototype.$axios = axiosApi;
Vuex.Store.prototype.$axios = axiosApi;

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
