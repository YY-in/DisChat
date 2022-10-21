# DisChat

一个Discord的 Jetpack Compose 的实现

# 组件
**网络组件**
[Ktro](https://ktor.io/)
Ktor 是一个使用强大的 Kotlin 语言在互联系统中构建异步服务器与客户端的框架。
轻松创建微服务、多平台HTTP客户端应用程序。
参考网站：[https://ktor.kotlincn.net/]

**UI组件**
[compose-shimmer](https://ktor.kotlincn.net/)
一个提供Shimmer的Jetpack Compose库

[coil](https://coil-kt.github.io/coil/)
一个利用 kotin Corountines 实现的图片加载库


# 主要界面设计

界面设计基于Jetpack Compose 和 Material Design3的标准，融合了部分Material You 的组件

## 注册与登录

![欢迎界面](https://qiniu.yyin.top/welcomeLayout_v2.png "欢迎界面")

![登录界面](https://qiniu.yyin.top/loginLayout.png "登录界面")

![注册界面](https://qiniu.yyin.top/registerLayout_v1.png "注册界面")

![忘记密码界面](https://qiniu.yyin.top/forgetLayout_v1.png "忘记密码界面")

![更改密码界面](https://qiniu.yyin.top/changePasswordLayout_v1.png "更改密码界面")

## 聊天界面

![频道加载完成聊天室主界面](https://qiniu.yyin.top/20221005201433.png "频道加载完成聊天室主界面")

![频道加载中聊天室主界面](https://qiniu.yyin.top/20221005202008.png "频道加载中聊天室主界面")


![频道未选择提示界面](https://qiniu.yyin.top/20221005203248.png "频道未选择提示界面")

![频道加载出现问题](https://qiniu.yyin.top/20221005203854.png "频道加载出现问题")

## 导览选择界面
![当前用户已加载状态](https://qiniu.yyin.top/currentUserItemLoadedState.png)

![当前用户加载状态](https://qiniu.yyin.top/currentUserItemLoadingState.png)

![工会列表加载状态](https://qiniu.yyin.top/guildsListLoading.png)

![工会列表已加载状态](https://qiniu.yyin.top/guildsListLoaded.png)

![频道列表加载状态](https://qiniu.yyin.top/guildsChannelsLoading.png)

![频道列表已加载状态](https://qiniu.yyin.top/guildsChannelsLoaded.png)