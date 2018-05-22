package com.zsc.rxmvp.mvp.tab.taba

import android.os.Bundle
import com.zsc.core.base.BaseMvpFragment
import com.zsc.core.dagger.ActivityScoped
import com.zsc.rxmvp.R
import com.zsc.rxmvp.utils.logd
import kotlinx.android.synthetic.main.taba_frag.*
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@ActivityScoped
class TabaFragment @Inject
constructor() : BaseMvpFragment<TabaPresenter>(),TabaContract.View {
    //点击次数
    private var clickTimes=0

    override val layout = R.layout.taba_frag

    override fun initData(savedInstanceState: Bundle?) {
        logd("A:initData")
        tvTabA.text = "TabAText"
        tvTabA.setOnClickListener {
            mPresenter.changeText("changeText${clickTimes++}")
        }
    }

    override fun changeViewText(string: String) {
        tvTabA.text = string
    }


}
