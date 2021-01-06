package com.example.ForecastApp.searchScreen.ui

import android.content.Context
import com.example.ForecastApp.model.weather.Forecast
import com.example.ForecastApp.mvp.BaseContract


interface WeatherSearchPresenter{

    fun attach(context:Context,fragView: View)
    fun getWeatherDetails()
    fun setSelectedLocation(toString: String)
    fun getRecentForecasts()


    interface View :BaseContract.View{

        fun savedSearchesInit()
        fun autoCompleteSearchInit()
        fun showRecentSavedSearches(cities: List<Forecast>)
        fun showProgress(b: Boolean)
        fun showError(error: Throwable?)
        fun showNoRecentSearches()


    }
}




