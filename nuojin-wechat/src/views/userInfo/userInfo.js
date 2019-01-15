import { getCustomer } from "@/api/customerApi"

export default {

  created() {
    this.getCustomer()
  },
 
  data() {
    return {
      customerInfo:{},
      levelname:''  
    };
  },

  methods: {

    getCustomer(){
      getCustomer().then(response => {
        if (response.resultCode === '1') {
          this.customerInfo = response.resultData.info
          if(this.customerInfo.level == 2)
            this.levelname = '合伙人'
          else if(this.customerInfo.level == 1)
            this.levelname = '会员客户'
          else
            this.levelname = '普通客户'
        }
      })
    },


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
