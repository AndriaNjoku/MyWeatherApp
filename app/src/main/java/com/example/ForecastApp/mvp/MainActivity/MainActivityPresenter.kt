package com.example.ForecastApp.mvp.MainScreenFragment

import android.content.Context
import com.example.ForecastApp.HomeActivity
import com.example.ForecastApp.NetworkHelper

class MainActivityPresenter(private val context: Context) : MainActivityContract.Presenter {

    val activityView = context as HomeActivity

    override fun attach(context: Context) {
        activityView.showMainPageFragment()
    }

    override fun detatchView() {
        TODO("Not yet implemented")
    }

    val myActivity = context as HomeActivity

    private var mNetworkHelper: NetworkHelper? = null

    override fun initiateNetworkFragment() {
        //when we create the mainActivity we check if the network helper fragment has been created (invisible)
        //this helps with network checks. If it is null then we create a new instance in the activity.
        //Check for network connectivity
        mNetworkHelper = myActivity.supportFragmentManager.findFragmentByTag(NetworkHelper.TAG) as NetworkHelper?
        if (mNetworkHelper == null) {
            mNetworkHelper = NetworkHelper.newInstance()
            myActivity.supportFragmentManager.beginTransaction().add(mNetworkHelper!!, NetworkHelper.TAG).commit()
        }

    }

    override fun attachSearchResultsFrag(location: Long) {
        TODO("Not yet implemented")
    }
}
