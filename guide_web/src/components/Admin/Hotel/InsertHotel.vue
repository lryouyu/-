<template>
    <transition name="el-zoom-in-bottom">
        <div v-show="true" class="transition-box" style="height: auto">
            <el-card class="box-card" >
                <el-form ref="ruleFormRef" :model="hotel" :rules="rules">
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
                    <!--图片-->
                    <el-form-item label="点击可上传酒店图片">
                        <el-upload
                                v-for="imageUrl in imageUrls"
                                class="avatar-uploader"
                                name="imageFile"
                                action="/api/upload/image"
                                :show-file-list="false"
                                :on-success="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload"
                                :key="imageUrl.key"
                                @click="updateurl(imageUrl)"
                        >
                            <!--图片回显-->
                            <img v-if="imageUrl.imgPath" :src="'/'+imageUrl.imgPath" class="avatar" />
                            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                        </el-upload>
                    </el-form-item>

                    <!--房型-->
                    <el-scrollbar height="300px">
                        <el-form-item label="提供房型">
                            <el-table :data="houses" style="width: 100%">
                                <el-table-column prop="houseName" label="房型名称" width="180" />
                                <el-table-column prop="cost" label="每日单价" />
                                <el-table-column prop="personCount" label="入住人数" />
                                <el-table-column prop="num" label="房间总数" />
                                <el-table-column prop="introduce" label="房间介绍" />
                                <el-table-column prop="" label="操作">
                                    <template #default="scope">
                                        <el-button type="danger"  @click="deleteHouseForm(scope.row.houseName)" >删除</el-button>
                                    </template>
                                </el-table-column>
                            </el-table>
                            <el-button style="margin-top: 12px; margin-left: 450px;" @click="insertHouse">新增</el-button>
                        </el-form-item>
                    </el-scrollbar>
                    <el-form-item>
                        <el-button style="margin-top: 12px; margin-left: 200px;" @click="submitForm">确认</el-button>
                        <el-button style="margin-top: 12px; margin-left: 200px;" @click="cancel">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-card>
            <!--弹出框-->
            <el-dialog v-model="dialogFormVisible1" title="新增房型">
                <el-form :model="houseForm" :rule="rules1">
                    <el-form-item label="房型名称">
                        <el-input v-model="houseForm.houseName"/>
                    </el-form-item>
                    <el-form-item label="每日单价">
                        <el-input v-model="houseForm.cost"/>
                    </el-form-item>
                    <el-form-item label="入住人数">
                        <el-input-number v-model="houseForm.personCount" :min="1" :step="1"/>
                    </el-form-item>
                    <el-form-item label="房间总数">
                        <el-input-number v-model="houseForm.num" :min="1" :step="5"/>
                    </el-form-item>
                    <el-form-item label="房间介绍">
                        <el-input v-model="houseForm.introduce" />
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


    import {reactive, ref} from "vue";
    import {ElMessage, ElMessageBox} from "element-plus";
    import { Plus } from '@element-plus/icons-vue'
    import {useRouter} from 'vue-router'
    import {addHotel, addHouse, addImage} from "@/api/hotel";
    let hotel = ref({id:null,hotelName:null,content:null,address:null,score:null,tel:null});
    const imageUrls = ref([
        {key:1,imgPath:null,attId:null},{key:2,imgPath:null,attId:null},
        {key:3,imgPath:null,attId:null},{key:4,imgPath:null,attId:null},
        {key:5,imgPath:null,attId:null},{key:6,imgPath:null,attId:null},
        {key:7,imgPath:null,attId:null},{key:8,imgPath:null,attId:null},
        {key:9,imgPath:null,attId:null},{key:10,imgPath:null,attId:null}
    ]);
    const dialogFormVisible1= ref(false)
    const houseForm = ref({houseName:null,cost:null,personCount:null,num:null,introduce:null})
    let houses = ref([]);
    const imgId=ref();
    const router = useRouter();
    const updateurl = (imageUrl) => {
        console.log(imageUrl);
        imgId.value=imageUrl.key;
    };

    const updateUrls = reactive([])
    const handleAvatarSuccess = (response,event) => {
        //图片上传成功的图片 要接收返回值，设置回显图片路径imageUrl
        for (let i=0; i< 10;i++){
            if (imgId.value==imageUrls.value[i].key){
                imageUrls.value[i].imgPath = response.data;
                updateUrls.push(imageUrls.value[i])
            }
        }
        console.log(updateUrls)
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

    const rules = reactive({
        hotelName: [
            {required: true, message: '酒店名称不能为空', trigger: 'blur'}
        ],
        content: [
            {required: true, message: '酒店介绍不能为空', trigger: 'blur'},
            {min: 0, max: 250, message: '酒店介绍最多不能超过250字', trigger: "blur"}
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
    const submitForm = () => {
        ElMessageBox.confirm(
            '新增该酒店，要继续吗',
            '警告',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(() => {
                addHotel(hotel.value).then(
                    (response)=>{
                        let hotelId = response.data.id;
                        for (let i = 0; i < updateUrls.length;i++){
                            updateUrls[i].hotelId = hotelId;
                            addImage(updateUrls[i])
                        }
                        for (var i= 0;i<houses.value.length;i++){
                            houses.value[i].hotelId=hotelId
                            addHouse(houses.value[i])
                        }
                    })

                ElMessage({
                    type: 'success',
                    message: '添加完成',
                })
                console.log(111)
                router.push({name: 'hotel'});
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消更新',
                })
            })

    };
    const cancel = () => {
        router.push({name: 'hotel'});
    };

    const rules1 = reactive({
        houseName: [
            {required: true, message: '房型名称不能为空', trigger: 'blur'}
        ],
        cost: [
            {required: true, message: '每日单价不能为空', trigger: 'blur'},
        ],
        personCount: [
            {required: true,message: '入住人数不能为空',trigger: 'blur'},
        ],
        num: [
            {required: true,message: '房间数目不能为空',trigger: 'blur'}
        ],
        introduce: [
            {required: true, message: '房屋介绍不能为空', trigger: 'blur'}
        ],
    });

    const insertHouse = () => {
        dialogFormVisible1.value = true;
        houseForm.value.houseName = null;
        houseForm.value.personCount = null;
        houseForm.value.cost = null;
        houseForm.value.num = null;
        houseForm.value.introduce = null;
    };
    const insHouse = () => {
        let houseValue = ref({});
        const temp = {...houseForm.value}
        houseValue.value = temp
        houses.value.push(houseValue.value)
        console.log(houses.value)
        dialogFormVisible1.value = false;
    };
    const deleteHouseForm = (houseName) => {
        for (var i = 0;i<houses.value.length;i++){
            if (houses.value[i].houseName==houseName){
                houses.value.splice(i,1);
            }
        }
    };

</script>

<style scoped>

    .transition-box {
        background-color: white;
        height: 600px;
        border-radius: 10px;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
    .avatar-uploader {
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