package com.zsc.core.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.zsc.core.R
import com.zsc.core.base.engine.IPresenter
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import dagger.internal.Beta
import javax.inject.Inject


/**
 * BaseActivity和DaggerActivity的合并,并且注入了泛型Presenter
 * @author Zsc
 * @date 2017/10/4
 */
@Beta
abstract class BaseMvpActivity<P : IPresenter<*>> : BaseActivity(),
        HasFragmentInjector, HasSupportFragmentInjector {
    @Inject
    protected lateinit var mPresenter: P

    override val layout: Int
        get() = R.layout.mvp_act
    /**
     * DaggerActivity添加
     */
    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<android.app.Fragment>

    /**
     * 绑定生命周期
     */
    override fun initLifecycle() {
        if (!useFragment()) {
            lifecycle.addObserver(mPresenter)
        }
    }

    /**
     * DaggerActivity添加
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    /**
     * DaggerActivity添加
     */
    override fun supportFragmentInjector() = supportFragmentInjector

    /**
     * DaggerActivity添加
     */
    override fun fragmentInjector() = frameworkFragmentInjector


}
