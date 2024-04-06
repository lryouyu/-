<template>
    <el-row>
        <el-col :span="24">
            <h2 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;padding-top: 20px">攻略</h2>
        </el-col>
    </el-row>
    <el-row>
        <el-col :span="16">
            <el-tabs type="border-card">
                <el-tab-pane label="全部">
                    <el-scrollbar height="525px">
                        <div v-if="strategies.length==0">暂无相关攻略，去别处看看吧</div>
                        <el-card class="box-card" style="width: 740px" v-for="strategy in strategies">
                            <template #header>
                                <div class="card-header">
                                    <span>{{strategy.title}}</span>
                                    <el-button class="button" :id="strategy.id" text @click="toStrategyDetail($event)">去看看</el-button>
                                </div>
                            </template>
                            <div>
                                <el-row>
                                    <el-col :span="8" style="text-align: left">
                                        简介:{{strategy.content}}
                                    </el-col>
                                    <el-col :span="8">
                                    </el-col>
                                    <el-col :span="8">
                                        分类：{{strategy.type}}
                                    </el-col>
                                </el-row>
                            </div>
                            <div style="padding-top: 10px;">
                                <el-row>
                                    <el-col :span="8">
                                        发布时间:{{useDateFormat(strategy.time,"YYYY-MM-DD HH:mm:ss")}}
                                    </el-col>
                                    <el-col :span="8">

                                    </el-col>
                                    <el-col :span="8">
                                        点赞数：{{strategy.like}}
                                    </el-col>
                                </el-row>
                            </div>
                        </el-card>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="趣味">
                    <el-scrollbar height="525px">
                        <div v-if="strategyInterest.length==0">暂无相关攻略，去别处看看吧</div>
                        <el-card class="box-card" style="width: 740px" v-for="strategy in strategyInterest">
                            <template #header>
                                <div class="card-header">
                                    <span>{{strategy.title}}</span>
                                </div>
                            </template>
                            <div>
                                <el-row>
                                    <el-col :span="8" style="text-align: left">
                                        简介:{{strategy.content}}
                                    </el-col>
                                    <el-col :span="8">
                                    </el-col>
                                    <el-col :span="8">
                                        分类：{{strategy.type}}
                                    </el-col>
                                </el-row>
                            </div>
                            <div style="padding-top: 10px;">
                                <el-row>
                                    <el-col :span="8">
                                        发布时间:{{useDateFormat(strategy.time,"YYYY-MM-DD HH:mm:ss")}}
                                    </el-col>
                                    <el-col :span="8">

                                    </el-col>
                                    <el-col :span="8">
                                        点赞数：{{strategy.like}}
                                    </el-col>
                                </el-row>

                            </div>
                        </el-card>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="娱乐">
                    <el-scrollbar height="525px">
                        <div v-if="strategyFun.length==0">暂无相关攻略，去别处看看吧</div>
                    <el-card class="box-card" style="width: 740px" v-for="strategy in strategyFun">
                        <template #header>
                            <div class="card-header">
                                <span>{{strategy.title}}</span>
                            </div>
                        </template>
                        <div>
                            <el-row>
                                <el-col :span="8" style="text-align: left">
                                    简介:{{strategy.content}}
                                </el-col>
                                <el-col :span="8">
                                </el-col>
                                <el-col :span="8">
                                    分类：{{strategy.type}}
                                </el-col>
                            </el-row>
                        </div>
                        <div style="padding-top: 10px;">
                            <el-row>
                                <el-col :span="8">
                                    发布时间:{{useDateFormat(strategy.time,"YYYY-MM-DD HH:mm:ss")}}
                                </el-col>
                                <el-col :span="8">

                                </el-col>
                                <el-col :span="8">
                                    点赞数：{{strategy.like}}
                                </el-col>
                            </el-row>

                        </div>
                    </el-card>
                </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="度假">
                    <el-scrollbar height="525px">
                        <div v-if="strategyHoliday.length==0">暂无相关攻略，去别处看看吧</div>
                    <el-card class="box-card" style="width: 740px" v-for="strategy in strategyHoliday">
                        <template #header>
                            <div class="card-header">
                                <span>{{strategy.title}}</span>
                            </div>
                        </template>
                        <div>
                            <el-row>
                                <el-col :span="8" style="text-align: left">
                                    简介:{{strategy.content}}
                                </el-col>
                                <el-col :span="8">
                                </el-col>
                                <el-col :span="8">
                                    分类：{{strategy.type}}
                                </el-col>
                            </el-row>
                        </div>
                        <div style="padding-top: 10px;">
                            <el-row>
                                <el-col :span="8">
                                    发布时间:{{useDateFormat(strategy.time,"YYYY-MM-DD HH:mm:ss")}}
                                </el-col>
                                <el-col :span="8">

                                </el-col>
                                <el-col :span="8">
                                    点赞数：{{strategy.like}}
                                </el-col>
                            </el-row>

                        </div>
                    </el-card>
                    </el-scrollbar>

                </el-tab-pane>
                <el-tab-pane label="商务">
                    <el-scrollbar height="525px">
                        <div v-if="strategyBusiness.length==0">暂无相关攻略，去别处看看吧</div>
                        <el-card class="box-card" style="width: 740px" v-for="strategy in strategyBusiness">
                            <template #header>
                                <div class="card-header">
                                    <span>{{strategy.title}}</span>
                                </div>
                            </template>
                            <div>
                                <el-row>
                                    <el-col :span="8" style="text-align: left">
                                        简介:{{strategy.content}}
                                    </el-col>
                                    <el-col :span="8">
                                    </el-col>
                                    <el-col :span="8">
                                        分类：{{strategy.type}}
                                    </el-col>
                                </el-row>
                            </div>
                            <div style="padding-top: 10px;">
                                <el-row>
                                    <el-col :span="8">
                                        发布时间:{{useDateFormat(strategy.time,"YYYY-MM-DD HH:mm:ss")}}
                                    </el-col>
                                    <el-col :span="8">

                                    </el-col>
                                    <el-col :span="8">
                                        点赞数：{{strategy.like}}
                                    </el-col>
                                </el-row>

                            </div>
                        </el-card>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="自然">
                    <el-scrollbar height="525px">
                        <div v-if="strategyNatural.length==0">暂无相关攻略，去别处看看吧</div>

                        <el-card class="box-card" style="width: 740px" v-for="strategy in strategyNatural">
                            <template #header>
                                <div class="card-header">
                                    <span>{{strategy.title}}</span>
                                </div>
                            </template>
                            <div>
                                <el-row>
                                    <el-col :span="8" style="text-align: left">
                                        简介:{{strategy.content}}
                                    </el-col>
                                    <el-col :span="8">
                                    </el-col>
                                    <el-col :span="8">
                                        分类：{{strategy.type}}
                                    </el-col>
                                </el-row>
                            </div>
                            <div style="padding-top: 10px;">
                                <el-row>
                                    <el-col :span="8">
                                        发布时间:{{useDateFormat(strategy.time,"YYYY-MM-DD HH:mm:ss")}}
                                    </el-col>
                                    <el-col :span="8">

                                    </el-col>
                                    <el-col :span="8">
                                        点赞数：{{strategy.like}}
                                    </el-col>
                                </el-row>

                            </div>
                        </el-card>
                    </el-scrollbar>

                </el-tab-pane>
            </el-tabs>
        </el-col>
        <el-col :span="8">
            <div style="border-radius: 10px;margin-left: 30px; background-image: linear-gradient(to right, cornflowerblue, lightskyblue); width: 350px; height: 600px;">
                <span style="font-family: 'your art font', cursive; color: black;font-size: 30px">攻略排行</span>
                    <el-card class="box-card" v-for="strategy in strategyTop" style="margin-top: 10px;border-radius: 10px;width: 350px;">
                        <template #header>
                            <div class="card-header" style="display: flex" :id="strategy.id" @click="toStrategyDetail($event)">
                                <span>{{strategy.title}}</span>
                                <el-button class="button" text style="display: flex">去看看</el-button>
                            </div>
                        </template>
                        <div><span style="color: #666;font-size: 14px;text-align: justify">类别:{{strategy.type}}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp                    <svg
                                style="width:15px;height: 15px" t="1693634116772" class="icon" viewBox="0 0 1024 1024"
                                version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4010" width="200" height="200"><path
                                d="M832 364.8h-147.2s19.2-64 32-179.2c6.4-57.6-38.4-115.2-102.4-121.6h-12.8c-51.2 0-83.2 32-102.4 76.8l-38.4 96c-32 64-57.6 102.4-76.8 115.2-25.6 12.8-121.6 12.8-128 12.8H128c-38.4 0-64 25.6-64 57.6v480c0 32 25.6 57.6 64 57.6h646.4c96 0 121.6-64 134.4-153.6l51.2-307.2c6.4-70.4-6.4-134.4-128-134.4z m-576 537.6H128V422.4h128v480z m640-409.6l-51.2 307.2c-12.8 57.6-12.8 102.4-76.8 102.4H320V422.4c44.8 0 70.4-6.4 89.6-19.2 32-12.8 64-64 108.8-147.2 25.6-64 38.4-96 44.8-102.4 6.4-19.2 19.2-32 44.8-32h6.4c32 0 44.8 32 44.8 51.2-12.8 102.4-32 166.4-32 166.4l-25.6 83.2h243.2c19.2 0 32 0 44.8 12.8 12.8 12.8 6.4 38.4 6.4 57.6z"
                                p-id="4011"></path></svg>
                                {{strategy.like}}
                             </span>
                        </div>
                    </el-card>

                </div>
        </el-col>
    </el-row>
    <el-row>
        <el-col :span="18">
            <h2 style=" margin: 0;display: flex;border-bottom: 2px solid #ccc;padding-top: 20px">游记</h2>
        </el-col>

        <el-col :span="6" style="padding-top: 10px">
            <el-button type="primary" @click="createTravel">+创建游记</el-button>
        </el-col>
    </el-row>
    <el-row>
        <el-col :span="24">
            <el-tabs type="border-card" style="height: 600px;">
                <el-tab-pane label="全部">
                    <el-scrollbar height="525px">
                        <div v-if="travels.length==0"><p style="padding-top:150px;">暂无相关游记，去别处看看吧</p></div>
                        <el-row style="padding-top: 20px">
                            <el-col
                                    v-for="travel in travels"
                                    :key="travel.id"
                                    :span="8"
                                    :offset="index > 0 ? 2 : 0"
                            >
                                <el-card :body-style="{ padding: '0px' }" class="attraction-card">
                                    <img
                                            :src="'/'+travel.imgPath"
                                            style="width: 100%;display: block;height:300px"
                                            fit="fill"
                                    />
                                    <div style="padding: 14px;height: 150px">
                                        <span style="text-align: justify">{{travel.title}}</span>
                                        <p style="text-align: justify">类别：&nbsp{{travel.type}}</p>
                                        <p style="text-align: justify;color: #666;font-size: 14px;line-height: 1.8;width:350px;">简介：{{travel.introduce}}</p>
                                        <div style="margin-left: 250px">
                                            <el-popover placement="right" :width="400" trigger="hover">
                                                <template #reference>
                                                    <el-button text class="button">详细内容>></el-button>
                                                </template>
                                                <div>
                                                    <p style="color: #666;font-size: 14px;line-height: 2.8;width:350px;margin-left: 20px">{{ travel.content }}</p>
                                                </div>
                                            </el-popover>

                                        </div>
                                    </div>
                                    <div style="text-align: right ">
                                        <el-avatar
                                                :src="'/'+travel.userImgPath"
                                                style="text-align: right"
                                        />&nbsp{{travel.username}}
                                    </div>

                                </el-card>

                            </el-col>
                        </el-row>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="趣味">
                    <el-scrollbar height="525px">
                        <div v-if="travelInterest.length==0"><p style="padding-top:150px;">暂无相关游记，去别处看看吧</p></div>
                        <el-row style="padding-top: 20px">
                            <el-col
                                    v-for="travel in travelInterest"
                                    :key="travel.id"
                                    :span="8"
                                    :offset="index > 0 ? 2 : 0"
                            >
                                <el-card :body-style="{ padding: '0px' }" class="attraction-card">
                                    <img
                                            :src="'/'+travel.imgPath"
                                            style="width: 100%;display: block;height:300px"
                                            fit="fill"
                                    />
                                    <div style="padding: 14px;height: 150px">
                                        <span style="text-align: justify">{{travel.title}}</span>
                                        <p style="text-align: justify">类别：&nbsp{{travel.type}}</p>
                                        <p style="text-align: justify;color: #666;font-size: 14px;line-height: 1.8;width:350px;">简介：{{travel.introduce}}</p>
                                        <div style="margin-left: 250px">
                                            <el-popover placement="right" :width="400" trigger="hover">
                                                <template #reference>
                                                    <el-button text class="button">详细内容>></el-button>
                                                </template>
                                                <div>
                                                    <p style="color: #666;font-size: 14px;line-height: 2.8;width:350px;margin-left: 20px">{{ travel.content }}</p>
                                                </div>
                                            </el-popover>

                                        </div>
                                    </div>
                                    <div style="text-align: right ">
                                        <el-avatar
                                                :src="'/'+travel.userImgPath"
                                                style="text-align: right"
                                        />&nbsp{{travel.username}}
                                    </div>

                                </el-card>

                            </el-col>
                        </el-row>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="娱乐">
                    <el-scrollbar height="525px">
                        <div v-if="travelFun.length==0"><p style="padding-top:150px;">暂无相关游记，去别处看看吧</p></div>
                        <el-row style="padding-top: 20px">
                            <el-col
                                    v-for="travel in travelFun"
                                    :key="travel.id"
                                    :span="8"
                                    :offset="index > 0 ? 2 : 0"
                            >
                                <el-card :body-style="{ padding: '0px' }" class="attraction-card">
                                    <img
                                            :src="'/'+travel.imgPath"
                                            style="width: 100%;display: block;height:300px"
                                            fit="fill"
                                    />
                                    <div style="padding: 14px;height: 150px">
                                        <span style="text-align: justify">{{travel.title}}</span>
                                        <p style="text-align: justify">类别：&nbsp{{travel.type}}</p>
                                        <p style="text-align: justify;color: #666;font-size: 14px;line-height: 1.8;width:350px;">简介：{{travel.introduce}}</p>
                                        <div style="margin-left: 250px">
                                            <el-popover placement="right" :width="400" trigger="hover">
                                                <template #reference>
                                                    <el-button text class="button">详细内容>></el-button>
                                                </template>
                                                <div>
                                                    <p style="color: #666;font-size: 14px;line-height: 2.8;width:350px;margin-left: 20px">{{ travel.content }}</p>
                                                </div>
                                            </el-popover>

                                        </div>
                                    </div>
                                    <div style="text-align: right ">
                                        <el-avatar
                                                :src="'/'+travel.userImgPath"
                                                style="text-align: right"
                                        />&nbsp{{travel.username}}
                                    </div>

                                </el-card>

                            </el-col>
                        </el-row>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="度假">
                    <el-scrollbar height="525px">
                        <div v-if="travelHoliday.length==0"><p style="padding-top:150px;">暂无相关游记，去别处看看吧</p></div>
                        <el-row style="padding-top: 20px">
                            <el-col
                                    v-for="travel in travelHoliday"
                                    :key="travel.id"
                                    :span="8"
                                    :offset="index > 0 ? 2 : 0"
                            >
                                <el-card :body-style="{ padding: '0px' }" class="attraction-card">
                                    <img
                                            :src="'/'+travel.imgPath"
                                            style="width: 100%;display: block;height:300px"
                                            fit="fill"
                                    />
                                    <div style="padding: 14px;height: 150px">
                                        <span style="text-align: justify">{{travel.title}}</span>
                                        <p style="text-align: justify">类别：&nbsp{{travel.type}}</p>
                                        <p style="text-align: justify;color: #666;font-size: 14px;line-height: 1.8;width:350px;">简介：{{travel.introduce}}</p>
                                        <div style="margin-left: 250px">
                                            <el-popover placement="right" :width="400" trigger="hover">
                                                <template #reference>
                                                    <el-button text class="button">详细内容>></el-button>
                                                </template>
                                                <div>
                                                    <p style="color: #666;font-size: 14px;line-height: 2.8;width:350px;margin-left: 20px">{{ travel.content }}</p>
                                                </div>
                                            </el-popover>

                                        </div>
                                    </div>
                                    <div style="text-align: right ">
                                        <el-avatar
                                                :src="'/'+travel.userImgPath"
                                                style="text-align: right"
                                        />&nbsp{{travel.username}}
                                    </div>

                                </el-card>

                            </el-col>
                        </el-row>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="商务">
                    <el-scrollbar height="525px">
                        <div v-if="travelBusiness.length==0"><p style="padding-top:150px;">暂无相关游记，去别处看看吧</p></div>
                        <el-row style="padding-top: 20px">
                            <el-col
                                    v-for="travel in travelBusiness"
                                    :key="travel.id"
                                    :span="8"
                                    :offset="index > 0 ? 2 : 0"
                            >
                                <el-card :body-style="{ padding: '0px' }" class="attraction-card">
                                    <img
                                            :src="'/'+travel.imgPath"
                                            style="width: 100%;display: block;height:300px"
                                            fit="fill"
                                    />
                                    <div style="padding: 14px;height: 150px">
                                        <span style="text-align: justify">{{travel.title}}</span>
                                        <p style="text-align: justify">类别：&nbsp{{travel.type}}</p>
                                        <p style="text-align: justify;color: #666;font-size: 14px;line-height: 1.8;width:350px;">简介：{{travel.introduce}}</p>
                                        <div style="margin-left: 250px">
                                            <el-popover placement="right" :width="400" trigger="hover">
                                                <template #reference>
                                                    <el-button text class="button">详细内容>></el-button>
                                                </template>
                                                <div>
                                                    <p style="color: #666;font-size: 14px;line-height: 2.8;width:350px;margin-left: 20px">{{ travel.content }}</p>
                                                </div>
                                            </el-popover>

                                        </div>
                                    </div>
                                    <div style="text-align: right ">
                                        <el-avatar
                                                :src="'/'+travel.userImgPath"
                                                style="text-align: right"
                                        />&nbsp{{travel.username}}
                                    </div>

                                </el-card>

                            </el-col>
                        </el-row>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="自然">
                    <el-scrollbar height="525px">
                        <div v-if="travelNatural.length==0"><p style="padding-top:150px;">暂无相关游记，去别处看看吧</p></div>
                        <el-row style="padding-top: 20px">
                            <el-col
                                    v-for="travel in travelNatural"
                                    :key="travel.id"
                                    :span="8"
                                    :offset="index > 0 ? 2 : 0"
                            >
                                <el-card :body-style="{ padding: '0px' }" class="attraction-card">
                                    <img
                                            :src="'/'+travel.imgPath"
                                            style="width: 100%;display: block;height:300px"
                                            fit="fill"
                                    />
                                    <div style="padding: 14px;height: 150px">
                                        <span style="text-align: justify">{{travel.title}}</span>
                                        <p style="text-align: justify">类别：&nbsp{{travel.type}}</p>
                                        <p style="text-align: justify;color: #666;font-size: 14px;line-height: 1.8;width:350px;">简介：{{travel.introduce}}</p>
                                        <div style="margin-left: 250px">
                                            <el-popover placement="right" :width="400" trigger="hover">
                                                <template #reference>
                                                    <el-button text class="button">详细内容>></el-button>
                                                </template>
                                                <div>
                                                    <p style="color: #666;font-size: 14px;line-height: 2.8;width:350px;margin-left: 20px">{{ travel.content }}</p>
                                                </div>
                                            </el-popover>

                                        </div>
                                    </div>
                                    <div style="text-align: right ">
                                        <el-avatar
                                                :src="'/'+travel.userImgPath"
                                                style="text-align: right"
                                        />&nbsp{{travel.username}}
                                    </div>

                                </el-card>

                            </el-col>
                        </el-row>
                    </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="搜索">
                    <el-row>
                        <el-col :span="16">
                            <div class="grid-content ep-bg-purple">
                                <el-input style="height: 40px" v-model="input" placeholder="请输入要搜索的游记题目"/>
                            </div>
                        </el-col>
                        <el-col :span="4">
                            <div class="grid-content ep-bg-purple-light">
                                <el-button style="width: 160px;height: 40px" type="primary" @click="searchStrategy">搜索
                                </el-button>
                            </div>
                        </el-col>
                    </el-row>
                    <el-scrollbar height="525px">
                        <div v-if="travelResult.length==0"><p style="padding-top:150px;">暂无相关游记，去别处看看吧</p></div>
                        <el-row style="padding-top: 20px">
                            <el-col
                                    v-for="travel in travelResult"
                                    :key="travel.id"
                                    :span="8"
                                    :offset="index > 0 ? 2 : 0"
                            >
                                <el-card :body-style="{ padding: '0px' }" class="attraction-card">
                                    <img
                                            :src="'/'+travel.imgPath"
                                            style="width: 100%;display: block;height:300px"
                                            fit="fill"
                                    />
                                    <div style="padding: 14px;height: 150px">
                                        <span style="text-align: justify">{{travel.title}}</span>
                                        <p style="text-align: justify">类别：&nbsp{{travel.type}}</p>
                                        <p style="text-align: justify;color: #666;font-size: 14px;line-height: 1.8;width:350px;">简介：{{travel.introduce}}</p>
                                        <div style="margin-left: 250px">
                                            <el-popover placement="right" :width="400" trigger="hover">
                                                <template #reference>
                                                    <el-button text class="button">详细内容>></el-button>
                                                </template>
                                                <div>
                                                    <p style="color: #666;font-size: 14px;line-height: 2.8;width:350px;margin-left: 20px">{{ travel.content }}</p>
                                                </div>
                                            </el-popover>

                                        </div>
                                    </div>
                                    <div style="text-align: right ">
                                        <el-avatar
                                                :src="'/'+travel.userImgPath"
                                                style="text-align: right"
                                        />&nbsp{{travel.username}}
                                    </div>

                                </el-card>

                            </el-col>
                        </el-row>
                    </el-scrollbar>
                </el-tab-pane>

            </el-tabs>
        </el-col>
    </el-row>
    <!--评论弹出框-->
    <el-dialog v-model="dialogFormVisible1" title="新增游记">
        <el-form :model="newTravel" :rule="rules1">
            <el-form-item label="标题">
                <el-input v-model="newTravel.title"/>
            </el-form-item>
            <el-form-item label="游记类别" prop="title" >
                <el-select v-model="newTravel.type" class="m-2" placeholder="请选择类别" size="large">
                    <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                    />
                </el-select>
            </el-form-item>
            <el-form-item label="游记简介" prop="introduce">
                <el-input type="textarea" v-model="newTravel.introduce" style="width:350px;" autosize></el-input>
            </el-form-item>
            <el-form-item label="游记内容" prop="content" >
                <el-input type="textarea" v-model="newTravel.content"  style="width:350px;" autosize></el-input>
            </el-form-item>
            <el-form-item label="游记封面" prop="" >
                <el-upload
                        class="avatar-uploader"
                        name="imageFile"
                        action="/api/upload/image"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload"

                >
                    <!--图片回显-->
                    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                </el-upload>
            </el-form-item>

        </el-form>
        <template #footer>
                        <span class="dialog-footer">
                        <el-button @click="cancelCreate">Cancel</el-button>
                        <el-button type="primary" @click="insertTravel">确认</el-button>
                        </span>
        </template>
    </el-dialog>
</template>

<script setup>
    import {ref,reactive,onMounted} from 'vue'
    import {getStrategy, getStrategyById, getStrategyByType, getStrategyTop, updateStrategy} from "@/api/strategy";
    import { useDateFormat } from '@vueuse/core'
    import {useRouter} from "vue-router";
    import {useStore} from 'vuex'
    import {addTravel, getTravelByTitle, getTravelByType, getTravels, updateTravel} from "@/api/travel";
    import {ElMessage, ElMessageBox} from "element-plus";
    import { Plus } from '@element-plus/icons-vue'
    import {getUser} from "@/api/user";
    const strategies = ref([]);
    const strategyInterest = ref([]);
    const strategyFun = ref([]);
    const strategyHoliday = ref([]);
    const strategyBusiness = ref([]);
    const strategyNatural = ref([]);
    const strategyTop = ref([]);
    const store = useStore();

    const travels = ref([])
    const travelInterest = ref([]);
    const travelFun = ref([]);
    const travelHoliday = ref([]);
    const travelBusiness = ref([]);
    const travelNatural = ref([]);
    const travelResult = ref([]);
    const router = useRouter();
    const dialogFormVisible1 = ref(false);
    const newTravel = ref({id:null,title:null,type:null,introduce:null,content:null,time:null,like:0,userId:null,imgPath:null})
    const imageUrl = ref();
    const options = [
        {
            value: '趣味',
            label: '趣味',
        },
        {
            value: '娱乐',
            label: '娱乐',
        },
        {
            value: '度假',
            label: '度假',
        },
        {
            value: '商务',
            label: '商务',
        },
        {
            value: '自然',
            label: '自然',
        },
    ]
    const options1 = [{
        value: '按名称搜索',
        label: '按名称搜索',
        }]
    const rules1 = reactive({
        title: [
            {required: true, message: '标题不能为空', trigger: 'blur'},
            {min: 0, max: 15, message: '标题最多不能超过15字', trigger: "blur"}
        ],
        introduce: [
            {required: true, message: '游记简介不能为空', trigger: 'blur'},
            {min: 0, max: 100, message: '游记简介最多不能超过30字', trigger: "blur"}
        ],
        content: [
            {required: true,message: '游记内容不能为空',trigger: 'blur'},
            {min: 0, max: 200, message: '游记内容最多不超过200字', trigger: "blur"}
        ],
    });
    const input = ref();


    onMounted(()=>{
        getStrategy().then((response) => {
            strategies.value = response.data;
        });
        getStrategyTop().then((response) => {
            strategyTop.value = response.data;
        });
        getStrategyByType({type: "趣味"}).then((response) => {
            strategyInterest.value = response.data;
        });
        getStrategyByType({type: "娱乐"}).then((response) => {
            strategyFun.value = response.data;
        });

        getStrategyByType({type: "度假"}).then((response) => {
            strategyHoliday.value = response.data;
        });
        getStrategyByType({type: "商务"}).then((response) => {
            strategyBusiness.value = response.data;
        });
        getStrategyByType({type: "自然"}).then((response) => {
            strategyNatural.value = response.data;
        });
        getTravels().then((response) => {
            travels.value = response.data;
            for (let i =0; i<travels.value.length;i++) {
                getUser({id: travels.value[i].userId}).then((response) => {
                    console.log(response.data)
                    travels.value[i].username = response.data.username;
                    travels.value[i].userImgPath = response.data.imgPath;
                });
            }
            console.log(travels.value)
        });
        getTravelByType({type: "趣味"}).then((response) => {
            travelInterest.value = response.data;
            for (let i =0; i<travelInterest.value.length;i++) {
                getUser({id: travelInterest.value[i].userId}).then((response) => {
                    console.log(response.data)
                    travelInterest.value[i].username = response.data.username;
                    travelInterest.value[i].userImgPath = response.data.imgPath;
                });
            }
        });
        getTravelByType({type: "娱乐"}).then((response) => {
            travelFun.value = response.data;
            for (let i =0; i<travelFun.value.length;i++) {
                getUser({id: travelFun.value[i].userId}).then((response) => {
                    console.log(response.data)
                    travelFun.value[i].username = response.data.username;
                    travelFun.value[i].userImgPath = response.data.imgPath;
                });
            }
        });

        getTravelByType({type: "度假"}).then((response) => {
            travelHoliday.value = response.data;
            for (let i =0; i<travelHoliday.value.length;i++) {
                getUser({id: travelHoliday.value[i].userId}).then((response) => {
                    console.log(response.data)
                    travelHoliday.value[i].username = response.data.username;
                    travelHoliday.value[i].userImgPath = response.data.imgPath;
                });
            }
        });
        getTravelByType({type: "商务"}).then((response) => {
            travelBusiness.value = response.data;
            for (let i =0; i<travelBusiness.value.length;i++) {
                getUser({id: travelBusiness.value[i].userId}).then((response) => {
                    console.log(response.data)
                    travelBusiness.value[i].username = response.data.username;
                    travelBusiness.value[i].userImgPath = response.data.imgPath;
                });
            }
        });
        getTravelByType({type: "自然"}).then((response) => {
            travelNatural.value = response.data;
            for (let i =0; i<travelNatural.value.length;i++) {
                getUser({id: travelNatural.value[i].userId}).then((response) => {
                    console.log(response.data)
                    travelNatural.value[i].username = response.data.username;
                    travelNatural.value[i].userImgPath = response.data.imgPath;
                });
            }
        });

    })
    const toStrategyDetail = (event) => {
        const strId = event.currentTarget.id
        const params = {id:strId}
        console.log(params)
        router.push({name: 'strategyDetail',params})
    };
    const createTravel = () => {
        if (store.getters.getId == null) {
            ElMessageBox.alert('登录已经过期，请重新登录', '登录过期', {
                confirmButtonText: '重新登录',
                callback: () => {
                    //跳转登录页面
                    window.location.href = "/login";
                },
            });
        } else {
            dialogFormVisible1.value = true;
            imageUrl.value = null;
        }

    };
    const insertTravel = () => {
        console.log(newTravel.value)
        newTravel.value.userId = store.getters.getId
        console.log(travels)
        addTravel(newTravel.value).then((response) => {
            let travelValue = ref({});
            const temp = {...newTravel.value}
            travelValue.value = temp
            if (response.data!=null){
                travels.value.push(travelValue.value)
            }
            switch (response.data.type) {
                case "趣味":
                    travelInterest.value.push(travelValue.value);
                    break;
                case "娱乐":
                    travelFun.value.push(travelValue.value);
                    break;
                case "度假":
                    travelHoliday.value.push(travelValue.value);
                    break;
                case "商务":
                    travelBusiness.value.push(travelValue.value);
                    break;
                case "自然":
                    travelNatural.value.push(travelValue.value);
                    break;
            }
            dialogFormVisible1.value = false;
            ElMessage({
                message: '创建成功！',
                type: 'success',
            })
        });
    };
    const handleAvatarSuccess = (res) => {
        //图片上传成功的图片 要接收返回值，设置回显图片路径imageUrl
        console.log(res.data)
        imageUrl.value = res.data;
        console.log(imageUrl.value)
        newTravel.value.imgPath = imageUrl.value;
    };
    const beforeAvatarUpload= (rawFile) => {
        console.log(rawFile.size)
        if (rawFile.type !== 'image/jpeg') {
            ElMessage.error('Avatar picture must be JPG format!')
            return false
        }
        if (rawFile.size > 1048576) {
            ElMessage.error('Avatar picture size can not exceed 1MB!')
            return false
        }
        return true
    }
    const searchStrategy = () => {
        getTravelByTitle({title: input.value}).then((response) => {
            travelResult.value = response.data;
            console.log(travelResult.value)
        });
    };

</script>

<style scoped>
    .scrollbar-demo-item {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 50px;
        margin: 10px;
        text-align: center;
        border-radius: 4px;
        background: var(--el-color-primary-light-9);
        color: var(--el-color-primary);
    }
    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .avatar-uploader .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }

    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .box-card {
        width: 480px;
    }
</style>

<style>
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

    .el-icon.avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        text-align: center;
    }
</style>