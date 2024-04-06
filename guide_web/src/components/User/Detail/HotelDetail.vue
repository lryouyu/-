<template>
    <el-row>

        <el-col :span="16">
            <el-page-header @back="goBack">
                <template #content>
                    <h1 class="text-large font-600 mr-3"> {{hotel.hotelName}} </h1>
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
                <el-col :span="24"><el-icon><Location /></el-icon><span style="width:350px;margin-left: 20px">&nbsp地址:{{hotel.address}}</span></el-col>
                <el-col :span="24"><span>&nbsp</span></el-col>
                <el-col :span="24">
                    <h3 style="color: #333;font-size: 18px;margin-bottom: 10px;"><el-icon><OfficeBuilding /></el-icon>酒店介绍</h3>
                    <p style="color: #666;font-size: 14px;line-height: 2.8;width:350px;margin-left: 20px">{{ hotel.content }}</p>
                    <el-button type="primary"  @click="scrollToTable">预定房间</el-button>
                </el-col>
            </div>
        </el-col>
    </el-row>
    <h2 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;">房间</h2>
    <el-table :data="houses" style="width: 100%">
        <el-table-column prop="houseName" label="房型名称" width="180" />
        <el-table-column prop="cost" label="每日单价" />
        <el-table-column prop="personCount" label="入住人数" />
        <el-table-column prop="num" label="剩余数量" />
        <el-table-column prop="introduce" label="房间介绍" />
        <el-table-column prop="" label="操作">
            <template #default="scope">
                <el-button type="danger"  @click="orderHouse(scope.row)" v-if="scope.row.num!=0">预订</el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-dialog v-model="dialogFormVisible" title="预定">
        <el-form :model="order" :rule="rules1">
            <el-form-item label="入住时间">
                <el-date-picker
                        v-model="value1"
                        type="daterange"
                        range-separator="To"
                        start-placeholder="入住"
                        end-placeholder="退房"
                        :size="size"
                        style="width: 350px"
                        @change="changetime"
                        :disabled-date="disabledDate"
                />
            </el-form-item>
            <span>支付价格{{days}}</span>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="cancelOrder">Cancel</el-button>
                <el-button type="primary" @click="insertOrder">确认</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
    import { ref ,reactive,onMounted,onUpdated} from 'vue'
    import {useRoute,useRouter} from 'vue-router'
    import {useStore} from 'vuex'
    import {
        Location,
        OfficeBuilding,
    } from '@element-plus/icons-vue'
    import {
        addOrder,
        getHotel,
        getHouses,
        getImageUrl, getOrders,getOrdersByHouseId
    } from "@/api/hotel";
    import {ElMessage,ElMessageBox} from "element-plus";
    import { useDateFormat } from '@vueuse/core'
    import {updateUser} from "@/api/user";
    import {updateStrategy} from "@/api/strategy";

    const dialogFormVisible = ref(false)
    const route = useRoute();
    const router = useRouter();
    const store = useStore();
    let hotel = ref({});
    let houses = ref();
    let imageUrls = ref();
    const value1 = ref();

    const disabledDate = (time) =>{
        //return time.getTime() > Date.now(); //禁止选择今天以后的时间
        return time.getTime() < Date.now() - 8.64e7;//禁止选择今天以前的时间
    }


    onMounted(()=>{
        //获取评论
        getHouses({id: route.params.id}).then((response)=>{
            houses.value = response.data;
            for (let i=0;i<houses.value.length;i++) {
                getOrdersByHouseId({houseId: houses.value[i].id}).then((response) => {
                    const count = response.data.length
                    houses.value[i].num -= count;
                });
            }
            console.log(houses.value)
        })
        getHotel({id: route.params.id}).then(
            (response) => {
                hotel.value = response.data;
                console.log(hotel.value)
            }
        )
        getImageUrl({id: route.params.id}).then((response)=>{
            imageUrls.value = response.data;
            console.log(imageUrls.value)
        })

    })
    const order = ref({id:null,inTime:null,outTime:null,houseId:null,hotelName:null,houseName:null,userId:null,cost:null,username:null})
    const scrollToTable = () => {
        const tableElement = document.querySelector('.el-table');
        if (tableElement) {
            tableElement.scrollIntoView({behavior: 'smooth'});
        }
    };
    const orderHouse = (house) => {
        order.value.userId = store.getters.getId;
        order.value.houseId = house.id
        order.value.hotelName = hotel.value.hotelName
        order.value.houseName = house.houseName
        order.value.username = store.getters.getRealName
        order.value.cost = house.cost;
        value1.value = null
        days.value = null;
        if (order.value.userId==null){
            ElMessageBox.alert('登录已经过期，请重新登录', '登录过期', {
                confirmButtonText: '重新登录',
                callback: () => {
                    //跳转登录页面
                    window.location.href = "/login";
                },
            })
        }else {
            dialogFormVisible.value = true;
        }

    };
    const insertOrder = ()=>{
        console.log(order)
        addOrder(order.value).then((response)=>{
            if(response.data!=null){
                dialogFormVisible.value = false;
                alert("预定成功，可在我的订单中查看")
            }
        })
    }
    const days = ref();
    /**
     * @DateDiff 计算相差的天数
     * @param Date_end 结束时间
     * @param Date_start 开始时间
     * @returns {number} 相差天数
     */
    const getDateTime = (date) => {
        let dateSpan, iDays;
        order.value.inTime = useDateFormat(date[0],"YYYY-MM-DD").value;
        order.value.outTime = useDateFormat(date[1],"YYYY-MM-DD").value;
        let sDate1 = Date.parse(getDateFilter(date[0].getTime())); // 修正：将 getDateFilter 包装的部分移动到外面
        let sDate2 = Date.parse(getDateFilter(date[1].getTime())); // 修正：将 getDateFilter 包装的部分移动到外面
        dateSpan = sDate2 - sDate1;
        dateSpan = Math.abs(dateSpan);
        iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
        return iDays; // 添加：返回计算结果
    };
    // 辅助函数：getDateFilter
    const getDateFilter = (timestamp) => {
        const date = new Date(timestamp);
        // 这里可以对日期进行一些处理，例如格式化成特定的字符串格式，示例代码省略
        return date; // 返回处理后的日期
    };
    const changetime = () => {
            days.value = getDateTime(value1.value)*order.value.cost;
            console.log(days.value)
            console.log(order.value)

    };
    const cancelOrder = () => {
        value1.value = null
        days.value = null;
        dialogFormVisible.value = false;
    };
    const goBack = () => {
        router.back();
    };
</script>

<style scoped>

</style>