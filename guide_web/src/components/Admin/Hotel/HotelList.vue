<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box" style="height: auto">
            <el-card class="box-card" v-for="hotel in hotels">
                <template #header>
                    <div class="card-header">
                        <span>{{hotel.hotelName}}</span>
                        <el-button class="button" :id="hotel.id" text @click="clickBtn($event)">更新酒店信息</el-button>
                        <el-button class="button" :id="hotel.id" text @click="deleteBtn($event)">删除酒店</el-button>
                    </div>
                </template>
                <el-descriptions
                        title="酒店信息"
                        direction="vertical"
                        :column="4"
                        border
                >
                    <el-descriptions-item label="酒店编号：">{{hotel.id}}</el-descriptions-item>
                    <el-descriptions-item label="酒店电话：">{{hotel.tel}}</el-descriptions-item>
                    <el-descriptions-item label="酒店评分：" :span="2">{{hotel.score}}</el-descriptions-item>
                    <el-descriptions-item label="酒店地址：">
                        {{hotel.address}}
                    </el-descriptions-item>
                    <el-descriptions-item label="酒店介绍"
                    >{{hotel.content}}
                    </el-descriptions-item>
                </el-descriptions>
<!--                <el-descriptions title="酒店信息">-->
<!--                    <el-row>-->
<!--                        <el-descriptions-item label="酒店编号：">{{hotel.id}}</el-descriptions-item><br>-->
<!--                    </el-row>-->
<!--                    <el-row>-->
<!--                        <el-descriptions-item label="酒店介绍："><p>{{hotel.content}}</p></el-descriptions-item>-->
<!--                    </el-row>-->

<!--                    <el-descriptions-item label="酒店地址：">{{hotel.address}}</el-descriptions-item>-->
<!--                    <el-descriptions-item label="酒店电话：">{{hotel.tel}}</el-descriptions-item>-->
<!--                    <el-descriptions-item label="酒店评分：">{{hotel.score}}</el-descriptions-item>-->
<!--                    <el-descriptions-item label=""><p></p></el-descriptions-item>-->
<!--                </el-descriptions>-->
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
    import {deleteHotel, getHotels} from "@/api/hotel";
    const hotels=ref();
    const router = useRouter();

    onMounted(()=>{
        getHotels().then((response)=>{
            hotels.value= response.data;
            console.log(hotels)
        })
    })

    const clickBtn = (event) => {
        const hotelId = event.currentTarget.id;
        const params = {id: hotelId};
        router.push({name: 'updateHotel',params})
    };


    const deleteBtn = (event) => {
        const hotelId = event.currentTarget.id;
        ElMessageBox.confirm(
            '删除该酒店，要继续吗',
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
                deleteHotel({id:hotelId})
                getHotels().then((response)=>{
                    hotels.value= response.data;
                    console.log(hotels)
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