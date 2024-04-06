const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    //解决跨域 使用前端代理
    proxy: {
      '/api': {
        target:"http://localhost:8081",
        changeOrigin: true, //允许跨域
        //路径重写
        pathRewrite:{
          '^/api': '' //  /api重写成空字符串
        }
      },
      '/image': {
        target:"http://localhost:8081",
        changeOrigin: true, //允许跨域
        //路径重写
        // pathRewrite:{
        //     '^/api': '' //  /api重写成空字符串
        // }
      }

    }
  }
})
