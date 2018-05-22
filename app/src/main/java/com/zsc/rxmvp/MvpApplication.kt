package com.zsc.rxmvp

import com.blankj.utilcode.util.Utils
import com.zsc.core.BuildConfig
import com.zsc.core.retrofit.RxHttp
import com.zsc.rxmvp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import okhttp3.logging.HttpLoggingInterceptor


/**
 * @author Zsc
 * @date 2018/2/10
 * @desc
 */
class MvpApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        initRxHttp()


    }

    private fun initRxHttp() {
        //RxHttp设置
        RxHttp.setGlobalBaseUrl("http://your.base.url")
                .addLoggingInterceptor(BuildConfig.DEBUG)

        //可以自定义设置retrofit
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
