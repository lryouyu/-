<template>
    <div class="body">
        <div class="login">
            <el-tabs type="border-card" style="border-radius: 10px">
                <el-tab-pane label="用户登录" style="border-radius: 10px"><el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
                    <el-tab-pane label="登录" name="login" >
                        <el-form ref="ruleFormRef" :model="userForm" :rules="rules">
                            <!--prop的值，表单的key值，rules定义的规则的key值-->
                            <el-form-item label="用户名" prop="username" >
                                <el-input v-model="userForm.username" style="width:350px;"></el-input>
                            </el-form-item>
                            <el-form-item label="密  码 " prop="password">
                                &nbsp&nbsp&nbsp<el-input v-model="userForm.password" type="password" style="width:350px;"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button style="margin-top: 12px; margin-left: 200px;" @click="submitForm">登录</el-button>
                                <Vcode :show="isShow" @success="loginsuccess" @close="close" @fail="fail" :img="[Img]"></Vcode>
                                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<el-link href="/find" type="primary">忘记密码?</el-link>
                            </el-form-item>
                        </el-form>
                    </el-tab-pane>
                    <el-tab-pane label="注册" name="register">
                        <el-form ref="ruleFormRef1" :model="userForm1" :rules="rules1">
                            <div v-if="!isHidden1">
                                <!--prop的值，表单的key值，rules定义的规则的key值-->
                                <el-form-item label="用户名" prop="username" >
                                    &nbsp&nbsp&nbsp&nbsp<el-input v-model="userForm1.username" style="width:400px;"></el-input>
                                </el-form-item>
                                <el-form-item label="密  码" prop="password" >
                                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<el-input v-model="userForm1.password" type="password" style="width:400px;"></el-input>
                                </el-form-item>
                                <el-form-item label="* 确认密码" prop="isPassword" >
                                    <el-input v-model="isPassword" type="password" style="width:400px;"></el-input>
                                </el-form-item>
                                <el-form-item label="手机号" prop="tel" >
                                    &nbsp&nbsp&nbsp&nbsp<el-input v-model="userForm1.tel" style="width:400px;"></el-input>
                                </el-form-item>
                            </div>

                            <div v-if="isHidden2">
                                <p>请上传头像</p>
                                <!--action 上传的地址-->
                                <el-upload
                                        class="avatar-uploader"
                                        name="imageFile"
                                        action="/api/upload/image"
                                        :show-file-list="false"
                                        :on-success="handleAvatarSuccess"
                                        :before-upload="beforeAvatarUpload"

                                >
                                    <!--图片回显-->
                                    <img v-if="imageUrl" :src="'/'+imageUrl" class="avatar" />
                                    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                                </el-upload>
                                <!--prop的值，表单的key值，rules定义的规则的key值-->
                                <el-form-item label="真实姓名" prop="realName" style="width:400px;padding-top:10px;">
                                    <el-input v-model="userForm1.realName"></el-input>
                                </el-form-item>
                                <el-form-item label="身份证号" prop="card" style="width:400px;">
                                    <el-input v-model="userForm1.card"></el-input>
                                </el-form-item>
                                <el-form-item label="性别">
                                    <el-radio-group v-model="sex">
                                        <el-radio label="男" />
                                        <el-radio label="女" />
                                    </el-radio-group>
                                </el-form-item>
                                <el-form-item label="年龄" prop="age" style="width:400px;">
                                    <el-input v-model="userForm1.age"></el-input>
                                </el-form-item>
                            </div>
                            <div v-if="isHidden3">
                                <Vcode :show="isShow2" @success="success1" @close="close" @fail="fail" :img="[Img]"></Vcode>
                                <!--prop的值，表单的key值，rules定义的规则的key值-->
                                <el-result
                                        icon="success"
                                        title="Success Tip"
                                        sub-title="Please follow the instructions"
                                >
                                </el-result>
                            </div>
                        </el-form>
                        <el-steps :active="active" finish-status="success">
                            <el-step title="注册" />
                            <el-step title="完善用户信息" />
                            <el-step title="完成注册" />
                        </el-steps>
                        <el-button style="margin-top: 12px" @click="submitForm2">{{step}}</el-button>
                    </el-tab-pane>
                </el-tabs></el-tab-pane>
                <el-tab-pane label="管理员登陆" style="border-radius: 10px">
                    <el-form ref="ruleFormRef2" :model="adminForm" :rules="rules2">
                        <!--prop的值，表单的key值，rules定义的规则的key值-->
                        <el-form-item label="" prop="" >

                        </el-form-item>
                        <el-form-item label="" prop="" >

                        </el-form-item>
                        <el-form-item label="用户名" prop="username" >
                            <el-input v-model="adminForm.username" style="width:350px;"></el-input>
                        </el-form-item>
                        <el-form-item label="" prop="" >

                        </el-form-item>
                        <el-form-item label="密  码 " prop="password">
                            &nbsp&nbsp&nbsp<el-input v-model="adminForm.password" type="password" style="width:350px;"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button style="margin-top: 12px; margin-left: 200px;" @click="submitForm3">登录</el-button>
                            <Vcode :show="isShow1" @success="success2" @close="close" @fail="fail" :img="[Img]"></Vcode>
                        </el-form-item>
                    </el-form>
                </el-tab-pane >
            </el-tabs>
        </div>
    </div>


</template>

<script setup>
    import {ref, reactive,onMounted} from 'vue'
    import {useStore} from 'vuex'
    import {useRouter} from 'vue-router'
    import axios from 'axios'
    import qs from 'qs'
    import {userlogin, addUser, adminlogin, getUser} from '@/api/user'
    import user from '@/store/user'
    import { ElMessage } from 'element-plus'
    import { Plus } from '@element-plus/icons-vue'
    import SIdentify from '@/components/Sidentify'
    import { UploadProps } from 'element-plus'

    //自定义背景图，自己准备一张图片，引入即可
    import Img from '@/assets/img.jpg'
    //引入'vue3-puzzle-vcode'插件
    import Vcode from 'vue3-puzzle-vcode'
    //验证码模态框是否出现，默认不展示
    const isShow = ref(false)
    const isShow1 = ref(false)
    const isShow2 = ref(false)
    const router = useRouter();
    //用户通过了验证
    const loginsuccess = (msg) => {
        isShow.value = false
        console.log('验证通过' + msg)
        console.log("success")
        router.push({name: 'hotelshow'})
    }
    //用户点击遮罩层，关闭模态框
    const close = () => {
        isShow.value = false
    }
    //用户验证失败
    const fail = () => {
        console.log('验证失败')
    }

    const beforeAvatarUpload= (rawFile) => {
        console.log(rawFile.size)
        if (rawFile.type !== 'image/jpeg') {
            ElMessage.error('Avatar picture must be JPG format!')
            return false
        }
        if (rawFile.size > 1048576) {
            ElMessage.error('Avatar picture size can not exceed 1MB!')
            return false
        }
        return true
    }

    const step=ref('下一步')
    const activeName=ref('login')
    const isHidden1=ref(false);
    const isHidden2=ref(false);
    const isHidden3=ref(false);

    const imageUrl = ref("");
    const handleAvatarSuccess = (res) => {
        //图片上传成功的图片 要接收返回值，设置回显图片路径imageUrl
        console.log(res.data)
        imageUrl.value = res.data;
        console.log(imageUrl.value)
        userForm1.imgPath = imageUrl.value;
    };

    const isPassword=ref();

    const handleClick = (tab, event) => {
        userForm1.username="";
        userForm1.password="";
        userForm1.tel = "";
        userForm1.imgPath="";
        isPassword.value="";
        active.value = 0
        isHidden1.value = false;
        isHidden2.value = false;
        isHidden3.value = false;
    }

    const sex = ref("男");
    const active = ref(0)

    const userForm = reactive({username: "", password: ""});
    const userForm1 = reactive({username: "", password: "",tel: "",imgPath: "",sex: 1,card: "",age: null});
    const store = useStore();

    const rules = reactive({
        username: [
            {required: true, message: '用户名不能为空', trigger: 'blur'},
            {min: 3, max: 10, message: '用户名长度3-10个字符', trigger: "blur"}
        ],
        password: [
            {required: true, message: '密码不能为空', trigger: 'blur'},
            {min: 3, max: 10, message: '密码长度3-10个字符', trigger: "blur"}
        ]

    });
    const rules1 = reactive({
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
        realName: [
            {required: true, message: '真实姓名不能为空', trigger: 'blur'},
        ],
        card: [
            {required: true, message: '身份证号不能为空', trigger: 'blur'},
            {min: 18, max: 18, message: '身份证号长度18个字符', trigger: "blur"}
        ],

        age: [
            {required: false},
            {min: 1, max: 3, message: '年龄不符', trigger: "blur"}
        ]
    });
    const ruleFormRef = ref();
    const ruleFormRef1 = ref();
    const submitForm = () => {
        ruleFormRef.value.validate((valid) => {
            if (valid) {
                console.log("登录了")
                userlogin(userForm).then(
                    (response) => {
                        console.log(response)
                        if (response.data == null) {
                            alert(response.msg);
                            router.push({path: '/login'})
                        } else {
                            console.log("zhix")
                            const userId = response.data.id;
                            getUser({id:userId}).then((response)=>{
                                console.log(response.data)
                                store.commit("saveUser", response.data)
                            })
                            isShow.value = true;
                        }

                    }
                )
            }
        });
    }
    const submitForm2 = () => {
        ruleFormRef1.value.validate((valid) => {
            console.log(valid)
            if (valid) {
                if (isPassword.value == userForm1.password) {
                    active.value++;
                    console.log(active.value);
                    if (active.value == 1) {
                        console.log("执行1")
                        isHidden1.value = true;
                        isHidden2.value = true;
                    }
                } else {
                    alert("两次输入密码值不一致，请重新输入")
                    userForm1.password=""
                    isPassword.value=""
                }

                if (active.value == 2) {
                    isHidden2.value = false;
                    isHidden3.value = true;
                    isShow2.value=true;
                    step.value = "完成并登录";
                }
                if (active.value > 2) {
                    console.log(active.value)
                    //router.push({path: '/login'});
                    location.reload();
                    console.log(userForm)
                }

            }
        });
    }
    const success1 = (msg) => {
        isShow2.value = false
        if (sex == "男") {
            userForm1.sex = 1;
        } else {
            userForm1.sex = 0;
        }
        addUser(userForm1).then(
            (response) => {
                console.log(response)
                const user = response.data
                store.commit("saveUser", user)
            }
        )
        console.log('验证通过' + msg)
    }

    const adminForm = reactive({id: null,username: "", password: ""});
    const ruleFormRef2 = ref();
    const success2 = (msg) => {
        isShow.value = false;
        console.log('验证通过' + msg)
        console.log("success2")
        router.push({name:'admin'})
    }
    const submitForm3 = () =>{
        ruleFormRef2.value.validate((valid) => {
            if (valid) {
                adminlogin(adminForm).then(
                    (response) => {
                        console.log(response)
                        if (response.data == null) {
                            alert(response.msg);
                            router.push({path: '/login'})
                        } else {
                            console.log("zhix")
                            const admin = response.data;
                            console.log(admin)
                            store.commit("saveAdmin", admin)
                            console.log(store.getters.getAdminId)
                            isShow1.value = true;

                        }

                    }
                )
            }
        });
    }




</script>

<style scoped>
    .avatar-uploader .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
    .body {
        background-image: url('../assets/back.jpg'); /* 设置背景图片路径 */
        background-size: cover; /* 图片自适应容器大小 */
        background-position: center; /* 图片居中显示 */
    }

    .login {
        display: flex;
        justify-content: center;
        align-items: center;
        z-index:999;
        height: 725px;
        border-radius: 10px;
    }
          .el-link .el-icon--right.el-icon {
              vertical-align: text-bottom;
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