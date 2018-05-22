package com.zsc.rxmvp.mvp.main

import com.zsc.core.base.BasePresenter
import com.zsc.core.dagger.ActivityScoped
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-24
 * @desc
 */
@ActivityScoped
class MainPresenter @Inject
constructor() : BasePresenter<MainContract.View>(), MainContract.Presenter
