package com.zsc.core.retrofit

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