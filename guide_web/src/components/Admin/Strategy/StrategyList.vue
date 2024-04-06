<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" v-for="strategy in strategies">
                <template #header>
                    <div class="card-header">
                        <span>{{strategy.title}}</span>
                        <el-button class="button" :id="strategy.id" text @click="clickBtn($event)">更新攻略</el-button>
                        <el-button class="button" :id="strategy.id" text @click="deleteBtn($event)">删除攻略</el-button>
                    </div>
                </template>
                <el-descriptions title="攻略信息">
                    <el-descriptions-item label="攻略编号：">{{strategy.id}}</el-descriptions-item><br>
                    <el-descriptions-item label="攻略标题">{{strategy.title}}</el-descriptions-item>
                    <el-descriptions-item label="攻略类别">{{strategy.type}}</el-descriptions-item>
                    <el-descriptions-item label="攻略简介">{{strategy.introduce}}</el-descriptions-item>
                    <el-descriptions-item label="更新时间">{{useDateFormat(strategy.time,'YYYY-MM-DD HH:mm:ss')}}&nbsp&nbsp&nbsp&nbsp&nbsp</el-descriptions-item>
                    <el-descriptions-item label="点赞数">{{strategy.like}}</el-descriptions-item>
                    <el-descriptions-item label=""><p></p></el-descriptions-item>
                </el-descriptions>
            </el-card>
        </div>
    </transition>

</template>

<script setup>
    import {ref,reactive,onMounted} from 'vue'
    import {deleteStratety, getStrategy} from "@/api/strategy";
    import { useDateFormat } from '@vueuse/core'
    import {useRouter} from 'vue-router'
    import {ElMessage, ElMessageBox} from "element-plus";
    import {deleteAttraction} from "@/api/attraction";
    const router = useRouter();
    let strategies = ref();

    onMounted(()=>{
        getStrategy().then((response)=>{
            strategies.value = response.data;
            console.log(strategies)
        })
    })

    const clickBtn = (event) => {
        const strId = event.currentTarget.id;
        const params = {id: strId};
        console.log(params.id);
        router.push({name: 'updateStrategy',params})
    };

    const deleteBtn = (event) => {
        const strId = event.currentTarget.id;
        ElMessageBox.confirm(
            '删除该景点，要继续吗',
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
                deleteStratety({id:strId})
                getStrategy().then((response)=>{
                    strategies.value = response.data;
                    console.log(strategies)
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