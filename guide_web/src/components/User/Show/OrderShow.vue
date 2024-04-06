<template>
    <el-page-header :icon="ArrowLeft" @back="goBack">
        <template #content>
            <span class="text-large font-600 mr-3">酒店订单</span>
        </template>
    </el-page-header>
        <div v-show="true" class="transition-box" v-if="orders.length!= 0" style="padding-top: 20px">
            <el-card class="box-card" v-for="order in orders">
                <template #header>
                    <div class="card-header">
                        <span>{{order.username}}</span>
                        <el-button class="button" :id="order.id" text @click="deleteBtn($event)" v-if="new Date(order.inTime)>new Date()">取消订单</el-button>
                        <el-button class="button" :id="order.id" text @click="deleteBtn($event)" v-if="new Date(order.inTime)<new Date()">删除订单</el-button>
                    </div>
                </template>
                <el-descriptions title="订单信息">
                    <el-descriptions-item label="订单编号：">{{order.id}}</el-descriptions-item><br>
                    <el-descriptions-item label="用户名">{{order.username}}</el-descriptions-item>
                    <el-descriptions-item label=""></el-descriptions-item>
                    <el-descriptions-item label="入住时间">{{order.inTime}}</el-descriptions-item>
                    <el-descriptions-item label="离开时间">{{order.outTime}}</el-descriptions-item>
                    <el-descriptions-item label="酒店名称">{{order.hotelName}}</el-descriptions-item>
                    <el-descriptions-item label="房型名称">{{order.houseName}}</el-descriptions-item>
                    <el-descriptions-item label="花费金额">{{order.cost}}</el-descriptions-item>
                    <el-descriptions-item label="购买时间">{{dateFormat(order.time,"yyyy-MM-dd HH:mm:ss")}}</el-descriptions-item>
                </el-descriptions>
            </el-card>
        </div>
    <div v-if="orders.length== 0">
        <p>暂无相关订单</p>
    </div>
</template>

<script setup>
    import { ref,onMounted,reactive,computed} from 'vue'
    import {useRouter} from 'vue-router'
    import {ElMessage, ElMessageBox} from "element-plus";
    import {
        StarFilled
    } from '@element-plus/icons-vue'
    import { useDateFormat } from '@vueuse/core'
    import {getOrders,deleteOrder,getOrdersByUserId} from "@/api/hotel";
    import {useStore} from 'vuex'
    import {dateFormat} from "@/utils/DateChange";

    const orders = ref([]);
    const router = useRouter();
    const store = useStore();

    onMounted(()=>{
        getOrdersByUserId({id:store.getters.getId}).then((response)=>{
            orders.value = response.data;
            console.log(orders)
        })
    })
    const goBack = () => {
        router.back();
    };


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
                getOrdersByUserId({id:store.getters.getId}).then((response)=>{
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