<template>
    <el-page-header :icon="ArrowLeft" @back="goBack">
        <template #content>
            <span class="text-large font-600 mr-3">个人游记</span>
        </template>
    </el-page-header>
        <el-row style="padding-top: 20px">
            <el-col
                    v-for="travel in travels"
                    :key="travel.id"
                    :span="8"
                    :offset="index > 0 ? 2 : 0"
            >
                <el-card :body-style="{ padding: '0px' }" class="attraction-card">
                    <img
                            :src="'/'+travel.imgPath"
                            style="width: 100%;display: block;height:300px"
                            fit="fill"
                    />
                    <div style="padding: 14px;height: 180px">
                        <span style="text-align: justify">{{travel.title}}</span>
                        <p style="text-align: justify">类别：&nbsp{{travel.type}}</p>
                        <p style="text-align: justify;color: #666;font-size: 14px;line-height: 1.8;width:350px;">简介：{{travel.introduce}}</p>
                        <div style="margin-left: 250px">
                            <el-button text class="button" :id="travel.id" @click="deleteBtn($event)">删除游记</el-button>
                            <el-popover placement="right" :width="400" trigger="hover">
                                <template #reference>
                                    <el-button text class="button">详细内容>></el-button>
                                </template>
                                <div>
                                    <p style="color: #666;font-size: 14px;line-height: 2.8;width:350px;margin-left: 20px">{{ travel.content }}</p>
                                </div>
                            </el-popover>
                        </div>
                    </div>
                </el-card>

            </el-col>
            <div v-if="travels.length==0">
                <p>暂无游记，快去发布一篇吧</p>
            </div>
        </el-row>
    <el-backtop :right="100" :bottom="100" />
</template>

<script setup>
    import { ref,onMounted,reactive,computed} from 'vue'
    import {useRouter} from 'vue-router'
    import {ElMessage, ElMessageBox} from "element-plus";
    import {
        StarFilled
    } from '@element-plus/icons-vue'
    import { useDateFormat } from '@vueuse/core'
    import {useStore} from 'vuex'
    import {dateFormat} from "@/utils/DateChange";
    import {getTravelByUserId,deleteTravel} from "@/api/travel";

    const router = useRouter();
    const store = useStore();
    const travels =ref([])

    onMounted(()=>{
        getTravelByUserId({userId: store.getters.getId}).then((response) => {
            travels.value = response.data;
        });
    })
    const goBack = () => {
        router.back();
    };

    const deleteBtn = (event) => {
        const travelId = event.currentTarget.id;
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
                console.log(store.getters.getId)
                deleteTravel({id:travelId})
                getTravelByUserId({userId:store.getters.getId}).then((response)=>{
                    travels.value = response.data;
                    console.log(travels)
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
    .scrollbar-demo-item {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 50px;
        margin: 10px;
        text-align: center;
        border-radius: 4px;
        background: var(--el-color-primary-light-9);
        color: var(--el-color-primary);
    }
</style>