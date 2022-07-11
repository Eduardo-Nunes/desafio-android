package com.picpay.desafio.android.utils.networkstate

import android.content.Context
import androidx.lifecycle.LifecycleOwner

interface NetworkStateObserver : LifecycleOwner {
    fun networkStateChanged(networkState: NetworkState)
    fun getContext(): Context
}