<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="show" class="transition-box">
                <el-card class="box-card" v-for="user in users">
                    <template #header>
                        <div class="card-header">
                            <span>{{user.username}}</span>
                            <el-button class="button" :id="user.id" text @click="clickBtn($event)">更新用户信息</el-button>
                            <el-button class="button" :id="user.id" text @click="deleteBtn($event)">删除用户</el-button>
                        </div>
                    </template>
                    <el-descriptions title="用户信息">
                        <el-descriptions-item label="用户编号：">{{user.id}}</el-descriptions-item><br>
                        <el-descriptions-item label="用户电话">{{user.tel}}</el-descriptions-item>
                        <el-descriptions-item label="密码">{{user.password}}</el-descriptions-item>
                        <el-descriptions-item label="头像"><img :src="'/'+user.imgPath" class="avatar" /></el-descriptions-item>
                        <el-descriptions-item label="真实姓名"><p>{{user.realName}}</p></el-descriptions-item>

                        <el-descriptions-item label="性别"><p v-if="user.sex">男</p><p v-if="!user.sex&&user.sex!=null">女</p></el-descriptions-item>
                        <el-descriptions-item label="年龄"><p>{{user.age}}</p></el-descriptions-item>
                        <el-descriptions-item label="身份证号"
                        >{{user.card}}</el-descriptions-item>
                    </el-descriptions>
                </el-card>
        </div>
    </transition>
</template>

<script setup>
    import { ref,onMounted,reactive,computed} from 'vue'
    import {deleteUser, getUsers, update} from "@/api/user";
    import {useRouter} from 'vue-router'
    import {ElMessage, ElMessageBox} from "element-plus";

    const router = useRouter();
    const show = ref(false)
    let users = ref([]);

    let inputStyle=ref("border: none;")



    onMounted(()=>{
        show.value = true;
        getUsers().then((response)=>{
            users.value= response.data;
            console.log(users)
        })
    })

    const clickBtn = (event) => {
        const userId = event.currentTarget.id;
        const params = {id: userId};
        router.push({name: 'updateUser',params})
    };


    const deleteBtn = (event) => {
        const userId = event.currentTarget.id;
        ElMessageBox.confirm(
            '删除该用户，要继续吗',
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
                deleteUser({id:userId})
                getUsers().then((response)=>{
                    users.value= response.data;
                    console.log(users)
                })
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消删除',
                })
            })

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