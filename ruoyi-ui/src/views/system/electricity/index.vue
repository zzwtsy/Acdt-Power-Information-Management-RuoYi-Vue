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
      <el-form-item label="宿舍号" prop="dormitoryId">
        <el-input
          v-model="queryParams.dormitoryId"
          placeholder="请输入宿舍号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="照明电费" prop="lightingElectricityUsage">
        <el-input
          v-model="queryParams.lightingElectricityUsage"
          placeholder="请输入照明电费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="空调电费" prop="airElectricityUsage">
        <el-input
          v-model="queryParams.airElectricityUsage"
          placeholder="请输入空调电费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用电日期" prop="date">
        <el-input
          v-model="queryParams.date"
          placeholder="请输入用电日期"
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
          v-hasPermi="['system:electricity:add']"
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
          v-hasPermi="['system:electricity:edit']"
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
          v-hasPermi="['system:electricity:remove']"
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
          v-hasPermi="['system:electricity:export']"
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
      :data="electricityList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键 Id" align="center" prop="id" />
      <el-table-column label="宿舍号" align="center" prop="dormitoryId" />
      <el-table-column
        label="照明电费"
        align="center"
        prop="lightingElectricityUsage"
      />
      <el-table-column
        label="空调电费"
        align="center"
        prop="airElectricityUsage"
      />
      <el-table-column label="用电日期" align="center" prop="date" />
      <el-table-column label="备注" align="center" prop="remark" />
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
            v-hasPermi="['system:electricity:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:electricity:remove']"
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

    <!-- 添加或修改宿舍电费记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="宿舍号" prop="dormitoryId">
          <el-input v-model="form.dormitoryId" placeholder="请输入宿舍号" />
        </el-form-item>
        <el-form-item label="照明电费" prop="lightingElectricityUsage">
          <el-input
            v-model="form.lightingElectricityUsage"
            placeholder="请输入照明电费"
          />
        </el-form-item>
        <el-form-item label="空调电费" prop="airElectricityUsage">
          <el-input
            v-model="form.airElectricityUsage"
            placeholder="请输入空调电费"
          />
        </el-form-item>
        <el-form-item label="用电日期" prop="date">
          <el-input v-model="form.date" placeholder="请输入用电日期" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input
            v-model="form.remark"
            type="textarea"
            placeholder="请输入内容"
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
  listElectricity,
  getElectricity,
  delElectricity,
  addElectricity,
  updateElectricity,
} from "@/api/system/electricity";

export default {
  name: "Electricity",
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
      // 宿舍电费记录表格数据
      electricityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dormitoryId: null,
        lightingElectricityUsage: null,
        airElectricityUsage: null,
        date: null,
      },
      // 表单参数
      form: {
        dormitoryId: Number,
        lightingElectricityUsage: Number,
        airElectricityUsage: Number,
        date: String,
        remark: String,
      },
      // 表单校验
      rules: {
        dormitoryId: [
          { required: true, message: "宿舍号不能为空", trigger: "blur" },
        ],
        lightingElectricityUsage: [
          { required: true, message: "照明电费不能为空", trigger: "blur" },
        ],
        airElectricityUsage: [
          { required: true, message: "空调电费不能为空", trigger: "blur" },
        ],
        date: [
          { required: true, message: "用电日期不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询宿舍电费记录列表 */
    getList() {
      this.loading = true;
      listElectricity(this.queryParams).then((response) => {
        this.electricityList = response.rows;
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
        id: null,
        dormitoryId: null,
        lightingElectricityUsage: null,
        airElectricityUsage: null,
        date: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加宿舍电费记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getElectricity(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改宿舍电费记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            this.form.updateBy = this.$store.state.user.name;
            updateElectricity(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.createBy = this.$store.state.user.name;
            addElectricity(this.form).then((response) => {
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
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除宿舍电费记录编号为"' + ids + '"的数据项？')
        .then(function () {
          return delElectricity(ids);
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
        "api/electricity/export",
        {
          ...this.queryParams,
        },
        `electricity_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
