package com.example.minimoneybox.model


import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.model.weather.Forecast
import com.example.ForecastApp.mvp.MainScreenFragment.DetailFragmentContract
import com.example.ForecastApp.searchScreen.ui.MainScreenFragmentContract
import com.example.ForecastApp.weatherResultsScreen.ui.SearchResultsFragmentContract


interface ApplicationModelContract {


    fun start()
    fun stop()
    fun addToDb(forecast: Forecast)
    fun handleEmptyDb()
    fun noStoredData(): Any
    fun handleResultRecent(days: List<Forecast>)
    fun handleResultSearch(days: List<Day>)
    fun getForecastSearch(isOnline: Boolean, city: String, view: SearchResultsFragmentContract.View)
    fun getRecentForecasts(view: MainScreenFragmentContract.View)
    fun getForecastDayDetails(location: String, view: DetailFragmentContract.View)
    fun handleResultDetail(days: List<Day>)
}
