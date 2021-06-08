import Vue from 'vue'
import App from './App.vue'
import Mint from 'mint-ui';
import 'mint-ui/lib/style.css'

import router from './router/router.js';
import axios from 'axios';
import Vueaxios from 'vue-axios';

Vue.use(Mint);
Vue.prototype.axios = axios
Vue.use(Vueaxios, axios);

var baseURLStr = window.location.protocol + "//" + window.location.hostname;
if (window.location.port != "") {
  baseURLStr = baseURLStr + ":" + window.location.port;
}
baseURLStr = baseURLStr + "/submit/";
//baseURLStr = "http://localhost/submit/"
axios.defaults.baseURL = process.env.NODE_ENV === 'development' ? baseURLStr : baseURLStr

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
