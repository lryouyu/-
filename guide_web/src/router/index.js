import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    children: [
      {
        path: '/hotelshow',
        name: 'hotelshow',
        component: () => import('@/components/User/Show/HotelShow')
      },
      {
        path: '/hotelDetail/:id',
        name: 'hotelDetail',
        component: () => import('@/components/User/Detail/HotelDetail')
      },
      {
        path: '/attractionshow',
        name: 'attractionshow',
        component: () => import('@/components/User/Show/AttractionShow')
      },
      {
        path: '/attractionDetail/:id',
        name: 'attractionDetail',
        component: () => import('@/components/User/Detail/AttractionDetail')
      },
      {
        path: '/foodshow',
        name: 'foodshow',
        component: () => import('@/components/User/Show/FoodShow')
      },
      {
        path: '/articleshow',
        name: 'articleshow',
        component: () => import('@/components/User/Show/ArticleShow')
      },
      {
        path: '/strategyDetail/:id',
        name: 'strategyDetail',
        component: () => import('@/components/User/Detail/StrategyDetail')
      },
      {
        path: '/busshow',
        name: 'busshow',
        component: () => import('@/components/User/Show/BusShow')
      },
      {
        path: '/usershow',
        name: 'usershow',
        component: () => import('@/components/User/Show/UserShow')
      },
      {
        path: '/ordershow',
        name: 'ordershow',
        component: () => import('@/components/User/Show/OrderShow')
      },
      {
        path: '/ticketshow',
        name: 'ticketshow',
        component: () => import('@/components/User/Show/TicketShow')
      },
      {
        path: '/travelshow',
        name: 'travelshow',
        component: () => import('@/components/User/Show/TravelShow')
      }
    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: ()=>import('@/views/Login')
  },
  {
    path: '/find',
    name: 'find',
    component: ()=>import('@/views/FindPassword')
  },
  {
    path: '/admin',
    name: 'admin',
    component: ()=>import('@/views/Admin'),
    children: [
      {
        path: '/admin/userlist',
        name: 'userlist',
        component: ()=>import('@/components/Admin/UserList')
      },
      {
        path: '/admin/attraction',
        name: 'attraction',
        component: ()=>import('@/components/Admin/Attraction/AttractionList')
      },
      {
        path: '/admin/insertAtt',
        name: 'insertAtt',
        component: ()=>import('@/components/Admin/Attraction/InsertAtt')
      },
      {
        path: '/admin/strategy',
        name: 'strategy',
        component: ()=>import('@/components/Admin/Strategy/StrategyList')
      },
      {
        path: '/admin/insertStr',
        name: 'insertStr',
        component: ()=>import('@/components/Admin/Strategy/InsertStrategy')
      },
      {
        path: '/admin/travel',
        name: 'travel',
        component: ()=>import('@/components/Admin/Travel')
      },
      {
        path: '/admin/bus',
        name: 'bus',
        component: ()=>import('@/components/Admin/Bus/BusList')
      },
      {
        path: '/admin/insertBus',
        name: 'insertBus',
        component: ()=>import('@/components/Admin/Bus/InsertBus')
      },
      {
        path: '/admin/food',
        name: 'food',
        component: ()=>import('@/components/Admin/Food/FoodList')
      },
      {
        path: '/admin/insertFood',
        name: 'insertFood',
        component: ()=>import('@/components/Admin/Food/InsertFood')
      },
      {
        path: '/admin/ticket',
        name: 'ticket',
        component: ()=>import('@/components/Admin/Ticket/TicketList')
      },
      {
        path: '/admin/insertTicket',
        name: 'insertTicket',
        component: ()=>import('@/components/Admin/Ticket/InsertTicket')
      },
      {
        path: '/admin/insertHotel',
        name: 'insertHotel',
        component: ()=>import('@/components/Admin/Hotel/InsertHotel')
      },
      {
        path: '/admin/hotel',
        name: 'hotel',
        component: ()=>import('@/components/Admin/Hotel/HotelList')
      },
      {
        path: '/admin/order',
        name: 'order',
        component: () => import('@/components/Admin/Hotel/Order')
      },
      {
        path: '/admin/updateUser/:id',
        name:'updateUser',
        component: ()=>import('@/components/Admin/Update/UpdateUser')
      },
      {
        path: '/admin/updateAttraction/:id',
        name:'updateAttraction',
        component: ()=>import('@/components/Admin/Update/UpdateAttraction')
      },
      {
        path: '/admin/updateStrategy/:id',
        name:'updateStrategy',
        component: ()=>import('@/components/Admin/Update/UpdateStrategy')
      },
      {
        path: '/admin/updateTravel/:id',
        name:'updateTravel',
        component: ()=>import('@/components/Admin/Update/UpdateTravel')
      },
      {
        path: '/admin/updateBus/:id',
        name:'updateBus',
        component: ()=>import('@/components/Admin/Update/UpdateBus')
      },
      {
        path: '/admin/updateFood/:id',
        name:'updateFood',
        component: ()=>import('@/components/Admin/Update/UpdateFood')
      },
      {
        path: '/admin/updateTicket/:id',
        name:'updateTicket',
        component: ()=>import('@/components/Admin/Update/UpdateTicket')
      },
      {
        path: '/admin/useTicket',
        name: 'userTicket',
        component: ()=>import('@/components/Admin/Ticket/UseTicketList')
      },
      {
        path: '/admin/updateHotel/:id',
        name:'updateHotel',
        component: ()=>import('@/components/Admin/Update/UpdateHotel')
      }
    ]
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
export default router
