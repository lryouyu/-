<template>
    <el-page-header :icon="ArrowLeft" @back="goBack">
        <template #content>
            <span class="text-large font-600 mr-3">景区订单</span>
        </template>
    </el-page-header>
        <div v-show="true" class="transition-box" v-if="useTickets.length!= 0" style="padding-top: 20px">
            <el-card class="box-card" v-for="useTicket in useTickets">
                <template #header>
                    <div class="card-header">
                        <span>{{useTicket.username}}</span>
                        <el-button class="button" :id="useTicket.id" text @click="deleteBtn($event)" v-if="new Date(useTicket.useTime) > new Date()">退票</el-button>
                        <el-button class="button" :id="useTicket.id" text @click="deleteBtn($event)" v-if="new Date(useTicket.useTime) < new Date()">删除门票</el-button>

                    </div>
                </template>
                <el-descriptions title="门票信息">
                    <el-descriptions-item label="门票编号：">{{useTicket.id}}</el-descriptions-item><br>
                    <el-descriptions-item label="用户名">{{useTicket.username}}</el-descriptions-item>
                    <el-descriptions-item label=""></el-descriptions-item>
                    <el-descriptions-item label="使用时间">{{dateFormat(useTicket.useTime,'yyyy-MM-dd HH:mm:ss')}}</el-descriptions-item>
                    <el-descriptions-item label="购买时间">{{dateFormat(useTicket.time,'yyyy-MM-dd HH:mm:ss')}}</el-descriptions-item>
                    <el-descriptions-item label=""></el-descriptions-item>

                    <div v-for="ticket in tickets">
                        <el-descriptions-item label="开始时间~结束时间" v-if="ticket.id == useTicket.ticketId">
                            {{ticket.startTime}}~{{ticket.endTime}}
                        </el-descriptions-item>

                        <el-descriptions-item label="景点名称" v-if="ticket.id == useTicket.ticketId">
                            {{ticket.attName}}
                        </el-descriptions-item>
                        <el-descriptions-item label="价格" v-if="ticket.id == useTicket.ticketId">
                            {{ticket.cost}}
                        </el-descriptions-item>
                    </div>
                </el-descriptions>
            </el-card>
        </div>
    <div v-if="useTickets.length== 0">
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
    import {getUseTicketsByUserId, deleteUseTicket, getTickets, getUseTicketByTicketId} from "@/api/ticket";
    import {useStore} from 'vuex'
    import {dateFormat} from '@/utils/DateChange'

    const useTickets = ref([]);
    const tickets = ref();
    const store = useStore();
    const router = useRouter();
    const ticketUseTime = ref()

    onMounted(()=>{
        getUseTicketsByUserId({id:store.getters.getId}).then((response)=>{
            useTickets.value= response.data;
            console.log(useTickets)
        })
        getTickets().then((response)=>{
            tickets.value = response.data;
            console.log(tickets)
        })
    })


    const deleteBtn = (event) => {
        const ticketId = event.currentTarget.id;
        ticketUseTime.value = event.currentTarget.useTime;

        console.log(ticketUseTime.value)
        if (ticketUseTime > new Date()) {
            console.log("dayu");
        } else {
            console.log("xiaoyu")
        }
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
                deleteUseTicket({id:ticketId})
                getUseTicketsByUserId({id:store.getters.getId}).then((response)=>{
                    useTickets.value= response.data;
                    console.log(useTickets)
                })
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消删除',
                })
            });

    }
    const goBack = () => {
        router.back();
    };

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