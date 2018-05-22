package com.zsc.rxmvp.mvp.tab

import android.os.Bundle

import com.zsc.core.base.BaseMvpFragment
import com.zsc.rxmvp.R
import com.zsc.core.dagger.ActivityScoped
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@ActivityScoped
class TabFragment @Inject
constructor() : BaseMvpFragment<TabPresenter>(),TabContract.View {

    override val layout = R.layout.tab_frag

    override fun initData(savedInstanceState: Bundle?) {

    }

}
