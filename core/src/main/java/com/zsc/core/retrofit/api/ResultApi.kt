package com.zsc.core.retrofit.api

/**
 * @author Zsc
 * @date 2018/2/14
 * @desc  标准接口返回数据
 */
class ResultApi<T> {
    /**
     * 返回code值，默认200为成功
     */
    var code: Int = 0
    /**
     * 错误信息，返回成功时为null
     */
    var msg: String? = null
    /**
     * 返回具体信息内容
     */
    var data: T? = null

}
