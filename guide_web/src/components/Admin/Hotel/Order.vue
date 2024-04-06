<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" v-for="order in orders">
                <template #header>
                    <div class="card-header">
                        <span>{{order.username}}</span>
                        <el-button class="button" :id="order.id" text @click="deleteBtn($event)">删除订单</el-button>
                    </div>
                </template>
                <el-descriptions title="订单信息">
                    <el-descriptions-item label="订单编号：">{{order.id}}</el-descriptions-item><br>
                    <el-descriptions-item label="用户名">{{order.username}}</el-descriptions-item>
                    <el-descriptions-item label="用户编号">{{order.userId}}</el-descriptions-item>
                    <el-descriptions-item label="入住时间">{{order.inTime}}</el-descriptions-item>
                    <el-descriptions-item label="离开时间">{{order.outTime}}</el-descriptions-item>
                    <el-descriptions-item label="酒店名称">{{order.hotelName}}</el-descriptions-item>
                    <el-descriptions-item label="房型名称">{{order.houseName}}</el-descriptions-item>
                    <el-descriptions-item label="花费金额">{{order.cost}}</el-descriptions-item>
                    <el-descriptions-item label="购买时间">{{useDateFormat(order.time,"YYYY-MM-DD HH:mm:ss")}}</el-descriptions-item>
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
    import { useDateFormat } from '@vueuse/core'
    import {getOrders,deleteOrder} from "@/api/hotel";

    const orders = ref();
    const router = useRouter();

    onMounted(()=>{
        getOrders().then((response)=>{
            orders.value = response.data;
            console.log(orders)
        })
    })


    const deleteBtn = (event) => {
        const orderId = event.currentTarget.id;
        ElMessageBox.confirm(
            '删除该订单，要继续吗',
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
                deleteOrder({id:orderId})
                getOrders().then((response)=>{
                    orders.value = response.data;
                    console.log(orders)
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