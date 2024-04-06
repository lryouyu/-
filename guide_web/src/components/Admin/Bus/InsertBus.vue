<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" >
                <el-form ref="ruleFormRef" :model="bus" :rules="rules" style="height: 500px">

                    <!--表单数据：景点文本数据-->
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
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
                                step="00:15"
                                end="23:30"
                                placeholder="起始时间"
                        />
                        &nbsp&nbsp&nbsp&nbsp&nbsp<el-time-select
                            v-model="bus.endTime"
                            start="00:30"
                            step="00:15"
                            end="23:30"
                            placeholder="末班时间"
                    />
                    </el-form-item>
                    <el-form-item label="途径站 (站点之间请用‘-’分割)" prop="pathway" >
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
    import {useRoute,useRouter} from 'vue-router';
    import {ref, onMounted, reactive} from 'vue';
    import {ElMessage, ElMessageBox} from "element-plus";
    import {useStore} from 'vuex'
    import {addBus} from "@/api/bus";
    import { useDateFormat } from '@vueuse/core'

    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    let bus = ref({busName:"",start:"",end:"",startTime:null,endTime:null,pathway:""});


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
            '新增该公交，要继续吗',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(() => {
                console.log(bus.value)
                console.log(bus.value.startTime)
                addBus(bus.value)
                router.push({name: 'bus'});
                ElMessage({
                    type: 'success',
                    message: '新增成功',
                })

            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消创建',
                })
            })

    };
    const cancel = () =>{
        router.push({name: 'bus'});
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