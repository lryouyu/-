<template>

    <div class="body">
        <div class="container">
            <el-page-header @back="goBack">
                <template #content>
                    <h3 class="text-large font-600 mr-3"> 忘记密码 </h3>
                </template>
            </el-page-header>
            <!-- 这里是容器的内容 -->
            <el-form ref="ruleFormRef1" :model="userForm1" :rules="rules1">
                <div v-if="hidden1">
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-form-item label="用户名" prop="username" >
                        &nbsp&nbsp&nbsp&nbsp<el-input v-model="userForm1.username" style="width:400px;"></el-input>
                    </el-form-item>
                    <el-form-item label="真实姓名" prop="realName" >
                        <el-input v-model="userForm1.realName" style="width:400px;"></el-input>
                    </el-form-item>
                    <el-form-item label="电话" prop="tel" >
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<el-input v-model="userForm1.tel"  style="width:400px;"></el-input>
                    </el-form-item>
                    <el-form-item label="身份证号" prop="card" >
                        <el-input v-model="userForm1.card" style="width:400px;"></el-input>
                    </el-form-item>
                </div>

                <div v-if="hidden2">
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-form-item label="新的密码" prop="password" style="width:400px;padding-top:10px;">
                        <el-input type="password" v-model="userForm1.password"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="isPassword" style="width:400px;">
                        <el-input type="password" v-model="userForm1.isPassword"></el-input>
                    </el-form-item>
                </div>
                <div v-if="hidden3">
                    <Vcode :show="isShow2" @success="success2" @close="close" @fail="fail" :img="[Img]"></Vcode>
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-result
                            icon="success"
                            title="成功！"
                            sub-title="更改密码成功"
                    >
                        <template #extra>
                            <el-button type="primary" @click="goBack">返回登陆</el-button>
                        </template>
                    </el-result>
                </div>
            </el-form>
            <el-steps :active="active" finish-status="success">
                <el-step title="核实用户" />
                <el-step title="修改密码" />
                <el-step title="完成" />
            </el-steps>
            <el-button style="margin-top: 12px" @click="next" v-if="active!=2">下一步</el-button>

        </div>
    </div>


</template>

<script setup>
    import {reactive, ref} from 'vue'
    import {checkIsUser, updateUser} from "@/api/user";
    import { ElMessage } from 'element-plus'
    //自定义背景图，自己准备一张图片，引入即可
    import Img from '@/assets/img.jpg'
    //引入'vue3-puzzle-vcode'插件
    import Vcode from 'vue3-puzzle-vcode'
    import {useRouter} from 'vue-router'


    const isShow2 = ref(false)
    const ruleFormRef1 = ref();
    const router = useRouter();

    const active = ref(0)

    const userForm1 = ref({realName:null,tel:null,card:null,username:null,password:null,isPassword:null})
    const user = ref({})
    const hidden1 = ref(true);
    const hidden2 = ref(false);
    const hidden3 = ref(false);
    const rules1 = reactive({
        username: [
            {required: true, message: '用户名不能为空', trigger: 'blur'},
            {min: 3, max: 10, message: '用户名长度3-10个字符', trigger: "blur"}
        ],
        password: [
            {required: true, message: '密码不能为空', trigger: 'blur'},
            {min: 3, max: 10, message: '密码长度3-10个字符', trigger: "blur"}
        ],
        isPassword:[
            {required: true, message: '确认密码不能为空', trigger: 'blur'},
        ],
        tel: [
            {required: true, message: '电话号不能为空', trigger: 'blur'},
        ],
        card: [
            {required: true, message: '身份证号不能为空', trigger: 'blur'},
        ],
        realName: [
            {required: true, message: '真实姓名不能为空', trigger: 'blur'},
        ]

    });

    //用户验证失败
    const fail = () => {
        console.log('验证失败')
    }
    const next = () => {
        ruleFormRef1.value.validate((valid) => {
            console.log(valid)
            if (valid) {
                console.log(active.value)
                if (active.value == 0){
                    //确认用户身份
                    user.value.realName = userForm1.value.realName
                    user.value.username = userForm1.value.username
                    user.value.tel = userForm1.value.tel;
                    user.value.card = userForm1.value.card;
                    checkIsUser(user.value).then((response) => {
                        if (response.data != null) {
                            console.log(response.data);
                            user.value = response.data;
                            hidden1.value = false;
                            hidden2.value = true;
                            active.value++;
                        } else {
                            ElMessage.error('未找到此用户');
                        }
                    });
                }
                if (active.value == 1){
                    if (userForm1.value.password != userForm1.value.isPassword) {
                        ElMessage.error('两次输入密码不一致');
                    } else {
                        user.value.password = userForm1.password;
                        console.log(user.value)
                        isShow2.value = true;
                        hidden3.value = true;
                    }

                }
            }
        })
        if (active.value == 3) active.value = 0

    }
    const success2 = (msg) => {
        active.value++;
        isShow2.value = false;
        hidden2.value = false;
        console.log('验证通过' + msg)
        console.log("success2")
        user.value.password = userForm1.value.password;
        updateUser(user.value)
    }
    const goBack = () => {
        router.back()
    };
</script>

<style scoped>
    .container {
        width: 500px; /* 设置容器的宽度 */
        margin:  auto; /* 设置左右外边距为自动，实现水平居中 */
        background-color: white ; /* 可选：设置容器的背景颜色 */
        padding: 20px; /* 可选：设置容器的内边距 */
        border-radius: 10px;
    }
    .body {
        background-image: url(../assets/back2.jpg);
        background-size: cover;
        background-position: center;
        height: 720px;
        display: flex; justify-content: center; align-items: center;
    }
</style>