package com.zsc.rxmvp.mvp.main

import android.provider.SyncStateContract
import com.zsc.core.dagger.ActivityScoped
import com.zsc.core.dagger.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import retrofit2.http.Field

/**
 * @author Zsc
 * @date 2018-04-24
 * @desc
 */
@Module
abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun mainFragment(): MainFragment

    @ActivityScoped
    @Binds
    internal abstract fun mainPresenter(presenter: MainContract.Presenter): MainContract.Presenter


}
