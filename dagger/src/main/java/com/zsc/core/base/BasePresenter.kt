package com.zsc.core.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent

import com.zsc.core.base.engine.IPresenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Zsc
 * @date 2017/11/10
 * @desc
 */

open class BasePresenter<T> : IPresenter<T> {
    /** Mvp的V  */
    protected var mView: T? = null
    /** 观察者管理器 */
    protected val mCompositeDisposable by lazy { CompositeDisposable() }

    protected fun dispose() {
        mCompositeDisposable.dispose()
    }

    /**
     * 加入观察者绑定
     * @param disposable
     */
    protected fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    /**
     * 在对应的View的onCreate之后执行,获取View
     * @param view
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun takeView(view: T) {
        mView = view
    }

    /**
     * 在对应的View的onDestroy之前执行,释放View
     * 并解除所有的观察
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun dropView() {
        mView = null
        dispose()
    }
}
