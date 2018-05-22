package com.zsc.rxmvp.mvp.tab

import com.zsc.core.base.engine.IPresenter
import com.zsc.core.base.engine.IView

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
interface TabContract {

    interface View : IView<Presenter> {

    }
    interface Presenter : IPresenter<View> {

    }
}
