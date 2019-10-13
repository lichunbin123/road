<template>
  <div>
    <div class="title">
      <h>物资装备管理</h>
    </div>
    <div id="palceSelect" class="placeSelect">
      <el-tabs type="border-card">
        <el-tab-pane>
          <span slot="label"><i class="el-icon-house"></i> 应急资源仓库</span>
          <el-table
            :data="table1Data"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="name"
              label="资源仓库名称">
            </el-table-column>
            <el-table-column
              prop="type"
              label="资源仓库类型">
            </el-table-column>
            <el-table-column
              prop="scope"
              label="资源范围">
            </el-table-column>
            <el-table-column
              prop="place"
              label="具体位置">
            </el-table-column>
            <el-table-column
              prop="head"
              label="负责人">
            </el-table-column>
            <el-table-column
              prop="phone"
              label="联系电话">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="success" size="small">查看资源详情</el-button>
                <el-button @click="enterClick(scope.row)" type="success" size="small">进入仓库</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label"><i class="el-icon-truck"></i> 应急战备库</span>
          <el-table
            :data="table2Data"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="name"
              label="战备仓库名称">
            </el-table-column>
            <el-table-column
              prop="type"
              label="战备仓库类型">
            </el-table-column>
            <el-table-column
              prop="scope"
              label="资源范围">
            </el-table-column>
            <el-table-column
              prop="place"
              label="具体位置">
            </el-table-column>
            <el-table-column
              prop="head"
              label="负责人">
            </el-table-column>
            <el-table-column
              prop="phone"
              label="联系电话">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="success" size="small">查看资源详情</el-button>
                <el-button @click="enterClick(scope.row)" type="success" size="small">进入仓库</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label"><i class="el-icon-suitcase"></i> 应急救助基地</span>
          <el-table
            :data="table3Data"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="name"
              label="救助基地名称">
            </el-table-column>
            <el-table-column
              prop="type"
              label="救助基地类型">
            </el-table-column>
            <el-table-column
              prop="scope"
              label="资源范围">
            </el-table-column>
            <el-table-column
              prop="place"
              label="具体位置">
            </el-table-column>
            <el-table-column
              prop="head"
              label="负责人">
            </el-table-column>
            <el-table-column
              prop="phone"
              label="联系电话">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="success" size="small">查看资源详情</el-button>
                <el-button @click="enterClick(scope.row)" type="success" size="small">进入仓库</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label"><i class="el-icon-attract"></i> 应急器材库</span>
          <el-table
            :data="table4Data"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="name"
              label="器材仓库名称">
            </el-table-column>
            <el-table-column
              prop="type"
              label="器材仓库类型">
            </el-table-column>
            <el-table-column
              prop="scope"
              label="资源范围">
            </el-table-column>
            <el-table-column
              prop="place"
              label="具体位置">
            </el-table-column>
            <el-table-column
              prop="head"
              label="负责人">
            </el-table-column>
            <el-table-column
              prop="phone"
              label="联系电话">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="success" size="small">查看资源详情</el-button>
                <el-button @click="enterClick(scope.row)" type="success" size="small">进入仓库</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "MaterialManage",
    data() {
      return {
        table1Data: [],
        table2Data: [],
        table3Data: [],
        table4Data: [],
      }
    },
    created(){

    },
    methods: {
      initTable(){

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
</style>
