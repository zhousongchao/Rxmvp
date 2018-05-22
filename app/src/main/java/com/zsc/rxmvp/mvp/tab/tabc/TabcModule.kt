package com.zsc.rxmvp.mvp.tab.tabc

import com.zsc.core.dagger.ActivityScoped
import com.zsc.core.dagger.FragmentScoped

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@Module
abstract class TabcModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun tabcFragment():  TabcFragment

    @ActivityScoped
    @Binds
    internal abstract fun tabcPresenter(presenter: TabcContract.Presenter): TabcContract.Presenter
}
