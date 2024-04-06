<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" v-for="food in foods">
                <template #header>
                    <div class="card-header">
                        <span>{{food.foodName}}</span>
                        <el-button class="button" :id="food.id" text @click="clickBtn($event)">更新美食信息</el-button>
                        <el-button class="button" :id="food.id" text @click="deleteBtn($event)">删除美食</el-button>
                    </div>
                </template>
                <el-descriptions title="美食特产信息">
                    <el-descriptions-item label="美食特产编号：">{{food.id}}</el-descriptions-item><br>
                    <el-descriptions-item label="美食特产名称" >{{food.foodName}}</el-descriptions-item>
                    <el-descriptions-item label="所属景点名称">{{food.attName}}</el-descriptions-item>
                    <el-descriptions-item label="美食特产介绍" :span="3"></el-descriptions-item>
                    <el-descriptions-item label="" >{{food.content}}</el-descriptions-item>
                </el-descriptions>
            </el-card>
        </div>
    </transition>
</template>

<script setup>
    import { ref,onMounted,reactive,computed} from 'vue'
    import {useRouter} from 'vue-router'
    import {ElMessage, ElMessageBox} from "element-plus";
    import {
        StarFilled
    } from '@element-plus/icons-vue'
    import {deleteFood, getFoods} from "@/api/food";
    const foods = ref();
    const router = useRouter();

    onMounted(()=>{
        getFoods().then((response)=>{
            foods.value= response.data;
            console.log(foods)
        })
    })

    const clickBtn = (event) => {
        const foodId = event.currentTarget.id;
        const params = {id: foodId};
        router.push({name: 'updateFood',params})
    };


    const deleteBtn = (event) => {
        const foodId = event.currentTarget.id;
        ElMessageBox.confirm(
            '删除该美食特产，要继续吗',
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
                deleteFood({id:foodId})
                getFoods().then((response)=>{
                    foods.value= response.data;
                    console.log(foods)
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