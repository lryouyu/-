<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box">
            <el-card class="box-card" >
                <el-form ref="ruleFormRef" :model="hotel" :rules="rules">

                    <!--表单数据：景点文本数据-->
                    <!--prop的值，表单的key值，rules定义的规则的key值-->
                    <el-form-item label="酒店名称" prop="hotelName" >
                        <el-input v-model="hotel.hotelName" style="width:350px;"></el-input>
                    </el-form-item>
                    <el-form-item label="酒店介绍" prop="content">
                        &nbsp&nbsp&nbsp<el-input  type="textarea" v-model="hotel.content" style="width:350px;" autosize></el-input>
                    </el-form-item>
                    <el-form-item label="酒店地址" prop="address" >
                        &nbsp&nbsp&nbsp&nbsp<el-input type="textarea" v-model="hotel.address"  style="width:350px;" autosize></el-input>
                    </el-form-item>
                    <el-form-item label="酒店电话" prop="tel" >
                        &nbsp&nbsp&nbsp&nbsp<el-input v-model="hotel.tel"  style="width:350px;"></el-input>
                    </el-form-item>
                    <el-form-item label="酒店评分" prop="score">
                        <el-input v-model="hotel.score" style="width:350px;"></el-input>
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
                    <!--房型区-->
                    <el-scrollbar height="300px">
                        <el-form-item label="提供房型">
                            <el-table :data="houses" style="width: 100%">
                                <el-table-column prop="id" label="房型编号" width="180" />
                                <el-table-column prop="houseName" label="房型名称" width="180" />
                                <el-table-column prop="cost" label="每日单价" />
                                <el-table-column prop="personCount" label="入住人数" />
                                <el-table-column prop="num" label="房间总数" />
                                <el-table-column prop="" label="操作">
                                    <template #default="scope">
                                    <el-button type="warning"   @click="openDig(scope.row.id)">更新</el-button>
                                    <el-button type="danger"  @click="deleteHouseForm(scope.row.id)" >删除</el-button>
                                    </template>
                                </el-table-column>
                            </el-table>
                            <el-button style="margin-top: 12px; margin-left: 450px;" @click="insertHouse">新增</el-button>
                        </el-form-item>
                    </el-scrollbar>

                </el-form>
            </el-card>
            <el-dialog v-model="dialogFormVisible" title="新增房型">
                <el-form :model="houseForm">
                    <el-form-item label="房型名称">
                        <el-input v-model="house.houseName"/>
                    </el-form-item>
                    <el-form-item label="每日单价">
                        <el-input v-model="house.cost"/>
                    </el-form-item>
                    <el-form-item label="入住人数">
                        <el-input v-model="house.personCount"/>
                    </el-form-item>
                    <el-form-item label="房间总数">
                        <el-input v-model="house.num"/>
                    </el-form-item>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                    <el-button @click="cancelHouse">取消</el-button>
                    <el-button type="primary" @click="submitHouse">确认</el-button>
                    </span>
                </template>
            </el-dialog>
            <el-dialog v-model="dialogFormVisible1" title="新增房型">
                <el-form :model="houseForm">
                    <el-form-item label="房型名称">
                        <el-input v-model="houseForm.houseName"/>
                    </el-form-item>
                    <el-form-item label="每日单价">
                        <el-input v-model="houseForm.cost"/>
                    </el-form-item>
                    <el-form-item label="入住人数">
                        <el-input v-model="houseForm.personCount"/>
                    </el-form-item>
                    <el-form-item label="房间总数">
                        <el-input v-model="houseForm.num"/>
                    </el-form-item>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                    <el-button @click="cancelHouse1">Cancel</el-button>
                    <el-button type="primary" @click="insHouse">确认</el-button>
                    </span>
                </template>
            </el-dialog>

        </div>
    </transition>
</template>

<script setup>
    import { ref ,reactive,onMounted,onUpdated} from 'vue'
    import {useRoute,useRouter} from 'vue-router'
    import {
        getHotel,
        getHouses,
        getImageUrl,
        updateImage,
        deleteHouse,
        updateHotel, getHouse, updateHouse,addHouse
    } from "@/api/hotel";
    import {ElMessage,ElMessageBox} from "element-plus";
    import { useDateFormat } from '@vueuse/core'
    import {updateUser} from "@/api/user";
    import {updateStrategy} from "@/api/strategy";

    const route = useRoute();
    const router = useRouter();
    let hotel = ref({});
    let houses = ref();
    let imageUrls = ref();
    //被更新的house
    let house = ref({});
    let dialogFormVisible = ref(false);
    let dialogFormVisible1 = ref(false);
    const houseForm = reactive({houseName:null,cost:null,personCount:null,num:null})

    onMounted(()=>{
        //获取评论
        getHouses({id: route.params.id}).then((response)=>{
            houses.value = response.data;
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

    const submitForm = () => {
        ElMessageBox.confirm(
            '更新该酒店的信息，要继续吗',
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
                updateHotel(hotel.value);
                router.push({name: 'hotel'});
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
        router.push({name: 'hotel'});
    }

    const deleteCommentBtn = (event) => {
        const houseId = event.currentTarget.id;
        ElMessageBox.confirm(
            '删除该房型，要继续吗',
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
                deleteHouse({id:houseId})
                getHouses({id: route.params.id}).then((response)=>{
                    houses.value = response.data;
                    console.log(houses.value)
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
        hotelName: [
            {required: true, message: '酒店名称不能为空', trigger: 'blur'}
        ],
        content: [
            {required: true, message: '酒店介绍不能为空', trigger: 'blur'},
            {min: 0, max: 300, message: '酒店介绍最多不能超过300字', trigger: "blur"}
        ],
        address: [
            {required: true,message: '酒店地址不能为空',trigger: 'blur'},
            {min: 0, max: 100, message: '地址信息最多不超过100字', trigger: "blur"}
        ],address: [
            {required: true,message: '酒店地址不能为空',trigger: 'blur'},
            {min: 0, max: 100, message: '地址信息最多不超过100字', trigger: "blur"}
        ],tel: [
            {required: true,message: '酒店电话不能为空',trigger: 'blur'}
        ],
    });

    const openDig = (id)=>{
        getHouse({id:id}).then((response)=>{
            house.value = response.data;
            console.log(house.value)
        })
        dialogFormVisible.value = true;
    }
    const submitHouse = () => {
        dialogFormVisible = false;
        updateHouse(house.value);
        getHouses({id: route.params.id}).then((response)=>{
            houses.value = response.data;
            console.log(houses.value)
        })
    };
    const cancelHouse = () =>{
        dialogFormVisible.value = false;
    }
    const cancelHouse1 = () =>{
        dialogFormVisible1.value = false;
    }
    const deleteHouseForm = (id) => {
        ElMessageBox.confirm(
            '删除该房型的信息，要继续吗',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(() => {
                deleteHouse({id:id});
                getHouses({id: route.params.id}).then((response)=>{
                    houses.value = response.data;
                    console.log(houses.value)
                })
                ElMessage({
                    type: 'success',
                    message: '删除成功',
                })

            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消更新',
                })
            })
    };

    const insertHouse = () => {
        dialogFormVisible1.value = true;
    };
    const insHouse = () => {
        dialogFormVisible1.value = false;
        houseForm.hotelId = route.params.id;
        console.log(houseForm)
        addHouse(houseForm);
        getHouses({id: route.params.id}).then((response)=>{
            houses.value = response.data;
            console.log(houses.value)
        })
    };



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