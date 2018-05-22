package com.zsc.rxmvp.mvp.tab.tabc

import com.blankj.utilcode.util.LogUtils
import com.zsc.core.base.BasePresenter
import com.zsc.core.dagger.ActivityScoped
import com.zsc.core.retrofit.io2Main
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Zsc
 * @date 2018-04-27
 * @desc
 */
@ActivityScoped
class TabcPresenter @Inject
constructor() : BasePresenter<TabcContract.View>(), TabcContract.Presenter {
    //如果退出有其他数据需要处理则用第一种
    ///如果退出数据不需要处理则用第二种
    override fun toastDelay(string: String) {
        LogUtils.d("toastDelay")
        //延时三秒显示Toast，当view被销毁(=null)则不展示，LogUtils.d("toastDelay:$string")执行
        /*Observable.just(string)
                .delay(3, TimeUnit.SECONDS)
                .io2Main()
                .subscribe {
                    LogUtils.d("toastDelay:$string")
                    mView?.viewToastDelay(string)
                }*/
        //延时三秒显示Toast，当view被销毁则事件传递停止，LogUtils.d("toastDelay:$string")不执行
        Observable.just(string)
                .delay(3, TimeUnit.SECONDS)
                .io2Main()
                .subscribe(object : Observer<String> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        addDisposable(d)
                    }

                    override fun onNext(t: String) {
                        LogUtils.d("toastDelay:$string")
                        mView?.viewToastDelay(string)
                    }

                    override fun onError(e: Throwable) {
                    }

                })

    }
}
