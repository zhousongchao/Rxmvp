package com.zsc.core.retrofit.exception

import com.google.gson.JsonParseException
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
object ExceptionEngine {

    /**
     * 异常拦截处理
     * @param e
     * @return
     */
    fun handleException(e: Throwable): ApiException {
        return ApiError.run {
            when (e) {
                is HttpException -> ApiException(e, HTTP_ERROR,
                        HTTP_EXCEPTION)
                is JsonParseException,
                is JSONException,
                is ParseException -> ApiException(e, PARSE_ERROR, PARSE_EXCEPTION)
                is ConnectException -> ApiException(e, ApiError.NETWORK_ERROR, NETWORK_EXCEPTION)
                else -> ApiException(e, ApiError.UNKNOWN, UNKNOWN_EXCEPTION)
            }
        }

    }

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
            else -> UNKNOWN_EXCEPTION
        }
    }
}
