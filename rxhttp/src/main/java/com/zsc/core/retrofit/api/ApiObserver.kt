package com.zsc.core.retrofit.api

import com.zsc.core.retrofit.RxHttp
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author Zsc
 * @date 2018/2/14
 * @desc 简洁的网络请求观察者
 */
interface ApiObserver<T> : Observer<ResultApi<T>> {

    fun onSuccess(t: T)

    fun onFail(msg: String)

    override fun onSubscribe(d: Disposable) {}

    override fun onComplete() {}

    override fun onError(throwable: Throwable) {
        RxHttp.apiInterceptor.handleError(this, throwable)
    }

    override fun onNext(resultApi: ResultApi<T>) {
        RxHttp.apiInterceptor.handleNext(this, resultApi)
    }
}
