<template>
    <el-row :gutter="10">
        <el-col :span="16">
            <el-scrollbar height="650px">
            <div style="background: #42b983"/>
            <div class="block text-center" m="t-4">
            <el-carousel trigger="click" height="500px" style="border-radius: 10px">
                <el-carousel-item v-for="imageUrl in imageUrls" :key="item">
                    <el-image style="width: 800px; height: 500px" :src="'/'+imageUrl.imgPath" :fit="cover" />
                </el-carousel-item>
            </el-carousel>
            </div>
            <div style="margin-top: 20px;">
                <h2 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;">推荐酒店</h2>
                <el-carousel :interval="4000" type="card" height="400px" style="padding-top: 20px">
                <el-carousel-item v-for="hotel in hotels">
                    <el-card :body-style="{ padding: '0px' }" :id="hotel.id" @click="toHotel($event)">
                        <img style="width: 400px; height: 300px" :src="'/'+hotel.imgPath" :fit="cover" />
                        <div style="padding: 14px">
                            <span>{{hotel.hotelName}}</span>
                            <div class="bottom">
                                <time class="time"></time>
                                <el-button text class="button">去看看</el-button>
                            </div>
                        </div>
                    </el-card>
                </el-carousel-item>
            </el-carousel></div>
            </el-scrollbar>
        </el-col>
        <div style="position: fixed; top: 70px; right: 0;">
            <el-col :span="8"><div style="background: #42b983" />
                <div style="border-radius: 10px; background-image: linear-gradient(to right, cornflowerblue, lightskyblue); width: 400px; height: 200px;">
                    <span style="font-family: 'your art font', cursive; color: black;font-size: 30px">查询酒店</span>
                    <div style="background: white;height: 130px;border-radius: 10px;width: 370px;margin-left: 15px">
                        <span style="font-family: 'your art font', cursive; color: black;font-size: 20px;padding-left: 15px; display: flex">目的地名称</span>
                        <el-input v-model="hotelForm.hotelName" placeholder="" style="border: none;"></el-input>
                        <div style="padding-top: 20px">
                            <el-button type="primary" :icon="Search" @click="searchHotels" :disabled="hotelForm.hotelName==null">查询</el-button>
                        </div>
                    </div>
                </div>
                <div style="border-radius: 10px;background: #42b983;height: 450px;width: 400px;margin-top: 20px">
                    <div style="margin-left: 10px;margin-top: 10px">
                        <span>酒店列表</span>
                        <el-scrollbar height="420px">
                            <el-card class="box-card" v-for="hotel in hotelsResult" style="margin-top: 10px;border-radius: 10px"  v-if="isShow">
                                <template #header>
                                    <div class="card-header" style="display: flex" :id="hotel.id" @click="toHotel($event)">
                                        <span>{{hotel.hotelName}}</span>
                                        <el-button class="button" text style="display: flex">去看看</el-button>
                                    </div>
                                </template>
                                <div><span style="color: #666;font-size: 14px;text-align: justify">酒店评分:{{hotel.score}}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp酒店电话:{{hotel.tel}}</span></div>
                                <div><span style="color: #666;font-size: 14px;">酒店地址：{{hotel.address}}</span></div>
                                <div></div>

                            </el-card>
                            <div style="height: 300px;padding-top: 100px" v-if="!isShow">
                                <p ><el-icon><CircleCloseFilled /></el-icon></p>
                                <p>抱歉，未查询到相关酒店</p>
                            </div>

                        </el-scrollbar>
                    </div>
                </div>
            </el-col>
        </div>
    </el-row>
</template>

<script setup>
    import {ref,reactive,onMounted} from 'vue'
    import {useRouter} from 'vue-router'
    import {getHotels, getImageUrl, getImageUrls, searchHotel} from "@/api/hotel";
    import { Search,CircleCloseFilled } from '@element-plus/icons-vue'
    import {getAttractions} from "@/api/attraction";
    import router from "@/router";

    const value1 = ref([]);
    const imageUrls = ref([]);
    const hotelForm = ref({id:null,hotelName:null,address:null})
    const hotels = ref([]);
    const hotelsResult = ref([]);
    const isShow = ref(true);
    onMounted(()=>{
        getImageUrls().then((response)=>{
            imageUrls.value = response.data
        })
        getHotels().then((response) => {
            hotels.value = response.data
            hotelsResult.value = response.data
            hotels.value.forEach(item => {
                getImageUrl({id: item.id}).then((response) => {
                    item.imgPath = response.data[0].imgPath;
                })
            });
            console.log(hotels)
        })
    })

    const searchHotels = ()=>{
        searchHotel(hotelForm.value).then((response)=>{
            console.log(response.data)
            hotelsResult.value = response.data
            if (hotelsResult.value.length == 0) {
                isShow.value = false;
            } else {
                isShow.value = true;
            }
            console.log(isShow.value)
        })
    }
    const toHotel = (event) => {
        const id = event.currentTarget.id
        const params = {id}
        router.push({name:'hotelDetail',params})

    };

</script>

<style scoped>
    .demonstration {
        color: var(--el-text-color-secondary);
    }

    .el-carousel__item h3 {
        color: #475669;
        opacity: 0.75;
        line-height: 150px;
        margin: 0;
        text-align: center;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n + 1) {
        background-color: #d3dce6;
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

    .box-card {
        width: 380px;
    }
</style>