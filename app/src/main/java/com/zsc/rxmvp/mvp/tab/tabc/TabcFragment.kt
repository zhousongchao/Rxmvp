package com.zsc.rxmvp.mvp.tab.tabc

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils

import com.zsc.core.base.BaseMvpFragment
import com.zsc.rxmvp.R
import com.zsc.core.dagger.ActivityScoped
import com.zsc.rxmvp.R.id.tvTabC
import kotlinx.android.synthetic.main.tabc_frag.*
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@ActivityScoped
class TabcFragment @Inject
constructor() : BaseMvpFragment<TabcPresenter>(), TabcContract.View {
    //点击次数
    private var clickTimes = 0

    override val layout = R.layout.tabc_frag

    override fun initData(savedInstanceState: Bundle?) {
        tvTabC.setOnClickListener {
            mPresenter.toastDelay("toastDelay${clickTimes++}")
        }
    }

    override fun viewToastDelay(string: String) {
        ToastUtils.showShort(string)
        //这里要加？判断非空，在滑动到第tab和taba是tabc视图会被销毁，
        // presenter的mView不为null，在tab页使用mvp时要注意！！！！tab只会保留当前和相邻的视图
        if (tvTabC == null) {
            clickTimes--
        }
        tvTabC?.run {
            text = string
            //do something else,if not, use {tvTabC?.text= string}
        }
    }


}
