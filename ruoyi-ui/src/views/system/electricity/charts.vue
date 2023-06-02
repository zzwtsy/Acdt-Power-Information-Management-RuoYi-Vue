<template>
    <div>
        <el-row>
            <el-col>
                <div class="block">
                    <el-date-picker v-model="date" type="daterange" align="right" unlink-panels range-separator="至"
                        start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd"
                        :picker-options="pickerOptions">
                    </el-date-picker>
                    <el-select v-model="dormId" placeholder="请选择" style="margin-left: 10px;">
                        <el-option v-for="item in dormIds" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                    <el-button type="primary" icon="el-icon-search" @click="handleQuery()"
                        style="margin-left: 10px ;">确定</el-button>
                </div>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="24" class="card-box">
                <div class="el-table el-table--enable-row-hover el-table--medium">
                    <div ref="electricityusage" style="width: 100%; height: 420px" />
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script lang="js">
import { getElectricityListByDate, getQueryDormIds } from "@/api/system/electricity";
import * as echarts from "echarts";

export default {
    name: "BarCharts",
    data() {
        return {
            electricityusage: null,
            start: "2022-01-01",
            end: "2022-01-07",
            pickerOptions: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                        picker.$emit('pick', [start, end]);
                    }
                }]
            },
            date: [],
            dormIds: [],
            dormId: '',
        }
    },
    created() {
        this.getQueryDormIds();
    },
    methods: {
        getQueryDormIds() {
            getQueryDormIds().then((response) => {
                for (let i = 0; i < response.length; i++) {
                    this.dormIds.push({
                        value: response[i],
                        label: response[i]
                    })
                }
            })
        },
        handleQuery() {
            if (this.date.length == 0) {
                alert("请选择日期");
                return;
            }
            if (this.dormId == '') {
                alert("请选择宿舍");
                return;
            }
            this.$modal.loading("正在加载数据，请稍候！");
            getElectricityListByDate(this.date[0], this.date[1], this.dormId).then((response) => {
                this.$modal.closeLoading();
                if (response.data.date.length == 0) {
                    alert("所选日期暂无数据");
                    return;
                }
                this.electricityusage = echarts.init(this.$refs.electricityusage, "macarons");
                this.electricityusage.setOption({
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {},
                    xAxis: [
                        {
                            type: "category",
                            data: response.data.date
                        }
                    ],
                    yAxis:
                    {
                        type: "value",
                        name: "用电量",
                        position: "left",
                        axisLabel: {
                            formatter: "{value} 度"
                        }
                    }
                    ,
                    series: [
                        {
                            name: "空调用电量",
                            type: "bar",
                            data: response.data.airElectricityUsageData
                        }, {
                            name: "照明用电量",
                            type: "bar",
                            data: response.data.lightingElectricityUsageData
                        }
                    ]
                })
            })
        }
    }
}
</script>

<style scoped>
.card-box {
    margin-top: 5px;
    margin-bottom: 5px;
    border: 1px solid #DCDFE6;
    border-radius: 3px;
}

.el-table::before {
    height: 0;
}
</style>