package com.zsc.rxmvp.mvp.tab.tabb

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
abstract class TabbModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun tabbFragment():  TabbFragment

    @ActivityScoped
    @Binds
    internal abstract fun tabbPresenter(presenter: TabbContract.Presenter): TabbContract.Presenter
}
