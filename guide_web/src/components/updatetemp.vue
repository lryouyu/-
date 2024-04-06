<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card">
                <el-page-header @back="goBack">
                </el-page-header>
                <el-form ref="ruleFormRef" :model="bus" :rules="rules" style="height: 500px">
                    <!--表单数据：景点文本数据-->
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-form-item label="" prop="">
                    </el-form-item>
                    <el-form-item label="" prop="">
                    </el-form-item>
                    <el-form-item label="" prop="">
                    </el-form-item>

                    <el-form-item label="游记标题" prop="title">
                        {{travel.title}}
                    </el-form-item>
                    <el-form-item label="游记类别" prop="title">
                        {{travel.type}}
                    </el-form-item>

                    <el-form-item label="游记简介" prop="introduce">
                        {{travel.introduce}}
                    </el-form-item>
                    <el-form-item label="游记内容" prop="content">
                        {{travel.content}}
                    </el-form-item>
                    <el-form-item>
                        <el-button style="margin-top: 12px; margin-left: 200px;" @click="submitForm">确认</el-button>
                        <el-button style="margin-top: 12px; margin-left: 200px;" @click="cancel">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-card>
        </div>
    </transition>
</template>

<script setup>
    import {useRoute, useRouter} from "vue-router";
    import {onMounted, ref} from "vue";
    import {ElMessage, ElMessageBox} from "element-plus";
    import {updateStrategy} from "@/api/strategy";

    const route = useRoute();
    const router = useRouter();
    let travel = ref({});
    let comments = ref();
    onMounted(() => {
        //获取攻略
        getTravelById({id: route.params.id}).then((response) => {
            travel.value = response.data;
            console.log(travel.value)
        })
        //获取评论
        getTravelComment({id: route.params.id}).then((response) => {
            comments.value = response.data;
            console.log(comments.value)
        })
    })

    const submitForm = () => {
        ElMessageBox.confirm(
            '更新该攻略的信息，要继续吗',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(() => {
                console.log(strategy.value)
                strategy.value.time=null;
                updateStrategy(strategy.value);
                router.push({name: 'strategy'});
                ElMessage({
                    type: 'success',
                    message: '更新完成',
                })

            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消更新',
                })
            })

    };
    const cancel = () =>{
        router.push({name: 'strategy'});
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