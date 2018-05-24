package com.zsc.core.retrofit.exception

/**
 * @author zsc
 * @date 2017/11/15
 * 网络请求错误
 */
class ApiException(throwable: Throwable,
                   /** 错误码  */
                   var code: Int,
                   /** 错误信息  */
                   override var message: String = ApiError.UNKNOWN_EXCEPTION
) : Exception(throwable)