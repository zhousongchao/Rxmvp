package com.zsc.rxmvp.mvp.common

import com.zsc.core.dagger.ActivityScoped
import com.zsc.core.dagger.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@Module
abstract class CommonModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun commonFragment(): CommonFragment

    @ActivityScoped
    @Binds
    internal abstract fun commonPresenter(presenter: CommonContract.Presenter): CommonContract.Presenter

    /**
     * dagger注入编译成java静态方法CommonModule类名要相同
     */
    companion object CommonModule {
        @JvmStatic
        @Provides
        @ActivityScoped
        internal fun provideId(activity: CommonActivity): String {
            //测试注入
            return activity.intent.getStringExtra("1235") ?: "1236"
        }

        @JvmStatic
        @Provides
        @ActivityScoped
        internal fun provideIsAdd(activity: CommonActivity): Boolean {
            //测试注入
            return null== activity.intent.getStringExtra("1235")
        }

    }

}


