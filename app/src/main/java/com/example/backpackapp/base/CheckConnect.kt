package com.example.backpackapp.base

import android.content.Context
import android.net.ConnectivityManager


@Suppress("DEPRECATION")
class CheckConnect {
    companion object {
        fun checkConnection(context: Context): Boolean {
            var haveConnectInternet = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected) {
                haveConnectInternet = true
            }
            return haveConnectInternet
        }
    }
}