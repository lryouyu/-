<template>
    <el-row>
        <el-col :span="16">
            <el-page-header @back="goBack">
                <template #content>
                    <h1 class="text-large font-600 mr-3"> {{strategy.title}} </h1>
                </template>
            </el-page-header>
            <div>
                <h3 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;padding-top: 20px">内容</h3>
                <p style="color: #666;font-size: 14px;line-height: 2.8;width:600px;margin-left: 20px;text-align: left">{{ strategy.content }}</p>
                <h3 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;padding-top: 20px">注意事项</h3>
                <p style="color: #666;font-size: 14px;line-height: 2.8;width:600px;margin-left: 20px;text-align: left">{{ strategy.warn }}</p>
                <h3 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;padding-top: 20px">交通情况</h3>
                <p style="color: #666;font-size: 14px;line-height: 2.8;width:600px;margin-left: 20px;text-align: left">{{ strategy.traffic }}</p>
                <div style="text-align: right">
                    <svg  v-if="!likeState" @click="userLike"  style="width:20px;height: 20px" t="1693634116772" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4010" width="200" height="200"><path d="M832 364.8h-147.2s19.2-64 32-179.2c6.4-57.6-38.4-115.2-102.4-121.6h-12.8c-51.2 0-83.2 32-102.4 76.8l-38.4 96c-32 64-57.6 102.4-76.8 115.2-25.6 12.8-121.6 12.8-128 12.8H128c-38.4 0-64 25.6-64 57.6v480c0 32 25.6 57.6 64 57.6h646.4c96 0 121.6-64 134.4-153.6l51.2-307.2c6.4-70.4-6.4-134.4-128-134.4z m-576 537.6H128V422.4h128v480z m640-409.6l-51.2 307.2c-12.8 57.6-12.8 102.4-76.8 102.4H320V422.4c44.8 0 70.4-6.4 89.6-19.2 32-12.8 64-64 108.8-147.2 25.6-64 38.4-96 44.8-102.4 6.4-19.2 19.2-32 44.8-32h6.4c32 0 44.8 32 44.8 51.2-12.8 102.4-32 166.4-32 166.4l-25.6 83.2h243.2c19.2 0 32 0 44.8 12.8 12.8 12.8 6.4 38.4 6.4 57.6z" p-id="4011"></path></svg>
                    <svg  v-if="likeState" @click="userLike"  style="width:20px;height: 20px" t="1693634361469" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4150" width="200" height="200"><path d="M832 364.8h-147.2s19.2-64 32-179.2c6.4-57.6-38.4-115.2-102.4-121.6h-12.8c-51.2 0-83.2 32-102.4 76.8l-38.4 96c-32 64-57.6 102.4-76.8 115.2-25.6 12.8-121.6 12.8-128 12.8H128c-38.4 0-64 25.6-64 57.6v480c0 32 25.6 57.6 64 57.6h646.4c96 0 121.6-64 134.4-153.6l51.2-307.2c6.4-70.4-6.4-134.4-128-134.4z m-576 537.6H128V422.4h128v480z m640-409.6l-51.2 307.2c-12.8 57.6-12.8 102.4-76.8 102.4H320V422.4c44.8 0 70.4-6.4 89.6-19.2 32-12.8 64-64 108.8-147.2 25.6-64 38.4-96 44.8-102.4 6.4-19.2 19.2-32 44.8-32h6.4c32 0 44.8 32 44.8 51.2-12.8 102.4-32 166.4-32 166.4l-25.6 83.2h243.2c19.2 0 32 0 44.8 12.8 12.8 12.8 6.4 38.4 6.4 57.6z" fill="#f4ea2a" p-id="4151"></path></svg>
                    {{strategy.like}}
                </div>
            </div>
        </el-col>
        <el-col :span="8" style="padding-top: 50px">
            <div style="border-radius: 10px;margin-left: 30px; background-image: linear-gradient(to right, cornflowerblue, lightskyblue); width: 350px; height: 600px;">
                <div>
                    <h2 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;padding-top: 20px">评论</h2>
                    <el-scrollbar height="500px">
                        <el-affix  :offset="180">
                            <el-button type="primary" @click="insertComment">我也要评论</el-button>
                        </el-affix>
                        <div v-if="comments.length==0">
                            <p>暂无评论，快发一个吧</p>
                        </div>
                        <div v-for="comment in comments" style="padding-top: 10px;border-radius: 10px;background-color: #42b983">
                            <el-descriptions title="" >
                                <template #title>
                                    <el-avatar
                                            :src="'/'+comment.imgPath"
                                    />&nbsp{{comment.username}}
                                </template>
                                <el-descriptions-item label="">{{comment.content}}</el-descriptions-item>
                                <el-descriptions-item label=""></el-descriptions-item>
                                <el-descriptions-item label=""></el-descriptions-item>
                                <el-descriptions-item label="评论时间">
                                    {{dateFormat(comment.time,'yyyy-MM-dd HH:mm:ss')}}
                                </el-descriptions-item>
                                <el-descriptions-item label="" v-if="store.getters.getId==comment.userId">
                                    <el-button text  @click="deleteOwn(comment.id)">删除</el-button>
                                </el-descriptions-item>
                            </el-descriptions>
                        </div>
                    </el-scrollbar>


                    <!--评论弹出框-->
                    <el-dialog v-model="dialogFormVisible1" title="新增评论">
                        <el-form :model="newComment" :rule="rules1">
                            <el-form-item label="评论内容">
                                <el-input v-model="newComment.content"/>{{fontNum}}
                            </el-form-item>

                        </el-form>
                        <template #footer>
                        <span class="dialog-footer">
                        <el-button @click="cancelCreate">Cancel</el-button>
                        <el-button type="primary" @click="createComment">确认</el-button>
                        </span>
                        </template>
                    </el-dialog>
                </div>
            </div>

        </el-col>
    </el-row>


</template>

<script setup>
    import {ref,reactive,onMounted} from 'vue'
    import {useRoute,useRouter} from 'vue-router'
    import {getStrategyById, getComment, deleteComment, addComment, updateStrategy} from "@/api/strategy";
    import {getUser} from "@/api/user";
    import { useDateFormat } from '@vueuse/core'
    import {ElMessage, ElMessageBox} from "element-plus";
    import {
        Location,
        OfficeBuilding,
    } from '@element-plus/icons-vue'
    import {useStore} from 'vuex'
    import {dateFormat} from "@/utils/DateChange";

    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    const strategy = ref({});
    const comments = ref([]);
    const newComment = ref({id:null,content:null,time:null,strId:parseInt(route.params.id),userId:store.getters.getId})
    const dialogFormVisible1 = ref(false)
    const likeState = ref(false)


    onMounted(()=>{
        console.log("执行onMount")
        console.log(route.params.id)
        getStrategyById({id: route.params.id}).then((response)=>{
            strategy.value = response.data;
            console.log(strategy.value)
        })
        getComment({id: route.params.id}).then((response) => {
            comments.value = response.data;
            for (let i = 0; i < comments.value.length; i++) {
                //查询作者信息
                getUser({id:comments.value[i].userId}).then((response)=>{
                    comments.value[i].username = response.data.username;
                    comments.value[i].imgPath = response.data.imgPath;
                })
                console.log(comments.value[i])
            }
        });
    })
    //回退
    const goBack = () => {
        router.back();
    }
    const insertComment = () => {
        dialogFormVisible1.value = true;
        newComment.value.content = null;
    };
    const deleteOwn = (id) => {
        ElMessageBox.confirm(
            '确定要删除评论吗',
            'Warning',
            {
                confirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning',
            }
        )
            .then(() => {
                deleteComment({id}).then((response)=>{
                    if (response.data){
                        getComment({id: route.params.id}).then((response) => {
                            comments.value = response.data;
                            for (let i = 0; i < comments.value.length; i++) {
                                //查询作者信息
                                getUser({id:comments.value[i].userId}).then((response)=>{
                                    comments.value[i].username = response.data.username;
                                    comments.value[i].imgPath = response.data.imgPath;
                                })
                            }
                            console.log(comments.value)
                        });
                        ElMessage({
                            type: 'success',
                            message: '删除成功',
                        })
                    }
                })
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: 'Delete canceled',
                })
            })
    }
    const createComment = () => {
        console.log(newComment.value)
        addComment(newComment.value).then((response)=>{
            if (response.data!=null){
                dialogFormVisible1.value = false;
                ElMessage({
                    message: '添加成功',
                    type: 'success',
                })
                getComment({id: route.params.id}).then((response) => {
                    comments.value = response.data;
                    for (let i = 0; i < comments.value.length; i++) {
                        //查询作者信息
                        getUser({id:comments.value[i].userId}).then((response)=>{
                            comments.value[i].username = response.data.username;
                            comments.value[i].imgPath = response.data.imgPath;
                        })
                    }
                    console.log(comments.value)
                });
            }
        });
    };
    const cancelCreate = () => {
        dialogFormVisible1.value = false;
        newComment.value.content = null;
    };
    const userLike = () => {
        likeState.value = !likeState.value;
        if(likeState.value==true) {
            strategy.value.like++;
            updateStrategy(strategy.value).then(() => {
                getStrategyById({id: route.params.id}).then((response)=>{
                    strategy.value = response.data;
                    console.log(strategy.value)
                })
            });
        }else {
            strategy.value.like--;
            updateStrategy(strategy.value).then(() => {
                getStrategyById({id: route.params.id}).then((response)=>{
                    strategy.value = response.data;
                    console.log(strategy.value)
                })
            });
        }

    };

</script>

<style scoped>

</style>