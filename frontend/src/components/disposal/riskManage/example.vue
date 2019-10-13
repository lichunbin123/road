<template>
  <div>
    <div id="l-map"></div>
    <div id="r-result"></div>
  </div>
</template>
<script>
  import BMap from 'BMap'
  export default {
    name: 'BaiduMap',
    data() {
      return {}
    },
    mounted() {
      var map = new BMap.Map("l-map");
      map.centerAndZoom(new BMap.Point(116.404, 39.915), 14);
      var driving = new BMap.DrivingRoute(map, {
        renderOptions: {
          map: map,
          autoViewport: true,
          panel: "r-result"
        }
      });
      var start = new BMap.Point(116.310791, 40.003419);
      var end = new BMap.Point(116.486419, 39.877282);
      driving.search(start, end);
    }
  }
</script>
<style scoped>
  #l-map{height:300px;width:100%;}
  #r-result,#r-result{width:100%;height: 800px}
</style>



