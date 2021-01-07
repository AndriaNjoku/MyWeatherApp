package com.example.ForecastApp.weatherDetailScreen.ui

import android.content.Context

class DetailFragmentPresenter(private val modelInteractor: ApplicationModelContract) : DetailFragmentContract.Presenter {

    lateinit var activityContext: Context
    lateinit var fragView: DetailFragmentContract.View

    override fun attach(context: Context,fragView: DetailFragmentContract.View) {
        this.activityContext = context
        this.fragView = fragVie
    }

    override fun getDayDetails(location: String) {
        modelInteractor.getForecastDayDetails(location,fragView)
    }

    override fun detatchView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}
