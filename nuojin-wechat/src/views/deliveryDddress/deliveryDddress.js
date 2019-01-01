export default {
  data() {
    return {
      selection: false
    };
  },
  methods: {
    selectionBtn() {
      this.selection = !this.selection;
    },
    goEditAddress(editAddressId) {
      this.$router.push({
        path: '/editAddress',
        query: { id: editAddressId }
      })
    }
  }
};
