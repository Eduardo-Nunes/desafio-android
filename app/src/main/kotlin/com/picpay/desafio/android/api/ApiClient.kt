package com.picpay.desafio.android.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
private const val CONNECTION_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 150L

class ApiClient(
    private var baseUrl: String,
    private var okHttpClient: OkHttpClient
) {
    companion object {
        fun createOkHttpClient(
            interceptors: List<Interceptor?> = emptyList(),
            networkInterceptors: List<Interceptor?> = emptyList()
        ): OkHttpClient = with(OkHttpClient.Builder()) {
            interceptors.filterNotNull().forEach { addInterceptor(it) }
            networkInterceptors.filterNotNull().forEach { addNetworkInterceptor(it) }
            connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            build()
        }
    }

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofitBuilder.build().create(serviceClass)
    }
}