<template>
  <div>
    <div class="header" id="header">
      <h>公路基础信息Map</h>
    </div>
    <div id="body" class="body">
      <div class="map" id="Map">
      </div>
      <div id="info" class="info">
        <div id="infotitle" class="infotitle">
          <span>路线规划</span>
        </div>
        <div id="input" class="input">
        <el-input
          placeholder="请输入起始点"
          v-model="start"
          clearable>
        </el-input>
        <el-input
          placeholder="终点"
          v-model="end"
          clearable>
        </el-input>
        <el-button type="success" @click="searchByStationName1()"round>搜索</el-button>
        </div>
        <div id="infosearchtitle" class="infotitle">
          <span>公路基础信息查询</span>
        </div>
        <div id="infosearch" class="infosearch">
          <el-row >
            <h>请选择市&nbsp&nbsp</h>
          <el-select v-model="value1" placeholder="请选择市" class="firstitem" @change="first" clearable>
            <el-option
              v-for="item in firstitem"
              :key="item.name"
              :label="item.name"
              :value="item.name">
            </el-option>
          </el-select>
          </el-row>
          <el-row>
            <h>请选择县区</h>
          <el-select v-model="value2" placeholder="请选择县区" class="seconditem" @change="second" clearable>
            <el-option
              v-for="item in seconditem"
              :key="item.county"
              :label="item.county"
              :value="item.county">
            </el-option>
          </el-select>
          </el-row>
          <el-row>
            <h>请选择类型</h>
          <el-select v-model="value3" placeholder="请选择类型"  @change="third" clearable>
            <el-option
              v-for="item in types"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          </el-row>
          <el-row>
            <h>请选择路段</h>
            <el-select v-model="value4" placeholder="请选择" class="lastitem" @change="last" clearable>
              <el-option
                v-for="item in lastitem"
                :key="item.name"
                :label="item.name"
                :value="item.name">
              </el-option>
            </el-select>
          </el-row>

          <el-table :data="roadData" v-if="toroad">
            <el-table-column property="name" label="路段名称" ></el-table-column>
            <el-table-column property="county" label="所属县区"></el-table-column>
            <el-table-column property="level" label="道路等级"></el-table-column>
            <el-table-column property="lanes" label="车道数"></el-table-column>
            <el-table-column property="roadtype" label="路面面层类型"></el-table-column>
            <el-table-column property="roadwidth" label="路面宽度"></el-table-column>
            <el-table-column property="greenarea" label="绿化面积"></el-table-column>
          </el-table>


          <el-table :data="bridgeData" v-if="tobridge">
            <el-table-column property="name" label="桥梁名称" ></el-table-column>
            <el-table-column property="county" label="所属县区"></el-table-column>
            <el-table-column property="bridgetype" label="桥梁类型等级"></el-table-column>
            <el-table-column property="structuralsystem" label="结构体系"></el-table-column>
            <el-table-column property="width" label="宽度"></el-table-column>
            <el-table-column property="length" label="长度"></el-table-column>
            <el-table-column property="roadtype" label="路面面层类型"></el-table-column>
          </el-table>

          <el-table :data="tunnelData" v-if="totunnel">
            <el-table-column property="name" label="隧道名称"></el-table-column>
            <el-table-column property="county" label="所属县区"></el-table-column>
            <el-table-column property="roadtype" label="面层类型等级"></el-table-column>
            <el-table-column property="length" label="长度"></el-table-column>
            <el-table-column property="width" label="宽度"></el-table-column>
            <el-table-column property="lanes" label="车道数"></el-table-column>
            <el-table-column property="height" label="高度"></el-table-column>
            <el-table-column property="rocktype" label="岩层类型"></el-table-column>
          </el-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    import axios from 'axios'
    export default {
      name: 'BaiduMap',
      components: {},
      data() {
        return {
          address_detail: "",
          userlocation: {lng: "", lat: ""},
          from: null,
          to: null,
          start: "",
          end: "",
          value1: "",
          value2: "",
          props: [],
          options: [],
          firstitem: [],
          seconditem: [],
          lastitem: [],
          roadData: [],
          bridgeData: [],
          tunnelData: [],
          toroad: false,
          tobridge: false,
          totunnel: false,
          types: [{
            value: '路段',
            label: '路段'
          }, {
            value: '桥梁',
            label: '桥梁'
          }, {
            value: '隧道',
            label: '隧道'
          }],
          value3: "",
          value4: "",
        }
      },
      mounted() {
        this.initMap();
        this.searchcity();
      },

      methods: {
        searchcity(){
          const url = `http://localhost:8083/monitoring/bdmap/searchcity`
          return axios({
            method: 'post',
            url: url
          }).then(res => {
            if (res.status == 200){
              var result = res.data;
              console.log(result)
              console.log(result.responseData);
              this.firstitem = result.responseData;

            }
          }).catch(err => {
            console.log(err.response)
            this.$message("获取信息失败")
          })
        },
        first(){
          console.log("nihao")
          console.log(this.value1);
          var city = this.value1;
          return axios.post("http://localhost:8083/monitoring/bdmap/searchcounty/" + city).then(res => {
            if (res.status == 200){
              var result = res.data;
              this.seconditem = result.responseData;
            }
          }).catch(err => {
            console.log(err.response)
            this.$message("查询失败")
          })
        },
        second(){

        },
        third(){
          var value1 = this.value1;
          var value2 = this.value2;
          var value3 = this.value3;
          return axios.post("http://localhost:8083/monitoring/bdmap/searchname/" + value1+"/"+value2+"/"+value3).then(res => {
            if (res.status == 200){
              var result = res.data;
              this.lastitem = result.responseData;
            }
          }).catch(err => {
            console.log(err.response)
            this.$message("查询失败")
          })
        },
        last(){
          var value1 = this.value1;
          var value2 = this.value2;
          var value3 = this.value3;
          var value4 = this.value4;
          return axios.post("http://localhost:8083/monitoring/bdmap/searchinfo/" + value1+"/"+value2+"/"+value3+"/"+value4).then(res => {
            if (res.status == 200){
              var result = res.data;
              if (value3 == "路段"){
                this.toroad = true;
                this.tobridge = false;
                this.totunnel = false;
                this.roadData = result.responseData;
              }else if (value3 == "桥梁"){
                this.tobridge = true;
                this.toroad = false;
                this.totunnel = false
                this.bridgeData = result.responseData;
              } else if (value3 == "隧道"){
                this.totunnel = true;
                this.toroad = false;
                this.tobridge = false;
                this.tunnelData = result.responseData;
              }
            }
          }).catch(err => {
            console.log(err.response)
            this.$message("查询失败")
          })
        },
        initMap(){
          // 创建地图实例
          const map = new BMap.Map("Map");
          // window.map = map;

          // 创建点坐标
          const point = new BMap.Point(115.80633819273, 28.668198507504);

          // 初始化地图，设置中心点坐标和地图级别
          map.centerAndZoom(point, 15);
          map.setCurrentCity("南昌");
          map.enableScrollWheelZoom(true);

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
        searchByStationName1: function () {
            var _this = this;
            var startAddrr = this.start;//获得起点地名
            var localSearch = new BMap.LocalSearch(map);
            localSearch.setSearchCompleteCallback(function (searchResult) {
              var poi = searchResult.getPoi(0);//获得起点经纬度坐标
              if (poi != null) { //判断地名是否存在
                console.log('from poi', poi);
                _this.from = poi.point;
                console.log('from', _this.from);
                _this.searchByStationName2();
              }
              else{
                alert("请输入正确的地名！")
              }
            });
            localSearch.search(startAddrr);
          },
          searchByStationName2: function () {
            var _this = this;
            var endAddrr = this.end; //获得目的地地名
            var localSearch = new BMap.LocalSearch(map);
            localSearch.setSearchCompleteCallback(function (searchResult) {
              var poi = searchResult.getPoi(0); //获得目的地经纬度坐标
              if (poi != null) { //判断目的地是否存在
                _this.to = poi.point;
                _this.run();
              }
              else{
                alert("请输入正确的地名！")
              }

            });
            localSearch.search(endAddrr);
          },
          run: function () {
            map.clearOverlays() // 清除地图上所有的覆盖物
            var walking = new BMap.WalkingRoute(map) // 创建步行实例
            walking.search(this.from, this.to) // 第一个步行搜索
            let that = this;
            walking.setSearchCompleteCallback(function () {
              console.log('completeCallback start!');
              var pts = walking.getResults().getPlan(0).getRoute(0).getPath() // 通过步行实例，获得一系列点的数组

              var polyline = new BMap.Polyline(pts)
              map.addOverlay(polyline)
              console.log('pollyline!', polyline);
              var m1 = new BMap.Marker(that.from) // 创建2个marker
              var m2 = new BMap.Marker(that.to)
              map.addOverlay(m1)
              map.addOverlay(m2)

              var lab1 = new BMap.Label('起点', {position: that.from}) // 创建2个label
              var lab2 = new BMap.Label('终点', {position: that.to})
              map.addOverlay(lab1)
              map.addOverlay(lab2)
              console.log('setTimeout!');
              setTimeout(function () {
                map.setViewport([that.from, that.to]) // 调整到最佳视野
              }, 1000)
            })
          }
        }
      }

</script>

<style scoped>
  .map {
    width: 70%;
    height: 800px;
    float: left;
  }
  .info {
    width: 30%;
    height: 800px;
    float: right;
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
  .infotitle{
    font-size: 25px;
    font-weight: bold;
    background-color: gainsboro;
    margin-top: 10px;
    border-radius: 30px;
  }
  .infosearchtitle{
    font-size: 25px;
    font-weight: bold;
    background-color: gainsboro;
    margin-top: 10px;
    border-radius: 30px;
  }
  .input{
    margin-top: 20px;
  }
  .infosearch{

  }
  .el-row{
    text-align: left;
    margin-left: 20px;
    margin-top: 20px;

  }
</style>
