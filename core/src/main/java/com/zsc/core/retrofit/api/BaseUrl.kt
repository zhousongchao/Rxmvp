package com.zsc.core.retrofit.api

/**
 * @author Zsc
 * @date   2018/5/6
 * @desc
 */
@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrl(val value: String)

