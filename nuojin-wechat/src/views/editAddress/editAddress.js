import VDistpicker from 'v-distpicker'

import {insertAddress,getAddressById} from "@/api/customerApi"

export default {
  
  data() {
    return {
      btnShowFlag: false,
      city:'请选择',
      addInp :false,
      mask:false,
      consignee:'',
      mobile:'',
      provinceCode:'',
      provinceName:'',
      cityCode:'',
      cityName:'',
      areaCode:'',
      areaName:'',
      address:'',
      postcode:'',
      selection:false,
     
      addressmodel:{}
    }
  },

  components:{
    VDistpicker
  },
  methods: {
    toAddress(){
      this.mask = true;
      this.addInp = true;
    },
    
    selected(data){
      //console.log(data)
      this.mask =false;
      this.addInp = false;
      this.city = data.province.value + ' ' + data.city.value +' ' + data.area.value
      this.provinceCode  = data.province.code
      this.provinceValue  = data.province.value
      this.cityCode  = data.city.code
      this.cityValue  = data.city.value
      this.areaCode  = data.area.code
      this.areaValue  = data.area.value
      
    },
    
    Insert(){
      
      if(this.provinceCode==''|| this.cityCode==''|| this.areaCode =='')
      {
        this.$toast.show("请选择省市区！")
        return;
      }
      if(this.consignee == '')
      {
        this.$toast.show("请输入收货人")
        return;
      }
      if(this.address == ''){
        this.$toast.show("请填写详细地址")
        return;
      }
      
      if(this.mobile == ''){
        this.$toast.show("请输入手机号码")
        return;
      }

      if(this.postcode == ''){
        this.$toast.show("请输入邮政编码")
        return;
      }
      
      let params = {"provinceCode":this.provinceCode,
      "provinceValue":this.provinceValue,
      "cityCode":this.cityCode,
      "cityValue":this.cityValue,
      "areaCode":this.areaCode,
      "areaValue":this.areaValue,
      "consignee":this.consignee,
      "address":this.address,
      "mobile":this.mobile,
      "addressId":this.addressmodel.addressId,
      "postcode":this.postcode
      }
      
      insertAddress(params).then(response => {
        if (response.resultCode === '1') {
          const that = this
          this.$toast.show("保存成功",function(){
            
            that.$router.push({
              path: '/deliveryDddress'
            
            })

          })
        }
        else
        {
          this.$toast.show("保存失败")

        }
      })
    }
  },
  // mounted(){
  //   console.log(this.$refs)
  //   this.$refs.mask.style.height = document.getElementById('app').clientHeight + 'px'
  // },
  created() {
    
    if (this.$route.query.addressId) {
      getAddressById(this.$route.query.addressId).then(response => {
        if (response.resultCode === '1') {
            this.addressmodel = response.resultData.info
            this.consignee = this.addressmodel.name 
            this.mobile = this.addressmodel.mobile
          
            this.address =this.addressmodel.detailAddress
            this.cityValue = this.addressmodel.cityValue
            this.provinceValue =  this.addressmodel.provinceValue
            this.areaValue = this.addressmodel.areaValue 
            
            this.postcode =this.addressmodel.postcode
          }
        else
        {
          this.$toast.show("系统错误")
          
        }

      })
    } 
    else
    {

    }


  }
};
