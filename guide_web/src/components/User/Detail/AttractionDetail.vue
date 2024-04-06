<template>
    <el-row>
        <el-col :span="16">
            <el-page-header @back="goBack">
                <template #content>
                    <h1 class="text-large font-600 mr-3"> {{attraction.attName}} </h1>
                </template>
            </el-page-header>
            <div class="block text-center" m="t-4">
                <el-carousel trigger="click" height="500px" style="border-radius: 10px">
                    <el-carousel-item v-for="imageUrl in imageUrls" :key="item">
                        <el-image style="width: 800px; height: 500px" :src="'/'+imageUrl.imgPath" :fit="cover" />
                    </el-carousel-item>
                </el-carousel>
            </div>
        </el-col>
        <el-col :span="8" style="padding-top: 60px">
            <div>
                <el-col :span="24"><el-icon><Location /></el-icon><span style="width:350px;margin-left: 20px">&nbsp地址:{{attraction.address}}</span></el-col>
                <el-col :span="24"><span>&nbsp</span></el-col>
                <el-col :span="24">
                    <h3 style="color: #333;font-size: 18px;margin-bottom: 10px;"><el-icon><OfficeBuilding /></el-icon>景区介绍</h3>
                    <p style="color: #666;font-size: 14px;line-height: 2.8;width:350px;margin-left: 20px">{{ attraction.introduce }}</p>
                    <p style="color: #666;font-size: 14px;line-height: 2.8;width:350px;">官方电话:{{ attraction.tel }}</p>
                    <el-button type="primary"  @click="scrollToTable">购买门票</el-button>
                </el-col>
            </div>
        </el-col>
    </el-row>

    <h2 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;padding-top: 20px">景区服务</h2>
    <p style="color: #666;font-size: 14px;line-height: 2.8;width:600px;margin-left: 20px">{{ attraction.introduce }}</p>
    <h2 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;padding-top: 30px">门票</h2>
    <el-table :data="tickets" style="width: 100%">
        <el-table-column prop="startTime" label="开始时间" width="180" />
        <el-table-column prop="endTime" label="结束时间" />
        <el-table-column prop="cost" label="价格" />
        <el-table-column prop="leftTicket" label="剩余数量"></el-table-column>
        <el-table-column prop="" label="操作">
            <template #default="scope">
                <el-button type="danger"  @click="buyTicket(scope.row)">购买</el-button>
            </template>
        </el-table-column>
    </el-table>
    <div>
        <h2 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;padding-top: 20px">评论</h2>

        <el-affix  :offset="80">
            <el-button type="primary" @click="insertComment">我也要评论</el-button>
        </el-affix>
        <div v-if="comments.length==0">
            <p>暂无评论，快发一个吧</p>
        </div>
        <div v-for="comment in comments" style="padding-top: 10px">
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

        <!--购票弹出框-->
        <el-dialog v-model="dialogFormVisible" title="购票">
            <el-form :model="useTicket">
                <el-form-item label="姓名" prop="time">
                    <span>{{useTicket.username}}</span>
                </el-form-item>
                <el-form-item label="" prop="time">
                    <div class="block">
                        <span class="demonstration">使用时间</span>
                        <el-date-picker
                                v-model="useTicket.useTime"
                                type="date"
                                placeholder="Pick a day"
                                :disabled-date="disabledDate"
                                :size="size"
                        />
                    </div>
                </el-form-item>
                <span>支付价格{{ticketCost}}</span>
            </el-form>
            <template #footer>
            <span class="dialog-footer">
                <el-button @click="cancelBuy">Cancel</el-button>
                <el-button type="primary" @click="insertTicket">确认</el-button>
            </span>
            </template>
        </el-dialog>
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

</template>

<script setup>
    import { ref ,reactive,onMounted,onUpdated} from 'vue'
    import {useRoute,useRouter} from 'vue-router'
    import {useStore} from 'vuex'
    import {
        Location,
        OfficeBuilding,
    } from '@element-plus/icons-vue'
    import {ElMessage,ElMessageBox} from "element-plus";
    import { useDateFormat } from '@vueuse/core'
    import {
        addTicket,
        buyUserTicket,
        getTickets,
        getTicketsByAttId,
        getUseTicketByTicketId,
        getUseTickets
    } from "@/api/ticket";
    import {
        addComment,
        deleteComment,
        getAttraction,
        getAttractionComments,
        getComment,
        getImageUrl
    } from "@/api/attraction";
    import {getUser} from "@/api/user";
    import {dateFormat} from "@/utils/DateChange";

    const route = useRoute();
    const router = useRouter();
    const store = useStore();
    let attraction = ref({});
    let tickets = ref([]);
    let imageUrls = ref([]);
    const comments = ref([])
    const value1 = ref();
    const dialogFormVisible = ref(false)
    const dialogFormVisible1 = ref(false)
    const useTicket = ref({id:null,userId:store.getters.getId,ticketId:null,time:null,useTime:Date.now(),username:store.getters.getRealName})
    const disabledDate = (time) => {
        return time.getTime() < Date.now()
    }
    const ticketCost = ref();
    const newComment = ref({id:null,content:null,time:null,strId:parseInt(route.params.id),userId:store.getters.getId})
    const rules1 = reactive({
        content: [
            {required: true, message: '评论内容不能为空', trigger: 'blur'},
            {min: 0, max: 50, message: '评论最多不能超过50字', trigger: "blur"}
        ]
    });
    const fontNum = ref();


    onMounted(()=> {
        //获取评论
        getTicketsByAttId({id: route.params.id}).then((response) => {
                console.log(response.data)
                tickets.value = response.data;
                console.log(tickets.value)
                for (let i = 0; i < tickets.value.length; i++) {
                    console.log("zhing")
                    //查询售出数量
                    getUseTicketByTicketId({id: tickets.value[i].id}).then((response) => {
                        let useTicketCount = response.data;
                        tickets.value[i].leftTicket = tickets.value[i].count-useTicketCount.length;

                    })
                }

            })
        getAttraction({id: route.params.id}).then(
            (response) => {
                attraction.value = response.data;
                console.log(attraction.value)
            }
        )
        getImageUrl({id: route.params.id}).then((response) => {
            imageUrls.value = response.data;
            console.log(imageUrls.value)
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
    })
    const scrollToTable = () => {
        const tableElement = document.querySelector('.el-table');
        if (tableElement) {
            tableElement.scrollIntoView({behavior: 'smooth'});
        }
    };
    //买票
    const buyTicket = (ticket) => {
        if (ticket.leftTicket == 0) {
            alert('抱歉，该门票已售空');
        } else {
            useTicket.value.ticketId = ticket.id;
            ticketCost.value = ticket.cost
            dialogFormVisible.value = true;
        }

    };
    const insertTicket = ()=>{
        console.log(useTicket)
        buyUserTicket(useTicket.value).then((response)=>{
            if(response.data!=null){
                dialogFormVisible.value = false;
                alert("预定成功，可在我的订单中查看")
                getTicketsByAttId({id: route.params.id}).then((response) => {
                    console.log(response.data)
                    tickets.value = response.data;
                    console.log(tickets.value)
                    for (let i = 0; i < tickets.value.length; i++) {
                        console.log("zhing")
                        //查询售出数量
                        getUseTicketByTicketId({id: tickets.value[i].id}).then((response) => {
                            let useTicketCount = response.data;
                            tickets.value[i].leftTicket = tickets.value[i].count-useTicketCount.length;
                        })
                    }

                })
            }
        })
    }
    const cancelBuy = () => {
        dialogFormVisible.value = false;
    };
    //回退
    const goBack = () => {
        router.back();
    }
    //评论
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
</script>

<style scoped>

</style>