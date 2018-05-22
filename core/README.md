# RxMvp

标签（空格分隔）： android mvp kotlin plugin


a library for android make you easy to use mvp+kotlin+dagger+retrofit+rxjava

---

## 1 项目分包

```
//首字母小写为包名，首字母大写.kt文件为类
com
└── zsc【公司/个人代称】
    └── app【项目名】
        ├── XxxxApplication.kt 定义 Application 类
        ├── api 接口入口
        ├── constant
        │   ├── Constants.kt 定义配置数据（常量）
        ├── data 数据处理
        │   ├── Class.kt（定义的 model数据结构等，不含复杂操作）
        │   ├── factory 数据工厂，对数据进行加工
        │   ├── source 数据来源
        │   │   ├── local 来源于本地的数据，比如 SP，Database，File
        │   │   ├── remote 来源于远端的数据
        │   │   ├── DataSource.kt数据获取接口定义
        │   │   └── Repository.kt数据获取仓库，调用local和remote
        ├── di 依赖注入
        ├── mvp 功能入口
        │   ├── feature0 功能 0
        │   │   ├── feature0Activity.kt
        │   │   ├── feature0Fragment.kt
        │   │   ├── feature0Contract.kt
        │   │   ├── feature0Module.kt
        │   │   ├── feature0Presenter.kt
        │   │   ├── xxAdapter.kt
        │   │   └── ... 其他 .kt类
        │   └── ...其他功能
        ├── util 工具类
        └── view 自定义View

gradle
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-kapt'
apply plugin: 'kotlin-android-extensions'
    ├──project(':core')
    ├──"com.android.support.constraint:constraint-layout: 【ConstraintLayout】1.1.0"
    ├──"com.android.support:appcompat-v7:                 【v7包】26.1.0以上自带LifecycleOwner"
    ├──"com.blankj:utilcode:                              【工具类集合,可选】1.14.0"
    ├──"com.flyco.tablayout:FlycoTabLayout_Lib:           【Tab栏，可选】2.1.2@aar"
    ├──~~~~~~~~~~~~~~~~~~~~
    ├──kapt "com.google.dagger:dagger-compiler:           【dagger生成代码】2.13"
    └──kapt "com.google.dagger:dagger-android-processor:       ... "
```
依赖包:implementation 'com.zsc.core:rxmvp:1.0.0'
```
com
└── zsc
    └── core
        ├──base 【基础类Fragment、Activity、Presenter等】
        │   ├──engine【基类接口包】
        │   │   ├──IActivity.kt     【Activity基类接口】
        │   │   ├──IFragment.kt     【Fragment基类接口】
        │   │   ├──IPresenter.kt    【Presenter基类接口】
        │   │   └──IView.kt         【View基类接口】
        │   ├── BaseActivity.kt     【Activity基类】
        │   ├── BaseFragment.kt     【Fragment基类】
        │   ├── BaseMvpActivity.kt  【MvpActivity基类】
        │   ├── BaseMvpFragment.kt  【MvpFragment基类】
        │   └── BasePresenter.kt    【Presenter基类】
        ├── dagger    【Dagger辅助类】
        │   ├──ActivityScoped.kt   【dagger】
        │   └──FragmentScoped.kt   【dagger】
        ├── retrofit  【Retrofit辅助类】
        │   ├──api 【Retrofit调用接口相关类】
        │   │   ├──ApiObserver.kt  【数据观察者】
        │   │   ├──BaseUrl.kt      【用于配置RxHttp的动态baseurl和多baseurl】
        │   │   ├──ResultApi.kt    【网络请求数据实体类】
        │   ├──exception    【retrofit异常处理】
        │   │   ├──ApiError.kt         【retrofit请求错误自定义】
        │   │   ├──ApiException.kt     【retrofit请求异常类】
        │   │   └──ExceptionEngine.kt  【retrofit请求异常捕获】
        │   ├──RxHttp.kt       【retrofit网络请求简单封装】
        └── └──RxUtils.kt      【Rxjava工具】

gradle
apply plugin: 'kotlin-android'
    ├──"org.jetbrains.kotlin:kotlin-stdlib-jdk7:     【kotlin包】1.2.40"
    ├──"io.reactivex.rxjava2:rxkotlin:               【rxjava/rxkotlin】2.2.0"
    ├──"io.reactivex.rxjava2:rxandroid:              【rxandroid】2.0.1"
    ├──"com.squareup.retrofit2:adapter-rxjava2:      【retrofit2】2.3.0"
    ├──"com.squareup.retrofit2:retrofit:                 ..."
    ├──"com.squareup.retrofit2:converter-scalars:        ..."
    ├──"com.squareup.retrofit2:converter-gson:           ..."
    ├──"com.squareup.okhttp3:logging-interceptor:    【okhttp:loging】3.5.0"
    ├──"com.google.dagger:dagger:                    【dagger】2.13"
    ├──"com.google.dagger:dagger-android:                ..."
    ├──"com.google.dagger:dagger-android-support:        ..."
    └──"com.android.support:appcompat-v7:            【v7包：compileOnly】27.1.1"
```

## 2 具体使用

### 2.1 导入依赖:
```
//app的build.gradle添加
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-kapt'

//app的build.gradle的dependencies下添加
implementation 'com.zsc.core:rxmvp:1.0.0'
//添加Utils工具集合(by BlankJ)
implementation "com.blankj:utilcode:1.14.0"
//添加生成的代码插件
kapt 'com.google.dagger:dagger-compiler:2.13'
kapt 'com.google.dagger:dagger-android-processor:2.13'
```
### 2.2 添加注入类:

#### 2.2.1 项目的Application继承DaggerApplication(),示例代码如下
```
import com.blankj.utilcode.util.Utils
import com.zsc.core.retrofit.RxHttp
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import okhttp3.logging.HttpLoggingInterceptor

class MvpApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        initRxHttp()


    }

    private fun initRxHttp() {
        //RxHttp设置
        RxHttp.setGlobalBaseUrl("http://your.base.url")

        //可以自定义设置全局retrofit
        //RxHttp.retrofitBuilder=...

    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        appComponent.inject(this)
        return appComponent
    }

}

```

>注意
1:DaggerAppComponent是在配置完后,Build->Make Project自动生成的,包名是(你的项目包名.di)
2:需要在app的AndroidManifest.xml中使用该application
3:如果不继承DaggerApplication,则需要把所有DaggerApplication的代码移到该application

#### 2.2.2 项目包名.di包下添加类ActivityBindingModule
```

import com.zsc.core.dagger.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

}

```

#### 2.2.3 项目包名.di包下添加类ApplicationModule(MvpApplication是你自己的Application类名)
```
import android.app.Application
import android.content.Context

import dagger.Binds
import dagger.Module

/**
 */
@Module
abstract class ApplicationModule {
    @Binds
    abstract fun bindContext(application: Application): Context
}
```

#### 2.2.4 项目包名.di包下添加类AppComponent
```

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: MvpApplication)

    override fun inject(application: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

```

### 2.3 插件使用

>基于rxmvp库的配置

#### 2.3.1

安装MvpHelper插件

#### 2.3.2

选中包,然后点击code->MvpHelper,输入功能模块名称(首字母大写,比如User),然后确认,自动生成对应的`XxxxActivity`,`XxxxFragment`,`XxxxContract`,`XxxxPresenter`,`XxxxModule`,`xxxx_frag.xml`,自动在ActivityBindingModule.kt添加注入,自动在AndroidManifest.xml中注册`XxxxActivity`,只需要关心`XxxxContract`,`XxxxPresenter`,`XxxxFragment`中的代码逻辑



