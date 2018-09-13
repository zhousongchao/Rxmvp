package com.zsc.core.retrofit.exception

/**
 * @author zsc
 * @date 2017/11/15
 * 网络请求错误
 */
class ApiException(/** 错误码  */
                   var code: Int,
                   /** 错误信息  */
                   override var message: String = ApiError.UNKNOWN_EXCEPTION
) : Exception(){

    companion object{
        val EMPTY_ERROR=ApiException(ApiError.API_EMPTY_MSG_ERROR,
                ApiError.API_EMPTY_MSG_EXCEPTION)
    }

}