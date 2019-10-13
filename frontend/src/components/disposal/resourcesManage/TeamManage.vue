<template>
  <div>
    <div class="title">
      <h>专业队伍管理</h>
    </div>

    <div>
      <el-tabs type="border-card">
        <el-tab-pane>
          <span slot="label"><i class="el-icon-house"></i> 应急专业队伍</span>
          <el-table
            :data="tableData1"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="name"
              label="队伍名称">
            </el-table-column>
            <el-table-column
              prop="type"
              label="队伍类型">
            </el-table-column>
            <el-table-column
              prop="place"
              label="队伍所处位置">
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
          <span slot="label"><i class="el-icon-truck"></i> 社会企业应急预备队伍</span>
          <el-table
            :data="tableData2"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="name"
              label="队伍名称">
            </el-table-column>
            <el-table-column
              prop="type"
              label="队伍类型">
            </el-table-column>
            <el-table-column
              prop="place"
              label="队伍位置">
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
          <span slot="label"><i class="el-icon-suitcase"></i> 应急专家库</span>
          <el-table
            :data="tableData3"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="name"
              label="应急专家库名称">
            </el-table-column>
            <el-table-column
              prop="type"
              label="专家库类型">
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
    name: "TeamManage",
    data() {
      return {
        tableData1: [],
        tableData2: [],
        tableData3: [],
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
