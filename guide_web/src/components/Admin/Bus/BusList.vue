<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" v-for="bus in buses">
                <template #header>
                    <div class="card-header">
                        <span>{{bus.busName}}</span>
                        <el-button class="button" :id="bus.id" text @click="clickBtn($event)">更新公交信息</el-button>
                        <el-button class="button" :id="bus.id" text @click="deleteBtn($event)">删除公交</el-button>
                    </div>
                </template>
                <el-descriptions title="公交信息">
                    <el-descriptions-item label="公交编号：">{{bus.id}}</el-descriptions-item><br>
                    <el-descriptions-item label="公交名称">{{bus.busName}}</el-descriptions-item>
                    <el-descriptions-item label="起始站">{{bus.start}}</el-descriptions-item>
                    <el-descriptions-item label="终点站">{{bus.end}}</el-descriptions-item>
                    <el-descriptions-item label="发车时间">{{bus.startTime}}</el-descriptions-item>
                    <el-descriptions-item label="末班时间">{{bus.endTime}}</el-descriptions-item>
                    <el-descriptions-item label="途径站"><span style="width: 200px;">{{bus.pathway}}</span></el-descriptions-item>
                    <el-descriptions-item label=""><p></p></el-descriptions-item>
                </el-descriptions>
            </el-card>
        </div>
    </transition>
</template>

<script setup>
    import { ref,onMounted,reactive,computed,} from 'vue'
    import {useRouter,} from 'vue-router'
    import {ElMessage, ElMessageBox} from "element-plus";
    import {
        StarFilled
    } from '@element-plus/icons-vue'
    import { useDateFormat } from '@vueuse/core'
    import {deleteBus, getBuses} from "@/api/bus";

    const buses=ref();
    const router = useRouter();

    onMounted(()=>{
        getBuses().then((response)=>{
            buses.value= response.data;
            console.log(buses)
        })
    })
    router.beforeEach((to, from, next) => {
        // 在这里根据需要更新组件的数据
        getBuses().then((response) => {
            buses.value = response.data;
            console.log(buses)
        })// 例如，调用一个方法来更新数
        // 调用 next() 继续路由的导航过程
        next();
    });


    const clickBtn = (event) => {
        const busId = event.currentTarget.id;
        const params = {id: busId};
        router.push({name: 'updateBus',params})
    };


    const deleteBtn = (event) => {
        const busId = event.currentTarget.id;
        ElMessageBox.confirm(
            '删除该公交，要继续吗',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(() => {
                ElMessage({
                    type: 'success',
                    message: '删除成功',
                })
                console.log("执行到这")
                deleteBus({id:busId})
                getBuses().then((response)=>{
                    buses.value= response.data;
                    console.log(buses)
                })
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消删除',
                })
            })

    }
</script>

<style scoped>
    .transition-box {
        background-color: white;
        height: 600px;
        border-radius: 10px;
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
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>