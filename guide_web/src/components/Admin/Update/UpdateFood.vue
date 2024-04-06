<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" >
                <el-form ref="ruleFormRef" :model="food" :rules="rules">

                    <!--表单数据：景点文本数据-->
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-form-item label="美食特产名称" prop="foodName" >
                        <el-input v-model="food.foodName" style="width:350px;"></el-input>
                    </el-form-item>
                    <el-form-item label="美食特产介绍" prop="content">
                        <el-input type="textarea" v-model="food.content" style="width:350px;" autosize></el-input>
                    </el-form-item>
                    <el-form-item label="所属景点名称" prop="attName">
                        <el-input type="textarea" v-model="food.attName" style="width:350px;" autosize></el-input>
                    </el-form-item>

                    <!--图片上传区-->

                    <el-form-item label="点击可更换图片">
                        <el-upload
                                v-for="imageUrl in imageUrls"
                                class="avatar-uploader"
                                name="imageFile"
                                action="/api/upload/image"
                                :show-file-list="false"
                                :on-success="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload"
                                @click="updateurl(imageUrl)"
                        ><!--图片回显-->
                            <img :src="'/'+imageUrl.imgPath" class="avatar" style="padding: 10px"/>
                        </el-upload>
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
    import {
        getFood,
        getImageUrl,
        updateImage,
        updateFood
    } from "@/api/food";
    import {ElMessage,ElMessageBox} from "element-plus";
    import { useDateFormat } from '@vueuse/core'

    const route = useRoute();
    const router = useRouter();
    let food = ref({});
    let imageUrls = ref();

    onMounted(()=>{
        getFood({id: route.params.id}).then(
            (response) => {
                food.value = response.data;
                console.log(food.value)
            }
        )
        getImageUrl({id: route.params.id}).then((response)=>{
            imageUrls.value = response.data;
            console.log(imageUrls.value)
        })

    })

    const submitForm = () => {
        ElMessageBox.confirm(
            '更新该美食特产的信息，要继续吗',
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
                for (let i=0; i< updateImages.value.length;i++){
                    updateImage(updateImages.value[i]);
                }
                updateFood(food.value);
                router.push({name: 'food'});
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消更新',
                })
            })

    };

    let imgId=ref();

    const updateurl = (imageUrl) => {
        console.log(imageUrl);
        imgId.value=imageUrl.id;
    };

    let updateImages=ref([]);

    const handleAvatarSuccess = (response,event) => {
        //图片上传成功的图片 要接收返回值，设置回显图片路径imageUrl
        for (let i=0; i< imageUrls.value.length;i++){
            if (imgId.value==imageUrls.value[i].id){
                imageUrls.value[i].imgPath = response.data;
                updateImages.value.push(imageUrls.value[i]);

            }
        }
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
    const cancel = () =>{
        router.push({name: 'food'});
    }

    const rules = reactive({
        foodName: [
            {required: true, message: '美食特产名不能为空', trigger: 'blur'}
        ],
        content: [
            {required: true, message: '美食特产介绍不能为空', trigger: 'blur'},
            {min: 0, max: 100, message: '美食特产介绍最多不能超过100字', trigger: "blur"}
        ],
        attName: [
            {required: true, message: '所属景点名称不能为空', trigger: 'blur'}
        ],
    });

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