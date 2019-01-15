import { Swiper, Slide } from "vue-swiper-component";
import { getByProductFirstType } from "@/api/productApi"

export default {
  components: {
    Swiper,
    Slide
  },
  filters: {
    moneyFormat: function(money) {
      if (money && money != null) {
        money = String(money)
        const left = money.split('.')[0]
        let right = money.split('.')[1]
        right = right ? (right.length >= 2 ? '.' + right.substr(0, 2) : '.' + right + '0') : '.00'
        const temp = left.split('').reverse().join('').match(/(\d{1,3})/g)
        return (Number(money) < 0 ? '-' : '') + temp.join(',').split('').reverse().join('') + right
      } else if (money === 0) { // 注意===在这里的使用，如果传入的money为0,if中会将其判定为boolean类型，故而要另外做===判断
        return '0.00'
      } else {
        return ''
      }
    }
  },
  data() {
    return {
      showIndex: 0,
      productList: [],
      note: {
        backgroundImage: "url()",
        backgroundRepeat: "no-repeat",
        backgroundSize: "25px auto",
        marginTop: "5px",
      }
    };
  },
  created() {
    this.getByProductFirstType('wine')
  },
  methods: {
    getByProductFirstType(productFirstType) {
      getByProductFirstType(productFirstType).then(response => {
        if (response.resultCode === '1') {
          this.productList = response.resultData.info
        }
       
      })
    },
    changeShowIndex(idx) {
      this.showIndex = idx;
    },
    goProductDetail(id) {
      
      this.$router.push({
        path: '/detailsPage',
        query: { productId: id }
      })
    },
    goUserInfo() {
      this.$router.push({
        path: '/userInfo'
      })
    }
  }
};
