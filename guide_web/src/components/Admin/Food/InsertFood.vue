<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" >
                <el-form ref="ruleFormRef" :model="food" :rules="rules">
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-form-item label="美食特产名称" prop="foodName" >
                        <el-input v-model="food.foodName" style="width:350px;"></el-input>
                    </el-form-item>
                    <el-form-item label="美食特产介绍" prop="content">
                        <el-input  type="textarea" v-model="food.content" style="width:350px;" autosize></el-input>
                    </el-form-item>

                    <el-form-item label="点击上传图片">
                        <el-upload
                                v-for="imageUrl in imageUrls"
                                class="avatar-uploader"
                                name="imageFile"
                                action="/api/upload/image"
                                :show-file-list="false"
                                :on-success="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload"
                                :key="imageUrl.key"
                                @click="updateurl(imageUrl)"
                        >
                            <!--图片回显-->
                            <img v-if="imageUrl.imgPath" :src="'/'+imageUrl.imgPath" class="avatar" />
                            <el-icon v-else class="avatar-uploader-icon"><Plus/></el-icon>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="所属景点名称" prop="attName">
                        <el-input  type="textarea" v-model="food.attName" style="width:350px;" autosize></el-input>
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


    import {reactive, ref} from "vue";
    import {ElMessage, ElMessageBox} from "element-plus";
    import {addFood, addImage, updateFood, updateImage} from "@/api/food";
    import {Plus} from '@element-plus/icons-vue'
    import {useRouter} from 'vue-router'

    let food = ref({foodName: "", content: "", attName: ""});
    const imageUrls = ref([
        {key: 1, imgPath: null, foodId: null}, {key: 2, imgPath: null, foodId: null},
        {key: 3, imgPath: null, foodId: null}, {key: 4, imgPath: null, foodId: null},
    ]);

    const imgId = ref();
    const router = useRouter();
    const updateurl = (imageUrl) => {
        console.log(imageUrl.key);
        imgId.value = imageUrl.key;
    };
    const updateUrls = reactive([])
    const handleAvatarSuccess = (response,event) => {
        //图片上传成功的图片 要接收返回值，设置回显图片路径imageUrl
        for (let i=0; i< 4;i++){
            if (imgId.value==imageUrls.value[i].key){
                imageUrls.value[i].imgPath = response.data;
                updateUrls.push(imageUrls.value[i])
            }
        }
        console.log(updateUrls)
    };
    const beforeAvatarUpload= (rawFile) => {
        console.log(rawFile.size)
        if (rawFile.type !== 'image/jpeg') {
            ElMessage.error('Avatar picture must be JPG format!')
            console.log("执行1")
            return false
        }
        if (rawFile.size > 1048576) {
            ElMessage.error('Avatar picture size can not exceed 2MB!')
            console.log("执行2")
            return false
        }
        return true
    }

    const rules = reactive({
        foodName: [
            {required: true, message: '美食特产名不能为空', trigger: 'blur'}
        ],
        content: [
            {required: true, message: '美食特产介绍不能为空', trigger: 'blur'},
            {min: 0, max: 200, message: '美食特产介绍最多不能超过200字', trigger: "blur"}
        ],
        attName: [
            {required: true, message: '所属景点名称不能为空', trigger: 'blur'}
        ],
    });
    const submitForm = () => {
        ElMessageBox.confirm(
            '新增该美食特产，要继续吗',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(() => {
                addFood(food.value).then(
                    (response) => {
                        let foodId = response.data.id;
                        for (let i = 0; i < updateUrls.length; i++) {
                            updateUrls[i].foodId = foodId;
                            addImage(updateUrls[i])
                        }
                    })
                ElMessage({
                    type: 'success',
                    message: '添加完成',
                })
                console.log(111)
                router.push({name: 'food'});
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消添加',
                })
            })

    };
    const cancel = () => {
        router.push({name: 'food'});
    };


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