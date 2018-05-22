package com.zsc.rxmvp.mvp.tab.tabb

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils

import com.zsc.core.base.BaseMvpFragment
import com.zsc.rxmvp.R
import com.zsc.core.dagger.ActivityScoped
import kotlinx.android.synthetic.main.tabb_frag.*
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@ActivityScoped
class TabbFragment @Inject
constructor() : BaseMvpFragment<TabbPresenter>(), TabbContract.View {
    //点击次数
    private var clickTimes = 0

    override val layout = R.layout.tabb_frag

    override fun initData(savedInstanceState: Bundle?) {
        tvTabB.setOnClickListener {
            mPresenter.toast("ToastUtils${clickTimes++}")
        }
    }

    override fun viewToast(string: String) {
        ToastUtils.showShort(string)
    }


}
