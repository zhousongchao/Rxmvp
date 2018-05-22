package com.zsc.rxmvp.mvp.tab.taba

import com.zsc.core.base.engine.IPresenter
import com.zsc.core.base.engine.IView

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
interface TabaContract {

    interface View : IView<Presenter> {
        fun changeViewText(string: String)
    }
    interface Presenter : IPresenter<View> {
        fun changeText(string: String)
    }
}
