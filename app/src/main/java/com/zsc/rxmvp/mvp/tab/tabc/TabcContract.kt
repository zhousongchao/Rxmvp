package com.zsc.rxmvp.mvp.tab.tabc

import com.zsc.core.base.engine.IPresenter
import com.zsc.core.base.engine.IView

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
interface TabcContract {

    interface View : IView<Presenter> {
        fun viewToastDelay(string: String)
    }

    interface Presenter : IPresenter<View> {
        fun toastDelay(string: String)
    }
}
