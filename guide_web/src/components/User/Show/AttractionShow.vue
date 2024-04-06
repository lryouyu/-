<template>
    <el-row :gutter="10">
        <el-col :span="16">
            <el-scrollbar height="650px">
                <div style="background: #42b983"/>
                <div class="block text-center" m="t-4">
                    <el-carousel trigger="click" height="500px" style="border-radius: 10px">
                        <el-carousel-item v-for="imageUrl in imageUrls" :key="item">
                            <!--                    <img v-if="imageUrl.imgPath" :src="'http://localhost:8080/'+imageUrl.imgPath" class="avatar" :fit="cover" />-->
                            <el-image style="width: 800px; height: 500px" :src="'http://localhost:8080/'+imageUrl.imgPath" :fit="cover" />
                        </el-carousel-item>
                    </el-carousel>
                </div>
                <div style="margin-top: 20px;">
                    <h2 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;">推荐景点</h2>
                    <el-row>
                        <el-col
                                v-for="attraction in attractions"
                                :key="attraction.id"
                                :span="8"
                                :offset="index > 0 ? 2 : 0"
                        >
                            <el-card :body-style="{ padding: '0px' }" class="attraction-card">
                                <img
                                        :src="'/'+attraction.imgPath"
                                        style="width: 100%;display: block;height:180px"
                                        fit="fill"
                                />
                                <div style="padding: 14px">
                                    <span style="text-align: justify">{{attraction.attName}}&nbsp&nbsp&nbsp评分{{attraction.score}}</span>
                                    <div class="bottom">
                                        <time class="time">{{ currentDate }}</time>
                                        <el-button text class="button" :id="attraction.id" @click="toAttraction($event)">去看看>></el-button>
                                    </div>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                    </div>
            </el-scrollbar>
        </el-col>
        <div style="position: fixed; top: 70px; right: 0;">
            <el-col :span="8"><div style="background: #42b983" />
                <div style="border-radius: 10px; background-image: linear-gradient(to right, cornflowerblue, lightskyblue); width: 400px; height: 200px;">
                    <span style="font-family: 'your art font', cursive; color: black;font-size: 30px">查询景点</span>
                    <div style="background: white;height: 130px;border-radius: 10px;width: 370px;margin-left: 15px">
                        <span style="font-family: 'your art font', cursive; color: black;font-size: 20px;padding-left: 15px; display: flex">目的地名称</span>
                        <el-input v-model="attractionForm.attName" placeholder="" style="border: none;"></el-input>
                        <div style="padding-top: 20px">
                            <el-button type="primary" :icon="Search" @click="searchAttractions" :disabled="attractionForm.attName==null">查询</el-button>
                        </div>
                    </div>
                </div>
                <div style="border-radius: 10px;background: #42b983;height: 450px;width: 400px;margin-top: 20px">
                    <div style="margin-left: 10px;margin-top: 10px">
                        <span>景点列表</span>
                        <el-scrollbar height="420px">
                            <el-card class="box-card" v-for="attraction in attractionsResult" style="margin-top: 10px;border-radius: 10px" v-if="isShow">
                                <template #header>
                                    <div class="card-header" style="display: flex" :id="attraction.id" @click="toAttraction($event)">
                                        <span>{{attraction.attName}}</span>
                                        <el-button class="button" text style="display: flex">去看看</el-button>
                                    </div>
                                </template>
                                <div><span style="color: #666;font-size: 14px;text-align: justify">景点星级:{{attraction.star}}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp官方电话:{{attraction.tel}}</span></div>
                                <div><span style="color: #666;font-size: 14px;">景点地址：{{attraction.address}}</span></div>
                            </el-card>
                            <div style="height: 300px;padding-top: 100px" v-if="!isShow">
                                <p ><el-icon><CircleCloseFilled /></el-icon></p>
                                <p>抱歉，未查询到相关景点</p>
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
    import {} from 'vue-router'
    import { Search, } from '@element-plus/icons-vue'
    import {getAttractions,getImageUrls,searchAttraction,getImageUrl} from "@/api/attraction";
    import router from "@/router";

    const value1 = ref([]);
    const imageUrls = ref([]);
    const attractionForm = ref({id:null,hotelName:null,address:null})
    const attractions = ref([]);
    const attractionsResult = ref([]);
    const isShow = ref(true)
    onMounted(()=>{
        getImageUrls().then((response)=>{
            imageUrls.value = response.data
        })
        getAttractions().then((response) => {
            attractions.value = response.data
            attractionsResult.value = response.data
            attractions.value.forEach(item => {
                getImageUrl({id: item.id}).then((response) => {
                    item.imgPath = response.data[0].imgPath;
                })
            });
            console.log(attractions)
        })
    })

    const searchAttractions = ()=>{
        searchAttraction(attractionForm.value).then((response)=>{
            console.log(response.data)
            attractionsResult.value = response.data
            if (attractionsResult.value.length == 0) {
                isShow.value = false;
            } else {
                isShow.value = true;
            }
            console.log(isShow.value)
        })
    }
    const toAttraction = (event) => {
        const id = event.currentTarget.id
        const params = {id}
        router.push({name:'attractionDetail',params})

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
    .time {
        font-size: 12px;
        color: #999;
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .button {
        padding: 0;
        min-height: auto;
    }

    .attraction-card {
        transition: transform 0.3s ease-in-out;
        box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
    }

    .attraction-card:hover {
        transform: translateY(-5px);
        box-shadow: 0px 6px 10px rgba(0, 0, 0, 0.2); /* 悬浮时的阴影效果 */
    }

</style>