package com.zsc.core.retrofit

import com.zsc.core.retrofit.api.BaseUrl
import com.zsc.core.retrofit.exception.ApiInterceptor
import com.zsc.core.retrofit.exception.ApiInterceptorDefault
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Zsc
 * @date 2017/11/15
 * @desc 网络请求入口
 */
object RxHttp {
    /** 错误信息捕获 */
    var apiInterceptor: ApiInterceptor = ApiInterceptorDefault
    /** 全局OkHttpClient，可以获取用来重新设置  */
    var okHttpClientBuilder: OkHttpClient.Builder
    /** 全局Retrofit，可以获取用来重新设置  */
    var retrofitBuilder: Retrofit.Builder
    /** Retrofit的baseUrl*/
    var globalBaseUrl = "http://www.zsc.com/"
        set(value) {
            field = value
            retrofitBuilder.baseUrl(value)
        }
    /** 是否缓存retrofit实例 */
    var isCacheRetrofit: Boolean = true

    /** 缓存retrofit实例 */
    private val retrofitMap by lazy {
        mutableMapOf<String, Retrofit>()
    }

    init {
        okHttpClientBuilder = createClientBuilder()
        retrofitBuilder = createRetrofitBuilder()
    }

    /**
     * 调用入口
     */
    inline fun <reified T> create(): T {
        return create(T::class.java)
    }

    /**
     * 调用入口
     * @param service
     * @param <T>
     * @return <T>
     * @desc 获取的retrofit
     * 优先级：1、(interface从未通过RxHttp调用)retrofitMap（put或调用-如果缓存）缓存的service.simpleName为key的retrofit
     *        2、(interface上有@BaseUrl标注)retrofitMap缓存或新建@BaseUrl标注的value为baseUrl的retrofit
     *                  并缓存在retrofitMap，key为interface.name
     *        3、(interface没有@BaseUrl标注)，新建以RxHttp.url为key的retrofit并缓存
     */
    fun <T> create(service: Class<T>): T {
        if (isCacheRetrofit) {
            retrofitMap[service.simpleName]?.run {
                return this.create(service)
            }
        }
        val baseUrl = if (service.isAnnotationPresent(BaseUrl::class.java))
            service.getAnnotation(BaseUrl::class.java).value
        else null
        return createRetrofit(baseUrl)
                .apply {
                    if (isCacheRetrofit) {
                        retrofitMap[service.simpleName] = this
                    }
                }.create(service)
    }

    /**
     * 创建RetrofitBuilder实例
     */
    private fun createRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(globalBaseUrl)
                //解析非json数据
                //.addConverterFactory(ScalarsConverterFactory.create())
                //解析json数据
                .addConverterFactory(GsonConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }


    fun addLoggingInterceptor(isDebug: Boolean) {
        if (!isDebug) return
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }


    /**
     * 获取Retrofit，baseUrl为null则用retrofitBuilder默认的url
     * @return
     */
    private fun createRetrofit(baseUrl: String? = null): Retrofit {
        return retrofitBuilder
                .baseUrl(baseUrl ?: globalBaseUrl)
                //okHttp配置
                .client(okHttpClientBuilder.build())
                .build()
    }

    /**
     * 创建OkHttpClient.Builder
     * @return
     */
    private fun createClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    /**
     * 用于配置自定义的retrofit
     * key是baseUrl：则baseUrl传null，retrofit可选
     * key是interface的simpleName，则baseUrl和retrofit必须填写一个
     */
    fun putRetrofit(key: String, baseUrl: String? = null, retrofit: Retrofit? = null) {
        retrofitMap[key] = retrofit ?: createRetrofit(baseUrl ?: key)
    }

    /**
     * 根据key移除Retrofit
     */
    fun removeRetrofit(key: String) = retrofitMap.remove(key)

    /**
     * 移除所有Retrofit
     */
    fun crearRetrofits() = retrofitMap.clear()

}
