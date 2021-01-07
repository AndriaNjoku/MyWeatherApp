package com.example.ForecastApp.weatherSearchScreen.ui

import com.example.ForecastApp.model.weather.Forecast
import com.example.ForecastApp.BasePresenter


interface WeatherSearchPresenter : BasePresenter {

    fun setSelectedLocation(toString: String)
    fun dispose()


    interface View : BasePresenter.View{

        fun savedSearchesInit()
        fun autoCompleteSearchInit()
        fun showRecentSavedSearches(cities: List<Forecast>)
        fun showProgress(b: Boolean)
        fun showError(error: Throwable)
    }
}




