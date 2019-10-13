<template>
  <div>
    <div id="Bmap" class="Bmap">

    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "MapManage",
    data() {
      return {

      }
    },
    created() {

    },
    mounted() {
      this.initBmap();
    },
    methods: {
      initBmap(){
        // 百度地图API功能
        const map = new BMap.Map("Bmap");    // 创建Map实例
        // 创建点坐标
        const point = new BMap.Point(115.80633819273, 28.668198507504);

        // 初始化地图，设置中心点坐标和地图级别
        map.centerAndZoom(point, 15);
        map.setCurrentCity("南昌");
        map.enableScrollWheelZoom(true);

        //添加浏览器定位
        var geolocation = new BMap.Geolocation();
        geolocation.getCurrentPosition(function(r){
          if(this.getStatus() == BMAP_STATUS_SUCCESS){
            var mk = new BMap.Marker(r.point);
            map.addOverlay(mk);
            map.panTo(r.point);
          }
          else {
            alert('failed'+this.getStatus());
          }
        },{enableHighAccuracy: true})

        // 添加定位控件
        var geolocationControl = new BMap.GeolocationControl();
        geolocationControl.addEventListener("locationSuccess", function(e){
          // 定位成功事件
          var address = '';
          address += e.addressComponent.province;
          address += e.addressComponent.city;
          address += e.addressComponent.district;
          address += e.addressComponent.street;
          address += e.addressComponent.streetNumber;
        });
        geolocationControl.addEventListener("locationError",function(e){
          // 定位失败事件
          alert(e.message);
        });
        map.addControl(geolocationControl);

        // 定义一个控件类,即function
        function ZoomControl(){
          // 默认停靠位置和偏移量
          this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
          this.defaultOffset = new BMap.Size(10, 10);
        }

        // 通过JavaScript的prototype属性继承于BMap.Control
        ZoomControl.prototype = new BMap.Control();

        // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
        // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
        ZoomControl.prototype.initialize = function(map){
          // 创建一个DOM元素
          var bt =document.createElement("button");           //createElement生成button对象
          bt.innerHTML = '显示仓库点';
          bt.onclick = function () {//绑定点击事件
            map.clearOverlays();
            var markerArr = [
              { title: "一号仓库", point: "115.94999520983819,28.55267749247153", address: "一号仓库", tel: "12306",type: "仓库"},
              { title: "二号仓库", point: "115.95042639648284,28.58141911743437", address: "二号仓库", tel: "18500000000",type: "仓库"},
              { title: "三号仓库", point: "115.9161470582334,28.556959452045638", address: "三号仓库", tel: "18500000000",type: "仓库"},
              { title: "四号仓库", point: "115.9350474061571,28.537066135085666", address: "四号仓库", tel: "18500000000",type: "仓库"}]
            var pointmk = new Array(); //存放标注点经纬信息的数组
            var marker = new Array(); //存放标注点对象的数组
            var info = new Array(); //存放提示信息窗口对象的数组
            for (var i = 0; i < markerArr.length; i++) {
              var p0 = markerArr[i].point.split(",")[0]; //
              var p1 = markerArr[i].point.split(",")[1]; //按照原数组的point格式将地图点坐标的经纬度分别提出来
              pointmk[i] = new window.BMap.Point(p0, p1); //循环生成新的地图点
              // var myIcon = new BMap.Icon("/static/ck.jpg", new BMap.Size(20,20));
              marker[i] = new window.BMap.Marker(pointmk[i]); //按照地图点坐标生成标记
              map.addOverlay(marker[i]);
              marker[i].setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
              var label = new window.BMap.Label(markerArr[i].title, { offset: new window.BMap.Size(20, -10) });
              marker[i].setLabel(label);
              info[i] = new window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;’>" + markerArr[i].title + "</br>地址：" + markerArr[i].address + "</br> 电话：" + markerArr[i].tel + "</br></p>"); // 创建信息窗口对象
            }
            marker[0].addEventListener("mouseover", function () {
              this.openInfoWindow(info[0]);
            });
            marker[1].addEventListener("mouseover", function () {
              this.openInfoWindow(info[1]);
            });
            marker[2].addEventListener("mouseover", function () {
              this.openInfoWindow(info[2]);
            });
            marker[3].addEventListener("mouseover", function () {
              this.openInfoWindow(info[3]);
            });
          };
          // 添加DOM元素到地图中
          map.getContainer().appendChild(bt);
          // 将DOM元素返回
          return bt;
        }
        // 创建控件
        var myZoomCtrl = new ZoomControl();
        // 添加到地图当中
        map.addControl(myZoomCtrl);

        // 定义一个控件类,即function
        function ZoomControl1(){
          // 默认停靠位置和偏移量
          this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
          this.defaultOffset = new BMap.Size(140, 10);
        }
        // 通过JavaScript的prototype属性继承于BMap.Control
        ZoomControl1.prototype = new BMap.Control();

        // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
        // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
        ZoomControl1.prototype.initialize = function(map){
          // 创建一个DOM元素
          var bt =document.createElement("button");           //createElement生成button对象
          bt.innerHTML = '显示物资点';
          bt.onclick = function () {//绑定点击事件
            map.clearOverlays();
            var markerArr = [
              { title: "一号沙堆", point: "115.96616470901246,28.550773793583648", address: "一号沙堆", tel: "12306",type: "物资"},
              { title: "二号沙堆", point: "115.98614302354777,28.55445424672301", address: "二号沙堆", tel: "18500000000",type: "物资"}]
            var pointm = new Array(); //存放标注点经纬信息的数组
            var marker = new Array(); //存放标注点对象的数组
            var info = new Array(); //存放提示信息窗口对象的数组
            for (var i = 0; i < markerArr.length; i++) {
              var p0 = markerArr[i].point.split(",")[0]; //
              var p1 = markerArr[i].point.split(",")[1]; //按照原数组的point格式将地图点坐标的经纬度分别提出来
              pointm[i] = new window.BMap.Point(p0, p1); //循环生成新的地图点
              // var myIcon = new BMap.Icon("/static/ck.jpg", new BMap.Size(20,20));
              marker[i] = new window.BMap.Marker(pointm[i]); //按照地图点坐标生成标记
              map.addOverlay(marker[i]);
              marker[i].setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
              var label = new window.BMap.Label(markerArr[i].title, { offset: new window.BMap.Size(20, -10) });
              marker[i].setLabel(label);
              info[i] = new window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;’>" + markerArr[i].title + "</br>地址：" + markerArr[i].address + "</br> 电话：" + markerArr[i].tel + "</br></p>"); // 创建信息窗口对象
            }
            marker[0].addEventListener("mouseover", function () {
              this.openInfoWindow(info[0]);
            });
            marker[1].addEventListener("mouseover", function () {
              this.openInfoWindow(info[1]);
            });
          };
          // 添加DOM元素到地图中
          map.getContainer().appendChild(bt);
          // 将DOM元素返回
          return bt;
        }
        // 创建控件
        var myZoomCtrl1 = new ZoomControl1();
        // 添加到地图当中
        map.addControl(myZoomCtrl1);

        //添加显示经纬度的点击事件
        function showInfo(e){
          alert(e.point.lng + ", " + e.point.lat);
        }
        map.addEventListener("click", showInfo);
      }
    }
  }
</script>

<style scoped>
  .title {
    color: #333;
    text-align: left;
    font-size: 30px;
    height: 75px;
    line-height: 75px;
  }
  .Bmap {
    width: 1800px;
    height: 925px;
  }
</style>
