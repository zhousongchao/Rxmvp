package com.zsc.rxmvp.mvp.common

import android.os.Bundle
import com.blankj.utilcode.util.FragmentUtils
import com.zsc.core.base.BaseMvpActivity
import com.zsc.rxmvp.R
import dagger.Lazy
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
class CommonActivity : BaseMvpActivity<CommonPresenter>() {

    @Inject
    lateinit var commonFragmentProvider: Lazy<CommonFragment>

    override fun initData(savedInstanceState: Bundle?) {
        var commonFragment: CommonFragment? = supportFragmentManager
                .findFragmentById(android.R.id.content) as CommonFragment?
        if (commonFragment == null) {
            commonFragment = commonFragmentProvider.get()
        }
        FragmentUtils.add(supportFragmentManager,
                commonFragment!!, android.R.id.content)
    }
}
