<template>
    <el-row>
        <el-col :span="4">
            <div class="grid-content ep-bg-purple-light"><el-select v-model="value" class="m-2" placeholder="Select" size="large">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                />
            </el-select></div>
        </el-col>
        <el-col :span="16">
            <div class="grid-content ep-bg-purple">
                <el-input style="height: 40px" v-model="input" placeholder="Please input" />
            </div>
        </el-col>
        <el-col :span="4">
            <div class="grid-content ep-bg-purple-light"> <el-button style="width: 160px;height: 40px" type="primary" @click="searchBus">搜索</el-button></div>
        </el-col>
    </el-row>
    <el-row>

        <el-scrollbar height="600px">
            <div style="display: flex;flex-wrap: wrap;">
                <el-card class="box-card" style="width: 550px;margin:10px;" v-for="bus in buses">
                    <template #header>
                        <div class="card-header">
                            <span>{{bus.busName}}</span>
                        </div>
                        <el-popover placement="right" :width="400" trigger="hover">
                            <template #reference>
                                <el-button class="button" text style="margin-left: 400px">详细站点信息>></el-button>
                            </template>
                            <div>
                                <el-timeline class="horizontal-timeline">
                                    <el-timeline-item
                                            v-for="station in bus.stations"
                                    >
                                        {{ station}}
                                    </el-timeline-item>
                                </el-timeline>
                            </div>
                        </el-popover>
                    </template>
                    <div class="text item">运行时间：{{bus.startTime}}~{{bus.endTime}}</div>
                    <div class="text item">起始站：{{bus.start}}</div>
                    <div class="text item">终点站：{{bus.end}}</div>
                </el-card>
            </div>

        </el-scrollbar>
    </el-row>
    <el-backtop :right="100" :bottom="100" />

</template>

<script setup>
    import {ref, reactive, onMounted} from 'vue'
    import {getBuses,getBusByName,getBusByStation} from "@/api/bus";
    import { ElMessage } from 'element-plus'

    const buses = ref([]);
    const stations = ref([]);
    const value = ref("按名称搜索");
    const options = ref([
        {
            value: '按名称搜索',
            label: '按名称搜索',
        },{
            value: '按站点搜索',
            label: '按站点搜索',
        },])
    const input = ref('')

    onMounted(()=>{
        getBuses().then((response) => {
            buses.value = response.data;
            console.log(buses.value)
            for (let i =0; i<buses.value.length;i++) {
                stations.value = buses.value[i].pathway.split("-");
                buses.value[i].stations = stations.value;
                console.log(buses.value[i])
            }
        });
    })
    const searchBus = () => {
        if (input.value == null ||input.value==''){
            getBuses().then((response) => {
                buses.value = response.data;
                console.log(buses.value)
                for (let i =0; i<buses.value.length;i++) {
                    stations.value = buses.value[i].pathway.split("-");
                    buses.value[i].stations = stations.value;
                    console.log(buses.value[i])
                }
            });
        }
        else if (value.value=='按名称搜索'){
            getBusByName({busName: input.value}).then((response) => {
                if (response.data!=null) {
                    buses.value = response.data
                    console.log(buses.value)
                    for (let i =0; i<buses.value.length;i++) {
                        stations.value = buses.value[i].pathway.split("-");
                        buses.value[i].stations = stations.value;
                        console.log(buses.value[i])
                    }
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '抱歉，未查询到相关信息',
                        type: 'error',
                    })
                }
            });
        }
        else if (value.value=='按站点搜索'){
            getBusByStation({pathway: input.value}).then((response) => {
                if (response.data!=null) {
                    buses.value = response.data
                    console.log(buses.value)
                    for (let i =0; i<buses.value.length;i++) {
                        stations.value = buses.value[i].pathway.split("-");
                        buses.value[i].stations = stations.value;
                        console.log(buses.value[i])
                    }
                }
                else{
                    ElMessage({
                        showClose: true,
                        message: '抱歉，未查询到相关信息',
                        type: 'error',
                    })
                }
            });
        }
    };

</script>

<style scoped>
    .demonstration {
        color: var(--el-text-color-secondary);
    }

    .el-carousel__item h3 {
        color: #475669;
        opacity: 0.75;
        line-height: 150px;
        margin: 0;
        text-align: center;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n + 1) {
        background-color: #d3dce6;
    }

    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .box-card {
        width: 380px;
    }
    .time {
        font-size: 12px;
        color: #999;
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .button {
        padding: 0;
        min-height: auto;
    }

    .attraction-card {
        transition: transform 0.3s ease-in-out;
        box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
    }

    .attraction-card:hover {
        transform: translateY(-5px);
        box-shadow: 0px 6px 10px rgba(0, 0, 0, 0.2); /* 悬浮时的阴影效果 */
    }
    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .box-card {
        width: 480px;
    }
    .horizontal-timeline {
        flex-direction: row;
    }
</style>