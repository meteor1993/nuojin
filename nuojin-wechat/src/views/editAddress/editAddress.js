import VDistpicker from 'v-distpicker'


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
      postcode:''
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
      console.log(data)
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
      if(consignee == '')
      {
        this.$toast.show("请输入收货人")
        return;
      }
      if(this.address == ''){
        this.$toast.show("请填写详细地址")
        return;
      }
      
    }
  },
  // mounted(){
  //   console.log(this.$refs)
  //   this.$refs.mask.style.height = document.getElementById('app').clientHeight + 'px'
  // },
  created() {
    
    if (this.$route.query.id) {
      this.btnShowFlag = true
    }
  }
};
