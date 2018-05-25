package com.zsc.core.base

import android.content.Context
import android.support.v4.app.Fragment

import com.zsc.core.base.engine.IPresenter

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import dagger.internal.Beta

/**
 * BaseFragment结合DaggerFragment,注入IPresenter
 * @author Zsc
 * @date 2017/11/11
 * @desc
 */
@Beta
abstract class BaseMvpFragment<P : IPresenter<*>> : BaseFragment(), HasSupportFragmentInjector {

    @Inject
    protected lateinit var mPresenter: P

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun initLifecycle() {
        lifecycle.addObserver(mPresenter)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return childFragmentInjector
    }
}
