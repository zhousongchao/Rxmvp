package com.zsc.rxmvp.mvp.tab

import com.zsc.core.base.BasePresenter
import com.zsc.core.dagger.ActivityScoped
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@ActivityScoped
class TabPresenter @Inject
constructor() : BasePresenter<TabContract.View>(), TabContract.Presenter
