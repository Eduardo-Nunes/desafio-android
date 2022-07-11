package com.picpay.desafio.android

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.request.CachePolicy
import com.facebook.stetho.Stetho
import com.picpay.desafio.android.di.Modules.apiModule
import com.picpay.desafio.android.di.Modules.dataModule
import com.picpay.desafio.android.di.Modules.viewModule
import org.koin.core.context.startKoin

class ApplicationContext: Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            setupStetho()
        }
        setupKoinModules()
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupKoinModules() {
        startKoin {
            modules(
                listOf(
                    apiModule(),
                    dataModule(),
                    viewModule()
                )
            )
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .memoryCachePolicy(CachePolicy.DISABLED)
            .networkCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .error(R.drawable.ic_round_account_circle)
            .build()
    }
}