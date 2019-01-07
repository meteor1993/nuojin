

import { getByProductId, getPartnerPrice} from "@/api/productApi"


export default {
  components:{
    getByProductId,
    getPartnerPrice,
    
  },
  data() {
    return {
      productmodel: {},
      numberInput: 1,
      partnerlist:[],
      totalmoney:0,
     

    };
  },
  created() {
    this.getFetchData()
    this.getPartnerPriceList()
    
  },
  methods: {

    getFetchData() {
      getByProductId(this.$route.query.productId).then(response => {
        if (response.resultCode === '1') {
          this.productmodel = response.resultData.info
          this.totalmoney = this.numberInput*(this.productmodel.productNowPrice/100)
        }
        console.log(response)
      })
    },

    getPartnerPriceList(){
      getPartnerPrice(this.$route.query.productId).then(response=>{
        if(response.resultCode == '1')
         {
           
            this.partnerlist = response.resultData.info
         }
         
      })
    },

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
      this.checkMoney()
      this.totalmoney = this.numberInput*(this.productmodel.productNowPrice/100)

    },
    addNumber() {
      this.numberInput += 1
      //this.$options.methods.checkMoney()
      this.checkMoney()
      //alert(this.partnerlist)
      this.totalmoney = this.numberInput*(this.productmodel.productNowPrice/100)
    },
    
    checkMoney(){ 
      if(this.partnerlist!=undefined)
      {
        this.partnerlist.forEach(element => {
           
              if(this.numberInput>=element.startNum && this.numberInput<element.endNum)
              {
                this.productmodel.productNowPrice = element.price
              }
         });

      }
    },

    reductionNumber() {
      this.numberInput -= 1;
      if (this.numberInput < 0) {
        this.numberInput = 0;

      }
      this.checkMoney()
      this.totalmoney = this.numberInput*(this.productmodel.productNowPrice/100)
    }
  }
};
