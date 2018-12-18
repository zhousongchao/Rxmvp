package com.zsc.core.retrofit

import android.app.Dialog
import com.zsc.core.retrofit.api.ApiObserver
import com.zsc.core.retrofit.api.ProgressApiObserver
import com.zsc.core.retrofit.api.ResultApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Zsc
 * @date   2018/4/14
 * @desc
 */
fun <T> Observable<T>.io2Main(): Observable<T> {
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

/**
 * 网络请求的简单处理
 */
fun <T> Observable<ResultApi<T>>.subscribeX(
        r: (T) -> Unit,
        e: ((String) -> Unit)? = null) {
    subscribe(object : ApiObserver<T> {
        override fun onSuccess(t: T) {
            r(t)
        }

        override fun onFail(msg: String) {
            if (e == null) {
                RxHttp.apiInterceptor.doOnError(msg)
            } else {
                e(msg)
            }
        }

    })
}

/**
 *  附带弹窗的网络请求的处理
 */
fun <T> Observable<ResultApi<T>>.subscribeP(
        dialog: Dialog,
        r: (T) -> Unit,
        e: ((String) -> Unit)? = null) {
    subscribe(object : ProgressApiObserver<T>(dialog) {
        override fun onSuccess(t: T) {
            r(t)
        }

        override fun onFail(msg: String) {
            if (e == null) {
                RxHttp.apiInterceptor.doOnError(msg)
            } else {
                e(msg)
            }
        }

    })
}