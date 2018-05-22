package com.zsc.rxmvp.mvp.tab.tabb

import com.zsc.core.base.BasePresenter
import com.zsc.core.dagger.ActivityScoped
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@ActivityScoped
class TabbPresenter @Inject
constructor() : BasePresenter<TabbContract.View>(), TabbContract.Presenter {
    override fun toast(string: String) {
        mView?.viewToast(string)
    }
}
