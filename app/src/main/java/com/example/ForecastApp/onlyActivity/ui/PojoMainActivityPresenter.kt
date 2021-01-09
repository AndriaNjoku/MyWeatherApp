package com.example.ForecastApp.onlyActivity.ui

import android.app.Activity
import android.content.Context
import com.example.ForecastApp.onlyActivity.HomeActivity
import com.example.ForecastApp.NetworkHelper

class PojoMainActivityPresenter(
        val view: MainActivityPresenter.View,
        val context: Context) : MainActivityPresenter{
    
   init{
       view.showWeatherSearchFragment()
    }

    private var mNetworkHelper: NetworkHelper? = null

    override fun initiateNetworkFragment() {
        
        val homeActivity = context as HomeActivity
        mNetworkHelper = homeActivity.supportFragmentManager.findFragmentByTag(NetworkHelper.TAG) as NetworkHelper?
        if (mNetworkHelper == null) {
            mNetworkHelper = NetworkHelper.newInstance()
            homeActivity.supportFragmentManager.beginTransaction().add(mNetworkHelper!!, NetworkHelper.TAG).commit()
        }
    }
}
