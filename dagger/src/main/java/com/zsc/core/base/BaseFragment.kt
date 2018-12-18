package com.zsc.core.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zsc.core.base.engine.IFragment

/**
 * DaggerFragment的合并
 * @author Zsc
 * @date 2017/10/4
 */
abstract class BaseFragment : Fragment(), IFragment {

    protected var rootView: View? = null

    protected var isInit = false

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(layout, container, false)
        } else {
            isInit = true
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isInit) {
            init(savedInstanceState)
        }

    }

    /**
     * 初始化数据
     * @param savedInstanceState
     */
    protected fun init(savedInstanceState: Bundle?) {
        initLifecycle()
        //设置Fragment可以添加Menu
        setHasOptionsMenu(true)
        //初始化数据
        initData(savedInstanceState)

    }


    override fun getView(): View? {
        return rootView
    }

    /**
     * 用于获取activity，避免空验证！！
     */
    val appCompatActivity: AppCompatActivity
        get() = activity as AppCompatActivity


}