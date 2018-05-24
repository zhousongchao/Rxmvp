package com.zsc.core.retrofit.api

import com.zsc.core.retrofit.exception.ExceptionEngine
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author Zsc
 * @date 2018/2/14
 * @desc
 */

interface ApiObserver<T> : Observer<ResultApi<T>> {

    fun onSuccess(t: T)

    fun onFail(msg: String)

    override fun onSubscribe(d: Disposable) {}

    override fun onComplete() {}

    override fun onError(throwable: Throwable) {
        onFail(ExceptionEngine.handleErrorMsg(throwable))
    }

    override fun onNext(resultApi: ResultApi<T>) {
        if (resultApi.code != 200 || resultApi.data == null) {
            onFail(resultApi.msg ?: "数据返回错误")
        } else onSuccess(resultApi.data!!)
    }

}
