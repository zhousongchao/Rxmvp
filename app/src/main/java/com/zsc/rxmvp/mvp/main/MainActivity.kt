package com.zsc.rxmvp.mvp.main

import com.zsc.core.base.BaseMvpActivity

/**
 * @author Zsc
 * @date 2018-04-24
 * @desc
 */
class MainActivity : BaseMvpActivity<MainPresenter, MainFragment>() {
    override val isSwipeBack: Boolean
        get() = false

}
