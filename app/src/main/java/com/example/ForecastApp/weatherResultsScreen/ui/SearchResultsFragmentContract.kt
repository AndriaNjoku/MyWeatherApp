package com.example.ForecastApp.weatherResultsScreen.ui

import android.content.Context
import com.example.ForecastApp.model.weather.Day
import com.example.ForecastApp.mvp.BaseContract


interface SearchResultsFragmentContract{
    interface View :BaseContract.View{
        fun showProgress(b: Boolean)
        fun showError(error: Throwable?)
        fun setActivityTitle(name: String?)
        fun showTryAgain(shouldShow: Boolean)
        fun showSearchResults(days: List<Day>)
    }

    interface Presenter: BaseContract.Presenter<View> {

        fun attach(context: Context, fragView: View)
        fun showSearchResults(location: String)
    }
}




