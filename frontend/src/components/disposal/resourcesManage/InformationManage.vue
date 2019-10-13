<template>
  <div>
    <div class="title">
      <h>应急信息管理</h>
    </div>
    <div>
      <el-row>
        <el-col :span="6" style="width: 50%">
          <div id="header" class="header">
            <h style="">应急处置基本常识</h>
          </div>
          <div id="commonsense" class="commonsense">
            <el-collapse v-model="activeNames" @change="handleChange">
              <el-collapse-item title="一，立即停车" name="1">
                <div id="content" class="content">车辆发生交通事故后，应立即停车。停车以后，按规定拉紧手制动，切断电源，开启危险信号灯。如在夜间发生事故，还需开示宽灯，尾灯。在高速公路发生事故后，还需在车后按照规定设置危险警告标示。</div>
              </el-collapse-item>
              <el-collapse-item title="二，及时报案" name="2">
                <div id="content" class="content">当事人在事故发生后，应及时将事故发生的时间，地点，肇事车辆及伤亡情况，打电话或委托过往车辆，行人向附近的公干机关或执勤交警报案。如果现场发生火灾，还应该向消防部门报告。</div>
              </el-collapse-item>
              <el-collapse-item title="三，抢救伤者" name="3">
                <div id="content" class="content">当事人确认受伤者的伤情后，能采取紧急抢救措施的，应尽最大努力抢救，包括采取止血，包扎，固定，搬运和心肺复苏等，并设法送就近的医院抢救治疗。</div>
              </el-collapse-item>
              <el-collapse-item title="四，保护现场" name="4">
                <div id="content" class="content">保护现场的原始状态，包括其中的车辆，人员，牲畜和遗留的痕迹，散落物不随意挪动位置。</div>
              </el-collapse-item>
              <el-collapse-item title="五，做好防火防爆措施" name="5">
                <div id="content" class="content">事故当事人还应做好防火防爆措施。首先，应关掉车辆的引擎，消除其他可以引起火警的隐患。不要在事故现场吸烟，以防引燃易燃易爆物品。</div>
              </el-collapse-item>
              <el-collapse-item title="六，协助现场调查取证" name="6">
                <div id="content" class="content">在交通警察勘察现场和调查取证时，当事人必须如实向交警部门陈述交通事故发生的经过，不得隐瞒交通事故的真实情况。</div>
              </el-collapse-item>
            </el-collapse>
          </div>
        </el-col>
        <el-col :span="6" style="width: 50%">
          <div id="header" class="header">
            <h style="">常用处置技术措施及手段</h>
          </div>
          <div id="measures" class="measures">
            <el-collapse v-model="activeNames" @change="handleChange">
              <el-collapse-item title="应急处置原则" name="7">
                <div id="content" class="content">
                  <h>1.冷静判断，审时度势。</br>

                  在行车过程中，遇到紧急情况时，要保持清醒的头脑，迅速作出判断，采取正确的处置措施，千万不能犹豫不决，错失良机。</br>

                  2.及时减速，规避风险。</br>

                  紧急情况发生时，规避和减轻交通事故危害和损失的有效措施是制动减速，同时向其他交通参与者及时传递危险信号。</br>

                  3.避重就轻，减少损失。</br>

                  事故不可避免时，驾驶员应避重就轻，向损失较轻、危害较小的一方避让，尽量减轻事故的损失和后果。</br>

                  4.以人为本、生命至上。</br>

                  遇到紧急情况采取措施时，应遵循“以人为本、生命至上”的原则，“宁让车受损，不让人伤亡”，尽可能地消除威胁人身安全的因素。</br>
                  </h>
                </div>
              </el-collapse-item>
              <el-collapse-item title="事故现场处置流程" name="8">
                <div id="content" class="content">
                  <h>（1）发生交通事故时，驾驶员应立即将车辆停至公路或高速公路的安全停车地带，避开人群集中区域。车停稳后，迅速关闭电源总开关，开启危险报警闪光灯、示廓灯。</br>

                  （2）在车辆后方同车道50~100米处设置警告标志，高速公路应在150米外设置警告标志，人员在车后50~100米路边或高速公路护栏外的安全地带等待救援。</br>
                  （3）疏散现场人员，避免二次事故的发生。运输危险货物的车辆，根据危险货物的爆炸、易燃、毒害、感染、腐蚀、放射性等不同危险特性设置初始隔离区。</br>

                  （4）保护现场，及时亲自或委托他人拨打交通事故报警电话122，说明事故发生时间和地点、报告人姓名、事故造成的人员伤亡和损失等情况。交警到达现场后，驾驶员应积极配合交警进行现场勘查分析。</br>

                    （5）如事故现场出现伤员，在确认事故现场周围环境安全的前提下，根据现场救护条件，以先救命、后治伤为救护原则，尽量采取减轻伤员痛苦和减少死亡的措施。</h></div>
              </el-collapse-item>
              <el-collapse-item title="交通事故的报告程序" name="9">
                <div id="content" class="content">
                  <h>
                  一、未造成人员伤亡</br>

                  1.仅造成较小财产损失</br>

                  （1）应立即报告本企业安全生产管理部门或安全生产负责人。</br>

                  （2）安全生产管理人员无法立即赶往现场时，驾驶员可与对方协商解决。</br>

                  （3）若协商不成，应拨打122，由公安机关交通管理部门处理。</br>

                  2.造成较大财产损失</br>

                  （1）驾驶员应立即报告本企业负责人，请求负责人赶赴现场协商解决。</br>

                  （2）若协商不成，应拨打122，由公安机关交通管理部门处理。</br>
                  二、造成人员伤亡</br>

                  （1）立即拨打122，报告给公安机关交通管理部门。</br>

                  （2）拨打120，通知医疗救护单位进行人员急救。</br>

                  （3）按企业安全事故报告程序和有关规定，报告企业负责人。</br>

                    （注：如情况紧急或事故造成了大量人员伤亡，驾驶员可直接向事故发生地县级以上人民政府应急管理部门报告。如事故现场发生火灾、爆炸，在自行扑教的同时，还应立即向消防救援部门报告。</br></h>
                </div>
              </el-collapse-item>
              <el-collapse-item title="小贴士" name="10">
                <div id="content" class="content">
                  <h>中华人民共和国民法通则》第一百二十九条规定：因紧急避险造成损害的，由引起险情发生的人承担民事责任。如果危险是由自然原因引起的，紧急避险的人不承担民事责任或只承担适当民事责任。

                    因紧急避险采取措施不当或者超过必要的限度，造成不应有的损害的，紧急避险人应当承担适当的民事责任。</h></div>
              </el-collapse-item>
            </el-collapse>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6" style="width: 100%">
          <div id="header" class="header">
            <h style="">以往案例</h>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "TeamManage",
    data() {
      return {
        activeNames: ['1'],
      }
    },
    created(){

    },
    methods: {
      handleChange(val) {
        console.log(val);
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
  .header{
    color: #333;
    text-align: center;
    font-size: 20px;
    height: 40px;
    line-height: 40px;
    background-color:  #B3C0D1;
  }
  .li{
    text-align:left;

  }
  #li{
    text-align:left;
  }
  .commonsense{
    width: 800px;
  }
  .measures{
    width: 800px;
  }
  .content{
    text-align: left;
    font-size: 18px;
  }
</style>
