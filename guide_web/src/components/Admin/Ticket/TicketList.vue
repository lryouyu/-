<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" v-for="ticket in tickets">
                <template #header>
                    <div class="card-header">
                        <span>{{ticket.attName}}</span>
                        <el-button class="button" :id="ticket.id" text @click="clickBtn($event)">更新门票信息</el-button>
                        <el-button class="button" :id="ticket.id" text @click="deleteBtn($event)">删除门票</el-button>
                    </div>
                </template>
                <el-descriptions title="门票信息">
                    <el-descriptions-item label="门票编号：">{{ticket.id}}</el-descriptions-item><br>
                    <el-descriptions-item label="景点名称">{{ticket.attName}}</el-descriptions-item>
                    <el-descriptions-item label="开始时间">{{ticket.startTime}}</el-descriptions-item>
                    <el-descriptions-item label="结束时间">{{ticket.endTime}}</el-descriptions-item>
                    <el-descriptions-item label="售价">{{ticket.cost}}</el-descriptions-item>
                    <el-descriptions-item label="总票数">{{ticket.count}}</el-descriptions-item>
                    <el-descriptions-item label=""><p></p></el-descriptions-item>
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
    import {deleteTicket, getTickets} from "@/api/ticket";

    const tickets = ref();
    const router = useRouter();

    onMounted(()=>{
        getTickets().then((response)=>{
            tickets.value= response.data;
            console.log(tickets)
        })
    })

    const clickBtn = (event) => {
        const ticketId = event.currentTarget.id;
        const params = {id: ticketId};
        router.push({name: 'updateTicket',params})
    };


    const deleteBtn = (event) => {
        const ticketId = event.currentTarget.id;
        ElMessageBox.confirm(
            '删除该门票，要继续吗',
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
                deleteTicket({id:ticketId})
                getTickets().then((response)=>{
                    tickets.value= response.data;
                    console.log(tickets)
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