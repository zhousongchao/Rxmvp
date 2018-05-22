package com.zsc.rxmvp.mvp.tab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.LogUtils
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.zsc.core.base.BaseMvpActivity
import com.zsc.rxmvp.R
import com.zsc.rxmvp.mvp.tab.taba.TabaFragment
import com.zsc.rxmvp.mvp.tab.taba.TabaPresenter
import com.zsc.rxmvp.mvp.tab.tabb.TabbFragment
import com.zsc.rxmvp.mvp.tab.tabb.TabbPresenter
import com.zsc.rxmvp.mvp.tab.tabc.TabcFragment
import com.zsc.rxmvp.mvp.tab.tabc.TabcPresenter
import dagger.Lazy
import kotlinx.android.synthetic.main.include_toolbar.*
import kotlinx.android.synthetic.main.tab_act.*
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
class TabActivity : BaseMvpActivity<TabPresenter,TabFragment>() {

    override val layout = R.layout.tab_act

    @Inject
    lateinit var tabaFragmentProvider: Lazy<TabaFragment>
    @Inject
    lateinit var tabbFragmentProvider: Lazy<TabbFragment>
    @Inject
    lateinit var tabcFragmentProvider: Lazy<TabcFragment>
    @Inject
    lateinit var tabaPresenter: TabaPresenter
    @Inject
    lateinit var tabbPresenter: TabbPresenter
    @Inject
    lateinit var tabcPresenter: TabcPresenter

    private val mTitles by lazy {
        arrayOf("Tab", "TabA", "TabB", "TabC")
    }
    private lateinit var mFragments: List<Fragment>
    private val mTabEntities by lazy {
        ArrayList<CustomTabEntity>()
    }

    override fun initData(savedInstanceState: Bundle?) {
        LogUtils.d("Tab:initData")
        setSupportActionBar(toolBar)
        mFragments = listOf(mFragmentProvider.get(),
                tabaFragmentProvider.get(),
                tabbFragmentProvider.get(),
                tabcFragmentProvider.get())
        mTabEntities.run {
            add(TabEntity(mTitles[0]))
            add(TabEntity(mTitles[1]))
            add(TabEntity(mTitles[2]))
            add(TabEntity(mTitles[3]))
        }
        viewPager.adapter = MyPagerAdapter(supportFragmentManager)

        tabLayout.setTabData(mTabEntities)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                tabLayout.currentTab = position
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

        })
        tabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabReselect(position: Int) {
            }

            override fun onTabSelect(position: Int) {
                viewPager.currentItem = position
            }
        })

    }

    private inner class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mTitles[position]
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }
    }

}
