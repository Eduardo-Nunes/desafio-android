package com.picpay.desafio.android.base

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.components.infobarview.InfoBarProvider
import com.picpay.desafio.android.components.infobarview.InfoBarView
import com.picpay.desafio.android.extensions.hasConnectionAlert
import com.picpay.desafio.android.extensions.noConnectionAlert
import com.picpay.desafio.android.utils.networkstate.NetworkState
import com.picpay.desafio.android.utils.networkstate.NetworkStateObserver
import com.picpay.desafio.android.utils.networkstate.NetworkStateListener

abstract class BaseActivity : AppCompatActivity(), InfoBarProvider, NetworkStateObserver {

    protected open var infoBar: InfoBarView? = null
    private var networkStateManager: NetworkStateListener? = null

    protected fun setupInfoBar(viewGroup: ViewGroup, bar: InfoBarView? = null) {
        infoBar = bar ?: InfoBarView(viewGroup)
        networkStateManager = NetworkStateListener(this)
    }

    override fun getInfoBarInstance(): InfoBarView? {
        return infoBar
    }

    override fun networkStateChanged(networkState: NetworkState) {
        when (networkState) {
            NetworkState.OFFLINE -> {
                infoBar?.noConnectionAlert()
            }
            NetworkState.ONLINE -> {
                infoBar?.hasConnectionAlert()
            }
        }
    }

    override fun onDestroy() {
        infoBar = null
        networkStateManager = null
        super.onDestroy()
    }

    override fun getContext(): Context {
        return this
    }
}