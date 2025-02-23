<template>
  <el-dialog
    :title="title"
    :visible.sync="show"
    width="40%"
    :before-close="handleClose"
  >
    <el-form :model="menuForm" :rules="rules" label-width="100px" >
      <el-form-item label="菜单名称" prop="menuTitle">
        <el-input v-model="menuForm.menuTitle"></el-input>
      </el-form-item>
      <el-form-item label="菜单图标" prop="menuIcon">
        <el-input v-model="menuForm.menuIcon"></el-input>
      </el-form-item>
      <el-form-item label="菜单级别">
        <el-select
          v-model="menuLevelFlag"
          placeholder="请选择"
          :disabled="false"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            @change="queryAllOneMenuList"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="权限" prop="menuRights">
        <el-select v-model="menuForm.menuRights" placeholder="请选择访问控制权限">
          <el-option
            label="销售员权限（开放权限）"
            :value="1"
          >
          </el-option>
          <el-option
            label="管理员权限（仅管理员）"
            :value="0"
          >
          </el-option>
        </el-select>
      </el-form-item>   
      <el-form-item label="选择一级菜单" v-show="allFlag">
        <el-select v-model="menuForm.menuLevel" placeholder="请选择一级菜单">
          <el-option
            v-for="item in options2"
            :key="item.menuId"
            :label="item.menuTitle"
            :value="item.menuId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="路由路径" v-show="menuLevelFlag === 2">
        <el-input v-model="menuForm.menuIndex"></el-input>
      </el-form-item>
      <el-form-item label="完整路径" v-show="menuLevelFlag === 2">
        <el-input v-model="menuForm.menuPath"></el-input>
      </el-form-item>
      <el-form-item label="排序" v-show="menuLevelFlag === 1">
        <el-input v-model="menuForm.menuSort"></el-input>
      </el-form-item>
      <el-form-item label="显示状态">
        <el-switch
          v-model="menuShowFlag"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submit()">确 定</el-button>
      <el-button @click="close">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {
  saveMenu,
  getAllOneMenuList,
  updateMenu,
  getAllTwoMenuList
} from "@/apis/system/menuManage.js";
export default {
  name: "addMenu",
  data() {
    return {
      //* 控制一级菜单列表状态
      allFlag: false,
      //* 控制弹窗
      show: false,
      //* 控制按钮加载状态
      loading: false,
      //* 弹窗标题
      title: "",
      //* 判断一二级
      menuLevelFlag: "",
      //* 显示状态
      menuShowFlag: true,
      //* 选择等级
      options: [
        {
          value: 1,
          label: "级别一",
        },
        {
          value: 2,
          label: "级别二",
        },
      ],
      //* 二级菜单
      options2: [],
      //* 表单项
      menuForm: {
        menuTitle: "",
        menuIndex: "",
        menuIcon: "",
        menuLevel: "",
        menuPath: "",
        menuSort: "",
        menuRights:"",
        menuShow: true,
      },
      parents: null,
      rules: {
          menuTitle: [
            { required: true, message: '请输入菜单名称', trigger: 'blur' },
          ],
          menuIcon: [
            { required: true, message: '请输入图标路径', trigger: 'blur' }
          ],
          menuRights: [
            { required: true, message: '请选择管理权限', trigger: 'change' }
          ],
        }
    };
  },
  watch: {
    menuLevelFlag: function (val) {
      if (val == 2) {
        this.allFlag = true;
      } else {
        this.allFlag = false;
      }
    },
  },
  mounted() {
    this.queryAllOneMenuList();
  },
  methods: {
    //*清空表单
    reset() {
      this.menuForm = {
        menuTitle: "",
        menuIndex: "",
        menuIcon: "",
        menuLevel: "",
        menuPath: "",
        menuRights:"",
        menuSort: "",
        menuShow: true,
      };
      this.menuLevelFlag = "";
    },
    //* 提交
    submit() {
      if (this.title == "新增菜单") {
        this.menuForm.menuSort = Number(this.menuForm.menuSort);
        if (this.menuLevelFlag === 1) {
          this.menuForm.menuLevel = 0;
        }
        if (this.menuShowFlag === true) {
          this.menuForm.menuShow = 1;
        } else {
          this.menuForm.menuShow = 0;
        }
        saveMenu(this.menuForm).then((res) => {
          this.show = false;
         window.location.reload()
        });
      } else {
        if (this.menuLevelFlag === 1) {
          this.menuForm.menuLevel = 0;
        }
        if (this.menuShowFlag === true) {
          this.menuForm.menuShow = 1;
        } else {
          this.menuForm.menuShow = 0;
        }
        updateMenu(this.menuForm).then((res) => {
          this.show = false;
         window.location.reload()
        });
      }
      setTimeout(() => {
      getAllTwoMenuList().then((res) => {
        localStorage.setItem("menuList", JSON.stringify(res.data));
      });
        
      }, 100);
    },
    //* 查找一集菜单
    queryAllOneMenuList() {
      // const _this = this
      getAllOneMenuList(this.ruleForm).then((res) => {
        // console.log(res.data);
        this.options2 = res.data;
      });
    },
    //*开启
    open(title, row) {
      this.reset();
      this.show = true;
      this.title = title;
      if (row) {
        this.menuForm = JSON.parse(JSON.stringify(row));
        // 显示状态
        if (this.menuForm.menuShow === 1) {
          this.menuShowFlag = true;
        } else {
          this.menuShowFlag = false;
        }
        // // 菜单级别状态
        if (this.menuForm.menuLevel == 0) {
          this.menuLevelFlag = 1;
        } else {
          this.menuLevelFlag = 2;
        }
      }
      //   this.resetForm(formName);
    },
    //*关闭
    close() {
      this.show = false;
    },
    //*关闭弹窗
    handleClose(done) {
      this.$confirm("系统可能不会保存您的更改", "确认关闭？")
        .then(() => {
          done();
          this.reset();
        })
        .catch(() => {});
    },
  },
};
</script>

<style scoped>
</style>