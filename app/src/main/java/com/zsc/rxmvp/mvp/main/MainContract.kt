package com.zsc.rxmvp.mvp.main

import com.zsc.core.base.engine.IPresenter
import com.zsc.core.base.engine.IView

/**
 * @author Zsc
 * @date 2018-04-24
 * @desc
 */
interface MainContract {

    interface View : IView<Presenter> {

    }

    interface Presenter : IPresenter<View> {

    }
}
