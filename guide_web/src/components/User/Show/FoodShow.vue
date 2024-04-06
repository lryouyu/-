<template>
    <el-row>
        <el-col :span="4">
            <div class="grid-content ep-bg-purple-light"><el-select v-model="value" class="m-2" placeholder="Select" size="large">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                />
            </el-select></div>
        </el-col>
        <el-col :span="16">
            <div class="grid-content ep-bg-purple">
                <el-input style="height: 40px" v-model="input" placeholder="Please input" />
            </div>
        </el-col>
        <el-col :span="4">
            <div class="grid-content ep-bg-purple-light"> <el-button style="width: 160px;height: 40px" type="primary" @click="searchFood">搜索</el-button></div>
        </el-col>
    </el-row>
    <el-row style="padding-top: 20px">
        <el-col
                v-for="food in Foods"
                :key="food.id"
                :span="8"
                :offset="index > 0 ? 2 : 0"
        >
            <el-card :body-style="{ padding: '0px' }" class="attraction-card">
                <img
                        :src="'/'+food.imgPath"
                        style="width: 100%;display: block;height:300px"
                        fit="fill"
                />
                <div style="padding: 14px;height: 250px">
                    <span style="text-align: justify">{{food.foodName}}——{{food.attName}}</span>
                    <div class="bottom">
                        <p style="color: #666;font-size: 14px;line-height: 1.8;width:350px;margin-left: 20px">{{food.content}}</p>
                    </div>
                </div>
            </el-card>
        </el-col>
    </el-row>
    <el-backtop :right="100" :bottom="100" />
</template>

<script setup>
    import {ref, reactive, onMounted} from 'vue'
    import {getFoods,getImageUrl,getFoodsByName,getFoodsByAttName} from "@/api/food";

    const value = ref("按名称搜索");
    const options = ref([
        {
            value: '按名称搜索',
            label: '按名称搜索',
        },{
            value: '按地点搜索',
            label: '按地点搜索',
        },])
    const input = ref('')

    const imageUrls = ref([]);
    const Foods = ref([]);

    onMounted(()=>{
        getFoods().then((response) => {
            Foods.value = response.data
            console.log(Foods.value)
            Foods.value.forEach(item => {
                getImageUrl({id: item.id}).then((response) => {
                    item.imgPath = response.data[0].imgPath;
                })
            });
            console.log(Foods)
        })
    })

    const searchFood = () => {
        if (input.value == null ||input.value==''){
            getFoods().then((response) => {
                Foods.value = response.data
                console.log(Foods.value)
                Foods.value.forEach(item => {
                    getImageUrl({id: item.id}).then((response) => {
                        item.imgPath = response.data[0].imgPath;
                    })
                });
                console.log(Foods)
            })
        }
        else if (value.value=='按名称搜索'){
            getFoodsByName({foodName: input.value}).then((response) => {
                if (response.data!=null) {
                    Foods.value = response.data
                    console.log(Foods.value)
                    Foods.value.forEach(item => {
                        getImageUrl({id: item.id}).then((response) => {
                            item.imgPath = response.data[0].imgPath;
                        })
                    });
                }
            });
        }
        else if (value.value=='按地点搜索'){
            getFoodsByAttName({attName: input.value}).then((response) => {
                if (response.data!=null) {
                    Foods.value = response.data
                    console.log(Foods.value)
                    Foods.value.forEach(item => {
                        getImageUrl({id: item.id}).then((response) => {
                            item.imgPath = response.data[0].imgPath;
                        })
                    });
                }
            });
        }
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