package com.zsc.core.retrofit.exception

import com.google.gson.JsonParseException
import com.zsc.core.retrofit.api.ApiObserver
import com.zsc.core.retrofit.api.ResultApi
import com.zsc.core.retrofit.exception.ApiError.API_EMPTY_MSG_EXCEPTION
import com.zsc.core.retrofit.exception.ApiError.HTTP_EXCEPTION
import com.zsc.core.retrofit.exception.ApiError.NETWORK_EXCEPTION
import com.zsc.core.retrofit.exception.ApiError.PARSE_EXCEPTION
import com.zsc.core.retrofit.exception.ApiError.UNKNOWN_EXCEPTION
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.text.ParseException

/**
 * 异常拦截
 * @author zsc
 * @date 2017/11/15
 */
interface ApiInterceptor {

    /**
     * 异常拦截处理
     * @param e
     * @return
     */
    fun handleErrorMsg(e: Throwable): String {
        return when (e) {
            is HttpException -> HTTP_EXCEPTION
            is JsonParseException,
            is JSONException,
            is ParseException -> PARSE_EXCEPTION
            is ConnectException -> NETWORK_EXCEPTION
            is ApiException -> API_EMPTY_MSG_EXCEPTION
            else -> UNKNOWN_EXCEPTION
        }
    }

    /**
     * 当错误处理为null时的操作,Toast或其他提示等
     */
    fun doOnError(msg: String) {}

    /**
     * 对返回数据的集中处理
     */
    fun <T> handleNext(apiObserver: ApiObserver<T>, resultApi: ResultApi<T>) {
        if (resultApi.code != 200 ||
                resultApi.data == null) {
            apiObserver.onFail(resultApi.msg
                    ?: handleErrorMsg(ApiException.EMPTY_ERROR))
        } else {
            apiObserver.onSuccess(resultApi.data!!)
        }
    }

    /**
     * 对返回错误的集中处理
     */
    fun <T> handleError(apiObserver: ApiObserver<T>, throwable: Throwable) {
        apiObserver.onFail(handleErrorMsg(throwable))
    }


}
