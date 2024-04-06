<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" >
                <el-page-header @back="goBack">
                </el-page-header>
                <el-form ref="ruleFormRef" :model="travel" :rules="rules" style="height: 500px">
                    <!--表单数据：景点文本数据-->
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-form-item label="" prop="" >
                    </el-form-item>
                    <el-form-item label="" prop="" >
                    </el-form-item>
                    <el-form-item label="" prop="" >
                    </el-form-item>

                    <el-form-item label="游记标题" prop="title" >
                        {{travel.title}}
                    </el-form-item>
                    <el-form-item label="游记类别" prop="title" >
                    {{travel.type}}
                    </el-form-item>

                    <el-form-item label="游记简介" prop="introduce">
                        {{travel.introduce}}
                    </el-form-item>
                    <el-form-item label="游记内容" prop="content" >
                        {{travel.content}}
                    </el-form-item>
                    <el-form-item label="游记封面" prop="content" >
                        <img v-if="travel.imgPath" :src="'/'+travel.imgPath" class="avatar" />
                    </el-form-item>


                </el-form>
            </el-card>
            <el-backtop :right="100" :bottom="100" />
        </div>

    </transition>

</template>

<script setup>
    import {useRoute, useRouter} from "vue-router";
    import {onMounted, ref} from "vue";
    import {getTravelById,getTravelComment,deleteTravelComment} from "@/api/travel";
    import { useDateFormat } from '@vueuse/core'
    import {ElMessage, ElMessageBox} from "element-plus";



    const route = useRoute();
    const router = useRouter();
    let travel = ref({});
    let comments = ref();
    onMounted(()=>{
        //获取攻略
        getTravelById({id: route.params.id}).then((response)=>{
            travel.value = response.data;
            console.log(travel.value)
        })
    })

    const deleteCommentBtn = (event) => {
        const commentId = event.currentTarget.id;
        ElMessageBox.confirm(
            '删除该评论，此操作不可撤销，要继续吗',
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
                console.log("zhin")
                deleteTravelComment({id:commentId})
                location.reload();
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消删除',
                })
            })
    };
    const goBack = ()=>{
        router.push({name:'travel'})
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
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>