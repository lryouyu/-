<template>
    <el-scrollbar height="800px">
        <div style="background: #A952C4">
            <div class="common-layout">
                <el-container>
                    <el-header style="background-color: beige;text-align: center;padding-top: 20px">信息管理{{store.getters.getAdminUsername}}</el-header>
                    <el-container >
                        <el-main>
                            <div class="common-layout">
                                <el-container>
                                    <el-aside width="200px" style="overflow: hidden; background-color: white "><el-row class="tac" style="width: 400px;">
                                        <el-col :span="12">
                                            <h5 class="mb-2">操作</h5>
                                            <el-menu
                                                    default-active="2"
                                                    class="el-menu-vertical-demo"
                                                    @open="handleOpen"
                                                    @close="handleClose"
                                                    active-text-color="lightblue"
                                                    background-color="white"
                                                    unique-opened="true"
                                            >
                                                <el-sub-menu index="1">
                                                    <template #title>
                                                        <el-icon><User /></el-icon>
                                                        <span>用户管理</span>
                                                    </template>
                                                    <el-menu-item index="1-1" @click="checkUser"><el-icon><View /></el-icon>查看用户</el-menu-item>
                                                </el-sub-menu>
                                                <el-sub-menu index="2">
                                                    <template #title>
                                                        <el-icon><Flag /></el-icon>
                                                        <span>景点管理</span>
                                                    </template>
                                                    <el-menu-item index="2-1" @click="checkAttraction"><el-icon><View /></el-icon>查看景点</el-menu-item>
                                                    <el-menu-item index="2-2" @click="insertAttraction"><el-icon><Plus /></el-icon>新增景点</el-menu-item>
                                                </el-sub-menu>
                                                <el-sub-menu index="3">
                                                    <template #title>
                                                        <el-icon><Notebook /></el-icon>
                                                        <span>攻略管理</span>
                                                    </template>
                                                    <el-menu-item index="3-1" @click="checkStrategy"><el-icon><View /></el-icon>查看攻略</el-menu-item>
                                                    <el-menu-item index="3-2" @click="insertStrategy"><el-icon><Plus /></el-icon>新增攻略</el-menu-item>
                                                </el-sub-menu>
                                                <el-sub-menu index="4">
                                                    <template #title>
                                                        <el-icon><Notebook /></el-icon>
                                                        <span>游记管理</span>
                                                    </template>
                                                    <el-menu-item index="4-1" @click="checkTravel"><el-icon><View /></el-icon>查看游记</el-menu-item>
                                                </el-sub-menu>
                                                <el-sub-menu index="5">
                                                    <template #title>
                                                        <el-icon><Van /></el-icon>
                                                        <span>公交管理</span>
                                                    </template>
                                                    <el-menu-item index="5-1" @click="checkBus"><el-icon><View /></el-icon>查看公交</el-menu-item>
                                                    <el-menu-item index="5-2" @click="insertBus"><el-icon><Plus /></el-icon>新增公交</el-menu-item>
                                                </el-sub-menu>
                                                <el-sub-menu index="6">
                                                    <template #title>
                                                        <el-icon><Watermelon /></el-icon>
                                                        <span>美食特产管理</span>
                                                    </template>
                                                    <el-menu-item index="6-1" @click="checkFood"><el-icon><View /></el-icon>查看美食特产</el-menu-item>
                                                    <el-menu-item index="6-2" @click="insertFood"><el-icon><Plus /></el-icon>新增美食特产</el-menu-item>
                                                </el-sub-menu>
                                                <el-sub-menu index="7">
                                                    <template #title>
                                                        <el-icon><Ticket /></el-icon>
                                                        <span>票务管理</span>
                                                    </template>
                                                    <el-menu-item index="7-1" @click="checkTicket"><el-icon><View /></el-icon>查看门票</el-menu-item>
                                                    <el-menu-item index="7-2" @click="insertTicket"><el-icon><Plus /></el-icon>新增门票</el-menu-item>
                                                    <el-menu-item index="7-3" @click="checkUseTicket"><el-icon><View /></el-icon>查看售出门票</el-menu-item>
                                                </el-sub-menu>
                                                <el-sub-menu index="8">
                                                    <template #title>
                                                        <el-icon><OfficeBuilding /></el-icon>
                                                        <span>酒店管理</span>
                                                    </template>
                                                    <el-menu-item index="8-1" @click="checkHotel"><el-icon><View /></el-icon>查看酒店</el-menu-item>
                                                    <el-menu-item index="8-2" @click="insertHotel"><el-icon><Plus /></el-icon>新增酒店</el-menu-item>
                                                    <el-menu-item index="8-3" @click="orderManage"><el-icon><List /></el-icon>订单管理</el-menu-item>
                                                </el-sub-menu>
                                                <el-sub-menu index="9">
                                                    <template #title>
                                                        <el-icon><Setting /></el-icon>
                                                        <span>系统设置</span>
                                                    </template>
                                                    <el-menu-item index="9-1" @click="logout"><el-icon><Setting /></el-icon>退出登录</el-menu-item>
                                                </el-sub-menu>
                                            </el-menu>
                                        </el-col>
                                    </el-row></el-aside>
                                    <el-main id="main">
                                        <transition name="el-zoom-in-bottom">
                                            <el-scrollbar height="auto">
                                                <router-view :key="path"></router-view>
                                            </el-scrollbar>
                                        </transition>
                                    </el-main>
                                </el-container>
                            </div>
                        </el-main>
                    </el-container>
                </el-container>
            </div>
        </div>
    </el-scrollbar>

</template>

<script setup>
    import {ref,reactive} from 'vue'
    import {useRouter,useRoute} from 'vue-router'
    import {
        Document,
        Menu as IconMenu,
        Location,
        Setting,
        User,
        Position,
        View,
        Notebook,
        Plus,
        Flag,
        Van,
        Watermelon,
        OfficeBuilding,
        Ticket,
        List
    } from '@element-plus/icons-vue'
    import { ElMessage, ElMessageBox } from 'element-plus'
    import {useStore} from 'vuex'
    import {adminlogout} from "@/api/user";
    //执行useStore函数 获取useStore对象
    const store = useStore();
    const show = ref(true)
    const route = useRoute()
    const path = reactive(route.path);
    const router = useRouter();
    const handleOpen = (key, keyPath) => {
        console.log(key, keyPath)
    }
    const handleClose = (key, keyPath) => {
        console.log(key, keyPath)
    }

    const checkUseTicket = () => {
        router.push({path: '/admin/useTicket'});
    };

    const checkUser = () => {
        router.push({path: '/admin/userlist'});
    };

    const checkAttraction = () => {
        router.push({path: '/admin/attraction'});
    };

    const insertAttraction = () => {
        router.push({path: '/admin/insertAtt'});
    };

    const checkStrategy = () => {
        router.push({path: '/admin/strategy'});
    };

    const insertStrategy = () => {
        router.push({path: '/admin/insertStr'});
    };
    const checkTravel = () => {
        router.push({path: '/admin/travel'});
    };

    const checkBus = () => {
        router.push({path: '/admin/bus'});
    };

    const insertBus = () => {
        router.push({path: '/admin/insertBus'});
    };
    const checkFood = () => {
        router.push({path: '/admin/food'});
    };

    const insertFood = () => {
        router.push({path: '/admin/insertFood'});
    };
    const checkTicket = () => {
        router.push({path: '/admin/ticket'});
    };

    const insertTicket = () => {
        router.push({path: '/admin/insertTicket'});
    };

    const insertHotel = () => {
        router.push({path:'/admin/insertHotel'})
    }
    const checkHotel = () => {
        router.push({path: '/admin/hotel'});
    };
    const orderManage = () => {
        router.push({path:'/admin/order'})
    };
    const logout = () => {
            ElMessageBox.confirm(
                '确认要退出登录吗',
                'Warning',
                {
                    confirmButtonText: '确认',
                    cancelButtonText: '取消',
                    type: 'warning',
                }
            )
                .then(() => {
                    adminlogout().then((response)=>{
                        ElMessage({
                            type: 'success',
                            message: response.data.msg,
                        })
                        router.push({path:'/login'})
                    })


                })
                .catch(() => {
                    ElMessage({
                        type: 'info',
                        message: '退出失败',
                    })
                })

    };


</script>

<style scoped>

    #main {
        border-top-right-radius: 10px;
        border-bottom-right-radius: 10px;
        background-color: #42b983;
        background-size: cover;
        background-attachment: fixed;
    }
</style>
