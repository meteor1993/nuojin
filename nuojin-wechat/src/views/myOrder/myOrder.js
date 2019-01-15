import { getOrderListByOpenId } from "@/api/orderApi"
import moment from 'moment'

export default {
    
        filters: {
            dateFormat: function(el) {
                return moment(el).format('YYYY-MM-DD HH:mm:ss')
            }
        },
      data() {
        return {
          showExpressCompany:'',
          showExpressNo:'',
          flag:false,
          orderlist:[]
        };
      },
      created(){
            this.getOrderListByOpenId()

      },
      methods: {
        getOrderListByOpenId(){
            getOrderListByOpenId().then(response => {
                if (response.resultCode === '1') {
                    this.orderlist = response.resultData.info
                    console.log(this.orderlist)
                }

        })
    
      },
      goback(){

        this.$router.go(-1)
      },
      ShowExpress(orderId){

        this.orderlist.forEach(element => {
            
            
            if(element.orderId==orderId)
            {
                
                if(element.expressCompany==null)
                {

                    this.$toast.show("暂无物流信息")
                    
                }
                else
                {
                    this.flag=true;
                    this.showExpressCompany =element.expressCompany
                    this.showExpressNo =element.expressNo 
                }
            }
            

        });
      },
      hideExpress() {
        this.flag=false
      }
      
    }
}