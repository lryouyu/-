<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" v-for="travel in travels">
                <template #header>
                    <div class="card-header">
                        <span>{{travel.title}}</span>
                        <el-button class="button" :id="travel.id" text @click="clickBtn($event)">查看游记</el-button>
                        <el-button class="button" :id="travel.id" text @click="deleteBtn($event)">删除游记</el-button>
                    </div>
                </template>
                <el-descriptions title="游记信息">
                    <el-descriptions-item label="游记编号：">{{travel.id}}</el-descriptions-item><br>
                    <el-descriptions-item label="游记标题">{{travel.title}}</el-descriptions-item>
                    <el-descriptions-item label="游记类别">{{travel.type}}</el-descriptions-item>
                    <el-descriptions-item label="游记简介">{{travel.introduce}}</el-descriptions-item>
                    <el-descriptions-item label="更新时间">{{dateFormat(travel.time,'yyyy-MM-dd HH:mm:ss')}}&nbsp&nbsp&nbsp&nbsp&nbsp</el-descriptions-item>
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
    import {deleteTravel, getTravels} from "@/api/travel";
    import {dateFormat} from "@/utils/DateChange";

    const router = useRouter();
    let travels = ref();

    onMounted(()=>{
        getTravels().then((response)=>{
            travels.value = response.data;
            console.log(travels)
        })
    })

    const clickBtn = (event) => {
        const strId = event.currentTarget.id;
        const params = {id: strId};
        console.log(params.id);
        router.push({name: 'updateTravel',params})
    };

    const deleteBtn = (event) => {
        const traId = event.currentTarget.id;
        ElMessageBox.confirm(
            '删除该游记，要继续吗',
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
                deleteTravel({id:traId})
                getTravels().then((response)=>{
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
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed var(--el-border-color);
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        transition: var(--el-transition-duration-fast);
    }

    .avatar-uploader .el-upload:hover {
        border-color: var(--el-color-primary);
    }
</style>