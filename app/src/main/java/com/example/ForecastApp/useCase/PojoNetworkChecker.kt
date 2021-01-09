package com.example.ForecastApp.useCase

import android.content.Context
import android.net.ConnectivityManager
import com.example.ForecastApp.di.composer.ActivityScope
import javax.inject.Inject

@ActivityScope
class PojoNetworkChecker @Inject constructor(val context: Context) : NetworkChecker {

    override fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}