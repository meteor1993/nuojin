
import { getJSSDK } from '@/utils/sdk'
import { getByProductId, getPartnerPrice} from "@/api/productApi"
import {getAddressList } from "@/api/customerApi"
import {InsertOrder} from "@/api/orderApi"
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
      chooseaddress:{},
      chooseaddressId:''
     

    };
  },
  created() {
    this.getFetchData()
    this.getPartnerPriceList()
    this.getAddressData()
    getJSSDK()
    
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

    OrderSumbit(){

      if(this.chooseaddressId == ''||this.chooseaddressId==undefined)
      { 
        this.$toast.show("请选择一个配送地址")

      }

      let params={
        "productId":this.productmodel.productId,
        "count":this.numberInput,
        "addressId":this.chooseaddressId
      }


      InsertOrder(params).then(response => {
        if (response.resultCode === '1') {
          let opthions = {
            appId: response.resultData.appId,
            timeStamp: response.resultData.timeStamp,
            nonceStr: response.resultData.nonceStr,
            package: response.resultData.package,
            signType: response.resultData.signType,
            paySign: response.resultData.paySign
          }
          console.log(opthions)
          this.onBridgeReady(opthions)
        }

      })


    },


    getAddressData(){

      getAddressList().then(response => {
        if (response.resultCode === '1') {
          if(response.resultData.info.length > 0)
          {
            
           
            this.chooseaddressId = this.$route.query.addressId

            if(this.chooseaddressId == ''||this.chooseaddressId==undefined)
            {

              this.chooseaddress = response.resultData.info[0]
              this.chooseaddressId = this.chooseaddress.addressId
             
            }
            else
            {

              response.resultData.info.forEach(element => {
                
                if(element.addressId == this.chooseaddressId)
                {
                  
                  this.chooseaddress = element
                  
                }
              });
             

            }
           
            
          }
        }
      
      })
    },

    goEditAddress() {


      sessionStorage.setItem('productId',this.productmodel.productId)
      this.$router.push({
        path: '/deliveryDddress',
        query:{
          
          //productId:this.productmodel.productId,
          
          addressId:this.chooseaddressId
        
        }
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
    },

    onBridgeReady (params) {
      window.WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
          'appId': params.appId, // 公众号名称，由商户传入
          'timeStamp': params.timeStamp, // 时间戳，自1970年以来的秒数
          'nonceStr': params.nonceStr, // 随机串
          'package': 'prepay_id='+params.package,
          'signType': params.signType, // 微信签名方式：
          'paySign': params.paySign // 微信签名
        },
        (res) => {
          if (res.err_msg === 'get_brand_wcpay_request:ok') {
            const that = this
            this.$toast.show("下单成功",function(){
              that.$router.push({
                path: '/myOrder'
              })
            })
          } else {
            this.$toast.show('支付失败，请重试')
          }
        }
      )
    }
  }
};
