package com.zsc.rxmvp.di

import com.zsc.core.dagger.ActivityScoped
import com.zsc.rxmvp.mvp.common.CommonActivity
import com.zsc.rxmvp.mvp.common.CommonModule
import com.zsc.rxmvp.mvp.main.MainActivity
import com.zsc.rxmvp.mvp.main.MainModule
import com.zsc.rxmvp.mvp.tab.TabActivity
import com.zsc.rxmvp.mvp.tab.TabModule
import com.zsc.rxmvp.mvp.tab.taba.TabaModule
import com.zsc.rxmvp.mvp.tab.tabb.TabbModule
import com.zsc.rxmvp.mvp.tab.tabc.TabcModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Zsc
 * @date  2017/11/9
 * @desc
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [CommonModule::class])
    abstract fun commonActivity(): CommonActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TabModule::class, TabaModule::class,
        TabbModule::class, TabcModule::class])
    abstract fun tabActivity(): TabActivity



}
        