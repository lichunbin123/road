<template>
  <div>
    <div class="header" id="header">
      <h >公路基础信息Map</h>
    </div>
    <div class="map" id="Map">
    </div>
  </div>
</template>

<script>
    export default {
      name: 'BaiduMap',
      components: {},
      data() {
        return {
          address_detail: "",
          userlocation: {lng: "", lat: ""},
        };
      },
      mounted() {
        // 创建地图实例
        const map = new BMap.Map("Map");
        // 创建点坐标
        const point = new BMap.Point(115.80633819273, 28.668198507504);

        // 初始化地图，设置中心点坐标和地图级别
        map.centerAndZoom(point, 15);
        map.setCurrentCity("南昌");
        map.enableScrollWheelZoom(true);
        // var marker = new BMap.Marker(point);
        // map.addOverlay(marker);

        //设置地图样式
        // map.setMapStyleV2({
        //   styleId: 'c546bff8e46a1d5844313c41d8994b95'
        // });
        //添加路况信息
        const ctrl = new BMapLib.TrafficControl({
          showPanel: false
        });
        map.addControl(ctrl);
        ctrl.setAnchor(BMAP_ANCHOR_TOP_RIGHT);

        //添加城市列表
        map.enableInertialDragging();

        map.enableContinuousZoom();
        var size = new BMap.Size(20, 30);

        map.addControl(new BMap.CityListControl({
          anchor: BMAP_ANCHOR_TOP_LEFT,
          offset: size,


          // 切换城市之间事件
          // onChangeBefore: function(){
          //    alert('before');
          // },
          // 切换城市之后事件
          // onChangeAfter:function(){
          //   alert('after');
          // }
        }));
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

        //添加全景

        var stCtrl = new BMap.PanoramaControl(); //构造全景控件
        stCtrl.setAnchor(BMAP_ANCHOR_BOTTOM_RIGHT);
        stCtrl.setOffset(new BMap.Size(20, 20));
        map.addControl(stCtrl);//添加全景控件

        // 定义一个控件类,即function
        function ZoomControl() {
          this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
          this.defaultOffset = new BMap.Size(90, 30);
        }

        // 通过JavaScript的prototype属性继承于BMap.Control
        ZoomControl.prototype = new BMap.Control();

        // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
        // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
        ZoomControl.prototype.initialize = function(map){
          // 创建一个DOM元素
          var div = document.createElement("div");
          div.innerHTML = '<div id="r-result"><input type="text" id="suggestId" name="address_detail" placeholder="请输入搜索的地址" v-model="address_detail" class="input_style"/></div>';

          // 添加DOM元素到地图中
          map.getContainer().appendChild(div);
          // 将DOM元素返回
          return div;
        }

        // 创建控件
        var myZoomCtrl = new ZoomControl();
        // 添加到地图当中
        map.addControl(myZoomCtrl);

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


        //实现搜索
        var th = this
        var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
          {
            "input": "suggestId",
            "location": map
          })
        var myValue
        ac.addEventListener("onconfirm", function (e) {    //鼠标点击下拉列表后的事件
          var _value = e.item.value;
          myValue = _value.province + _value.city + _value.district + _value.street + _value.business;
          this.address_detail = myValue
          setPlace();
        });

        function setPlace() {
          map.clearOverlays();    //清除地图上所有覆盖物
          function myFun() {
            th.userlocation = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            map.centerAndZoom(th.userlocation, 15);
            map.addOverlay(new BMap.Marker(th.userlocation));    //添加标注
          }

          var local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
          });
          local.search(myValue);
        }
      },

      methods: {
      }
    }
</script>

<style scoped>
  .map {
    width: 100%;
    height: 945px;
  }
  .el-input {
    width: 250px;
  }

  .header {
    background-color: gainsboro;
    color: #333;
    text-align: center;
    font-size: 20px;
    height: 50px;
    line-height: 50px;
  }
</style>
