package com.example.ForecastApp.weatherResultsScreen.ui

import android.content.Context
import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.BasePresenter


interface SearchResultsPresenter: BasePresenter{

    interface View : BasePresenter.View{
        fun showProgress(b: Boolean)
        fun showError(error: Throwable?)
        fun setActivityTitle(name: String?)
        fun showTryAgain(shouldShow: Boolean)
        fun showSearchResults(days: List<Day>)
    }
        fun showSearchResults(location: String)
        fun dispose()
    }





