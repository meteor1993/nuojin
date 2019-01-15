
import { getAddressList} from "@/api/customerApi"


export default {
  data() {
    return {
      selection: false,
      addresslist:[],
      showaddress:'',
      queryaddressid:'',
      productid:''
    };
  },
  created(){
    this.getAddressData()
  },
  methods: {

    goback(){

      this.$router.go(-1)
    },

    selectionBtn(addressId) {
     
      this.addresslist.forEach((element, index) => {
        
        
        
        if (element.addressId == addressId) {
          element.chooseFlag = '1';
          this.queryaddressid = element.addressId
        }
        else
        {
          element.chooseFlag = '0';

        }

      });


    },
    getAddressData(){

      
      getAddressList().then(response => {
        if (response.resultCode === '1') {
          if(response.resultData.info.length > 0)
          {
           
            this.addresslist = response.resultData.info
           
          }
          this.productid =  sessionStorage.getItem('productId')

        }
      
      })
    },
    

    goInsertAddress(){

      this.$router.push({
        path: '/editAddress'
      })

    },

    goEditAddress(addressId) {
      

      this.$router.push({
        path: '/editAddress',
        query: { addressId: addressId
         
        }
      })
    },
    goOrder(){

      //alert(111)
      if(this.queryaddressid == '')
      {

        this.$toast.show("请选择一个收货地址！")
        return;

      }
      sessionStorage.removeItem('productId')
      this.$router.push({
        path: '/order',
        query: { 
          addressId: this.queryaddressid,
          productId: this.productid
        }
      })

    }
  }
};
