<template>
  <div class="page-tabbar">
    <div class="page-wrap">
      <div class="head"><img class="head-img" height="60" width="172" src="../assets/logo-ow-bg.png" /></div>
      <div class="center">
        <h1 class="text">报考指南申领</h1>
      </div>
    </div>
    <div class="footer" style="color:#FFFFFF;text-align:center">Copyright © 2021 东北大学 学生工作处</div>

  </div>
</template>

<script>
import { Indicator } from 'mint-ui';
import { MessageBox } from 'mint-ui';
export default {
  name: 'page-tabbar',
  data() {
    return {
      state: false,
      code: "",

      imgurl: "",
      needPhone: false
    };
  },
  components: {


  },
  methods: {
    submitForm() {

      Indicator.open({
        text: '跳转中...',
        spinnerType: 'fading-circle'
      });
      this.axios.post('/student/login', {
        params: {
          code: this.code
        }
      }).then((res) => {
        if (res.data.code === 200) {
          localStorage.setItem("token", res.data.token);
          this.$router.push({ path: '/home/user' });
        }
        if (res.data.code === 401) {

          window.location.href = this.baseURL + "/submit/wxAuth/login"
        }
        if (res.data.code === 500) {
          MessageBox('提示', res.data.data);

        }
        Indicator.close();
      })
      this.dialogFormVisible = false;
    },
    // timestamp(url) {
    //   var getTimestamp = new Date().getTime();
    //   if (url.indexOf("?") > -1) {
    //     url = url + "&timestamp=" + getTimestamp
    //   } else {
    //     url = url + "?timestamp=" + getTimestamp
    //   }
    //   return url;
    // },
    // getCode() {
    //   var that = this;
    //   this.axios.get('student/verifyCode', {
    //     headers: {
    //       'Content-Type': 'application/x-www-form-urlencoded',
    //     },
    //     responseType: "arraybuffer", // 关键 设置 响应类型为二进制流
    //   }).then(function (response) {  // 将后台的图片二进制流传华为base64
    //     that.form.ASECode = response.headers.verifycode;
    //     return 'data:image/png;base64,' + btoa(
    //       new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), '')
    //     );
    //   }).then(data => {
    //     that.imgurl = data; // data即为图片地址
    //   });
    // },
    // verification() {

    //   this.axios
    //     .post("/verification", {
    //       params: {
    //       }
    //     }, { headers: { token: this.token } })
    //     .then(res => {
    //       if (res.data.code === 200) {
    //         this.state = true;
    //         this.getCode();
    //       } else if (res.data.code == 401) {
    //         this.$router.push({ path: "/login" });
    //       } else {
    //         MessageBox('提示', res.data.data);
    //         this.state = false;
    //       }

    //     });
    // }

  },
  created: function () {
    //调用获取凭证方法
    //this.getTicket();

    localStorage.setItem("token", null);
    this.baseURL = window.location.protocol + "//" + window.location.hostname;
    if (window.location.port != "") {
      this.baseURL = this.baseURL + ":" + window.location.port;
    }
    //console.log(this.$route.params.code)
    if (this.$route.params.code == null) {
      MessageBox('提示', "登陆失败，请重新进入");
    } else {
      this.code = this.$route.params.code
      this.submitForm()
    }

  },
};
</script>

<style>
.page-tabbar {
  overflow: hidden;
  height: 100vh;
}

.page-wrap {
  overflow: auto;
  height: 100%;
  padding-bottom: 100px;
}
.head {
  height: 80px;
  text-align: center;
  background-color: rgb(4, 62, 107);
}
.head img {
  padding: 11px;
}
.center2 {
  margin-left: 20px;
  margin-right: 20px;
  margin-bottom: 50px;
  border: 1px solid #dedede;
}
.center {
  margin-top: 50px;
}
.text {
  top: 0px;

  padding-bottom: 10%;
  font-size: x-large;
  font-weight: 500;
  text-align: center;
}
.mint-button--primary {
  width: 80%;
  margin: auto;
  background-color: rgb(4, 62, 107);
}
.footer {
  margin-top: -125px;
  width: 100%;
  height: 25px;
  position: fixed;
  z-index: 1;
  background-color: rgb(4, 62, 107);
}
</style>
