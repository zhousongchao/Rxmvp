package com.zsc.rxmvp

import com.zsc.core.retrofit.RxHttp
import com.zsc.rxmvp.di.DaggerAppComponent
import com.zsc.rxmvp.utils.ToastUtils
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


/**
 * @author Zsc
 * @date 2018/2/10
 * @desc
 */
class MvpApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        ToastUtils.init(this)
        initRxHttp()

    }

    private fun initRxHttp() {
        //RxHttp设置
        RxHttp.globalBaseUrl = "http://your.base.url"
        RxHttp.addLoggingInterceptor(BuildConfig.DEBUG)

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
