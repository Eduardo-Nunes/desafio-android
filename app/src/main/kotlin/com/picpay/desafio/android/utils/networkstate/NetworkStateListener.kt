package com.picpay.desafio.android.utils.networkstate

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

class NetworkStateListener(
    private val networkObserver: NetworkStateObserver
) {

    private val networkRequest: NetworkRequest by lazy {
        NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
    }

    private var lastNetworkState: NetworkState? = null

    private fun registerNetWorkCallback(context: Context) {
        getConnectivityManager(context).registerNetworkCallback(networkRequest, networkCallback)
        getConnectivityManager(context).requestNetwork(networkRequest, networkCallback)
    }

    private fun unregisterNetWorkCallback(context: Context) {
        getConnectivityManager(context).unregisterNetworkCallback(networkCallback)
    }

    private fun getConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // network is available for use
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            if (lastNetworkState != null) {
                networkObserver.networkStateChanged(NetworkState.ONLINE)
            }
            lastNetworkState = NetworkState.ONLINE
        }

        // lost network connection
        override fun onLost(network: Network) {
            super.onLost(network)
            networkObserver.networkStateChanged(NetworkState.OFFLINE)
            lastNetworkState = NetworkState.OFFLINE
        }

        // Network capabilities have changed for the network
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
        }

    }

    private fun start() {
        try {
            registerNetWorkCallback(networkObserver.getContext())
        } catch (e: Exception) {
        }
    }

    private fun stop() {
        try {
            lastNetworkState = null
            unregisterNetWorkCallback(networkObserver.getContext())
        } catch (e: Exception) {
        }
    }

    //  Lifecycle nextEvents
    private val lifecycleObserver = LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> start()
            Lifecycle.Event.ON_PAUSE -> stop()
            Lifecycle.Event.ON_DESTROY -> removeListeners()
            else -> Unit
        }
    }

    private fun addListeners() {
        networkObserver.lifecycle.addObserver(lifecycleObserver)
    }

    private fun removeListeners() {
        networkObserver.lifecycle.removeObserver(lifecycleObserver)
    }

    init {
        addListeners()
    }
}