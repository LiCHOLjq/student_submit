import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

var state = {
  admin: null,

  fitting_list_formselect: {
    fittingId: '',
    fittingForm: '',
    fittingOriginComputer: '',
    fittingCurrentComputer: '',
    fittingState: '',

  },
  compuer_list_formselect: {
    computerId: '',
    computerType: '',
    factory: '',
    computerModel: '',
    computerName: '',
    computerText: '',
    computerState: '',
    openRow: '',
    page: 1
  },

}
var mutations = {

  setfitting_list_formselect (state, data) {
    state.fitting_list_formselect = data;
  },
  setcomputer_list_formselect (state, data) {
    state.compuer_list_formselect = data;
  },
  setadmin (state, data) {
    state.admin = data;
  }

}

const store = new Vuex.Store({
  state,
  mutations
})

export default store;