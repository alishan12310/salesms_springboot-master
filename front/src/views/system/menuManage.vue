<template>
  <el-card class="cardClass">
    <div slot="header" class="cardClass_header">
      <h3>菜单设置</h3>
      <div>
        <el-button size="mini" type="success" icon="el-icon-plus" @click="open('新增菜单')">新增</el-button>
      </div>
    </div>
    <el-table :data="arr" style="width: 100%; margin-bottom: 20px" size="small" row-key="menuId" border default-expand-all
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
      <el-table-column prop="menuTitle" label="名称" width="180" align="left">
      </el-table-column>
      <el-table-column label="图标" width="80" align="center">
        <template slot-scope="scope">
          <i :class="scope.row.menuIcon"></i>
        </template>
      </el-table-column>
      <el-table-column prop="menuIndex" label="路由路径" align="center" width="250">
      </el-table-column>
      <el-table-column prop="menuPath" label="完整路径" align="center">
      </el-table-column>
      <el-table-column type="index" label="排序" align="center" width="100">
      </el-table-column>
      <el-table-column prop="menuShow" label="权限" align="center" width="100">
        <template slot-scope="scope">
          <el-tag size="small" :type="scope.row.menuRights === 1 ? 'success' : ''" disable-transitions>{{
            scope.row.menuRights == 0 ? "管理员" : "销售员" }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="menuShow" label="显示状态" align="center" width="100">
        <template slot-scope="scope">
          <el-tag size="small" :type="scope.row.menuShow === 1 ? 'success' : 'danger'" disable-transitions>{{
            scope.row.menuShow == 0 ? "隐藏" : "显示" }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
          <el-button type="warning" size="mini" icon="el-icon-edit" @click="open('修改菜单信息', scope.row)">修改</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="delMenu(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <addMenu ref="addMenuRef" :serch="search"></addMenu>
  </el-card>
</template>

<script>
import { queryMenu, deleteMenu } from "@/apis/system/menuManage.js";
import addMenu from "@/components/system/addMenu";

export default {
  name: "menuManage",
  components: {
    addMenu,
  },
  data() {
    return {
      arr: [],
    };
  },
  created() {
    this.search();
  },
  methods: {
    //请求
    search() {
      // this.arr = res.data
      queryMenu(this.$store.state.nowUser.userIdentity)
        .then((res) => {
          this.arr = res.data;   
        })
        .catch((err) => { });
    },
    //打开弹窗
    open(title, row) {
      const _this = this;
      _this.show = true;
      this.$refs["addMenuRef"].open(title, row);
      console.log(row);
    },
    //删除功能
    delMenu(row) {
      const _this = this;
      _this.$confirm('确认删除菜单“' + row.menuTitle + '”吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteMenu(row.menuId).then(function (res) {
          if (res.code === 0) {
            _this.$message({
              type: 'success',
              message: '菜单“' + row.menuTitle + "”" + res.msg,
            })
            _this.search();
            _this.$router.go(0)
          } else {
            _this.$router.go(0)
            _this.$message({
              type: 'warning',
              message: '菜单“' + row.menuTitle + "”" + res.msg,
            })
          }
        })
      }).catch(() => { })
    }
  },
};
</script>

<style></style>