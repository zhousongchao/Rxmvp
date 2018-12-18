package com.zsc.core.retrofit.api

import android.app.Dialog
import io.reactivex.disposables.Disposable

/**
 * @author Zsc
 * @date 2018/6/14
 * @desc 带进度的网络请求观察者
 * dialog随请求自动关闭
 */
abstract class ProgressApiObserver<T> constructor(var dialog: Dialog)
    : ApiObserver<T> {

    var disposable: Disposable? = null

    init {
        dialog.setOnCancelListener {
            disposable?.dispose()
        }
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onError(throwable: Throwable) {
        dialog.dismiss()
        super.onError(throwable)
    }

    override fun onNext(resultApi: ResultApi<T>) {
        dialog.dismiss()
        super.onNext(resultApi)
    }

}
