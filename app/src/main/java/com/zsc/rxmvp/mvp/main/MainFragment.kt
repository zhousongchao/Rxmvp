package com.zsc.rxmvp.mvp.main

import android.content.Intent
import android.os.Bundle
import com.zsc.core.base.BaseMvpFragment
import com.zsc.core.dagger.ActivityScoped
import com.zsc.rxmvp.R
import com.zsc.rxmvp.mvp.common.CommonActivity
import com.zsc.rxmvp.mvp.tab.TabActivity
import com.zsc.rxmvp.utils.logd
import kotlinx.android.synthetic.main.main_frag.*
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-24
 * @desc
 */
@ActivityScoped
class MainFragment @Inject
constructor() : BaseMvpFragment<MainPresenter>(), MainContract.View {

    override val layout = R.layout.main_frag

    override fun initData(savedInstanceState: Bundle?) {
        appCompatActivity?.setSupportActionBar(toolBarMain)
        tvToCommon.text = "ToCommon"
        logd("ToCommon")
        tvToCommon.setOnClickListener {
            val intent = Intent(context, CommonActivity::class.java)
            intent.putExtra("1235", "9876")
            startActivity(intent)
        }
        tvToTab.setOnClickListener {
            startActivity(TabActivity::class.java)
        }
    }


}
