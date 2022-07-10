package com.picpay.desafio.android.utils.networkstate

import android.content.Context
import androidx.lifecycle.LifecycleOwner

interface NetworkStateListener : LifecycleOwner {
    fun networkStateChanged(networkState: NetworkState)
    fun getContext(): Context
}