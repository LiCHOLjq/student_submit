<template>
  <div id="user">
    <!-- <mt-field label="用户ID" readonly="true" v-model="form.userId"></mt-field> -->
    <h1 v-if="student.studentState == 1" class="text">考生信息绑定</h1>
    <h1 v-if="student.studentState == 2" class="text">报考指南申领</h1>
    <h1 v-if="student.studentState == 3" class="text" style="padding-bottom:5%">报考指南申领</h1>

    <div v-if="student.studentState == 1">
      <mt-field label="考生姓名：" placeholder="请输入姓名" v-model="student.studentName" :state="student.studentName == '' || student.studentName == null ? 'error' : 'success'"></mt-field>
      <mt-field label="所在省份：" placeholder="请输入省份" v-model="student.studentProvince" :state="student.studentProvince == '' || student.studentProvince == null ? 'error' : 'success'"></mt-field>
      <mt-field label="中学名称：" placeholder="请输入中学名称" v-model="student.studentSchool" :state="student.studentSchool == '' || student.studentSchool == null ? 'error' : 'success'"></mt-field>
      <mt-field label="高考分数：" placeholder="请输入高考分数" v-model="student.studentMark" :state="student.studentMark == '' || student.studentMark == null ? 'error' : 'success'"></mt-field>
      <!-- <mt-field :class="'label_'+msg.msgTypeList[1]+' text_'+msg.msgTypeList[3]" v-if="msg.msgTypeList[0] == 'label' && msg.msgTypeList[2] != 'number'"
      :label="msg.msgTypeList[1] == 'none' ? '' : msg.msgTitle+':'"
      :type="msg.msgTypeList[2] == 'area' ? 'textarea' : 'text'"
      :rows=" msg.msgTypeList[2] == 'area' ? msg.msgRow :''"
      readonly="true"
      style="word-break:break-all"
      v-model="msg.msgValue"
    ></mt-field> -->

    </div>
    <div v-if="student.studentState == 2">
      <mt-field label="收件人姓名：" placeholder="请输入姓名" v-model="student.emsName" :state="student.emsName == '' || student.emsName == null ? 'error' : 'success'"></mt-field>
      <mt-field label="所在省份：" placeholder="请输入省份" v-model="student.emsProvince" :state="student.emsProvince == '' || student.emsProvince == null ? 'error' : 'success'"></mt-field>
      <mt-field label="手机号码：" placeholder="请输入手机号码" v-model="student.emsPhone" :state="isPoneAvailable(student.emsPhone) ? 'success' : 'error'"></mt-field>
      <mt-field label="电子邮箱：" placeholder="请输入电子邮箱" v-model="student.emsEmail" :state="isEmailAvailable(student.emsEmail) ? 'success' : 'error'"></mt-field>
      <mt-field label="收件地址：" type="textarea" rows="4" placeholder="请输入完整的收件地址" v-model="student.emsAddress" :state="student.emsAddress == '' || student.emsAddress == null ? 'error' : 'success'"></mt-field>
      <!-- <mt-field :class="'label_'+msg.msgTypeList[1]+' text_'+msg.msgTypeList[3]" v-if="msg.msgTypeList[0] == 'label' && msg.msgTypeList[2] != 'number'"
      :label="msg.msgTypeList[1] == 'none' ? '' : msg.msgTitle+':'"
      :type="msg.msgTypeList[2] == 'area' ? 'textarea' : 'text'"
      :rows=" msg.msgTypeList[2] == 'area' ? msg.msgRow :''"
      readonly="true"
      style="word-break:break-all"
      v-model="msg.msgValue"
    ></mt-field> -->

    </div>

    <div v-if="student.studentState == 3">
      <div style="text-align:center">
        <svg t="1623157009083" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="7086" width="150" height="150">
          <path d="M512 512m-448 0a448 448 0 1 0 896 0 448 448 0 1 0-896 0Z" fill="#4caf50" p-id="7087" data-spm-anchor-id="a313x.7781069.0.i1" class="selected"></path>
          <path d="M738.133333 311.466667L448 601.6l-119.466667-119.466667-59.733333 59.733334 179.2 179.2 349.866667-349.866667z" fill="#ffffff" p-id="7088" data-spm-anchor-id="a313x.7781069.0.i0" class=""></path>
        </svg>
        <p>您已成功申领报考指南</p>
        <p>以下是您的快递信息</p>
      </div>

      <mt-field label="快递单号：" readonly="true" v-model="student.emsNumber"></mt-field>
      <mt-field label="收件人姓名：" readonly="true" v-model="student.emsName"></mt-field>
      <mt-field label="手机号码：" readonly="true" v-model="student.emsPhone"></mt-field>
      <mt-field label="收件地址：" readonly="true" type="textarea" rows="4" v-model="student.emsAddress"></mt-field>
    </div>
    <div class="btn1">
      <!-- handleStudent(1);questionSubmit();nextStep2();   -->
      <mt-button v-if="student.studentState == 1" type="primary" size="large" @click="bind();">绑定</mt-button>
      <mt-button v-if="student.studentState == 2" type="primary" size="large" @click="submit();">申领</mt-button>
      <!-- handleStudent(questionState+2);questionSubmit(); -->
    </div>
  </div>
</template>

<script>
import { Indicator } from 'mint-ui';
import { MessageBox } from 'mint-ui';
export default {
  name: 'page-tabbar',
  data() {
    return {
      token: "",
      baseURL: "",
      student: {
        studentId: "",
        openid: "",
        nickname: "",
        sex: "",
        province: "",
        city: "",
        country: "",
        headimgurl: "",
        studentName: "",
        studentProvince: "",
        studentSchool: "",
        studentMark: "",
        emsName: "",
        emsProvince: "",
        emsAddress: "",
        emsPhone: "",
        emsEmail: "",
        emsNumber: "",
        studentState: 0,
        studentFirstDate: "",
        studentLastDate: "",
        studentLoginTime: "",
      }

    };
  },
  components: {


  },
  methods: {
    //     handleStudent(state){
    //       this.questionStudent.studentQuestionMsg = this.questionSubmitForm.question;
    //       this.questionStudent.studentQuestionState = state;
    //     },
    init() {
      // alert(this.baseURL);
      // MessageBox.alert("登陆跳转").then(action => {
      //   window.location.href = this.baseURL + "/submit/wxAuth/login"
      // });
      Indicator.open({
        text: '数据加载中...',
        spinnerType: 'fading-circle'
      });
      this.axios.post('/student/getStudent', {
        params: {}
      }, { headers: { token: this.token } }).then((res) => {
        if (res.data.code === 200) {
          this.student = res.data.data;
        } else if (res.data.code == 401) {
          window.location.href = this.baseURL + "/submit/wxAuth/login"
        } else {
          MessageBox.alert(res.data.data).then(action => {
            window.location.href = this.baseURL + "/submit/wxAuth/login"
          });
        }
        Indicator.close();
      });
    },
    submit() {



      if (this.student.emsName == '' || this.student.emsName == null) {
        MessageBox.alert("请输入姓名").then(action => { });
        return;
      }
      if (this.student.emsProvince == '' || this.student.emsProvince == null) {
        MessageBox.alert("请输入省份").then(action => { });
        return;

      }
      if (!this.isPoneAvailable(this.student.emsPhone)) {
        MessageBox.alert("请输入正确的手机号码").then(action => { });
        return;
      }
      if (!this.isEmailAvailable(this.student.emsEmail)) {
        MessageBox.alert("请输入正确的电子邮箱").then(action => { });
        return;
      }
      if (this.student.emsAddress == '' || this.student.emsAddress == null) {

        MessageBox.alert("请输入收件地址").then(action => { });
        return;
      }


      Indicator.open({
        text: '数据提交中...',
        spinnerType: 'fading-circle'
      });
      this.axios.post('/student/submitApply', {
        params: {
          student: this.student
        }
      }, { headers: { token: this.token } }).then((res) => {
        if (res.data.code === 200) {
          MessageBox.alert(res.data.data).then(action => {
            this.init();
          });

        } else if (res.data.code == 401) {
          window.location.href = this.baseURL + "/submit/wxAuth/login"
        } else {
          MessageBox.alert(res.data.data).then(action => {
            window.location.href = this.baseURL + "/submit/wxAuth/login"
          });
        }
        Indicator.close();
      });
    },
    isPoneAvailable(pone) {
      var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
      if (!myreg.test(pone)) {
        return false;
      } else {
        return true;
      }
    },
    isEmailAvailable(emailInput) {
      var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if (!myreg.test(emailInput)) {
        return false;
      }
      else {
        return true;
      }
    },

    bind() {
      if (this.student.studentName == '' || this.student.studentName == null) {
        MessageBox.alert("请输入姓名").then(action => { });
        return;
      }
      if (this.student.studentProvince == '' || this.student.studentProvince == null) {

        MessageBox.alert("请输入省份").then(action => { });
        return;

      }
      if (this.student.studentSchool == '' || this.student.studentSchool == null) {

        MessageBox.alert("请输入中学名称").then(action => { });
        return;

      }
      if (this.student.studentMark == '' || this.student.studentMark == null) {

        MessageBox.alert("请输入高考分数").then(action => { });
        return;
      }


      Indicator.open({
        text: '数据提交中...',
        spinnerType: 'fading-circle'
      });
      this.axios.post('/student/submitBind', {
        params: {
          student: this.student
        }
      }, { headers: { token: this.token } }).then((res) => {
        if (res.data.code === 200) {
          MessageBox.alert(res.data.data).then(action => {
            this.init();
          });

        } else if (res.data.code == 401) {
          window.location.href = this.baseURL + "/submit/wxAuth/login"
        } else {
          MessageBox.alert(res.data.data).then(action => {
            window.location.href = this.baseURL + "/submit/wxAuth/login"
          });
        }
        Indicator.close();
      });
    }


    //     questionSubmit(){
    //       //this.handleStudent();
    //       Indicator.open({
    //         text: '数据提交中...',
    //         spinnerType: 'fading-circle'
    //       });
    //       this.axios.post('/student/submitQuestion', {
    //         params: {
    //           student:this.questionStudent
    //         }
    //         },{headers:{token:this.token}}).then((res) => {
    //         if (res.data.code === 200) {
    //           MessageBox.alert(res.data.data);
    //           this.questionSubmitForm.visible = false;
    //           this.init();
    //       }else if (res.data.code == 401) {
    //             this.$router.push({ path: "/login" });
    //           } else {
    //             MessageBox.alert(res.data.data).then(action=>{
    //                this.init();
    //             });
    //           }
    //         Indicator.close();
    //       });
    //     },
    //     nextStep1(){
    //       Indicator.open({
    //         text: '数据提交中...',
    //         spinnerType: 'fading-circle'
    //       });
    //       this.axios.post('/student/nextStep1', {
    //         params: {}
    //         },{headers:{token:this.token}}).then((res) => {
    //         if (res.data.code === 200) {
    //           //MessageBox.alert(res.data.data);
    //            this.init();
    //         }else if (res.data.code == 401) {
    //             this.$router.push({ path: "/login" });
    //           } else {
    //            MessageBox.alert(res.data.data).then(action=>{
    //               this.init();
    //             });
    //           }
    //         Indicator.close();
    //       });
    //     },
    //     nextStep2(){
    //       Indicator.open({
    //         text: '数据提交中...',
    //         spinnerType: 'fading-circle'
    //       });
    //       this.axios.post('/student/nextStep2', {
    //         params: {}
    //         },{headers:{token:this.token}}).then((res) => {
    //         if (res.data.code === 200) {
    //           //MessageBox.alert(res.data.data);
    //            this.init();
    //         }else if (res.data.code == 401) {
    //             this.$router.push({ path: "/login" });
    //           } else {
    //            MessageBox.alert(res.data.data).then(action=>{
    //               this.init();
    //             });
    //           }
    //         Indicator.close();
    //       });
    //     },
    //     verification(){

    //       this.axios
    //         .post("/verification", {
    //           params: {
    //           }
    //         },{ headers: { token: this.token } })
    //         .then(res => {
    //           if (res.data.code === 200) {
    //             this.init();
    //           } else if (res.data.code == 401) {
    //         this.$router.push({ path: "/login" });
    //       } else {
    //             MessageBox('提示', res.data.data);
    //             //this.state = false;
    //             this.$router.push({ path: "/login" });
    //           }

    //         });
    // },
    // qrww(){
    //       Indicator.open({
    //         text: '数据提交中...',
    //         spinnerType: 'fading-circle'
    //       });
    //       this.axios.post('/student/qrww', {
    //         params: {
    //           //student:this.questionStudent
    //         }
    //         },{headers:{token:this.token}}).then((res) => {
    //         if (res.data.code === 200) {
    //           MessageBox.alert(res.data.data);
    //           //this.questionSubmitForm.visible = false;
    //           this.init();
    //       }else if (res.data.code == 401) {
    //             this.$router.push({ path: "/login" });
    //           } else {
    //             MessageBox.alert(res.data.data).then(action=>{
    //                this.init();
    //             });
    //           }
    //         Indicator.close();
    //       });
    // },
    // yzxjrqrxxjm(){
    //       Indicator.open({
    //         text: '数据提交中...',
    //         spinnerType: 'fading-circle'
    //       });
    //       this.axios.post('/student/yzxjrqrxxjm', {
    //         params: {
    //           //student:this.questionStudent
    //         }
    //         },{headers:{token:this.token}}).then((res) => {
    //         if (res.data.code === 200) {
    //           //MessageBox.alert(res.data.data);
    //           //this.questionSubmitForm.visible = false;
    //           this.init();
    //       }else if (res.data.code == 401) {
    //             this.$router.push({ path: "/login" });
    //           } else {
    //             MessageBox.alert(res.data.data).then(action=>{
    //                this.init();
    //             });
    //           }
    //         Indicator.close();
    //       });
    // },
    // rkbqrks(){
    //       Indicator.open({
    //         text: '数据提交中...',
    //         spinnerType: 'fading-circle'
    //       });
    //       this.axios.post('/student/rkbqrks', {
    //         params: {
    //           //student:this.questionStudent
    //         }
    //         },{headers:{token:this.token}}).then((res) => {
    //         if (res.data.code === 200) {
    //           MessageBox.alert(res.data.data);
    //           //this.questionSubmitForm.visible = false;
    //           this.init();
    //       }else if (res.data.code == 401) {
    //             this.$router.push({ path: "/login" });
    //           } else {
    //             MessageBox.alert(res.data.data).then(action=>{
    //                this.init();
    //             });
    //           }
    //         Indicator.close();
    //       });
    // },
    // wtsb(){
    //   this.questionStudent.studentQuestionMsg = this.questionSubmitForm.question;
    //       Indicator.open({
    //         text: '数据提交中...',
    //         spinnerType: 'fading-circle'
    //       });
    //       this.axios.post('/student/wtsb', {
    //         params: {
    //           student:this.questionStudent
    //         }
    //         },{headers:{token:this.token}}).then((res) => {
    //         if (res.data.code === 200) {
    //           MessageBox.alert(res.data.data);
    //           this.questionSubmitForm.visible = false;
    //           this.init();
    //       }else if (res.data.code == 401) {
    //             this.$router.push({ path: "/login" });
    //           } else {
    //             MessageBox.alert(res.data.data).then(action=>{
    //                this.init();
    //             });
    //           }
    //         Indicator.close();
    //       });
    // }
  },
  created: function () {
    //登陆验证代码
    //权限验证代码
    this.token = localStorage.getItem("token");
    this.baseURL = window.location.protocol + "//" + window.location.hostname;
    if (window.location.port != "") {
      this.baseURL = this.baseURL + ":" + window.location.port;
    }
    //this.token = "w/UI0+DM8BbIqvnS6+QqP6yw+DkLNcb2KNAVT5fzVwFumdIcozoW0IMHIQfXg5Y6o8etfjP/WiJ3VgTWa+WD/fXHds79SEA3c29jM+mtPPvZEw9jfRpzpmhGaGZGw9nlY3CaShrdkB6iWIABRypYbvuS5kqW5K3WhAxSIi3/DlhZt6+DlcEUCEbaiOzo2cU3Vu3kuSvxPz6mo+mTxmZzTRk39oGPzkRnTfOASk5FxjXE9YkLFb4iA/TqPotKKrZFRGKILZCVX1SG6Y8Rk4mo5w=="
    if (this.token == "" || this.token == null) {
      window.location.href = this.baseURL + "/submit/wxAuth/login";
      return;
    }
    this.init();

  },
};
</script>

<style>
.btn1 {
  padding-top: 20px;
}
.btn2 {
  padding-top: 20px;
  padding-bottom: 40px;
}
.text {
  top: 0px;

  font-size: x-large;
  font-weight: 500;
  text-align: center;
}
.mint-field-clear {
  display: none;
}
.mint-cell-text {
  font-weight: 600;
}
textarea {
  font: 400 13.3333px Arial;
}
.mint-cell-wrapper {
  border-bottom: 1px solid #dedede;
}
.title {
  text-align: center;
}
.title.info {
  color: #303133;
}
.title.success {
  color: #67c23a;
}
.title.warning {
  color: #e6a23c;
}
.title.danger {
  color: #f56c6c;
}
.title.primary {
  color: #043e6b;
}
.number input {
  display: none;
}
.mint-field-core {
  line-height: 1.2;
}
.label_info .mint-cell-title {
  color: #303133;
}
.label_success .mint-cell-title {
  color: #67c23a;
}
.label_warning .mint-cell-title {
  color: #e6a23c;
}
.label_danger .mint-cell-title {
  color: #ef4f4f;
}
.label_primary .mint-cell-title {
  color: #043e6b;
}

.text_info .mint-cell-value input {
  color: #303133;
}
.text_success .mint-cell-value input {
  color: #67c23a;
}
.text_warning .mint-cell-value input {
  color: #e6a23c;
}
.text_danger .mint-cell-value input {
  color: #ef4f4f;
}
.text_primary .mint-cell-value input {
  color: #043e6b;
}

.text_info .mint-cell-value textarea {
  color: #303133;
}
.text_success .mint-cell-value textarea {
  color: #67c23a;
}
.text_warning .mint-cell-value textarea {
  color: #e6a23c;
}
.text_danger .mint-cell-value textarea {
  color: #ef4f4f;
}
.text_primary .mint-cell-value textarea {
  color: #043e6b;
}

.mint-badge.is-primary {
  background-color: #043e6b;
}
</style>
