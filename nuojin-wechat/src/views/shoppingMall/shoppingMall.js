import { Swiper, Slide } from "vue-swiper-component";

export default {
  components: {
    Swiper,
    Slide
  },

  data() {
    return {
      showIndex: 0,
      productList: [{
        id: '1',
        title: '星空版',
        payByMonth: 100,
        infoMess: '一款好酒',
        originalPrice: 564,
        nowPrice: 240
      },{
        id: '2',
        title: '星空版',
        payByMonth: 100,
        infoMess: '一款好酒',
        originalPrice: 564,
        nowPrice: 240
      }]
    };
  },
  methods: {
    changeShowIndex(idx) {
      this.showIndex = idx;
    },
    goProductDetail(id) {
      this.$router.push({
        path: '/detailsPage',
        query: { id: id }
      })
    },
    goUserInfo() {
      this.$router.push({
        path: '/userInfo'
      })
    }
  }
};
