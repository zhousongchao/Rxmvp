package com.zsc.core.retrofit.api

import com.zsc.core.retrofit.RxHttp
import com.zsc.core.retrofit.exception.ApiException
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
        onFail(RxHttp.exceptionHandle.handleErrorMsg(throwable))
    }

    override fun onNext(resultApi: ResultApi<T>) {
        if (resultApi.code != 200 || resultApi.data == null) {
            onFail(resultApi.msg
                    ?: RxHttp.exceptionHandle.handleErrorMsg(ApiException.EMPTY_ERROR))
        } else onSuccess(resultApi.data!!)
    }
}
