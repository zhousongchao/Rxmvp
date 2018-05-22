package com.zsc.rxmvp.mvp.tab.taba

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
abstract class TabaModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun tabaFragment():  TabaFragment

    @ActivityScoped
    @Binds
    internal abstract fun tabaPresenter(presenter: TabaContract.Presenter): TabaContract.Presenter
}
