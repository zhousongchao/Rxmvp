package com.zsc.rxmvp.utils

import android.util.Log

const val TAG="Rxmvp"

fun logd(text: Any?){
    Log.d(TAG,text.toString())
}
fun loge(text:Any?){
    Log.e(TAG,text.toString())
}