package com.zsc.core.base.engine

import android.app.Activity
import android.os.Bundle
import android.support.annotation.LayoutRes

interface IActivity {

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

    /**
     * 绑定生命周期
     */
    fun initLifecycle() {}

    /**
     * 设置Window,在setContentView之前
     */
    fun setWindow() {}
    /**
     * 是否使用Fragment，默认使用
     * @return
     */
    fun useFragment(): Boolean = true


}
