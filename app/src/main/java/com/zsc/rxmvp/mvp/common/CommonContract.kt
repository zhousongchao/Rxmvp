package com.zsc.rxmvp.mvp.common

import com.zsc.core.base.engine.IPresenter
import com.zsc.core.base.engine.IView

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
interface CommonContract {

    interface View : IView<Presenter> {

    }
    interface Presenter : IPresenter<View> {

    }
}
