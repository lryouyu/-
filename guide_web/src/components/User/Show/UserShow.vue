<template>
    <el-page-header :icon="ArrowLeft" @back="goBack">
        <template #content>
            <span class="text-large font-600 mr-3">个人中心</span>
        </template>
    </el-page-header>
    <div class="container">

        <div style="width: 500px">
            <el-descriptions
                    title=""
                    direction="horizontal"
                    :column="1"
                    :size="size"
                    border
            >
                <el-descriptions-item label="头像" style="text-align: center; display: flex; justify-content: center;">
                    <el-avatar :size="100" :src="store.getters.getImgPath" />
                </el-descriptions-item>


                <el-descriptions-item label="昵称">{{store.getters.getUsername}}</el-descriptions-item>
                <el-descriptions-item label="密码">
                    <div>
                        <div class="pass" style="float: left">
                            {{password}}
                        </div>
                        <div class="pass" style="float:right;">
                            <svg @click="changeState" v-if="showState" style="width: 20px;height: 20px" t="1693737932619" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6788" width="200" height="200"><path d="M928.842245 512.091074c0-5.006014-0.846274-9.193383-1.086751-9.691733-0.182149-2.480494-1.028423-7.001461-1.815345-9.374508-0.210801-0.590448-0.484024-1.209548-0.724501-1.799996-0.424672-1.360997-0.876973-2.691295-1.390673-3.749394-76.871785-168.137395-242.376213-281.144168-411.782507-281.144168-169.375595 0-334.865697 112.902396-411.388535 280.130072-0.921999 1.815345-1.572822 3.553942-1.981121 5.066389-0.181125 0.49835-0.39295 0.967024-0.558725 1.406023-1.512447 4.430916-1.542122 7.514137-1.421372 6.712889-0.710175 3.251044-1.360997 9.722432-1.360997 9.722432-0.181125 1.949398-0.181125 3.50687 0.030699 5.442966 0 0 0.649799 5.65479 0.968048 6.80294 0.090051 1.602498 0.483001 3.931542 0.951675 6.048763l-0.030699 0c0.408299 1.814322 0.968048 3.568269 1.738597 5.291516 0.393973 1.330298 0.862647 2.570545 1.270946 3.507894 76.976162 168.166047 242.436588 281.20352 411.781484 281.20352 169.436994 0 334.941422-112.945375 410.936233-279.328823 1.177825-2.177596 1.935072-4.233418 2.448772-6.018064 0.2415-0.543376 0.454348-1.027399 0.604774-1.511423 1.331321-3.872191 1.602498-7.227612 1.481747-7.227612l-0.028653 0.029676C928.027693 520.921183 928.842245 516.89959 928.842245 512.091074zM872.717993 514.146896c-0.029676 0.121773-0.091074 0.272199-0.151449 0.393973-0.090051 0.36225-0.240477 0.785899-0.332575 1.209548-68.403926 147.420561-212.830293 246.337431-360.191502 246.337431-146.997935 0-291.168476-98.642624-360.252901-246.578931-0.166799-0.5137-0.287549-0.998747-0.468674-1.481747-0.030699-0.484024-0.12075-0.876973-0.150426-1.150196-0.060375-0.300852-0.12075-0.724501-0.166799-1.088798l0-0.3776c0.166799-0.620124 0.286526-1.239224 0.347924-1.919722 0.12075-0.36225 0.211824-0.710175 0.347924-1.103124C220.132094 360.89042 364.680235 261.928524 512.041444 261.928524c147.420561 0 291.940049 99.051947 360.161826 246.322082 0.060375 0.287549 0.121773 0.530073 0.212848 0.726547 0.060375 0.2415 0.119727 0.484024 0.240477 0.740874 0.151449 1.104147 0.272199 2.192945 0.423649 2.736321C872.899118 513.028423 872.809067 513.572822 872.717993 514.146896z" fill="#272536" p-id="6789"></path><path d="M512.041444 373.060601c-76.598562 0-138.954749 62.325487-138.954749 138.939399 0 76.598562 62.356187 138.954749 138.954749 138.954749 76.598562 0 138.954749-62.356187 138.954749-138.954749C650.996193 435.386088 588.640006 373.060601 512.041444 373.060601zM512.041444 595.372849c-45.935192 0-83.371826-37.406958-83.371826-83.371826 0-45.950542 37.436634-83.356476 83.371826-83.356476 45.964868 0 83.373873 37.406958 83.373873 83.356476C595.414293 557.965891 558.006312 595.372849 512.041444 595.372849z" fill="#272536" p-id="6790"></path></svg>
                            <svg @click="changeState" v-if="!showState"  style="width: 20px;height: 20px" t="1693737846939" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5744" width="200" height="200"><path d="M190.65 612.86c-20.21-31.36-35.6-65.22-45.83-100.86 10.24-35.64 25.62-69.5 45.83-100.86 22.69-35.2 50.82-66.29 83.63-92.41 33.09-26.34 69.96-46.85 109.6-60.96 41.01-14.6 84.12-22 128.12-22s87.11 7.4 128.12 22c8.29 2.95 16.46 6.2 24.5 9.71l48.09-48.09C652.38 188.93 584.2 171.77 512 171.77c-209.88 0-385.89 144.97-433.38 340.23 20.42 83.96 64.62 158.6 125.04 216.44l45.26-45.26a382.718 382.718 0 0 1-58.27-70.32zM769.21 253.39l-46.02 46.02c9.08 6.04 17.94 12.48 26.53 19.32 32.81 26.12 60.94 57.21 83.63 92.41 20.21 31.36 35.6 65.22 45.83 100.86-10.24 35.64-25.62 69.5-45.83 100.86-22.69 35.2-50.82 66.29-83.63 92.41-33.09 26.34-69.96 46.85-109.6 60.96-41.01 14.6-84.12 22-128.12 22s-87.11-7.4-128.12-22a379.444 379.444 0 0 1-84.75-42.76l-45.97 45.97c72.99 52.11 162.33 82.78 258.84 82.78 209.88 0 385.89-144.97 433.38-340.23-25.81-106.13-89.61-197.39-176.17-258.6z" p-id="5745"></path><path d="M601.1 422.9c23.8 23.8 36.9 55.44 36.9 89.1s-13.11 65.3-36.9 89.1C577.3 624.9 545.66 638 512 638s-65.3-13.11-89.1-36.9c-0.23-0.23-0.46-0.47-0.69-0.71l-45.26 45.26C411.4 680.44 459.18 702 512 702c104.93 0 190-85.07 190-190 0-52.82-21.56-100.6-56.36-135.04l-45.26 45.25c0.24 0.24 0.48 0.46 0.72 0.69zM322 512c0 28.71 6.38 55.92 17.78 80.32l49.9-49.9c-2.43-9.83-3.68-20.03-3.68-30.42 0-33.66 13.11-65.3 36.9-89.1 23.8-23.8 55.44-36.9 89.1-36.9 10.39 0 20.58 1.25 30.42 3.68l49.9-49.9C567.92 328.38 540.71 322 512 322c-104.93 0-190 85.07-190 190z" p-id="5746"></path><path d="M847.06 188.09c-8.19 0-16.38 3.12-22.63 9.37L185.21 836.69c-12.5 12.5-12.5 32.76 0 45.25 6.25 6.25 14.44 9.37 22.63 9.37s16.38-3.12 22.63-9.37l639.22-639.22c12.5-12.5 12.5-32.76 0-45.25-6.25-6.26-14.44-9.38-22.63-9.38z" p-id="5747"></path></svg>
                        </div>
                    </div>
                </el-descriptions-item>
                <el-descriptions-item label="年龄">{{store.getters.getAge}}</el-descriptions-item>
                <el-descriptions-item label="电话">{{store.getters.getTel}}
                </el-descriptions-item>
                <el-descriptions-item label="性别">
                    <div v-if="store.getters.getSex">男</div>
                    <div v-if="!store.getters.getSex">女</div>
                </el-descriptions-item>
                <el-descriptions-item label="真实姓名">{{store.getters.getRealName}}
                </el-descriptions-item>
                <el-descriptions-item label="身份证号">{{store.getters.getCard}}
                </el-descriptions-item>
            </el-descriptions>
            <el-button type="primary" style="margin-top:10px;" @click="changeInform">更新</el-button>
        </div>
    </div>
    <!--更新信息-->
    <el-dialog v-model="dialogFormVisible" title="更新">
        <el-form :model="user" :rule="rules1">
            <el-form-item label="点击可更换图片">
                <el-upload
                        class="avatar-uploader"
                        name="imageFile"
                        action="/api/upload/image"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload"

                >
                    <!--图片回显-->
                    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                </el-upload>
            </el-form-item>
            <el-form-item label="昵称" prop="username">
                <el-input v-model="user.username" style="width:350px;"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="user.password" style="width:350px;"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
                <el-input v-model="user.age" style="width:350px;"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="tel">
                <el-input v-model="user.tel" style="width:350px;"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-radio-group v-model="sex" class="ml-4">
                    <el-radio label="男" size="large"></el-radio>
                    <el-radio label="女" size="large"></el-radio>
                </el-radio-group>

            </el-form-item>
            <el-form-item label="真实姓名" prop="realName">
                <el-input v-model="user.realName" style="width:350px;"></el-input>
            </el-form-item>
            <el-form-item label="身份证号" prop="card">
                <el-input v-model="user.card" style="width:350px;"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="cancelUser">Cancel</el-button>
                <el-button type="primary" @click="changeUser">确认</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
    import { ArrowLeft } from '@element-plus/icons-vue'
    import {useStore} from 'vuex'
    import {ref, onMounted, reactive} from 'vue'
    import {useRouter} from "vue-router";
    import {updateUser} from "@/api/user";
    import {ElMessage} from "element-plus";
    import { Plus } from '@element-plus/icons-vue'

    const store = useStore();
    const showState = ref(false);
    const passwordValue = ref('');
    const password = ref('');
    const user = ref({});
    const dialogFormVisible = ref(false);
    const sex = ref();
    const router = useRouter();
    const imageUrl = ref();
    const rules1 = reactive({
        username: [
            {required: true, message: '用户名不能为空', trigger: 'blur'},
            {min: 3, max: 10, message: '用户名长度3-10个字符', trigger: "blur"}
        ],
        password: [
            {required: true, message: '密码不能为空', trigger: 'blur'},
            {min: 3, max: 10, message: '密码长度3-10个字符', trigger: "blur"}
        ],
        tel:[
            {required: true,message:'电话号不能为空', trigger: 'blur'},
            {min: 11, max: 11, message: '电话号长度为11个字符', trigger: "blur"}
        ],
        card:[
            {required: true,message:'身份证号不能为空', trigger: 'blur'},
            {min: 18, max: 18, message: '身份证号长度为18个字符', trigger: "blur"}
        ]

    });

    onMounted(()=>{
        user.value = store.state.user
        delete user.value.token;
        for (let i = 0;i<store.getters.getPassword.length;i++){
            passwordValue.value +='*'
        }
        password.value = passwordValue.value
        imageUrl.value = user.value.imgPath;
        if (user.value.sex) {
            sex.value = "男";
        } else {
            sex.value = "女";
        }
    })
    const changeState = () => {
        showState.value = !showState.value;
        if (showState.value == false) {
            password.value = passwordValue.value;
        } else {
            password.value = store.getters.getPassword;
        }
    };
    const changeInform = () => {
        dialogFormVisible.value = true;
    };
    const changeUser = () => {
        if (sex.value == "男") {
            user.value.sex = 1;
        } else {
            user.value.sex = 0;
        }
        user.value.imgPath = imageUrl.value;
        updateUser(user.value);
        if (showState.value==true){
            password.value = user.value.password;
            console.log(password.value)
        }
        dialogFormVisible.value = false;
    };
    const cancelUser = () => {
        dialogFormVisible.value = false;
    };
    const handleAvatarSuccess = (res) => {
        //图片上传成功的图片 要接收返回值，设置回显图片路径imageUrl
        console.log(res.data)
        imageUrl.value = res.data;
        console.log(imageUrl.value)
    };
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
    const goBack = () => {
        router.back();
    };
</script>

<style scoped>
    .container {
        margin-top: 50px;
        display: flex;
        justify-content: center;
    }
    .demo-basic {
        text-align: center;
    }
    .demo-basic .sub-title {
        margin-bottom: 10px;
        font-size: 14px;
        color: var(--el-text-color-secondary);
    }
    .demo-basic .demo-basic--circle,
    .demo-basic .demo-basic--square {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .demo-basic .block:not(:last-child) {
        border-right: 1px solid var(--el-border-color);
    }
    .demo-basic .block {
        flex: 1;
    }
    .demo-basic .el-col:not(:last-child) {
        border-right: 1px solid var(--el-border-color);
    }
    .avatar-uploader .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>
<style>
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