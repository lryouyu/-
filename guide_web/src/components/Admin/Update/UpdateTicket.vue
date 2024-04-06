<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card">
                <el-page-header @back="goBack">
                </el-page-header>
                <el-form ref="ruleFormRef" :model="ticket" :rules="rules" style="height: 500px">

                    <!--表单数据：景点文本数据-->
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-form-item label="" prop="" >
                    </el-form-item>
                    <el-form-item label="" prop="" >
                    </el-form-item>
                    <el-form-item label="发售景点" prop="" >
                        &nbsp&nbsp
                        <el-select v-model="attValue" class="m-2" placeholder="请选择景点" size="large">
                            <el-option
                                    v-for="item in attractions"
                                    :key="item.id"
                                    :label="item.attName"
                                    :value="item.id"
                            >{{item.attName}}</el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="开始时间" prop="startTime">
                        <el-time-select
                                v-model="ticket.startTime"
                                :max-time="ticket.endTime"
                                class="mr-4"
                                placeholder="开始时间"
                                start="00:30"
                                step="00:30"
                                end="22:30"
                        />
                    </el-form-item>
                    <el-form-item label="结束时间" prop="endTime">
                        <el-time-select
                                v-model="ticket.endTime"
                                :min-time="ticket.startTime"
                                placeholder="结束时间"
                                start="00:30"
                                step="00:30"
                                end="22:30"
                        />
                    </el-form-item>

                    <el-form-item label="售价" prop="cost">
                        &nbsp&nbsp&nbsp<el-input-number v-model="ticket.cost" style="width:130px;" :step="5" :precision="2"></el-input-number>
                    </el-form-item>
                    <el-form-item label="总票数" prop="count">
                        &nbsp&nbsp&nbsp<el-input-number v-model="ticket.count" style="width:130px;" :step="5"></el-input-number>
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
    import {getAttraction, getAttractions} from "@/api/attraction";
    import {addTicket, getTicket, updateTicket} from "@/api/ticket";

    const route = useRoute();
    const router = useRouter();
    let ticket = ref({});
    const attractions = ref([]);
    let attValue = ref();
    onMounted(() => {
        getTicket({id: route.params.id}).then((response)=>{
            ticket.value = response.data;
            console.log(ticket.value)
            attValue.value = ticket.value.attId
        })
        getAttractions().then((response)=>{
            attractions.value = response.data;
            console.log(attractions)
        })

    })

    const rules = reactive({
        startTime: [
            {required: true, message: '开始时间不能为空', trigger: 'blur'},
        ],
        endTime: [
            {required: true, message: '结束时间不能为空', trigger: 'blur'},
            {min: ticket.value.startTime,  message: '结束时间不能小于开始时间', trigger: "blur"}
        ],
        cost: [
            {required: true,message: '售价不能为空',trigger: 'blur'}
        ],
        count: [
            {required: true,message: '总票数不能为空',trigger: 'blur'},
        ]
    });
    const submitForm = () => {
        console.log(attValue.value)
        ticket.value.attId=attValue.value
        getAttraction({id:attValue.value}).then((response)=>{
            ticket.value.attName = response.data.attName
        })
        console.log(ticket.value)
        ElMessageBox.confirm(
            '更新该门票，要继续吗',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(() => {
                updateTicket(ticket.value).then((response)=>{
                    console.log(response.data)
                    router.push({name: 'ticket'});
                    ElMessage({
                        type: 'success',
                        message: '更新成功',
                    })
                }).catch(()=>{
                    ElMessage({
                        type: 'info',
                        message: '更新出错',
                    })
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
        router.push({name: 'ticket'});
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