package com.zsc.rxmvp.mvp.tab

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
abstract class TabModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun tabFragment():  TabFragment

    @ActivityScoped
    @Binds
    internal abstract fun tabPresenter(presenter: TabContract.Presenter): TabContract.Presenter
}
