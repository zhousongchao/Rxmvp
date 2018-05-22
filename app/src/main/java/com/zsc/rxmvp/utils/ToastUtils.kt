package com.zsc.rxmvp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

fun Any?.toast(text: String){
    ToastUtils.showShort(text)
}

@SuppressLint("StaticFieldLeak")
object ToastUtils {
    lateinit var context: Context
    var toast: Toast? = null

    fun init(context: Context) {
        this.context = context
    }

    fun showShort(text: String) {
        toast?.cancel()
        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast?.show()
    }

}