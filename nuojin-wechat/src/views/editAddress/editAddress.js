export default {
  data() {
    return {
      btnShowFlag: false
    }
  },
  created() {
    if (this.$route.query.id) {
      this.btnShowFlag = true
    }
  }
};
