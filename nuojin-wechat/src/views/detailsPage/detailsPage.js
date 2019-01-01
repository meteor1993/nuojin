import { Swiper, Slide } from "vue-swiper-component";
export default {
  components: {
    Swiper,
    Slide
  },
  data() {
    return {
      model: {
        id: '1'
      }
    }
  },
  methods: {
    goPayOrder(id) {
      this.$router.push({
        path: '/order',
        query: { id: id }
      })
    }
  }
};
