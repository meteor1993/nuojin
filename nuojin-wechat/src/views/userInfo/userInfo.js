export default {
  methods: {
    goShoppingMall() {
      this.$router.push({
        path: '/shoppingMall'
      })
    },
    goMyOrder() {
      this.$router.push({
        path: '/myOrder'
      })
    },
    goPipelineRecord() {
      this.$router.push({
        path: '/pipelineRecord'
      })
    },
    goDeliveryDddress() {
      this.$router.push({
        path: '/deliveryDddress'
      })
    }
  }
};
