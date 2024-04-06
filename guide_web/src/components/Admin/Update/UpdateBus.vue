<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card">
                <el-page-header @back="goBack">
                </el-page-header>
                <el-form ref="ruleFormRef" :model="bus" :rules="rules" style="height: 500px">
                    <el-form-item label="" prop="" >
                    </el-form-item>
                    <el-form-item label="" prop="" >
                    </el-form-item>
                    <el-form-item label="" prop="" >
                    </el-form-item>
                    <el-form-item label="公交名称" prop="busName" >
                        <el-input v-model="bus.busName" style="width:350px;"></el-input>
                    </el-form-item>

                    <el-form-item label="始发站 " prop="start">
                        &nbsp&nbsp&nbsp<el-input v-model="bus.start" style="width:350px;" ></el-input>
                    </el-form-item>
                    <el-form-item label="终点站 " prop="end">
                        &nbsp&nbsp&nbsp<el-input v-model="bus.end" style="width:350px;" ></el-input>
                    </el-form-item>
                    <el-form-item label="运行时间">
                        <el-time-select
                                v-model="bus.startTime"
                                start="00:30"
                                step="00:30"
                                end="23:30"
                                placeholder="起始时间"
                        />
                        &nbsp&nbsp&nbsp&nbsp&nbsp<el-time-select
                            v-model="bus.endTime"
                            start="00:30"
                            step="00:30"
                            end="23:30"
                            placeholder="末班时间"
                    />
                    </el-form-item>
                    <el-form-item label="途径站 " prop="pathway" >
                        &nbsp&nbsp&nbsp<el-input type="textarea" v-model="bus.pathway"  style="width:350px;" autosize></el-input>
                    </el-form-item>
                    <el-form-item label="" prop="" >
                    </el-form-item>
                    <el-form-item label="" prop="" >
                    </el-form-item>
                    <el-form-item label="" prop="" >
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
    import {onMounted, reactive, ref} from "vue";
    import {ElMessage, ElMessageBox} from "element-plus";
    import {updateBus, getBusById} from "@/api/bus";

    const route = useRoute();
    const router = useRouter();
    let bus = ref({});
    onMounted(() => {
        getBusById({id: route.params.id}).then((response)=>{
            bus.value = response.data;
            console.log(bus.value)
        })

    })

    const rules = reactive({
        busName: [
            {required: true, message: '公交车名称不能为空', trigger: 'blur'},
            {min: 0, max: 15, message: '名称最多不能超过15字', trigger: "blur"}
        ],
        start: [
            {required: true, message: '始发站不能为空', trigger: 'blur'},
            {min: 0, max: 100, message: '始发站最多不能超过100字', trigger: "blur"}
        ],
        end: [
            {required: true,message: '终点站不能为空',trigger: 'blur'},
            {min: 0, max: 100, message: '终点站最多不超过100字', trigger: "blur"}
        ],
        pathway: [
            {required: true,message: '途径站点不能为空',trigger: 'blur'},
        ]
    });
    const submitForm = () => {
        ElMessageBox.confirm(
            '更新该公交的信息，要继续吗',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(() => {
                console.log(bus.value)
                updateBus(bus.value).then((response)=>{
                    if (response.data) {
                        router.push({name: 'bus'});
                        ElMessage({
                            type: 'success',
                            message: '更新完成',
                        });
                    } else {
                        ElMessage({
                            type: 'info',
                            message: '更新失败',
                        });
                    }
                });


            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消更新',
                })
            })

    };
    const cancel = () =>{
        router.push({name: 'bus'});
    }
    const goBack = () => {
        router.back();
    };
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