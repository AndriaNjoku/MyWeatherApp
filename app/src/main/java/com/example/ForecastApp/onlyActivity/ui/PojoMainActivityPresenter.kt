package com.example.ForecastApp.onlyActivity.ui

import android.content.Context
import com.example.ForecastApp.onlyActivity.HomeActivity
import com.example.ForecastApp.NetworkHelper

class PojoMainActivityPresenter(
        val view: MainActivityPresenter.View,
        val context: Context) : MainActivityPresenter{

    private val activityView = context as HomeActivity

   init{
       view.showWeatherSearchFragment()
    }
    val myActivity = context as HomeActivity

    private var mNetworkHelper: NetworkHelper? = null

    override fun initiateNetworkFragment() {
        mNetworkHelper = myActivity.supportFragmentManager.findFragmentByTag(NetworkHelper.TAG) as NetworkHelper?
        if (mNetworkHelper == null) {
            mNetworkHelper = NetworkHelper.newInstance()
            myActivity.supportFragmentManager.beginTransaction().add(mNetworkHelper!!, NetworkHelper.TAG).commit()
        }
    }
}
