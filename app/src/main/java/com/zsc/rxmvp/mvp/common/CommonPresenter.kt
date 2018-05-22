package com.zsc.rxmvp.mvp.common

import com.zsc.core.base.BasePresenter
import com.zsc.core.dagger.ActivityScoped
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@ActivityScoped
class CommonPresenter @Inject
constructor() : BasePresenter<CommonContract.View>(), CommonContract.Presenter
