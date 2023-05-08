<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="学校电费信息网站 cookie" prop="cookie">
        <el-input
          v-model="queryParams.cookie"
          placeholder="请输入学校电费信息网站 cookie"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宿舍门牌号列表" prop="dormIds">
        <el-input
          v-model="queryParams.dormIds"
          placeholder="请输入宿舍门牌号列表"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信 openid" prop="wechatOpenid">
        <el-input
          v-model="queryParams.wechatOpenid"
          placeholder="请输入微信 openid"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['electric:ElectricConfig:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['electric:ElectricConfig:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['electric:ElectricConfig:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['electric:ElectricConfig:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="ElectricConfigList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键 id" align="center" prop="id" />
      <el-table-column
        label="学校电费信息网站 cookie"
        align="center"
        prop="cookie"
      />
      <el-table-column label="宿舍门牌号列表" align="center" prop="dormIds" />
      <el-table-column label="微信 openid" align="center" prop="wechatOpenid" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['electric:ElectricConfig:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['electric:ElectricConfig:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改宿舍电费信息的相关配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="主键 id" prop="cookie">
          <el-input v-model="form.id" placeholder="请输入主键 id" />
        </el-form-item>
        <el-form-item label="学校电费信息网站 cookie" prop="cookie">
          <el-input
            v-model="form.cookie"
            placeholder="请输入学校电费信息网站 cookie"
          />
        </el-form-item>
        <el-form-item label="宿舍门牌号列表" prop="dormIds">
          <el-input v-model="form.dormIds" placeholder="请输入宿舍门牌号列表" />
        </el-form-item>
        <el-form-item label="微信 openid" prop="wechatOpenid">
          <el-input
            v-model="form.wechatOpenid"
            placeholder="请输入微信 openid"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listElectricConfig,
  getElectricConfig,
  delElectricConfig,
  addElectricConfig,
  updateElectricConfig,
} from "@/api/electric/ElectricConfig";

export default {
  name: "ElectricConfig",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 宿舍电费信息的相关配置表格数据
      ElectricConfigList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cookie: null,
        dormIds: null,
        wechatOpenid: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        cookie: [
          {
            required: true,
            message: "学校电费信息网站 cookie不能为空",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询宿舍电费信息的相关配置列表 */
    getList() {
      this.loading = true;
      listElectricConfig().then((response) => {
        this.ElectricConfigList = response.rows;
        const tempThis = this;
        const tempTotal = response.total;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        cookie: null,
        dormIds: null,
        wechatOpenid: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.cookie);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加宿舍电费信息的相关配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const tempRow = row;
      this.reset();
      const id = row.id || this.ids;
      getElectricConfig(id).then((response) => {
        const tempData = response.data;
        this.form = response.data;
        this.open = true;
        this.title = "修改宿舍电费信息的相关配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            this.form.updateBy = this.$store.state.user.name;
            updateElectricConfig(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addElectricConfig(this.form).then((response) => {
              this.form.createBy = this.$store.state.user.name;
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id || this.ids;
      this.$modal
        .confirm(
          '是否确认删除宿舍电费信息的相关配置编号为"' + id + '"的数据项？'
        )
        .then(function () {
          return delElectricConfig(id);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "electric/ElectricConfig/export",
        {
          ...this.queryParams,
        },
        `ElectricConfig_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
