package com.zsc.core.base.engine

import android.app.Activity
import android.os.Bundle
import android.support.annotation.LayoutRes

interface IActivity {

    /**
     * 是否侧滑退出
     */
    val isSwipeBack
        get() = true

    /**
     * 初始化 View,如果initView返回0,框架则不会调用[Activity.setContentView]
     * @return
     */
    @get:LayoutRes
    val layout: Int

    /**
     * 初始化数据
     * @param savedInstanceState
     */
    fun initData(savedInstanceState: Bundle?)


}
