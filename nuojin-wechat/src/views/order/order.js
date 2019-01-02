export default {
  data() {
    return {
      numberInput: 0
    };
  },
  methods: {
    goEditAddress() {
      this.$router.push({
        path: '/deliveryDddress'
      })
    },
    numberIn() {
      this.numberInput = Math.floor(this.numberInput);
      if (this.numberInput < 0) {
        this.numberInput = 0;
      }
    },
    addNumber() {
      this.numberInput += 1;
    },
    reductionNumber() {
      this.numberInput -= 1;
      if (this.numberInput < 0) {
        this.numberInput = 0;
      }
    }
  }
};
