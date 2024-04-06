<template>
    <transition name="el-zoom-in-bottom">
    <div v-show="true" class="transition-box">
        <el-card class="box-card" >
                <el-form ref="ruleFormRef" :model="user" :rules="rules">
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-form-item label="用户名" prop="username" >
                        <el-input v-model="user.username" style="width:350px;"></el-input>
                    </el-form-item>
                    <el-form-item label="密  码 " prop="password">
                        &nbsp&nbsp&nbsp<el-input v-model="user.password" style="width:350px;"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="tel" >
                        &nbsp&nbsp&nbsp&nbsp<el-input v-model="user.tel" style="width:400px;"></el-input>
                    </el-form-item>
                    <el-form-item label="点击可更换头像">
                        <el-upload
                                class="avatar-uploader"
                                name="imageFile"
                                action="/api/upload/image"
                                :show-file-list="false"
                                :on-success="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload"
                        >
                            <!--图片回显-->
                            <img :src="imageUrl" class="avatar" />
                        </el-upload>
                    </el-form-item>

                    <el-form-item label="真实姓名" prop="realName" style="width:400px;padding-top:10px;">
                        <el-input v-model="user.realName"></el-input>
                    </el-form-item>
                    <el-form-item label="身份证号" prop="card" style="width:400px;">
                        <el-input v-model="user.card"></el-input>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-radio-group v-model="sex">
                            <el-radio label="男" />
                            <el-radio label="女" />
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="年龄" prop="age" style="width:400px;">
                        <el-input v-model="user.age"></el-input>
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
    import { ref ,reactive,onMounted,onUpdated} from 'vue'
    import {useRoute,useRouter} from 'vue-router'
    import {getUser, login, updateUser} from "@/api/user";
    import {ElMessage,ElMessageBox} from "element-plus";

    const route = useRoute()
    const imageUrl=ref("")
    const router = useRouter()
    let sex=ref("")
    let user = ref({});
    onMounted(()=>{
        getUser({id: route.params.id}).then(
            (response) => {
                user.value = response.data;
                console.log(user.value.imgPath)
                imageUrl.value = response.data.imgPath;
                imageUrl.value='http://localhost:8080/'+imageUrl.value
                if (user.value.sex == 0) {
                    sex.value = "女";
                } else {
                    sex.value = "男";
                }
            }
        )

    })

    const handleAvatarSuccess = (res) => {
        //图片上传成功的图片 要接收返回值，设置回显图片路径imageUrl
        console.log(res.data)
        user.value.imgPath = res.data;
        imageUrl.value = 'http://localhost:8080/'+ res.data;
    };
    const beforeAvatarUpload= (rawFile) => {
        console.log(rawFile.size)
        if (rawFile.type !== 'image/jpeg') {
            ElMessage.error('Avatar picture must be JPG format!')
            return false
        }
        if (rawFile.size > 1048576) {
            ElMessage.error('Avatar picture size can not exceed 2MB!')
            return false
        }
        return true
    }

    const rules = reactive({
        username: [
            {required: true, message: '用户名不能为空', trigger: 'blur'},
            {min: 3, max: 10, message: '用户名长度3-10个字符', trigger: "blur"}
        ],
        password: [
            {required: true, message: '密码不能为空', trigger: 'blur'},
            {min: 3, max: 10, message: '密码长度3-10个字符', trigger: "blur"}
        ],
        tel: [
            {required: true, message: '电话号不能为空', trigger: 'blur'},
            {min: 11, max: 11, message: '电话号长度11个字符', trigger: "blur"}
        ],
        card: [
            {required: false},
            {min: 18, max: 18, message: '身份证号长度18个字符', trigger: "blur"}
        ],
    });

    const submitForm = () => {
        if (sex.value == "男") {
            user.value.sex = 1;
        } else {
            user.value.sex = 0;
        }
        ElMessageBox.confirm(
            '更新该用户的信息，要继续吗',
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
                    message: '更新完成',
                })
                updateUser(user.value);
                router.push({name: 'userlist'});
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消更新',
                })
            })

    };

    const cancel = () => {
        router.push({name: 'userlist'});
    };


</script>

<style scoped>
    .transition-box {
        background-color: white;
        height: 600px;
        border-radius: 10px;
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