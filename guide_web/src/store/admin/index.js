import Cookies from 'js-cookie'
const cookieId = "JSESSIONID"
const admin = {
    state: {
        id: null,
        username: "",
        token: Cookies.get(cookieId),// 获取名为 JSESSIONID 的 cookie 数据 作为token使
    },
    getters: {
        getAdminId(state) {
            return state.id
        },
        getAdminUsername(state) {
            return state.username;
        }

    },
    mutations: {
        saveAdmin(state,admin) {
            state.id = admin.id;
            state.username = admin.username;
        }
    },
    actions: {
    }
};
export default admin