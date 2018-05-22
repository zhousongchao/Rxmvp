package com.zsc.rxmvp.mvp.common

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.zsc.core.base.BaseMvpFragment
import com.zsc.core.dagger.ActivityScoped
import com.zsc.rxmvp.R
import com.zsc.rxmvp.R.id.toolBar
import kotlinx.android.synthetic.main.include_toolbar.*
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@ActivityScoped
class CommonFragment @Inject
constructor() : BaseMvpFragment<CommonPresenter>() {
    //Boolean不能使用lateinit，需要这么写
    var isAdd: Boolean = true
        @Inject set

    //测试注入
    @Inject
    lateinit var id: String

    override val layout = R.layout.common_frag

    override fun initData(savedInstanceState: Bundle?) {
        appCompatActivity?.setSupportActionBar(toolBar)
        LogUtils.d(id)
        LogUtils.d(isAdd)
    }

}
