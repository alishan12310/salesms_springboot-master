<template>
  <div>
    <!-- <img src="@/assets/2233_02.jpg" alt="2233" style="width: 800px;display: block;margin: 0 auto;"> -->
    <el-container>
      <el-main>
        <div class="box-all">
          <div class="box-card">
            <el-card shadow="hover" class="left-top">
              <div slot="header" class="clearfix">
                <span>备忘</span>
                <el-button
                  v-show="Noteslist.length != 0"
                  style="float: right; padding: 3px 0"
                  type="text"
                  @click="open('新增备忘',form)"
                  >新增备忘</el-button
                >
              </div>
              <div>
                <el-empty :image-size="110" v-if="Noteslist.length === 0">
                  <el-button
                    type="primary"
                    size="small"
                    @click="open('新增备忘',form)"
                    >新增备忘</el-button
                  >
                </el-empty>
                <div v-else>
                  <el-collapse v-model="activeNames" accordion>
                    <el-collapse-item
                      :title="'【'+ item.notesTime +'】'+ item.notesTitle "
                      :name="item.notesId"
                      v-for="item in Noteslist"
                      :key="item.notesId"
                    >
                      <div class="content">
                        {{ item.notesContent }}
                      </div>

                      <div class="item">
                        <div>
                          {{ item.notesDate }}
                        </div>
                        <div>
                          <el-button
                            type="primary"
                            icon="el-icon-edit"
                            circle
                            @click="open('修改备忘',item)"
                          ></el-button>
                          <el-button
                            type="danger"
                            icon="el-icon-delete"
                            circle
                            @click="delnotes(item.notesId, item.notesTitle)"
                          ></el-button>
                        </div>
                      </div>
                    </el-collapse-item>
                  </el-collapse>
                  <el-pagination
                    layout="total, prev, pager, next"
                    :hide-on-single-page="true"
                    :total="total"
                    :page-size="ruleForm.pageSize"
                    :current-page="ruleForm.pageNum"
                    @current-change="changePage"
                    class="indexPageClass"
                  ></el-pagination>
                </div>
              </div>
            </el-card>
            <el-card shadow="hover" style="width: 100%" class="left-bottom">
              <div slot="header" class="clearfix">
                <span>文嘉《明日歌》</span>
                <el-button
                  style="float: right; padding: 3px 0"
                  type="text"
                  @click="clickFn"
                  >暂停</el-button
                >
              </div>
              <div class="lb_center">
                <div>
                  <div style="font-size: 2.1vh; text-align: center">
                    明日复明日
                  </div>
                  <div style="font-size: 2.1vh; text-align: center">
                    明日何其多
                  </div>
                  <div style="font-size: 2.1vh; text-align: center">
                    我生待明日
                  </div>
                  <div style="font-size: 2.1vh; text-align: center">
                    万事成蹉跎
                  </div>
                  <div style="margin-top: 2.1vh"></div>
                  <el-statistic
                    ref="statistic"
                    @finish="hilarity"
                    format="HH:mm:ss"
                    :value="deadline4"
                    title="距离明日:"
                    time-indices
                  >
                  </el-statistic>
                </div>
              </div>
            </el-card>
          </div>
          <el-card shadow="hover" class="box-card">
            <div slot="header" class="clearfix">
              <span>日历</span>
            </div>
            <div class="right-div">
              <el-calendar v-model="value">
                <template slot="dateCell" slot-scope="{ date, data }">
                  <p :class="data.isSelected ? 'is-selected' : ''">
                    {{ data.day.split("-").slice(2).join("-") }}
                    {{ data.isSelected ? "✔️" : "" }}
                  </p>
                </template>
              </el-calendar>
            </div>
          </el-card>
        </div>
      </el-main>
    </el-container>
    <el-drawer
      :title="title"
      :before-close="handleClose"
      :visible.sync="dialog"
      direction="rtl"
      custom-class="demo-drawer"
      ref="drawer"
    >
      <div class="demo-drawer__content">
        <el-form :model="form" style="flex: 1">
          <el-form-item label="备忘标题" label-width="70px">
            <el-input v-model="form.notesTitle" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="备忘内容" label-width="70px">
            <el-input
              type="textarea"
              v-model="form.notesContent"
              autocomplete="off"
              :rows="10"
            ></el-input>
          </el-form-item>
          <el-form-item label="备忘日期" label-width="70px">
            <el-input v-model="form.notesDate" autocomplete="off" disabled>
            </el-input>
          </el-form-item>
          <el-form-item label="备忘时间" label-width="70px">
            <el-time-picker
              arrow-control
              v-model="form.notesTime"
              placeholder="任意时间点"
              format
              value-format="HH:mm:ss"
            >
            </el-time-picker>
          </el-form-item>
        </el-form>
        <div class="demo-drawer__footer">
          <el-button style="width: 100%" @click="cancelForm">取 消</el-button>
          <el-button
            style="width: 100%"
            type="primary"
            @click="submit()"
            :loading="loading"
            >{{ loading ? "提交中 ..." : "确 定" }}</el-button
          >
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import {
  queryNotes,
  deleteNotes,
  addNotes,
  updateNotes,
} from "@/apis/indexNotes.js";
export default {
  name: "indexComponent",
  data() {
    return {
      Noteslist: [],
      deadline2: Date.now() + 1000 * 60 * 60 * 8,
      deadline3: Date.now() + 1000 * 60 * 30,
      deadline4: Date.now() + (new Date().setHours(23, 59, 59) - Date.now()),
      stop: true,
      value: new Date(), // 日期
      activeNames: ["1"], // 手风琴
      dialog: false, // 抽屉
      loading: false, // 加载状态
      title: "",
      total: null,
      form: {
        // 提交表单
        notesTitle: "",
        notesContent: "",
        notesDate: "",
        notesTime: "",
        notesUser: "",
      },
      ruleForm: {
        key: "notes_date",
        value: "",
        pageNum: 1,
        pageSize: 4,
        flag: "",
      },
    };
  },
  watch: {
    value(newValue, oldValue) {
      this.getNowDate(newValue);
    },
  },
  created() {
    this.getNowDate(this.value);
    this.query();
  },
  methods: {
    //打开
    open(title,item) {
      this.dialog = true;
      this.title = title;
      this.form = item;
    },
    //删除一条备忘
    delnotes(id, notesTitle) {
      const _this = this;
      _this
        .$confirm("确认删除词条备忘“" + notesTitle + "”吗？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(() => {
          deleteNotes(id).then(function (res) {
            if (res.code === 0) {
              _this.$message({
                type: "success",
                message: "备忘“" + notesTitle + "”" + res.msg,
              });
              _this.query();
            } else {
              _this.$message({
                type: "warning",
                message: "备忘“" + notesTitle + "”" + res.msg,
              });
            }
          });
        })
        .catch(() => {});
    },
    getNowDate(newValue) {
      let now = newValue;
      let year = now.getFullYear(); //得到年份
      let month = now.getMonth(); //得到月份
      let date = now.getDate(); //得到日期
      month = month + 1;
      if (month < 10) month = "0" + month;
      if (date < 10) date = "0" + date;
      let nowDate = year + "-" + month + "-" + date; //（格式化"yyyy-MM-dd"）
      this.form.notesDate = nowDate;
      this.ruleForm.value = nowDate;
      this.query();
    },
    //查询备忘
    query() {
      const user = localStorage.getItem("userData");
      this.ruleForm.flag = user;

      queryNotes(this.ruleForm)
        .then((res) => {
          this.Noteslist = res.data;
          this.activeNames = this.Noteslist[0].notesId;
          this.total = res.total;
        })
        .catch(() => {});
    },
    // 提交按钮
    submit() {
      const _this = this;
      const user = localStorage.getItem("userData");
      _this.form.notesUser = user;
      if (this.title == '新增备忘') {
        addNotes(_this.form)
          .then((res) => {
            if (res.code === 0) {
              _this.$alert(_this.form.notesTitle + res.msg, "提示", {
                confirmButtonText: "确定",
                callback: (action) => {
                  this.cancelForm();
                  _this.query();
                },
              });
            }
          })
          .catch(() => {});
      }else{
        updateNotes(_this.form)
          .then((res) => {
            if (res.code === 0) {
              _this.$alert(_this.form.notesTitle + res.msg, "提示", {
                confirmButtonText: "确定",
                callback: (action) => {
                  this.cancelForm();
                  _this.query();
                },
              });
            }
          })
          .catch(() => {});
      }
    },
    // 点外边关闭表单
    handleClose(done) {
      if (this.loading) {
        return;
      }
      this.$confirm("确定要提交表单吗？")
        .then((_) => {
          this.loading = true;
          this.timer = setTimeout(() => {
            done();
            // 动画关闭需要一定的时间
            setTimeout(() => {
              this.loading = false;
            }, 400);
          }, 2000);
        })
        .catch((_) => {});
    },
    // 取消
    cancelForm() {
      this.loading = false;
      this.dialog = false;
      this.form = {};
      clearTimeout(this.timer);
    },
    // 分页
    changePage(pageNum) {
      this.ruleForm.pageNum = pageNum;
      this.query();
    },
    clickFn() {
      this.$refs.statistic.suspend(this.stop);
      this.stop = !this.stop;
    },
    hilarity() {
      this.$notify({
        title: "提示",
        message: "时间已到，你可知寸金难买寸光阴？",
        duration: 0,
      });
    },
  },
};
</script>

<style scoped>
.content {
  width: 80%;
  text-overflow: ellipsis;
}
.item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: -15px;
}
.demo-drawer__content {
  display: flex;
  padding: 15px;
  flex-direction: column;
  height: 100%;
}
.demo-drawer__content .demo-drawer__footer {
  display: flex;
  width: 100%;
}

.box-all {
  display: flex;
  justify-content: space-between;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.box-all .box-card {
  width: 49.5%;
  height: calc(100vh - 125px);
}

.box-all .box-card .right-div {
  display: flex;
  align-items: center;
  height: calc(100vh - 170px);
  justify-content: center;
}

.box-all .box-card .left-top {
  height: 384px;
  margin-bottom: 10px;
  position: relative;
}

.box-all .box-card .left-top .indexPageClass {
  position: absolute;
  margin-bottom: 15px;
  bottom: 0;
  right: 0;
}

.box-all .box-card .left-bottom {
  margin-bottom: 15px;
  height: calc(100vh - 520px);
}

.box-all .box-card .left-bottom .lb_center {
  display: flex;
  align-items: center;
  height: calc(100vh - 580px);
  justify-content: center;
}

.is-selected {
  color: #1989fa;
}

/deep/ .el-calendar-table .el-calendar-day {
  height: 10vh;
}

/deep/ .el-statistic .head {
  font-size: 2.1vh;
}

/deep/ .el-statistic .con .number {
  font-size: 2.1vh;
}
</style>