package com.zsc.core.retrofit.exception

/**
 * 与服务器约定好的异常
 */
object ApiError {
    /**
     * 未知错误
     */
    const val UNKNOWN = 1000
    const val UNKNOWN_EXCEPTION = "未知错误"
    /**
     * 解析错误
     */
    const val PARSE_ERROR = 1001
    const val PARSE_EXCEPTION = "解析错误"
    /**
     * 网络错误
     */
    const val NETWORK_ERROR = 1002
    const val NETWORK_EXCEPTION = "连接失败"
    /**
     * 协议出错
     */
    const val HTTP_ERROR = 1003
    const val HTTP_EXCEPTION = "网络错误"

    const val API_EMPTY_MSG_ERROR = 1004
    const val API_EMPTY_MSG_EXCEPTION = "数据返回错误"

}
