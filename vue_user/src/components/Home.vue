<template>
  <div class="page-tabbar">
    <div class="page-wrap">
      <div class="head"><img class="head-img" height="60" width="172" src="../assets/logo-ow-bg.png" /></div>
      <div class="center">
        <router-view />
      </div>
    </div>

    <div class="footer" style="color:#FFFFFF;text-align:center">Copyright © 2020 东北大学 学生处</div>
  </div>
</template>

<script>

export default {
  name: 'page-tabbar',
  data() {
    return {
      selected: '个人信息'
    };
  },
  components: {


  },
  mounted() {
    this.$router.push({ path: '/home/user' });
    // var user = localStorage.getItem("userid");
    // if (user == null) {
    //   this.$router.push({ path: '/login' });
    //   return;
    // }

  },
  methods: {

  },
  watch: {

    // 'selected': function (val) {
    //   if (this.selected == "样机查询") { this.$router.push({ path: '/home/computer' }); }
    //   if (this.selected == "配件查询") { this.$router.push({ path: '/home/fitting' }); }
    //   if (this.selected == "借用信息") { this.$router.push({ path: '/home/borrow' }); }
    //   if (this.selected == "个人信息") { this.$router.push({ path: '/home/user' }); }
    // }

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

.mint-header-title {
  font-size: large;
  font-weight: 400;
}
.mint-header {
  height: 50px;
  background-color: rgb(84, 92, 100);
}
.head img {
  padding: 11px;
}
.head {
  height: 80px;
  text-align: center;
  background-color: rgb(4, 62, 107);
}
.center {
  margin-top: 10px;
}
.mint-button--primary {
  width: 80%;
  margin: auto;
  background-color: rgb(4, 62, 107);
}
.mint-button--danger {
  width: 80%;
  margin: auto;
  background-color: #ef4f4f;
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
