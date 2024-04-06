import Cookies from 'js-cookie'
const cookieId = "JSESSIONID"
const user = {
    state: {
        id: null,
        username: "",
        password:"",
        imgPath:"",
        realName: "",
        tel: null,
        age: null,
        sex: null,
        token: Cookies.get(cookieId),// 获取名为 JSESSIONID 的 cookie 数据 作为token使
        card: null,
    },
    getters: {
        getId(state) {
            return state.id
        },
        getUsername(state) {
            return state.username;
        },
        getPassword(state) {
            return state.password;
        },
        getImgPath(state) {
            return state.imgPath;
        },
        getRealName(state) {
            return state.realName;
        },
        getTel(state) {
            return state.tel;
        },
        getAge(state) {
            return state.age;
        },
        getSex(state) {
            return state.sex;
        },
        getCard(state) {
            return state.card;
        },
    },
    mutations: {
        saveUser(state,user) {
            state.id = user.id;
            state.username = user.username;
            state.password = user.password;
            state.imgPath = user.imgPath;
            state.realName = user.realName;
            state.tel = user.tel;
            state.age = user.age;
            state.sex = user.sex;
            state.card = user.card;
        },
    },
    actions: {
    }
};
export default user