package com.zsc.core.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.zsc.core.base.engine.IPresenter
import dagger.Lazy
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import dagger.internal.Beta
import javax.inject.Inject

/**
 * BaseActivity和DaggerActivity的合并,并且注入了泛型Presenter和Fragment
 * @author Zsc
 * @date 2017/10/4
 */
@Beta
abstract class BaseMvpActivity<P : IPresenter<*>, F : BaseMvpFragment<*>> : BaseActivity(),
        HasFragmentInjector, HasSupportFragmentInjector {
    @Inject
    protected lateinit var mPresenter: P

    @Inject
    lateinit var mFragmentProvider: Lazy<F>

    override val layout = 0
    /**
     * DaggerActivity添加
     */
    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<android.app.Fragment>

    /**
     * 初始化
     */
    override fun initData(savedInstanceState: Bundle?) {
        initFragment()
    }

    /**
     * 加载Fragment到Activity
     */
    open fun initFragment() {
        val fragment = supportFragmentManager
                .findFragmentById(android.R.id.content) ?: mFragmentProvider.get()
        if (!fragment.isAdded) {
            supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, fragment)
                    .commit()
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
