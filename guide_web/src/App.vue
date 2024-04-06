<template>
    <nav>

    </nav>
    <router-view/>
</template>


<script setup>
    import {useRouter} from 'vue-router'
    import {useStore} from 'vuex'
    const store = useStore();
    const router = useRouter();


</script>

<style>

    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }



    nav a {
        font-weight: bold;
        color: #2c3e50;
    }

    nav a.router-link-exact-active {
        color: #42b983;
    }
</style>
