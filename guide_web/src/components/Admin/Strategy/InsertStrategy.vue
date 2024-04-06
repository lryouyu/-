<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" >
                <el-form ref="ruleFormRef" :model="strategy" :rules="rules" style="height: 600px">
                    <!--表单数据：景点文本数据-->
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-form-item label="" prop="" >
                    </el-form-item>
                    <el-form-item label="" prop="" >
                    </el-form-item>
                    <el-form-item label="" prop="" >
                    </el-form-item>

                    <el-form-item label="攻略标题" prop="title" >
                        <el-input v-model="strategy.title" style="width:350px;"></el-input>
                    </el-form-item>
                    <el-form-item label="攻略类别" prop="title" >
                        <el-select v-model="strategy.type" class="m-2" placeholder="请选择类别" size="large">
                            <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"
                            />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="攻略简介" prop="introduce">
                        <el-input type="textarea" v-model="strategy.introduce" style="width:350px;" autosize></el-input>
                    </el-form-item>
                    <el-form-item label="攻略内容" prop="content" >
                        <el-input type="textarea" v-model="strategy.content"  style="width:350px;" autosize></el-input>
                    </el-form-item>
                    <el-form-item label="注意事项" prop="warn" >
                        <el-input type="textarea" v-model="strategy.warn"  style="width:350px;" autosize></el-input>
                    </el-form-item>
                    <el-form-item label="交通指南" prop="traffic">
                        <el-input type="textarea" v-model="strategy.traffic"  style="width:350px;" autosize></el-input>
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
    import {addStrategy, getStrategyById, updateStrategy} from "@/api/strategy";
    import {ElMessage, ElMessageBox} from "element-plus";
    import {useStore} from 'vuex'
    import { Plus } from '@element-plus/icons-vue'

    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    let strategy = ref({id:null,title:"",introduce:"",content:"",type:"",traffic:"",warn:""});


    const rules = reactive({
        title: [
            {required: true, message: '标题不能为空', trigger: 'blur'},
            {min: 0, max: 15, message: '标题最多不能超过15字', trigger: "blur"}

        ],
        introduce: [
            {required: true, message: '攻略简介不能为空', trigger: 'blur'},
            {min: 0, max: 100, message: '攻略简介最多不能超过30字', trigger: "blur"}
        ],
        content: [
            {required: true,message: '攻略内容不能为空',trigger: 'blur'},
            {min: 0, max: 200, message: '攻略内容最多不超过200字', trigger: "blur"}
        ],
        warn: [
            {min: 0, max: 200, message: '注意事项最多不超过200字', trigger: "blur"}
        ],
        traffic: [
            {min: 0, max: 200, message: '交通指南最多不超过200字', trigger: "blur"}
        ],
    });
    const submitForm = () => {
        ElMessageBox.confirm(
            '新增该攻略，要继续吗',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(() => {
                console.log(strategy.value)
                strategy.value.adminId=store.getters.getAdminId;
                addStrategy(strategy.value);
                ElMessage({
                    type: 'success',
                    message: '创建成功',
                })
                router.push({name: 'strategy'});

            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消创建',
                })
            })

    };
    const cancel = () =>{
        router.push({name: 'strategy'});
    }
    const options = [
        {
            value: '趣味',
            label: '趣味',
        },
        {
            value: '娱乐',
            label: '娱乐',
        },
        {
            value: '度假',
            label: '度假',
        },
        {
            value: '商务',
            label: '商务',
        },
        {
            value: '自然',
            label: '自然',
        },
    ]

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

    .el-icon.avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        text-align: center;
    }
</style>