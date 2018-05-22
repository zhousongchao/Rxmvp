package com.zsc.rxmvp.mvp.tab.taba

import com.zsc.core.base.BasePresenter
import com.zsc.core.dagger.ActivityScoped
import com.zsc.rxmvp.utils.logd
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@ActivityScoped
class TabaPresenter @Inject
constructor() : BasePresenter<TabaContract.View>(), TabaContract.Presenter {
    override fun changeText(string: String) {
        logd(string)
        mView?.changeViewText(string)
    }
}
