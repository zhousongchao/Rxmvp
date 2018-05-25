package com.zsc.core.base.engine

import android.os.Bundle
import android.support.annotation.LayoutRes

interface IFragment {
    /**
     * 初始化View
     * @return
     */
    @get:LayoutRes
    val layout: Int

    fun initLifecycle() {}
    /**
     * 初始化数据
     */
    fun initData(savedInstanceState: Bundle?)
}
