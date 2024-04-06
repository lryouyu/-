<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" v-for="attraction in attractions">
                <template #header>
                    <div class="card-header">
                        <span>{{attraction.attName}}</span>
                        <el-button class="button" :id="attraction.id" text @click="clickBtn($event)">更新景点信息</el-button>
                        <el-button class="button" :id="attraction.id" text @click="deleteBtn($event)">删除景点</el-button>
                    </div>
                </template>
                <el-descriptions title="景点信息">
                    <el-descriptions-item label="景点编号：">{{attraction.id}}</el-descriptions-item><br>
                    <el-descriptions-item label="景点名称">{{attraction.attName}}</el-descriptions-item>
                    <div style="width: 20px;">
                        <el-descriptions-item label="景点星级">
                        <span v-for=" item1 in attraction.star">
                            <el-icon><StarFilled/></el-icon>
                        </span>
                        </el-descriptions-item>
                    </div>
                    <el-descriptions-item label="官方电话">{{attraction.tel}}</el-descriptions-item>
                    <el-descriptions-item label="景点地址">{{attraction.address}}</el-descriptions-item>
                    <el-descriptions-item label="开放时间">{{attraction.startTime}}~{{attraction.endTime}}</el-descriptions-item>
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
    import {deleteAttraction, getAttractions} from "@/api/attraction";
    const attractions=ref();
    const router = useRouter();

    onMounted(()=>{
        getAttractions().then((response)=>{
            attractions.value= response.data;
            console.log(attractions)
        })
    })

    const clickBtn = (event) => {
        const attId = event.currentTarget.id;
        const params = {id: attId};
        router.push({name: 'updateAttraction',params})
    };

    const deleteBtn = (event) => {
        const attId = event.currentTarget.id;
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
                deleteAttraction({id:attId})
                getAttractions().then((response)=>{
                    attractions.value= response.data;
                    console.log(attractions)
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