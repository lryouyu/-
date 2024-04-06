<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" >
                <el-form ref="ruleFormRef" :model="attraction" :rules="rules">

                    <!--表单数据：景点文本数据-->
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-form-item label="景点名称" prop="attName" >
                        <el-input v-model="attraction.attName" style="width:350px;"></el-input>
                    </el-form-item>
                    <el-form-item label="景点介绍" prop="introduce">
                        <el-input type="textarea" v-model="attraction.introduce" style="width:350px;" autosize></el-input>
                    </el-form-item>
                    <el-form-item label="景点星级" prop="star" >
                        <el-input-number v-model="attraction.star" :min="1" :max="5" style="width:150px;"></el-input-number>
                    </el-form-item>
                    <el-form-item label="服务信息" prop="service">
                        <el-input type="textarea" v-model="attraction.service" style="width:350px;" autosize></el-input>
                    </el-form-item>
                    <el-form-item label="景点地址" prop="address" >
                        <el-input v-model="attraction.address" style="width:350px;"></el-input>
                    </el-form-item>
                    <el-form-item label="官方电话" prop="tel" >
                        <el-input v-model="attraction.tel" style="width:350px;"></el-input>
                    </el-form-item>
                    <el-form-item label="景点评分" prop="score" >
                        <el-input v-model="attraction.score" style="width:350px;"></el-input>
                    </el-form-item>
                    <el-form-item label="开放时间">
                        <el-time-select
                                v-model="attraction.startTime"
                                start="00:30"
                                step="00:15"
                                end="23:30"
                                placeholder="开放时间"
                        />
                        &nbsp&nbsp&nbsp&nbsp&nbsp<el-time-select
                            v-model="attraction.endTime"
                            start="00:30"
                            step="00:15"
                            end="23:30"
                            placeholder="关闭时间"
                    />
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
                    <!--评论区-->
                    <el-scrollbar height="300px">
                        <el-form-item label="评论">
                            <el-row v-for="comment in comments" style="border: 1px solid;width: 1000px">
                                <el-col :span="48">
                                    <el-descriptions title="评论" style="width: auto">
                                        <el-descriptions-item label="作者编号">{{comment.userId}}&nbsp&nbsp&nbsp&nbsp&nbsp</el-descriptions-item>
                                        <el-descriptions-item label="评论时间">{{useDateFormat(comment.time,'YYYY-MM-DD HH:mm:ss')}}&nbsp&nbsp&nbsp&nbsp&nbsp</el-descriptions-item>
                                        <el-descriptions-item label="评论内容">{{comment.content}}</el-descriptions-item>
                                    </el-descriptions>
                                    <el-button :id="comment.id" text @click="deleteCommentBtn($event)">删除评论</el-button>

                                </el-col>
                            </el-row>
                        </el-form-item>
                    </el-scrollbar>

                </el-form>
            </el-card>


        </div>
    </transition>
</template>

<script setup>
    import { ref ,reactive,onMounted,onUpdated} from 'vue'
    import {useRoute,useRouter} from 'vue-router'
    import {
        getAttraction,
        getComment,
        getImageUrl,
        updateImage,
        deleteComment,
        updateAttraction
    } from "@/api/attraction";
    import {ElMessage,ElMessageBox} from "element-plus";
    import { useDateFormat } from '@vueuse/core'
    import {updateUser} from "@/api/user";

    const route = useRoute();
    const router = useRouter();
    let attraction = ref({});
    let comments = ref();
    let imageUrls = ref();

    onMounted(()=>{
        //获取评论
        getComment({id: route.params.id}).then((response)=>{
            comments.value = response.data;
            console.log(comments.value)
        })
        getAttraction({id: route.params.id}).then(
            (response) => {
                attraction.value = response.data;
                console.log(attraction.value)
            }
        )
        getImageUrl({id: route.params.id}).then((response)=>{
            imageUrls.value = response.data;
            console.log(imageUrls.value)
        })

    })

    const submitForm = () => {
        ElMessageBox.confirm(
            '更新该景点的信息，要继续吗',
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
                updateAttraction(attraction.value);
                router.push({name: 'attraction'});
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
        router.push({name: 'attraction'});
    }

    const deleteCommentBtn = (event) => {
        const commentId = event.currentTarget.id;
        ElMessageBox.confirm(
            '删除该评论，要继续吗',
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
                    message: '删除成功',
                })
                deleteComment({id:commentId})
                getComment({id: route.params.id}).then((response)=>{
                    comments.value = response.data;
                    console.log(comments.value)
                })
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消删除',
                })
            })
    };

    const rules = reactive({
        attName: [
            {required: true, message: '景点名不能为空', trigger: 'blur'}
        ],
        introduce: [
            {required: true, message: '景点介绍不能为空', trigger: 'blur'},
            {min: 0, max: 100, message: '景点介绍最多不能超过100字', trigger: "blur"}
        ],
        service: [
            {required: true,message: '服务信息不能为空',trigger: 'blur'},
            {min: 0, max: 100, message: '服务信息最多不超过100字', trigger: "blur"}
        ],
        tel: [
            {required: true, message: '官方电话不能为空', trigger: 'blur'}
        ],
        address: [
            {required: true, message: '景点地址不能为空', trigger: 'blur'}
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