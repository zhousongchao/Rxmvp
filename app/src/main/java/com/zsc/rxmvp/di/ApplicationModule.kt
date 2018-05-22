package com.zsc.rxmvp.di

import android.app.Application
import android.content.Context

import dagger.Binds
import dagger.Module

/**
 */
@Module
abstract class ApplicationModule {
    /** expose Application as an injectable context  */
    @Binds
    abstract fun bindContext(application: Application): Context
}

