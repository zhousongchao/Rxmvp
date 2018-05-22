package com.zsc.core.base.engine

import android.arch.lifecycle.LifecycleObserver

interface IPresenter<in T> : LifecycleObserver {

    /**
     *  绑定View
     */
    fun takeView(view: T)

    /**
     * 移除View
     */
    fun dropView()

}
