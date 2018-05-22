package com.zsc.rxmvp.mvp.tab.tabb

import com.zsc.core.base.engine.IPresenter
import com.zsc.core.base.engine.IView

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
interface TabbContract {

    interface View : IView<Presenter> {
        fun viewToast(string: String)
    }
    interface Presenter : IPresenter<View> {
        fun toast(string: String)
    }
}
