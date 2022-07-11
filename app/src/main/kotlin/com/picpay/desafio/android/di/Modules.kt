package com.picpay.desafio.android.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.api.ApiClient
import com.picpay.desafio.android.api.ApiClient.Companion.createOkHttpClient
import com.picpay.desafio.android.api.PicPayService
import com.picpay.desafio.android.data.PicpayRepository
import com.picpay.desafio.android.view.ContactsListViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

object Modules {
    private val backgroundDispatcher by lazy { Dispatchers.IO }
    private val apiClient by lazy {
        createApiClient(BuildConfig.DEBUG)
    }

    fun apiModule() = module {
        single() {
            apiClient.createService(PicPayService::class.java)
        }
    }

    fun dataModule() = module {
        factory {
            PicpayRepository(get())
        }
    }

    fun viewModule() = module {
        factory {
            ContactsListViewModel(backgroundDispatcher, get())
        }
    }

    private fun createApiClient(isDebug: Boolean): ApiClient =
        ApiClient(
            baseUrl = PicPayService.BASE_API_URL,
            okHttpClient = createOkHttpClient(
                interceptors = listOf(
                    if (isDebug) {
                        val httpLoggingInterceptor = HttpLoggingInterceptor()
                        httpLoggingInterceptor.apply {
                            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                        }
                    } else null
                ),
                networkInterceptors = listOf(
                    if (isDebug) StethoInterceptor() else null
                )
            )
        )

}