package com.zsc.rxmvp.mvp.main

import android.os.Bundle
import com.blankj.utilcode.util.FragmentUtils
import com.zsc.core.base.BaseMvpActivity
import com.zsc.rxmvp.R
import dagger.Lazy
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-24
 * @desc
 */
class MainActivity : BaseMvpActivity<MainPresenter>() {
    @Inject
    lateinit var mainFragmentProvider: Lazy<MainFragment>

    override fun initData(savedInstanceState: Bundle?) {
        var mainFragment: MainFragment? = supportFragmentManager
                .findFragmentById(android.R.id.content) as MainFragment?
        if (mainFragment == null) {
            mainFragment = mainFragmentProvider.get()
        }
        FragmentUtils.add(supportFragmentManager,
                mainFragment!!, android.R.id.content)
    }
}
