package com.zsc.core.base

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zsc.core.base.engine.IActivity


/**
 * @author Zsc
 * @date 2017/11/11
 * @desc
 */

abstract class BaseActivity : AppCompatActivity(), IActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置View之前的设置
        setWindow()
        val layoutResID = layout
        //如果initView返回0,框架则不会调用setContentView,自己在initData设置View
        if (layout != 0) {
            setContentView(layoutResID)
        }
        //如果不使用Fragment，直接用Activity作为View,则Presenter不绑定Activity生命周期，而绑定Fragment的生命周期
        initLifecycle()
        //初始化数据
        initData(savedInstanceState)
    }


    /** 竖屏限定,如果不限定竖屏，则重写该方法，方法内为空  */
    override fun setWindow() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    /** 无需传参快速启动Activity  */
    fun startActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}
