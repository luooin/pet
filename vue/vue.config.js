const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 80
  },
  chainWebpack: config =>{
    config.plugin('html')
        .tap(args => {
          args[0].title = "宠物领养平台";
          return args;
        })
  }
})
