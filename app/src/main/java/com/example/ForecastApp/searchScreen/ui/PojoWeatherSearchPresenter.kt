package com.example.ForecastApp.searchScreen.ui

import android.content.Context
import android.util.Log
import com.example.ForecastApp.HomeActivity
import com.example.ForecastApp.searchScreen.OnLocationSelectedListener
import com.example.minimoneybox.model.ApplicationModelContract



class PojoWeatherSearchPresenter(
        val view: WeatherSearchPresenter.View,
        private val myModelInteractor: ApplicationModelContract
) : WeatherSearchPresenter {

    lateinit var context : Context
    lateinit var fragView: WeatherSearchPresenter.View

    override fun attach(context: Context, fragView: MainScreenFragmentContract.View) {
        this.activityContext = context
        this.fragView=fragView

            myModelInteractor.getRecentForecasts(fragView)

    }
    override fun getWeatherDetails() {

    }

    override fun detatchView() {

    }

    private var mListener: OnLocationSelectedListener? = null
    lateinit var activityContext: Context

    override fun setSelectedLocation(location: String) {

        Log.e("presenter_main","insidesetlocation")

        checkIfListenerAttached()

        mListener?.onLocationSelected(location)
    }

    private fun checkIfListenerAttached() {
        if (mListener == null) {
            try {
                mListener = activityContext as HomeActivity
            } catch (e: ClassCastException) {
                throw ClassCastException("${activityContext}must implement OnLocationSelectedListener")
            }
        }
    }

    override fun getRecentForecasts(){

        myModelInteractor.getRecentForecasts(fragView)
    }

}
