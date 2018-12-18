package com.zsc.core.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.zsc.core.base.engine.IActivity
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.Utils
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper

/**
 * @author Zsc
 * @date 2017/11/11
 * @desc
 */
abstract class BaseActivity : AppCompatActivity(), IActivity, SwipeBackActivityBase {
    /**
     * 来自SwipeBackActivity
     */
    private var mHelper: SwipeBackActivityHelper? = null

    /**
     * 是否使用侧滑返回的SwipeBackLayout,避免默认使用而增加布局层次
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置View之前的设置
        if (isSwipeBack) {
            mHelper = SwipeBackActivityHelper(this)
            mHelper?.onActivityCreate()
        }
        //如果initView返回0,框架则不会调用setContentView,自己在initData设置View
        if (layout != 0) {
            setContentView(layout)
        }
        //初始化数据
        initData(savedInstanceState)
    }

    /**
     * 来自SwipeBackActivity
     */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mHelper?.onPostCreate()
    }

    /**
     * 来自SwipeBackActivity
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : View?> findViewById(id: Int): T? {
        return super.findViewById<T>(id) ?: if (isSwipeBack)
            mHelper?.findViewById(id) as T?
        else null
    }

    /**
     * 来自SwipeBackActivity
     */
    override fun getSwipeBackLayout(): SwipeBackLayout? {
        return if (isSwipeBack) {
            mHelper?.swipeBackLayout
        } else null
    }

    /**
     * 来自SwipeBackActivity
     */
    override fun setSwipeBackEnable(enable: Boolean) {
        swipeBackLayout?.setEnableGesture(enable)
    }

    /**
     * 来自SwipeBackActivity
     */
    override fun scrollToFinishActivity() {
        if (isSwipeBack) {
            Utils.convertActivityToTranslucent(this)
            swipeBackLayout?.scrollToFinishActivity()
        }
    }
}
