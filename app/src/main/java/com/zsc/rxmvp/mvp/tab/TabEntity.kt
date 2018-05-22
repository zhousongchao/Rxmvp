package com.zsc.rxmvp.mvp.tab

import com.flyco.tablayout.listener.CustomTabEntity

class TabEntity constructor(var tabText: String) : CustomTabEntity {
    override fun getTabUnselectedIcon(): Int = 0

    override fun getTabSelectedIcon(): Int = 0

    override fun getTabTitle(): String = tabText

}
